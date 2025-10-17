# System Design: Видеохостинг

Требуется спроектировать приложение, которое будет позволять загружать видео создателям контента и просматривать это видео всем остальным. Аналоги всем известны.

### Функциональные требования

Нам требуется спроектировать сервис, которые позволит реализовать следующие фичи

    Система должна позволять создателям каналов быстро заливать видео
    Видео должно по готовности попадать в ленты подписчиков каналов
    Зрители должны иметь возможность поменять качество видео при просмотре

### Нефункциональные требования

Решение должно обладать следующими архитектурными характеристиками

    Система должна обладать высокой доступностью
    Система должна быть масштабируемой и отказоустойчивой
    Мы должны по возможности обеспечить низкие затраты на инфраструктуру сервиса

### Формализация задачи

В этой задаче мне было бы интересно задать следующие вопросы для уточнения требований (ответы интервьюера я буду отмечать курсивом)

    Надо ли нам учитывать аутентификацию и авторизацию клиентов?
    Нет, она тут достаточно типовая и лучше не тратить на нее время
    Можем ли мы использовать внешние сервисы, например, CDN для раздачи контента?
    Да, можем, если обоснуем зачем они нам нужны и как мы будем с ними взаимодействовать. Конкретно по поводу CDN могу сказать, что создание полноценной CDN тянет на отдельную задачу по System Design
    Будем ли мы глубоко копать в транскодирование видео?
    Нет не будем, так как это уже достаточно специфичный домен и у нас нет цели проверять у всех кандидатов их знания
    В какие разрешения нам стоит конвертировать видео?
    Допустим в 360, 480, 720, 1080
    Оригинальное видео сохраняем после всех конвертаций или нет?
    Оригинальное видео будем удалять после всех конвертаций
    Видео должно становиться доступным у подписчиков когда все разрешения готовы или хотя бы одно?
    Видео будет становиться доступным после того, как все разрешения будут подготовлены
    Ленты подписчиков формируются умным образом или это просто последние видео, добавленные авторами, на которых мы подписаны
    Пускай ленты в нашем случае будут простыми с сортировкой по времени от самых последних к самым ранним, ленту мы будем получать порциями по 10 элементов

Отдельно зададим вопросы по нагрузке, которые влияют на то как нам потребуется масштабировать нашу систему:

    Какое количество пользователей у нашего сервиса мы ожидаем?
    DAU (Daily Active Users) нашего сервиса будет 10 mln
    Наши пользователи географически распределены?
    Да, они расположены в разных регионах
    Сколько видео в день будут смотреть наши пользователи?
    В среднем 10 видео в день
    Сколько видео в день будет загружаться
    В среднем пользователи загружают по 0.1 видео в день
    Какой средний размер видео будет загружаться в наш сервис?
    Средний размер видео будет 300 Mb
    Как часто пользователи будут запрашивать свой feed с видео?
    В среднем 5 раз в день
    Сколько храним загруженное видео
    Время хранения не ограничено сверху

## Архитектурные решения

### Основные компоненты

**1. Upload Service**
- Multipart upload для больших файлов (чанки по 10-20MB)
- Resumable uploads для надежности
- Прямая загрузка в Object Storage через pre-signed URLs
- Генерация upload_id и инициация transcoding pipeline

**2. Transcoding Service**
- Асинхронная обработка через message queue
- Параллельное транскодирование в разные разрешения (360p, 480p, 720p, 1080p)
- Использование специализированных воркеров (ffmpeg, GPU acceleration)
- Удаление оригинала после успешного транскодирования
- Отказоустойчивость: retry механизмы, dead letter queue

**3. Video Metadata Service**
- CRUD операции для метаданных видео (title, description, channel_id, upload_date)
- Хранение статусов: UPLOADING → PROCESSING → READY
- Tracking доступных разрешений для каждого видео
- View counters, likes, comments (out of scope детали)

**4. Feed Service**
- Генерация ленты подписок: последние видео от каналов, на которые подписан пользователь
- Пагинация по 10 элементов, сортировка по дате публикации
- Fan-out on read: при запросе собираем ленту из видео подписок
- Кэширование лент популярных пользователей (Redis)

**5. Streaming Service**
- Adaptive bitrate streaming (HLS/DASH)
- Генерация манифестов с разными качествами
- Поддержка переключения качества на лету

**6. Subscription Service**
- Управление подписками пользователей на каналы
- Fast lookups: кто на кого подписан

