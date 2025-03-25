//---------------------------------------------------Базовые понятия----------------------------------------------

var string1 = "2354";
var string2 = "7334.6446";
var string3 = "73d34.x644s6";
var integer = Number(string1);
var double = Number(string2);
var wrong = Number(string3);

console.log("string1 = "+string1);
console.log("string2 = "+string2);
console.log("string3 = "+string3);
console.log("integer = "+integer);
console.log("double = "+double);
console.log("wrong = "+wrong);

console.log("тип переменной string1: " + typeof(string1));
console.log("тип переменной integer: " + typeof(integer));
console.log("тип переменной string2: " + typeof(string2));
console.log("тип переменной double: " + typeof(double));
console.log("тип переменной wrong: " + typeof(wrong));
//или так:
// console.log("тип переменной string1: " + typeof string1);
// console.log("тип переменной integer: " + typeof integer);
// console.log("тип переменной string2: " + typeof string2);
// console.log("тип переменной double: " + typeof double);
// console.log("тип переменной wrong: " + typeof wrong);

console.log("typeof string1 === \"string\": "+(typeof string1 === 'string'));
console.log("typeof integer === \"number\": "+(typeof integer === "number"));
console.log("typeof string2 === \"string\": "+(typeof string2 === "string"));
console.log("typeof double === \"number\": "+(typeof double === "number"));
console.log("typeof wrong === \"string\": "+(typeof wrong === "string"));

alert(typeof 1);         // 'number'
alert(typeof true);      // 'boolean'
alert(typeof "Текст");   // 'string'
alert(typeof undefined); // 'undefined'
alert(typeof null);      // 'object' (ошибка в языке)
alert(typeof alert);     // 'function'
alert(typeof {});        // 'object'
alert(typeof []);        // 'object'
alert(typeof new Date);  // 'object'

function User() {}
function Animal(name) {}
function Rabbit(name) {}
Rabbit.prototype = Object.create(Animal.prototype);
class AnimalES6{ constructor(name){this.name = name;} }
class RabbitES6 extends AnimalES6{ constructor(name){super(name);} }
var u = new User();
var r = new Rabbit("кроль");
var rES6 = new RabbitES6("кроль");
alert(rES6.name);//кроль
console.log("u instanceof User: "+(u instanceof User));//true
console.log("r instanceof Animal: "+(r instanceof Animal));//true
console.log("r instanceof Object: "+(r instanceof Object));//true
console.log("rES6 instanceof AnimalES6: "+(rES6 instanceof AnimalES6));//true
console.log("rES6 instanceof Object: "+(rES6 instanceof Object));//true

var arr = [];
alert( arr instanceof Array ); // true
alert( arr instanceof Object ); // true


//Взяли метод toString, принадлежащий именно стандартному объекту {}.
// Нам пришлось это сделать, так как у Date и Array – свои собственные методы toString, которые работают иначе.
//Затем мы вызываем этот toString в контексте нужного объекта obj,
// и он возвращает его внутреннее, невидимое другими способами, свойство [[Class]]
var toString = {}.toString;
var arr = [1, 2];
alert(toString.call(arr)); // [object Array]
var date = new Date;
alert(toString.call(date)); // [object Date]
var user = {name: "Вася"};
alert(toString.call(user)); // [object Object]
alert(toString.call(123)); // [object Number]
alert(toString.call("строка")); // [object String]

function getClass(obj) {
    return {}.toString.call(obj).slice(8, -1);
}
alert( getClass(new Date) ); // Date
alert( getClass([1, 2, 3]) ); // Array



//------------------Парсинг чисел-------------------------
let i1 = parseInt('3.14');
console.log(i1);//3

let i2 = parseInt('450px');
console.log(i2);//450, т.к. пройдется сначала до первого не числового значения
let i3 = parseInt('4в50px');
console.log(i3);//4

let dex = parseInt('00ff0', 16);
console.log(dex);//4080

let f1 = parseFloat('3.14354236');
console.log(f1.toFixed(3));//3.15 - округляет математически и возвращает строку

let f2 = parseFloat('3.14354236');
console.log(Math.ceil(f2*1000)/1000);//3.144 - до потолка
console.log(Math.floor(f2*1000)/1000);//3.143 - до пола
console.log(Math.round(f2*1000)/1000);//3.144 - математически




//---------------------------------------------------Функции----------------------------------------------


//-----------------------------------------------------

// Function Declaration
function sum1(a, b) {
    return a + b;
}

// Function Expression
var sum2 = function(a, b) {
    return a + b;
};


//Named Function Expression----------------------------

