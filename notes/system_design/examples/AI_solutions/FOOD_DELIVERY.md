# Food Delivery System Design

Требуется спроектировать систему, которая позволит пользователям заказывать доставку готовой еды из ресторанов. Аналоги известны — Delivery Club, Yandex Eats, Uber Eats.

### Функциональные требования

Система должна позволять реализовать следующие фичи:

- Пользователь может выбрать ресторан и оформить заказ.
- Пользователь может отслеживать статус заказа (принят, готовится, в пути, доставлен).
- Курьеры могут принимать заказы, видеть адреса доставки и отмечать этапы выполнения.
- Рестораны получают уведомления о новых заказах и подтверждают возможность их выполнения.

### Нефункциональные требования

Решение должно обладать следующими архитектурными характеристиками:

- Система должна быть высокодоступной.
- Система должна поддерживать высокую нагрузку в часы пик.
- Обновления статуса заказа должны происходить в реальном времени (максимум 1–2 секунды задержки).
- Мы хотим минимизировать задержки между этапами выполнения заказа (end-to-end latency — не более 5 секунд на обработку транзакции).

### Формализация задачи

В этой задаче мне было бы интересно задать следующие вопросы для уточнения требований:

- Надо ли учитывать аутентификацию пользователей, курьеров и ресторанов?
  — Нет, считаем, что у нас есть базовая система авторизации.
- Нужно ли учитывать оплату заказа?
  — Да, но через внешний платежный шлюз.
- Считаем ли, что пользователи могут отменить заказ?
  — Да, но только до момента, когда курьер принял заказ.
- Нужно ли нам реализовывать рекомендации ресторанов или блюд?
  — Нет, считаем это отдельной задачей.
- Требуется ли нам поддержка геолокации курьеров в реальном времени?
  — Да, хотя с умеренной точностью (обновление координат раз в 10 секунд).

Отдельно зададим вопросы по нагрузке:

- Какое количество активных пользователей?
  — Около 5 млн DAU.
- Какое среднее количество заказов в день?
  — Около 1 млн заказов.
- Какова пиковая нагрузка?
  — До 100 тыс. заказов в час.
- Географическое распределение пользователей?
  — Основные города России, с плотной концентрацией в Москве и Санкт-Петербурге.
- Средний размер данных о заказе?
  — Порядка 5–10 KB.

## Архитектурные решения

### Общая архитектура
Микросервисная архитектура для обеспечения масштабируемости, отказоустойчивости и независимого развертывания компонентов.

### Ключевые компоненты

**API Layer:**
- API Gateway (Kong/AWS API Gateway) - единая точка входа, rate limiting, маршрутизация
- Load Balancer (Nginx) - распределение нагрузки

**Core Services:**
- **Order Service** - создание, управление и валидация заказов
- **Restaurant Service** - каталог ресторанов, меню, доступность
- **Courier Service** - управление курьерами, назначение заказов
- **Tracking Service** - отслеживание статусов в real-time
- **Payment Service** - интеграция с внешним платежным шлюзом
- **Notification Service** - уведомления пользователям, курьерам, ресторанам
- **Geolocation Service** - отслеживание позиций курьеров

**Data Layer:**
- **PostgreSQL** - транзакционные данные (заказы, пользователи, платежи)
- **MongoDB** - каталоги ресторанов и меню (гибкая схема)
- **Redis** - кэширование, сессии, real-time данные, геолокация
- **Redis Geospatial** - хранение координат курьеров

**Messaging & Events:**
- **Apache Kafka** - event streaming для асинхронной обработки, гарантированная доставка событий
- Topics: order-created, order-status-changed, courier-location-updated

**Real-time Communication:**
- **WebSockets** - двунаправленная связь для обновления статусов в реальном времени

### Выбранные технологии