**7. Storage Layer**
- **Object Storage (S3)**: хранение оригиналов и транскодированных видео
- **PostgreSQL**: метаданные видео, каналов, пользователей, подписок
- **Redis**: кэширование лент, метаданных популярных видео, view counters
- **Message Queue (Kafka/RabbitMQ)**: асинхронная обработка транскодирования

**8. CDN**
- Географически распределенная раздача видео контента
- Кэширование популярных видео на edge локациях
- Снижение latency и нагрузки на origin
- Cost optimization: меньше egress traffic из S3

### Ключевые технологии

- **API Gateway**: Nginx/Kong - routing, rate limiting, auth
- **Backend**: Go/Python - Upload/Metadata сервисы
- **Transcoding**: FFmpeg на воркерах (CPU/GPU), автомасштабирование
- **Database**: PostgreSQL + Read Replicas - метаданные, подписки
- **Cache**: Redis Cluster - ленты, hot metadata, counters
- **Queue**: Kafka - транскодирование pipeline, event streaming
- **Storage**: S3-compatible object storage - видео файлы
- **CDN**: CloudFront/Cloudflare - global content delivery
- **Streaming**: HLS/DASH протоколы
- **Monitoring**: Prometheus + Grafana + distributed tracing

### Оптимизации для NFR

**Высокая доступность:**
- Multi-region deployment для критичных сервисов
- PostgreSQL master-replica setup с автоматическим failover
- Redis cluster с репликацией
- S3 с cross-region replication для критичного контента
- Health checks и автоматический restart

**Масштабируемость:**
- Stateless микросервисы с горизонтальным масштабированием
- Автоскейлинг transcoding воркеров по длине очереди
- Database sharding по channel_id при необходимости
- Партиционирование таблиц по времени
- CDN edge caching снижает нагрузку на origin

**Отказоустойчивость:**
- Retry механизмы с exponential backoff
- Circuit breakers для внешних зависимостей
- Dead letter queues для failed jobs
- Graceful degradation: если transcoding не готов, показываем статус
- Multi-AZ deployment

**Низкие затраты:**
- CDN кэширование снижает egress из S3 (основная статья расходов)
- Удаление оригиналов после транскодирования (экономия 300TB/день)
- Object Storage lifecycle policies: архивирование редко просматриваемых видео в Glacier
- Spot instances для transcoding воркеров (до 70% экономии)
- Сжатие видео с оптимальными bitrate для каждого разрешения

## Архитектурная диаграмма

```mermaid
graph TB
    User[User]
    Creator[Content Creator]
    
    APIGateway[API Gateway]
    
    UploadService[Upload Service]
    VideoService[Video Metadata Service]
    FeedService[Feed Service]
    StreamingService[Streaming Service]
    SubService[Subscription Service]
    
    TranscodingWorkers[Transcoding Workers Pool]
    
    Kafka[Kafka Message Queue]
    
    PostgreSQL[(PostgreSQL Master)]
    ReadReplicas[(Read Replicas)]
    Redis[(Redis Cluster)]
    
    S3[S3 Object Storage]
    CDN[CDN CloudFront]
    
    Creator -->|upload video| APIGateway
    User -->|watch video| CDN
    User -->|get feed| APIGateway
    User -->|subscribe| APIGateway
    
    APIGateway --> UploadService
    APIGateway --> VideoService
    APIGateway --> FeedService
    APIGateway --> StreamingService
    APIGateway --> SubService
    
    UploadService -->|store original| S3
    UploadService -->|publish event| Kafka
    
    Kafka -->|transcode job| TranscodingWorkers
    
    TranscodingWorkers -->|read original| S3
    TranscodingWorkers -->|write transcoded| S3
    TranscodingWorkers -->|update status| VideoService
    
    VideoService --> PostgreSQL
    VideoService --> Redis
    
    FeedService --> SubService
    FeedService --> VideoService
    FeedService --> Redis
    FeedService --> ReadReplicas
    
    SubService --> PostgreSQL
    SubService --> Redis
    
    StreamingService -->|generate manifest| CDN
    
    S3 -->|origin| CDN
    
    PostgreSQL -.->|replicate| ReadReplicas
```

## Sequence диаграммы

### Загрузка видео