//Попробуем перенести функцию в другую переменную g:
function f(n) {
    return n ? n * f(n - 1) : 1;
}
var g = f;
f = null;
alert( g(5) ); // запуск функции с новым именем - ошибка при выполнении!
//Ошибка возникла потому что функция из своего кода обращается к своему старому имени f.
//А этой функции уже нет, f = null.


//Для того, чтобы функция всегда надёжно работала, объявим её как Named Function Expression:
var f1 = function factorial(n) {
    return n ? n * factorial(n - 1) : 1;
};
var g1 = f1;  // скопировали ссылку на функцию-факториал в g
f1 = null;

alert(g1(5)); // 120, работает!











//---------------------------------------------------Объекты----------------------------------------------


//--------------------Поля (свойства)---------------------
var person = {
    name: "Василий"
};
alert(person.lalala === undefined); // true, свойства нет
alert( person.name === undefined ); // false, свойство есть.
delete person.name; //удаляем поле
alert( person.name === undefined ); // true, теперь свойства нет

//более правлильно проверять наличие полей так:
var obj = {};
obj.test = undefined;
alert( "test" in obj ); // true
alert( "blabla" in obj ); // false

//Доступ через квадратные скобки
var person = {};
person['name'] = 'Вася'; // то же что и person.name = 'Вася'
alert( "name" in person ); // true

//Доступ к свойству через переменную
var person = {};
person.age = 25;
var key = 'age';
alert( person[key] ); // выведет person['age']

//Объявление со свойствами
var menuSetup = {
    width: 300,
    height: 200,
    title: "Menu"
};
// то же самое, что:
var menuSetup = {};
menuSetup.width = 300;
menuSetup.height = 200;
menuSetup.title = 'Menu';

//Названия свойств можно перечислять в кавычках
var menuSetup = {
    width: 300,
    'height': 200,
    "мама мыла раму": true
};

//В качестве значения можно тут же указать и другой объект:
var user = {
    name: "Таня",
    age: 25,
    size: {
        top: 90,
        middle: 60,
        bottom: 90
    }
};


//--------------------Перебор свойств---------------------
//FOR IN
var menu = {
    width: 300,
    height: 200,
    title: "Menu"
};
for (var key in menu) {
    // этот код будет вызван для каждого свойства объекта
    // ..и выведет имя свойства и его значение
    alert( "Ключ: " + key + " значение: " + menu[key] );
}

//получаем кол-во свойств
alert(Object.keys(menu).length);


//--------------------Клонирование---------------------
//с помощью паттерна "Прототип"
var obj = {
    width: 300,
    height: 200,
    title: "Menu",
    clone: function () {
        return {
            width : this.width,
            height : this.height,
            title : this.title,
            clone : this.clone
        };
    }
};
obj.width = 1;
obj.height = 2;
var obj2 = obj.clone();
var obj3 = obj2.clone();
for (var key in obj3) {
    console.log( "Ключ: " + key + " значение: " + obj3[key] );
}











//---------------------------------------------------Массивы----------------------------------------------

//pop
//Удаляет последний элемент из массива и возвращает его:
var fruits = ["Яблоко", "Апельсин", "Груша"];
alert( fruits.pop() ); // удалили "Груша"
alert( fruits ); // Яблоко, Апельсин

//push
//Добавляет элемент в конец массива:
var fruits = ["Яблоко", "Апельсин"];
fruits.push("Груша");
alert( fruits ); // Яблоко, Апельсин, Груша

//shift
//Удаляет из массива первый элемент и возвращает его:
var fruits = ["Яблоко", "Апельсин", "Груша"];
alert( fruits.shift() ); // удалили Яблоко
alert( fruits ); // Апельсин, Груша

//unshift
//Добавляет элемент в начало массива:
var fruits = ["Апельсин", "Груша"];
fruits.unshift('Яблоко');
alert( fruits ); // Яблоко, Апельсин, Груша

//цикл FOR - для массивов надежнее и быстрее чем FOR IN
var arr = ["Яблоко", "Апельсин", "Груша"];
arr.name = 'name!!!';//массиву технически можно задать и любое свойство
for (var i=0; i<arr.length; i++) {
    alert( arr[i] ); // Яблоко, Апельсин, Груша
}
for (var key in arr) {
    alert( arr[key] ); // Яблоко, Апельсин, Груша, name!!!
}

//Многомерные массивы
//Массивы в JavaScript могут содержать в качестве элементов другие массивы.
//Это можно использовать для создания многомерных массивов, например матриц:
var matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
];
alert( matrix[1][1] ); // центральный элемент