| Компонент | Технология | Обоснование |
|-----------|------------|-------------|
| Backend | Go/Node.js | Высокая производительность, асинхронность |
| API Gateway | Kong | Масштабируемость, плагины |
| Database (OLTP) | PostgreSQL | ACID, надежность для заказов |
| Database (Catalog) | MongoDB | Гибкая схема для меню |
| Cache | Redis | In-memory, low latency, геолокация |
| Message Queue | Apache Kafka | Высокая пропускная способность, durability |
| Real-time | WebSockets | Bidirectional, low latency |
| CDN | CloudFlare/AWS CloudFront | Статика (изображения меню) |

### Масштабирование под нагрузку

**Расчет нагрузки:**
- 1M заказов/день ≈ 12 заказов/сек (средняя)
- Пиковая: 100K заказов/час ≈ 28 заказов/сек
- 5M DAU → поиск ресторанов, меню (read-heavy)

**Стратегии:**
1. **Horizontal Scaling** - реплики сервисов (Order, Restaurant Service)
2. **Database Sharding** - по географическому признаку (города)
3. **Read Replicas** - для PostgreSQL (read-heavy операции)
4. **Caching Strategy**:
   - Redis для каталогов ресторанов (TTL 5 мин)
   - CDN для изображений блюд
   - Cache-aside pattern
5. **Rate Limiting** - защита от DDoS на API Gateway
6. **Circuit Breaker** - изоляция сбоев между сервисами

### Обеспечение real-time обновлений (< 2 сек)

1. **Event-Driven Architecture** - изменение статуса → событие в Kafka
2. **WebSocket Connections** - постоянное соединение с клиентами
3. **Notification Service** подписан на Kafka topics, пушит обновления через WebSockets
4. **Fallback**: Server-Sent Events (SSE) для клиентов без WS

### Обеспечение высокой доступности

1. **Multi-AZ Deployment** - распределение по зонам доступности
2. **Database Replication** - Primary-Replica для PostgreSQL
3. **Kafka Replication Factor** = 3
4. **Health Checks** - автоматический failover
5. **Graceful Degradation** - при недоступности Payment Gateway заказ сохраняется, оплата повторяется

### Геолокация курьеров

- Redis Geospatial (GEOADD, GEORADIUS) - хранение координат
- Обновление каждые 10 сек с клиента курьера
- Поиск ближайших курьеров - O(log N)
- TTL для координат - 30 сек (защита от "зависших" курьеров)

## Верхнеуровневая архитектура

```mermaid
graph TB
    subgraph Clients
        C[Customer App]
        CR[Courier App]
        R[Restaurant Panel]
    end

    subgraph Gateway
        LB[Load Balancer]
        AG[API Gateway]
    end

    subgraph Services
        OS[Order Service]
        RS[Restaurant Service]
        CS[Courier Service]
        TS[Tracking Service]
        PS[Payment Service]
        NS[Notification Service]
        GS[Geolocation Service]
    end

    subgraph Data
        PG[(PostgreSQL)]
        MG[(MongoDB)]
        RD[(Redis)]
    end

    subgraph Messaging
        KF[Kafka]
    end

    subgraph External
        PG_EXT[Payment Gateway]
    end

    C --> LB
    CR --> LB
    R --> LB
    LB --> AG
    AG --> OS
    AG --> RS
    AG --> CS
    AG --> TS
    AG --> GS

    OS --> PG
    OS --> KF
    RS --> MG
    RS --> RD
    CS --> PG
    CS --> RD
    TS --> RD
    GS --> RD

    KF --> NS
    KF --> TS

    PS --> PG_EXT
    OS --> PS

    NS -.WebSocket.-> C
    NS -.WebSocket.-> CR
    NS -.WebSocket.-> R
```

## Основные флоу

### 1. Создание заказа

