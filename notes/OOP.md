# Принципы ООП
+ Инкапусляция - это механизм, который объединяет данные и код, манипулирующий зтими данными, а также защищает и то, и другое от внешнего вмешательства или неправильного использования. По сути, всё то, что не входит в интерфейс, инкапсулируется в классе.
+ Наследование - это свойство системы, позволяющее описать новый класс на основе уже существующего с частично или полностью заимствующейся функциональностью
+ Полиморфизм
    + Ad-hoc-полиморфизм - один интерфейс — много реализаций
    + Параметрический полиморфизм - дженерики, обобщенные методы и классы
+ Абстракция - это способ выделить набор значимых характеристик объекта, исключая из рассмотрения незначимые. Соответственно, абстракция – это набор всех таких характеристик.

# SOLID

### *S* - Принцип единственной ответственности (Single Responsibility Principle)

Никогда не должно быть больше одной причины изменить программную сущность (класс, модуль и т.д.), т.е. сущность должна иметь только одну ответственность
<details>
<summary>Пример</summary>

Представьте себе модуль, который обрабатывает заказы. Если заказ верно сформирован, он сохраняет его в базу данных и высылает письмо для подтверждения заказа:

```java
public class OrderProcessor {

    public void process(Order order){
        if (order.isValid() && save(order)) {
            sendConfirmationEmail(order);
        }
    }

    private boolean save(Order order) {
        MySqlConnection connection = new MySqlConnection("database.url");
        // сохраняем заказ в базу данных

        return true;
    }

    private void sendConfirmationEmail(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();

        // Шлем письмо клиенту
    }
}
```
Такой модуль может измениться по трем причинам. Во-первых может стать другой логика обработки заказа, во-вторых, способ его сохранения (тип базы данных), в-третьих — способ отправки письма подтверждения (скажем, вместо email нужно отправлять SMS). 

Гораздо лучше разделить модуль на три отдельных, каждый из которых будет выполнять одну единственную функцию:
```java
public class MySQLOrderRepository {
    public boolean save(Order order) {
        MySqlConnection connection = new MySqlConnection("database.url");
        // сохраняем заказ в базу данных

        return true;
    }
}

public class ConfirmationEmailSender {
    public void sendConfirmationEmail(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();

        // Шлем письмо клиенту
    }
}

public class OrderProcessor {
    public void process(Order order){

        MySQLOrderRepository repository = new MySQLOrderRepository();
        ConfirmationEmailSender mailSender = new ConfirmationEmailSender();

        if (order.isValid() && repository.save(order)) {
            mailSender.sendConfirmationEmail(order);
        }
    }

}
```
</details>

### *O* - Принцип открытости/закрытости (Open Closed Principle)

Программные сущности (классы, модули, функции и т.п.) должны быть открыты для расширения, но закрыты для изменения (чтобы не сломать код использующий эти сущности).
<details>
<summary>Пример</summary>

Это означает, что должна быть возможность изменять внешнее поведение класса, не внося физические изменения в сам класс. Следуя этому принципу, классы разрабатываются так, чтобы для подстройки класса к конкретным условиям применения было достаточно расширить его и переопределить некоторые функции.

Продолжая наш пример с заказом, предположим, что нам нужно выполнять какие-то действия перед обработкой заказа и после отправки письма с подтверждением. 

Вместо того, чтобы менять сам класс OrderProcessor, мы расширим его потомком OrderProcessorWithPreAndPostProcessing и добьемся решения поставленной задачи, не нарушая принцип OCP: 
```java
public class OrderProcessorWithPreAndPostProcessing extends OrderProcessor {

    @Override
    public void process(Order order) {
        beforeProcessing();
        super.process(order);
        afterProcessing();
    }

    private void beforeProcessing() {
        // Осуществим некоторые действия перед обработкой заказа
    }

    private void afterProcessing() {
        // Осуществим некоторые действия после обработки заказа
    }
}
```
</details>