//split
var names = 'Маша, Петя, Марина, Василий';
var arr = names.split(', ');
for (var i = 0; i < arr.length; i++) {
    alert( 'Вам сообщение ' + arr[i] );
}

//join
var arr = ['Маша', 'Петя', 'Марина', 'Василий'];
var str = arr.join(';');
alert( str ); // Маша;Петя;Марина;Василий

//Удаление из массива
//Так как массивы являются объектами, то для удаления ключа можно воспользоваться обычным delete:
var arr = ["Я", "иду", "домой"];
delete arr[1]; // значение с индексом 1 удалено
// теперь arr = ["Я", undefined, "домой"];
alert( arr[1] ); // undefined


//---------------------------splice---------------------------
//Начнём с удаления:
var arr = ["Я", "изучаю", "JavaScript"];
arr.splice(1, 1); // начиная с позиции 1, удалить 1 элемент
alert( arr ); //  осталось ["Я", "JavaScript"]

//удалим 3 элемента и вставим другие на их место:
var arr = ["Я", "сейчас", "изучаю", "JavaScript"];
// удалить 3 первых элемента и добавить другие вместо них
arr.splice(0, 3, "Мы", "изучаем");
alert( arr ); // теперь ["Мы", "изучаем", "JavaScript"]

//splice возвращает массив из удаленных элементов:
var arr = ["Я", "сейчас", "изучаю", "JavaScript"];
// удалить 2 первых элемента
var removed = arr.splice(0, 2);
alert( removed ); // "Я", "сейчас" <-- array of removed elements

//splice также может вставлять элементы без удаления, для этого достаточно установить deleteCount в 0:
var arr = ["Я", "изучаю", "JavaScript"];
// с позиции 2, удалить 0, вставить "сложный", "язык"
arr.splice(2, 0, "сложный", "язык");
alert( arr ); // "Я", "изучаю", "сложный", "язык", "JavaScript"

//Допускается использование отрицательного номера позиции, которая в этом случае отсчитывается с конца:
var arr = [1, 2, 5];
// начиная с позиции индексом -1 (перед последним элементом)
// удалить 0 элементов,
// затем вставить числа 3 и 4
arr.splice(-1, 0, 3, 4);
alert( arr ); // результат: 1,2,3,4,5


//---------------------------slice---------------------------
var arr = ["Почему", "надо", "учить", "JavaScript"];
var arr2 = arr.slice(1, 3); // элементы 1, 2 (не включая 3)
alert( arr2 ); // надо, учить

//Если не указать end – копирование будет до конца массива:
var arr = ["Почему", "надо", "учить", "JavaScript"];
alert( arr.slice(1) ); // взять все элементы, начиная с номера 1

//Можно использовать отрицательные индексы, они отсчитываются с конца:
var arr2 = arr.slice(-2); // копировать от 2-го элемента с конца и дальше

//Если вообще не указать аргументов – скопируется весь массив:
var fullCopy = arr.slice();


//---------------------------sort()---------------------------
var arr = [ 1, 2, 15 ];
arr.sort();
//по умолчанию sort сортирует, преобразуя элементы к строке:
alert( arr );  // 1, 15, 2

//укажем эту функцию явно, отсортируем элементы массива как числа:
function compareNumeric(a, b) {
    if (a > b) return 1;
    if (a < b) return -1;
}
var arr = [ 15, 1, 2 ];
arr.sort(compareNumeric);
alert(arr);  // 1, 2, 15


//------------------------Object.keys()------------------------
var user = {
    name: "Петя",
    age: 30
};
var keys = Object.keys(user);// возвращает массив свойств объекта
alert( keys ); // name, age
for(var i = 0; i < keys.length; i++){
    console.log(user[keys[i]]);
}


//---------------------перебирающие методы-----------------------

//forEach
//arr.forEach(callback[, thisArg])
//для каждого элемента массива вызывает функцию callback.
var arr = ["Яблоко", "Апельсин", "Груша"];
arr.forEach(function(item, i, arr) {
    alert( i + ": " + item + " (массив:" + arr + ")" );
    //0: Яблоко (массив:Яблоко,Апельсин,Груша)
    //1: Апельсин (массив:Яблоко,Апельсин,Груша)
    //2: Груша (массив:Яблоко,Апельсин,Груша)
});

//filter
//arr.filter(callback[, thisArg])
//Он создаёт новый массив,
// в который войдут только те элементы arr,
// для которых вызов callback(item, i, arr) возвратит true.
var arr = [1, -1, 2, -2, 3];
var positiveArr = arr.filter(function(number) {
    return number > 0;
});
alert( positiveArr ); // 1,2,3