```mermaid
sequenceDiagram
    participant C as Customer
    participant AG as API Gateway
    participant OS as Order Service
    participant RS as Restaurant Service
    participant PS as Payment Service
    participant K as Kafka
    participant NS as Notification Service
    participant R as Restaurant

    C->>AG: POST /orders
    AG->>OS: Create Order
    OS->>RS: Check Restaurant Availability
    RS-->>OS: Available
    OS->>PS: Process Payment
    PS->>PS: Call Payment Gateway
    PS-->>OS: Payment Success
    OS->>K: Publish order.created
    OS-->>AG: Order Created (orderId)
    AG-->>C: 201 Created

    K->>NS: order.created event
    NS->>R: Push Notification (New Order)
```

### 2. Отслеживание статуса заказа

```mermaid
sequenceDiagram
    participant C as Customer
    participant AG as API Gateway
    participant TS as Tracking Service
    participant K as Kafka
    participant NS as Notification Service

    C->>AG: GET /orders/:id/status
    AG->>TS: Get Status
    TS-->>AG: Status: preparing
    AG-->>C: 200 OK

    Note over C,NS: Real-time updates via WebSocket

    C->>NS: WebSocket Connection
    NS-->>C: Connected

    Note over K: Restaurant updates order status

    K->>TS: order.status.changed
    TS->>TS: Update Redis Cache
    TS->>NS: Status Update Event
    NS-->>C: WS: Status cooking
```

### 3. Назначение и доставка курьера

```mermaid
sequenceDiagram
    participant R as Restaurant
    participant OS as Order Service
    participant CS as Courier Service
    participant GS as Geolocation Service
    participant K as Kafka
    participant CR as Courier
    participant C as Customer

    R->>OS: Confirm Order Ready
    OS->>K: Publish order.ready
    K->>CS: order.ready event
    CS->>GS: Find Nearest Couriers
    GS-->>CS: List of Couriers
    CS->>CR: Assign Order (Push)
    CR->>CS: Accept Order
    CS->>K: Publish order.assigned
    K->>C: Notify Customer (Courier Assigned)

    loop Every 10 seconds
        CR->>GS: Update Location
    end

    CR->>CS: Picked Up
    CS->>K: Publish order.picked_up
    K->>C: Notify Status Change

    CR->>CS: Delivered
    CS->>K: Publish order.delivered
    K->>C: Notify Delivered
```

### 4. Отмена заказа

```mermaid
sequenceDiagram
    participant C as Customer
    participant OS as Order Service
    participant CS as Courier Service
    participant PS as Payment Service
    participant K as Kafka
    participant R as Restaurant

    C->>OS: POST /orders/:id/cancel
    OS->>OS: Check Status
    alt Status = accepted or preparing
        OS->>PS: Refund Payment
        PS-->>OS: Refund Success
        OS->>K: Publish order.cancelled
        OS-->>C: 200 OK Cancelled
        K->>R: Notify Cancellation
    else Status = in_delivery
        OS-->>C: 400 Cannot Cancel
    end
```

## Основные сущности и модели данных

### Order (PostgreSQL)
```
id, user_id, restaurant_id, courier_id, status, 
items (JSON), total_amount, delivery_address, 
created_at, updated_at
```

### Restaurant (MongoDB)
```
id, name, description, address, menu (embedded), 
rating, available, opening_hours
```

### Courier (PostgreSQL)
```
id, name, phone, status (available/busy), 
current_order_id, rating
```

### Geolocation (Redis)
```
Key: courier:location
GeoHash: courier_id -> (lat, lng, timestamp)
```

## Мониторинг и метрики

- **Latency**: p50, p95, p99 для всех API endpoints
- **Throughput**: orders/sec, requests/sec
- **Error Rate**: 4xx, 5xx responses
- **Database Performance**: query time, connection pool
- **Kafka Lag**: consumer lag monitoring
- **Business Metrics**: order completion rate, average delivery time

---

**Итого:** Микросервисная архитектура с event-driven подходом обеспечивает масштабируемость (горизонтальное масштабирование сервисов), высокую доступность (репликация БД, multi-AZ), low latency (Redis cache, WebSockets для real-time), и отказоустойчивость (Kafka для гарантированной доставки событий, circuit breakers).

