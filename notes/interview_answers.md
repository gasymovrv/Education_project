### Самопрезентация 5 минут о себе:
1. Приветствие/Должность/Направление работы
3. Опыт более N лет, участвовал примерно в X проектах
4. Проекты на которых я работал (последние 2)
5. Описание проекта (создавались с нуля!), какие технологии применялись
6. Роль в проекте / Состав команды
7. Методология разработки (скрам, канбан)

# Ответы на типичные вопросы на собеседовании с Java-разработчиком
### Алгоритмы
+ [Категории алгоритмов и примеры](algorithms.md#Категории-алгоритмов-и-примеры)
+ [Оценка сложности алгоритмов](algorithms.md#оценка-сложности-алгоритмов-ofn)

### ООП
+ [Принципы ООП](OOP.md#Принципы-ООП)
+ [SOLID](OOP.md#SOLID)
+ [Паттерны GoF и их применения в JDK](OOP.md#Паттерны-GoF-и-их-применения-в-JDK)

### Java и JVM
+ [Java EE](java.md#Java-EE)
+ [Методы Object](java.md#Методы-Object)
+ [Нюансы синтаксиса Java](java.md#Нюансы-синтаксиса-Java)
+ [JVM компоненты](java.md#JVM-компоненты)
+ [Правила реализации equals/hashCode](java.md#Правила-реализации-equalshashCode)
+ [Java Collections API](java.md#Java-Collections-API)
+ [Java Stream API](java.md#Java-Stream-API)
+ [Пул строк](java.md#Пул-строк)
+ [WeakReference, SoftReference и др.](java.md#weakreference-softreference-и-др)
+ [Утечки памяти (Memory leaks)](java.md#утечки-памяти-memory-leaks)
+ [Отличия Java IO от Java NIO](java.md#Отличия-Java-IO-от-Java-NIO)
+ [Многопоточность в Java](java.md#Многопоточность)

### Spring
+ [Spring Core](spring_hibernate.md#Spring-Core)
+ [Spring Boot](spring_hibernate.md#Spring-Boot)
+ [Spring Data](spring_hibernate.md#Spring-Data)
+ [Spring Security](spring_hibernate.md#Spring-Security)
+ [Spring Webflux](spring_hibernate.md#Spring-Webflux)

### Hibernate
+ [Стратегии наследования](spring_hibernate.md#Стратегии-наследования)
+ [Проблема N+1](spring_hibernate.md#Проблема-N1)

### Базы данных
+ [Работа с БД через Spring Data](spring_hibernate.md#Spring-Data)
+ [Транзакции и ACID](databases.md#Транзакции-и-ACID)
+ [Теорема CAP](databases.md#Теорема-CAP)
+ [SQL СУБД](databases.md#SQL-СУБД)
+ [NoSQL СУБД](databases.md#NoSQL-СУБД)
+ [Система управления миграциями БД](liquibase.md)

### Web services
#### REST
+ работает только по http, 
+ данные в любом формате (чаще json), 
+ операции клиента с сервером stateless, CRUD. 
+ легче соапа. хорошо подходит когда нужно работать с фронтом

#### SOAP
+ работает по любому протоколу, 
+ данные только в xml. 
+ имеет строгий стандарт. 
+ Лучше подходит для сервер - сервер, считается более безопасным
+ wsdl - язык описания веб-сервисов и доступа к ним, основанный на языке XML. Основные элементы:
	+ types — типы XML-сообщений (xsd схемы, парсятся в классы)
	+ message — сообщения, используемые web-сервисом (связь операций с типами-классами)
	+ portType — список операций, которые могут быть выполнены с сообщениями (методы, будут ожидаться в теле запросов)
	+ binding — способ, которым сообщение будет доставлено (протокол и др.)
	+ service - это набор ендпойнтов
	+ помоему есть еще что-то связанное с шифрованием (signature, security ...)

### HTTP
+ [Структура](HTTP.md#Структура-HTTP)
+ [GET vs POST](HTTP.md#GET-для-безопасных-действий-POST-для-опасных)
+ [GET](HTTP.md#GET)
+ [POST](HTTP.md#POST)
+ [PUT](HTTP.md#PUT)
+ [PATCH](HTTP.md#PATCH)
+ [HEAD](HTTP.md#HEAD)
+ [OPTIONS](HTTP.md#OPTIONS)
+ [CONNECT](HTTP.md#CONNECT)
+ [Коды ответов](HTTP.md#Коды-ответов)

### Security
+ [Injections](security.md#Injections)
+ [Broken Authentication and Session Management](security.md#Broken-Authentication-and-Session-Management)
+ [XSS (Cross Site Scripting)](security.md#XSS-Cross-Site-Scripting)
+ [Insecure Direct Object References](security.md#Insecure-Direct-Object-References)
+ [Security Misconfiguration](security.md#Security-Misconfiguration)
+ [Cross-Site Request Forgery (CSRF)](security.md#Cross-Site-Request-Forgery-CSRF)
+ [Протокол OAuth 2.0](security.md#Протокол-OAuth-20)

### High load
+ [Основные подходы](high_load.md#Основные-подходы)
+ [Сага](high_load.md#Сага)

### Разное
+ [Concurrency / parallel / multithreading / asynchrony / reactivity](concurrency.md)
+ [CI/CD](CI_CD.md)
+ [Git](git.md)
+ [Kafka](kafka.md)
+ [Docker](docker.md)
+ [Двоичная система исчисления](binary.md)
+ [Bash](bash.md)
+ [Regexp](regex.md)
+ [HTML/CSS](html_css.md)
+ [React/NodeJS](react_nodejs.md)

#### Immutable объекты
+ Потокобезопасно
+ Возможность кэширования (например [пул строк](java.md#Пул-строк))
+ Отсутствие сайд эффектов
+ Как сделать?
  + Поля объявляем final
  + Сеттеры скрываем
  + В геттерах возвращаем копии для полей типа список или объект

#### Идемпотентность
Идемпотентность - это действие, многократное повторение которого эквивалентно однократному.
+ Примером такой операции могут служить GET-запросы в протоколе HTTP. По спецификации, сервер должен возвращать идентичные ответы на идентичные GET-запросы (при условии, что ресурс не изменился)
    
#### AOP/aspectj
+ AOP - аспекты используемые в Spring. 
    + Они могут юзать для создания прокси как jdk-dymanic (если у нашего класса есть интерфейс) так и cglib (если нтерфейса нет то прокси создастся с помощью наследования)
	+ В каком потоке вызывается аспект? - по умолчанию в том же, но можно настроить
+ AspectJ - аспекты, которые создаются на этапе компиляции и не требуют паттернов в коде. Сложнее, но быстрее

#### Кэш
+ Использовать когда много операций чтения, но мало изменений
+ Даст быстрое чтение но больше памяти
+ Появляется проблема устаревших данные - evicting кэша
	+ В hibernate реашется несколькими стратегиями обновления кеша:
		+ `read only` - Изменение данных при использовании этой стратегии приведёт к исключению.
		+ `nonstrict read write` - доступ для изменений не ограничивается и есть вероятность чтения устаревших
		+ `read write` - чтение из кеша блокируется (запрос идет в БД) при измении данных в данный момент

#### Agile
+ Scrum
  + разработка спринтами (2-3 недели)
  + митинги: daily, планирование спринта, ретроспектива спринта
  + стори пойнты для оценки задач

+ Kanban
  + циклическая разработка - нет спринтов и установленного времени к которому надо выполнить задачи
  + доска с визуализацией процесса: backlog, to do, in progress, review, test, done 
  + оценка задач по среднему времени их выполнения