//map
//Метод «arr.map(callback[, thisArg])» используется для трансформации массива.
//Он создаёт новый массив, который будет состоять из результатов вызова callback(item, i, arr) для каждого элемента arr.
var names = ['HTML', 'CSS', 'JavaScript'];
var nameLengths = names.map(function(name) {
    return name.length;
});
// получили массив с длинами
alert(nameLengths); // 4,3,10
alert(names); //HTML,CSS,JavaScript

//every/some
//Эти методы используются для проверки массива.
var arr = [1, -1, 2, -2, 3];
function isPositive(number) {
    return number > 0;
}
alert( arr.every(isPositive) ); // false, не все положительные
alert( arr.some(isPositive) ); // true, есть хоть одно положительное


//reduce/reduceRight
//Аргументы функции callback(previousValue, currentItem, index, arr):
//  previousValue – последний результат вызова функции, он же «промежуточный результат».
//  currentItem – текущий элемент массива, элементы перебираются по очереди слева-направо.
//  index – номер текущего элемента.
//  arr – обрабатываемый массив.
//Кроме callback, методу можно передать «начальное значение» – аргумент initialValue
//Если он есть, то на первом вызове значение previousValue будет равно initialValue,
// а если у reduce нет второго аргумента,
// то оно равно первому элементу массива, а перебор начинается со второго.

//Например, в качестве «свёртки» мы хотим получить сумму всех элементов массива.
var arr = [1, 2, 3, 4, 5];
// для каждого элемента массива запустить функцию,
// промежуточный результат передавать первым аргументом далее
var result = arr.reduce(function(sum, current) {
    return sum + current;
});
alert( result ); // 15

//Метод arr.reduceRight работает аналогично, но идёт по массиву справа-налево.

//Пример создания редюсом объекта
var something = '{"name": "Вася", "surname": "Петров","age": 35, "isAdmin": false}';
let arr = something.split(/,|:/);
let obj = arr.reduce((com, cur, i)=>{
    com[cur] = i;
    return com;
}, {});
console.log(obj);
//{
//"{\"name\"": 0
//" \"Вася\"": 1
//" \"surname\"": 2
//" \"Петров\"": 3
//"\"age\"": 4
//" 35": 5
//" \"isAdmin\"": 6
//" false}": 7
//}

//--------------------Псевдомассив аргументов "arguments"----------------------
function sayHi() {
    for (var i = 0; i < arguments.length; i++) {
        alert( "Привет, " + arguments[i] );
    }
}
sayHi("Винни", "Пятачок"); // 'Привет, Винни', 'Привет, Пятачок'


//Пример использования arguments
// copy(dst, src1, src2…)
//Копирует свойства из объектов src1, src2,... в объект dst. Возвращает получившийся объект.
function copy(dst) {
    // остальные аргументы остаются безымянными
    for (var i = 1; i < arguments.length; i++) {
        var arg = arguments[i];
        for (var key in arg) {
            dst[key] = arg[key];
        }
    }
    return dst;
}

var vasya = {
    age: 21,
    name: 'Вася',
    surname: 'Петров'
};
var user = {
    isAdmin: false,
    isEmailConfirmed: true
};

var student = {
    university: 'My university'
};

// добавить к vasya свойства из user и student
copy(vasya, user, student);

alert(vasya.isAdmin); // false
alert(vasya.university); // My university











//---------------------------------------------------Дата и Время----------------------------------------------

// new Date()
// Создает объект Date с текущей датой и временем:
var now = new Date();
alert(now);

// new Date(datestring)
// 24 часа после 01.01.1970 GMT+0:
var Jan02_1970 = new Date(3600 * 24 * 1000);
alert(Jan02_1970);

//new Date(year, month, date, hours, minutes, seconds, ms)
var d = new Date(2011, 0, 1, 0, 0, 0, 0); // // 1 января 2011, 00:00:00
var d2 = new Date(2011, 0, 1); // то же самое, часы/секунды по умолчанию равны 0
alert(d);


//Для доступа к компонентам даты-времени объекта Date используются следующие методы:
//
// getFullYear()//Получить год (из 4 цифр)
//
// getMonth()//Получить месяц, от 0 до 11.
//
// getDate()//Получить число месяца, от 1 до 31.
//
// getHours(), getMinutes(), getSeconds(), getMilliseconds()//Получить соответствующие компоненты.
//
// getDay()//получить день недели (числом от 0(воскресенье) до 6(суббота))
//
// getTime()//Возвращает число миллисекунд, прошедших с 1 января 1970 года GMT+0,
// то есть того же вида, который используется в конструкторе new Date(milliseconds).
//
// getTimezoneOffset()//Возвращает разницу между местным и UTC-временем, в минутах.