```mermaid
sequenceDiagram
    participant Creator
    participant API as API Gateway
    participant US as Upload Service
    participant S3 as S3 Storage
    participant Kafka
    participant TW as Transcoding Workers
    participant VS as Video Service
    participant FS as Feed Service
    
    Creator->>API: POST /videos/upload {metadata}
    API->>US: Initialize upload
    US->>VS: Create video record status=UPLOADING
    VS->>DB: INSERT video metadata
    US->>S3: Generate pre-signed URL
    US-->>Creator: {upload_id, presigned_url}
    
    Creator->>S3: PUT multipart upload chunks
    S3-->>Creator: Upload complete
    
    Creator->>API: POST /videos/{id}/complete
    API->>US: Finalize upload
    US->>VS: Update status=PROCESSING
    US->>Kafka: Publish transcode_job event
    US-->>Creator: Upload successful
    
    Kafka->>TW: Consume transcode job
    TW->>S3: Download original
    
    par Parallel transcoding
        TW->>TW: Transcode to 360p
        TW->>TW: Transcode to 480p
        TW->>TW: Transcode to 720p
        TW->>TW: Transcode to 1080p
    end
    
    TW->>S3: Upload all resolutions
    TW->>S3: Delete original
    TW->>VS: Update status=READY, available_resolutions
    VS->>DB: UPDATE video SET status=READY
    
    Note over FS: Video now appears in subscriber feeds
```

### Просмотр видео

```mermaid
sequenceDiagram
    participant User
    participant CDN
    participant SS as Streaming Service
    participant S3 as S3 Storage
    participant VS as Video Service
    participant Redis
    
    User->>CDN: GET /watch/{video_id}
    CDN->>SS: Request video manifest
    
    SS->>Redis: Check cache
    alt Cache hit
        Redis-->>SS: Cached metadata
    else Cache miss
        SS->>VS: Get video metadata
        VS->>DB: SELECT video WHERE id=?
        VS-->>SS: Video metadata with resolutions
        SS->>Redis: Cache metadata
    end
    
    SS->>S3: Generate HLS manifest
    S3-->>SS: Manifest with quality variants
    SS-->>CDN: Return manifest
    CDN-->>User: HLS manifest
    
    User->>CDN: GET video segments 720p
    alt CDN cache hit
        CDN-->>User: Serve from edge
    else CDN cache miss
        CDN->>S3: Fetch segment
        S3-->>CDN: Video segment
        CDN->>CDN: Cache segment
        CDN-->>User: Video segment
    end
    
    Note over User: User changes quality to 1080p
    User->>CDN: GET video segments 1080p
    CDN-->>User: Serve 1080p segments
    
    User->>API: POST /videos/{id}/view
    API->>VS: Increment view counter
    VS->>Redis: INCR views:{video_id}
```

### Получение ленты

```mermaid
sequenceDiagram
    participant User
    participant API as API Gateway
    participant FS as Feed Service
    participant SS as Subscription Service
    participant VS as Video Service
    participant Redis
    participant DB as Read Replica
    
    User->>API: GET /feed?page=1&limit=10
    API->>FS: Get user feed
    
    FS->>Redis: Check cached feed
    alt Cache hit
        Redis-->>FS: Cached feed
        FS-->>User: Return feed
    else Cache miss
        FS->>SS: Get user subscriptions
        SS->>Redis: Check cache
        alt Subs cached
            Redis-->>SS: Subscribed channel_ids
        else Subs not cached
            SS->>DB: SELECT subscriptions WHERE user_id=?
            DB-->>SS: Channel IDs
            SS->>Redis: Cache subscriptions
        end
        SS-->>FS: List of channel_ids
        
        FS->>VS: Get latest videos from channels
        VS->>DB: SELECT videos WHERE channel_id IN (...) AND status=READY ORDER BY published_at DESC LIMIT 10
        DB-->>VS: Video list
        VS-->>FS: Videos with metadata
        
        FS->>Redis: Cache feed TTL 5min
        FS-->>API: Feed items
        API-->>User: Return feed
    end
```

## Масштабирование и метрики

**При росте нагрузки:**
- Горизонтальное масштабирование всех stateless сервисов
- Увеличение transcoding workers pool (auto-scaling)
- Database sharding по channel_id или video_id ranges
- Региональное разворачивание для снижения latency
- Использование multiple CDN providers

**Database оптимизации:**
- Indexes: (channel_id, published_at), (user_id, created_at), (status)
- Partitioning videos table по датам (monthly)
- Separate DB для аналитики (ClickHouse)
- Archive старых метрик

**CDN стратегия:**
- Популярные видео cache на edge максимально долго
- Редкие видео - pull from origin по запросу
- Разные TTL для разных тиров контента

**Ключевые метрики:**
- Upload success rate и время загрузки (p95, p99)
- Transcoding queue length и processing time
- Video startup time и buffering rate
- CDN cache hit ratio (target >90%)
- Feed generation latency
- Storage costs per TB
- Egress traffic costs