### *L* - Принцип подстановки Барбары Лисков (Liskov Substitution Principle)

Объекты в программе должны быть заменяемыми на экземпляры их подтипов без изменения правильности выполнения программы
<details>
<summary>Пример</summary>

Это означает, что класс, разработанный путем расширения на основании базового класса, должен переопределять его методы так, чтобы не нарушалась функциональность с точки зрения клиента. 
То есть, если разработчик расширяет ваш класс и использует его в приложении, он не должен изменять ожидаемое поведение переопределенных методов.

Предположим у нас есть класс, который отвечает за валидацию заказа и проверяет, все ли из товаров заказа находятся на складе. 
У данного класса есть метод isValid который возвращает true или false:
```java
public class OrderStockValidator {

    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if (! item.isInStock()) {
                return false;
            }
        }

        return true;
    }
}
```

Также предположим, что некоторые заказы нужно валидировать иначе: проверять, все ли товары заказа находятся на складе и все ли товары упакованы. 
Для этого мы расширили класс OrderStockValidator классом OrderStockAndPackValidator:
```java
public class OrderStockAndPackValidator extends OrderStockValidator {

    @Override
    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if ( !item.isInStock() || !item.isPacked() ){
                throw new IllegalStateException(
                     String.format("Order %d is not valid!", order.getId())
                );
            }
        }

        return true;
    }
}
```

Однако в данном классе мы нарушили принцип LSP, так как вместо того, чтобы вернуть false, если заказ не прошел валидацию, наш метод бросает исключение IllegalStateException. Клиенты данного кода не рассчитывают на такое: они ожидают возвращения true или false. Это может привести к ошибкам в работе программы.
</details>

### *I* - Принцип разделения интерфейса (Interface Segregation Principle)
+ Много интерфейсов, специально предназначенных для клиентов, лучше, чем один интерфейс общего назначения. Иначе клиентам приходится реализовывать много лишних методов

### *D* - Принцип инверсии зависимостей (Dependency Inversion Principle)
+ Абстракции не должны зависеть от деталей. Детали должны зависеть от абстракций. Нарушаем, например, если инжектим не интерфейс, а реализацию


### Паттерны GoF и их применения в JDK
+ [Все паттерны со схемами, разбитые по категориям (C - creational, S - structural, B - behavioral)](resources/patterns_rus.pdf)
+ Прототип – `Cloneable`
+ Адаптер – `InputStreamReader`, `OutputStreamWriter` и др.
+ Декоратор – все подклассы `java.io.InputStream`, `OutputStream`, `Reader` и `Writer` имеют конструктор, принимающий объекты этих же классов
+ Прокси - все бины Spring или EJB, моки
+ Итератор – `java.util.Iterator` + внутренняя реализация его в коллекциях
+ Шаблонный метод - сервлеты (метод `service()`)
+ Легковес - `java.lang.Integer#valueOf(int)` – если значение есть в кэше (IntegerCache – от -128 до 127) то возвращет его, иначе новый объект (а также `Boolean`, `Byte`, `Character`, `Short`, `Long` и `BigDecimal`)
+ Посетитель - `java.nio.file.FileVisitor` 
+ Стратегия - `java.util.Comparator#compare()`, вызываемые из `Collections#sort(List, Comparator)`
+ Строитель – `StringBuilder`, `Appendable`  и др.
+ Цепочка обязанностей - `javax.servlet.Filter#doFilter()`
+ Фабричный метод - `java.util.EnumSet#of()`, `java.util.List#of()`
+ Примеры реализации всех паттернов ООП (GoF) 
  + Java [/_java_/src/main/java/patterns](../_java_/src/main/java/patterns)
  + Kotlin [/\_kotlin_/src/main/kotlin/patterns](../\_kotlin_/src/main/kotlin/ru/gasymovrv/\_kotlin_/patterns)