// Следующие методы позволяют устанавливать компоненты даты и времени:
//
// setFullYear(year [, month, date])
// setMonth(month [, date])
// setDate(date)
// setHours(hour [, min, sec, ms])
// setMinutes(min [, sec, ms])
// setSeconds(sec [, ms])
// setMilliseconds(ms)
// setTime(milliseconds) (устанавливает всю дату по миллисекундам с 01.01.1970 UTC)


//Неправильные компоненты даты автоматически распределяются по остальным.
//Например, нужно увеличить на 2 дня дату «28 февраля 2011». Может быть так, что это будет 2 марта,
// а может быть и 1 марта, если год високосный.
// Но нам обо всем этом думать не нужно. Просто прибавляем два дня. Остальное сделает Date:
var d = new Date(2011, 1, 28);
d.setDate(d.getDate() + 2);
alert(d); // 2 марта, 2011

var d = new Date();
d.setSeconds(d.getSeconds() + 70);
alert(d); // выведет корректную дату

//Можно установить и нулевые, и даже отрицательные компоненты. Например:
var d = new Date;
d.setDate(1); // поставить первое число месяца
alert(d);
d.setDate(0); // нулевого числа нет, будет последнее число предыдущего месяца
alert(d);


//даты можно вычитать, результат вычитания объектов Date – их временная разница, в миллисекундах.
//Это используют для измерения времени:
var start = new Date; // засекли время
// что-то сделать
for (var i = 0; i < 100000; i++) {
    var doSomething = i * i * i;
}
var end = new Date; // конец измерения
alert("Цикл занял " + (end - start) + " ms");


//Для измерения с одновременным выводом результатов в консоли есть методы:
//console.time(метка) – включить внутренний хронометр браузера с меткой.
//console.timeEnd(метка) – выключить внутренний хронометр браузера с меткой и вывести результат.
//Пример: сравниваем скорость FOR IN и FOR
var arr = [];
for (var i = 0; i < 1000; i++) {
    arr[i] = i+i;//заполняем массив
}
function walkIn(arr) {
    for (var key in arr) arr[key]++;
}
function walkLength(arr) {
    for (var i = 0; i < arr.length; i++) arr[i]++;
}
//прогоняет переданную ф-ю 10000раз
function bench(f) {
    for (var i = 0; i < 10000; i++) f(arr);
}

console.time("All Benchmarks");

console.time("FOR-IN");
bench(walkIn);
console.timeEnd("FOR-IN");

console.time("FOR");
bench(walkLength);
console.timeEnd("FOR");

console.timeEnd("All Benchmarks");


//--------------------Форматирование и вывод дат----------------------

//Пример с почти всеми параметрами даты и русским, затем английским (США) форматированием:
var date = new Date(2014, 11, 31, 12, 30, 0);
var options = {
    era: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    weekday: 'long',
    timezone: 'UTC',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric'
};
alert(date.toLocaleString("ru", options)); // среда, 31 декабря 2014 г. н.э. 12:30:00
alert(date.toLocaleString("en-US", options)); // Wednesday, December 31, 2014 Anno Domini 12:30:00 PM


//Пример 2
var date = new Date(2014, 11, 31, 12, 30, 0);
var options = {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
};
alert(date.toLocaleString("ru", options)); // 31 декабря 2014 г.
alert(date.toLocaleString("en-US", options)); // December 31, 2014


//Разбор строки, Date.parse
//Все современные браузеры, включая IE9+, понимают даты в упрощённом формате ISO 8601 Extended.
//
//    Этот формат выглядит так: YYYY-MM-DDTHH:mm:ss.sssZ, где:
//
//    YYYY-MM-DD – дата в формате год-месяц-день.
//    Обычный символ T используется как разделитель.
//    HH:mm:ss.sss – время: часы-минуты-секунды-миллисекунды.
//    Часть 'Z' обозначает временную зону – в формате +-hh:mm, либо символ Z, обозначающий UTC. По стандарту её можно не указывать, тогда UTC, но в Safari с этим ошибка, так что лучше указывать всегда.
//    Также возможны укороченные варианты, например YYYY-MM-DD или YYYY-MM или даже только YYYY.
//
//    Метод Date.parse(str) разбирает строку str в таком формате и возвращает соответствующее ей количество миллисекунд. Если это невозможно, Date.parse возвращает NaN.
//
//    Например:
var msUTC = Date.parse('2012-01-26T13:51:50.417Z'); // зона UTC
alert( msUTC ); // 1327571510417 (число миллисекунд)