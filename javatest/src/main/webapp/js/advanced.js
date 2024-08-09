
//-----------------------------------------------Замыкания, функции изнутри----------------------------------------------
//  Пример
//  Посмотрим пример, чтобы лучше понимать, как это работает:
function sayHi(name) {
    var phrase = "Привет, " + name;
    alert( phrase );
}
sayHi('Вася');

//  При вызове функции:
//  До выполнения первой строчки её кода, на стадии инициализации,
// интерпретатор создает пустой объект LexicalEnvironment и заполняет его.
//  В данном случае туда попадает аргумент name и единственная переменная phrase:
function sayHi(name) {
    // LexicalEnvironment = { name: 'Вася', phrase: undefined }
    var phrase = "Привет, " + name;
    alert( phrase );
}
sayHi('Вася');

//  Функция выполняется.
//
//  Во время выполнения происходит присвоение локальной переменной phrase,
// то есть, другими словами, присвоение свойству LexicalEnvironment.phrase нового значения:
function sayHi(name) {
    // LexicalEnvironment = { name: 'Вася', phrase: undefined }
    var phrase = "Привет, " + name;

    // LexicalEnvironment = { name: 'Вася', phrase: 'Привет, Вася'}
    alert( phrase );
}
sayHi('Вася');

//  В конце выполнения функции объект с переменными обычно выбрасывается и память очищается.
// В примерах выше так и происходит.
// Через некоторое время мы рассмотрим более сложные ситуации,
// при которых объект с переменными сохраняется и после завершения функции.




//Пример с веншними переменными
var userName = "Вася";
alert("window userName = "+userName); // "Вася"

var b = true;
while (b){
    b = false;
    var userName="Петя"; //перезаписывает внешнюю переменную
    alert("while userName = "+userName); // "Петя"
}

function sayHi() {
    var userName = "Коля"; //не перезаписывает внешнюю переменную, сохраняет в свой LexicalEnvironment
    alert("function userName = "+userName);
}
sayHi(); // "Коля"

alert("В итоге: "+userName);//Петя!!!


//Пример с веншними переменными 2
var value = "Внешняя переменная!";
function f() {
    if (1) {
        value = true;
    } else {
        var value = false;//хоть и недостижимо, но внутренняя переменная создалась
    }
    alert( value );
}
f();//true - выводим имено внутреннюю переменную
alert(value);//"Внешняя переменная!"


//пример со счётчиком (возвращаем вложенную функцию counter(), которая берет счетчик из замыкания):
function makeCounter() {
    var currentCount = 1;
    return function counter() {
        return currentCount++;
    };
}
var counter = makeCounter();
console.log("----counter----");
console.log(counter()); // 1
console.log(counter()); // 2
console.log("----counter2----");
var counter2 = makeCounter();
console.log(counter2()); // 1
console.log(counter2()); // 2
console.log(counter2()); // 3
console.log(counter2()); // 4
console.log("----counter----");
console.log(counter()); // 3



//Чтобы добавить счётчику возможностей – перейдём с функции на полноценный объект:
function makeCounter() {
    var currentCount = 1;

    return { // возвратим объект вместо функции
        getNext: function() {
            return currentCount++;
        },

        set: function(value) {
            currentCount = value;
        },

        reset: function() {
            currentCount = 1;
        }
    };
}
var counter = makeCounter();
console.log("----counter.getNext()----");
console.log(counter.getNext()); // 1
console.log(counter.getNext()); // 2
console.log("----counter.set(5)----");
counter.set(5);
console.log("----counter.getNext()----");
console.log(counter.getNext()); // 5
console.log(counter.getNext()); // 6
console.log("----counter.reset()----");
counter.reset();
console.log("----counter.getNext()----");
console.log(counter.getNext()); // 1
console.log(counter.getNext()); // 2



//Объект счётчика + функция
function makeCounter() {
    var currentCount = 1;
    // возвращаемся к функции
    function counter() {
        return currentCount++;
    }
    // ...и добавляем ей методы!
    counter.set = function(value) {
        currentCount = value;
    };
    counter.reset = function() {
        currentCount = 1;
    };
    return counter;
}
var counter = makeCounter();
console.log("----counter.getNext()----");
console.log(counter()); // 1
console.log(counter()); // 2
console.log("----counter.set(5)----");
counter.set(5);
console.log("----counter.getNext()----");
console.log(counter()); // 5
console.log(counter()); // 6
console.log("----counter.reset()----");
counter.reset();
console.log("----counter.getNext()----");
console.log(counter()); // 1
console.log(counter()); // 2



//реализовать строковый буфер на функциях в JavaScript
function makeBuffer() {
    var buffer = '';
    function getBuffer(string) {
        if(!string){
            return buffer;
        }
        buffer+=string;
    }
    getBuffer.clear = function () {
        buffer = '';
    };
    return getBuffer;
}
var buffer = makeBuffer();
// добавить значения к буферу
buffer('Замыкания');
buffer(' Использовать');
buffer(' Нужно!');
// получить текущее значение
alert( buffer() ); // Замыкания Использовать Нужно!
buffer.clear();
alert( buffer() ); // ''


//функция, созданная new Function, игнорирует внешнюю переменную a и выводит глобальную вместо неё:
var a = 1;
function getFunc() {
    var a = 2;
    var func = new Function('', 'alert(a)');
    return func;
}
getFunc()(); // 1, из window


//-----------------Очень веселый пример замыкания! -----------
//Сумма через замыкание
function sum(a) {
    return function(b) {
        return a+b;
    };
}
console.log(sum(5)(7)); // 12
console.log(sum(3)(7)); // 10
console.log(sum(8)(-4)); // 4


//-------Свойства функции(статичные переменные)----------------

//перепишем пример со счётчиком (возвращаем вложенную функцию counter(),
// у которой задан счетчик в качестве статической переменной):
function makeCounter() {
    function counter() {
        return counter.currentCount++;
    };
    counter.currentCount = 1;
    return counter;
}
var counter = makeCounter();
console.log("----counter----");
console.log(counter()); // 1
console.log(counter()); // 2
console.log("----counter2----");
var counter2 = makeCounter();
console.log(counter2()); // 1
console.log(counter2()); // 2
console.log(counter2()); // 3
console.log(counter2()); // 4
console.log("----counter----");
console.log(counter()); // 3
console.log("----counter - меняем счетчик из вне----");
counter.currentCount = 10;
console.log(counter()); // 10
console.log(counter()); // 11
console.log(counter()); // 12






//-----------------------------------------------Приём проектирования «Модуль»-----------------------------------------------

//схема устройства библиотеки lodash
;(function() {
    // lodash - основная функция для библиотеки
    function lodash(value) {
        // ...
    }

    // код функции size, пока что доступен только внутри
    function size(collection) {
        return Object.keys(collection).length;
    }

    // присвоим в lodash size и другие функции, которые нужно вынести из модуля
    lodash.size = size;
    // lodash.defaults = ...
    // lodash.cloneDeep = ...

    // "экспортировать" lodash наружу из модуля
    window._ = lodash; // в оригинальном коде здесь сложнее, но смысл тот же
})();
alert(_.size(["Яблоко", "Апельсин", "Груша"]));


//вариант модуля - через return
var _ = (function() {
    // lodash - основная функция для библиотеки
    function lodash(value) {
        // ...
    }

    // код функции size, пока что доступен только внутри
    function size(collection) {
        return Object.keys(collection).length;
    }

    // присвоим в lodash size и другие функции, которые нужно вынести из модуля
    lodash.size = size;
    // lodash.defaults = ...
    // lodash.cloneDeep = ...

    return lodash;
})();
alert(_.size(["Яблоко", "Апельсин", "Груша"]));






//-----------------------------------------------Методы объектов, this-----------------------------------------------
//Свойства-функции называют «методами» объектов.
// Их можно добавлять и удалять в любой момент, в том числе и явным присваиванием:
var user = {
    name: 'Василий'
};
user.sayHi = function () { // присвоили метод после создания объекта
    alert('Привет!');
};
// Вызов метода:
user.sayHi();


//Доступ к объекту через this
var user = {
    name: 'Василий',

    sayHi: function () {
        alert(this.name);
    }
};
user.sayHi(); // sayHi в контексте user


//this в ф-ии которая изначально не была методом объекта
//Если одну и ту же функцию запускать в контексте разных объектов, она будет получать разный this:
var user = { firstName: "Вася" };
var admin = { firstName: "Админ" };

function func() {
    alert( this.firstName );
}
user.f = func;
admin.g = func;
// this равен объекту перед точкой:
user.f(); // Вася
admin.g(); // Админ
admin['g'](); // Админ (не важно, доступ к объекту через точку или квадратные скобки)


//---------------Очень веселый пример this! -----------
var arr = ["a", "b"];
arr.push(function() {
    alert( this );
});
arr[2](); // a,b,function() {alert( this );} - то есть вывелся весь массив


//Случаи, когда метод оказался просто функцией
"use strict";//в старом режиме строчки (3) и (4) будут брать в this - window
var obj, method;
obj = {
    go: function () {
        alert(this);
    }
};
obj.go();               // (1) object
(obj.go)();             // (2) object

(method = obj.go)();    // (3) undefined -
// здесь мы сначала сохраняем функцию в переменную method,
// затем вызываем уже method(), а он не является частью объекта, поэтому this = undefined

(obj.go || obj.stop)(); // (4) undefined -
// операция получения свойства obj.go возвращает значение особого типа Reference Type,
// оператор || делает из Reference Type обычную функцию.



//--------toString--------
//Если в объекте присутствует метод toString,
// который возвращает примитив, то он используется для преобразования.
var user = {
    firstName: 'Василий',
    toString: function () {
        return 'Пользователь ' + this.firstName;
    }
};

alert(user);  // Пользователь Василий


//--------valueOf--------
//Для численного преобразования объекта используется метод valueOf, а если его нет – то toString:
var room = {
    number: 777,
    valueOf: function () {
        return this.number;
    },
    toString: function () {
        return this.number;
    }
};
alert(+room);  // 777, вызвался valueOf
delete room.valueOf; // valueOf удалён
alert(+room);  // 777, вызвался toString




//---------------------------------------------Создание объектов через "new"--------------------------------------------

//--------------------функции-конструкторы--------------------

// Если нужно создать много однотипных объектов, то используют «функции-конструкторы»,
// запуская их при помощи специального оператора new (ведь в JS функция и есть объект).
// Любая функция может быть вызвана с new,
// при этом она получает новый пустой объект в качестве this, в который она добавляет свойства.
// Чтобы выделить функции, задуманные как конструкторы, их называют с большой буквы: Animal
function Animal(name) {
    this.name = name;
    this.canWalk = true;
    this.toString = function () {
        return "name: " +this.name+", canWalk: "+this.canWalk;
    };

    this.getThis = function () {
        return "this: {"+this+"}";//печатаем объект
    }
}
var animal = new Animal("ёжик");//по сути получается что функция становится методом самой себя
console.log(animal);//name: ёжик, canWalk: true
console.log(animal.getThis());//this: name: ёжик, canWalk: true

//сравнение с this внутри методов
//мы можем сделать эту функцию методом другого объекта и получится тоже самое,
//но создать сможем только один раз так:
var obj = {
    anim: Animal
};
obj.anim("черепаха");
console.log("---------");
console.log(obj);//name: черепаха, canWalk: true
console.log(obj.getThis());//this: name: черепаха, canWalk: true


//--------Если функция не возвращает свой объект, то её результатом будет this
// (вернуть примитив нельзя, все равно будет this):
function A() {
    this.toString = function () {
        return "A";
    }
}
var a = new A;
alert(a); // "A" - несмотря на то, что return в ф-ии А нет, печатается все равно this



//------------методы в конструкторе(выше уже применял для toString или getThis)

function User(name) {
    this.name = name;
    this.sayHi = function() {
        alert( "Моё имя: " + this.name );
    };
}
var ivan = new User("Иван");
ivan.sayHi(); // Моё имя: Иван


//------------Локальные переменные
//В функции-конструкторе бывает удобно объявить вспомогательные локальные переменные и
// вложенные функции, которые будут видны только внутри:

function User(firstName, lastName) {
    // вспомогательная переменная
    var phrase = "Привет";
    //  вспомогательная вложенная функция
    function getFullName() {
        return firstName + " " + lastName;
    }
    this.sayHi = function() {
        alert( phrase + ", " + getFullName() ); // использование
    };
}
var vasya = new User("Вася", "Петров");
vasya.sayHi(); // Привет, Вася Петров


//конструкторы возвращают один внешний объект
var obj = {};

function A() { return obj; }
function B() { return obj; }

var a = new A;
var b = new B;

alert( a === b ); // true



//Калькулятор
function Calculator() {
    this.read = function () {
        this.a = Number(prompt("Введите а",0));
        this.b =  Number(prompt("Введите b",0));
    };
    this.sum = function () {
        return this.a+this.b;
    };
    this.mul = function () {
        return this.a*this.b;
    }
}
var calculator = new Calculator();
calculator.read();
alert( "Сумма=" + calculator.sum() );
alert( "Произведение=" + calculator.mul() );

//Аккумулятор
function Accumulator(startingValue) {
    this.value = startingValue;
    this.read = function () {
        this.value += Number(prompt("Введите значение",0));
    };
}
var accumulator = new Accumulator(1); // начальное значение 1
accumulator.read(); // прибавит ввод prompt к текущему значению
accumulator.read(); // прибавит ввод prompt к текущему значению
alert( accumulator.value ); // выведет текущее значение


//Калькулятор с возможностью добавления новых методов вычисления
//(только 2 числа и операция между ними, разделенные пробелом):
function Calculator() {

    var methods = {
        "-": function(a, b) {
            return a - b;
        },
        "+": function(a, b) {
            return a + b;
        }
    };

    this.calculate = function(str) {
        var split = str.split(' ');
        var a = +split[0];
        var op = split[1];
        var b = +split[2];

        if (!methods[op] || isNaN(a) || isNaN(b)) {
            return NaN;
        }
        return methods[op](a, b);
    };

    this.addMethod = function(name, func) {
        methods[name] = func;
    };
}

var calc = new Calculator;
calc.addMethod("*", function(a, b) {return a * b;});
calc.addMethod("/", function(a, b) {return a / b;});
calc.addMethod("**", function(a, b) {return Math.pow(a, b);});

alert( calc.calculate("2 - 3")); // -1
alert( calc.calculate("2 + 3")); // 5
alert( calc.calculate("2 * 3")); // 6
alert( calc.calculate("2 ** 3")); // 8












//-----------------------------------------Дескрипторы, геттеры и сеттеры свойств------------------------------------------


//-------------------------Дескриптор - Object.defineProperty------------

//Object.defineProperty(obj, prop, descriptor)
//Аргументы:
//
//obj
//Объект, в котором объявляется свойство.
//prop
//Имя свойства, которое нужно объявить или модифицировать.
//descriptor
//Дескриптор – объект, который описывает поведение свойства.
//В нём могут быть следующие поля:
//
//value – значение свойства, по умолчанию undefined
//writable – значение свойства можно менять, если true. По умолчанию false.
//configurable – если true, то свойство можно удалять,
//  а также менять его в дальнейшем при помощи новых вызовов defineProperty. По умолчанию false.
//enumerable – если true, то свойство просматривается в цикле for..in и методе Object.keys(). По умолчанию false.
//get – функция, которая возвращает значение свойства. По умолчанию undefined.
//set – функция, которая записывает значение свойства. По умолчанию undefined.

//Примеры:


//Два таких вызова работают одинаково:
var user = {};
// 1. простое присваивание
user.name = "Вася";
// 2. указание значения через дескриптор
Object.defineProperty(user, "name", {value: "Вася", configurable: true, writable: true, enumerable: true});


//Для того, чтобы сделать свойство неизменяемым, изменим его флаги writable и configurable:
"use strict";
var user = {};
Object.defineProperty(user, "name", {
    value: "Вася",
    writable: false, // запретить присвоение "user.name="
    configurable: false // запретить удаление "delete user.name"
});
// Теперь попытаемся изменить это свойство.
// в strict mode присвоение "user.name=" вызовет ошибку
//без strict просто молча не присвоится
user.name = "Петя";
alert(user.name);//"Вася"

//Свойство, скрытое для for…in
var user = {
    name: "Вася",
    toString: function() { return this.name; }
};
// помечаем toString как не подлежащий перебору в for..in
Object.defineProperty(user, "toString", {enumerable: false});
for(var key in user) alert(key);  // name



//--------------геттеры и сеттеры--------------

//---геттеры и сеттеры через дескриптор
var user = {
    firstName: "Вася",
    surname: "Петров"
};
Object.defineProperty(user, "fullName", {

    get: function() {
        return this.firstName + ' ' + this.surname;
    },

    set: function(value) {
        var split = value.split(' ');
        this.firstName = split[0];
        this.surname = split[1];
    }
});
user.fullName = "Петя Иванов";
alert( user.firstName ); // Петя
alert( user.surname ); // Иванов


//---в литералах (внутри {...}):
var user = {
    firstName: "Вася",
    surname: "Петров",

    get fullName() {
        return this.firstName + ' ' + this.surname;
    },

    set fullName(value) {
        var split = value.split(' ');
        this.firstName = split[0];
        this.surname = split[1];
    }
};
alert( user.fullName ); // Вася Петров (из геттера)
user.fullName = "Петя Иванов";
alert( user.firstName ); // Петя  (поставил сеттер)
alert( user.surname ); // Иванов (поставил сеттер)


//---а вот вариант если объект создается через конструктор:
function User(name, birthday) {
    this.name = name;
    this.birthday = birthday;

    // age будет высчитывать возраст по birthday
    Object.defineProperty(this, "age", {
        get: function() {
            var today = new Date();
            var yearDelta = today.getFullYear() - this.birthday.getFullYear();

            if (today.getMonth() > this.birthday.getMonth() ||
                (today.getMonth() === this.birthday.getMonth() && today.getDate() >= this.birthday.getDate())) {
                return yearDelta;
            }

            return yearDelta - 1;
        }
    });
}
var pete = new User("Петя", new Date(1987, 6, 1));
alert( pete.birthday ); // и дата рождения доступна
alert( pete.age );      // и возраст


//--- пример геттеров/сеттеров с использованием Object.defineProperties - задаем несколько свойств сразу
function User(fullName) {
    this.fullName = fullName;

    Object.defineProperties(this, {
        firstName: {
            get: function() {
                return this.fullName.split(' ')[0];
            },
            set: function(newFirstName) {
                this.fullName = newFirstName + ' ' + this.lastName;
            }
        },
        lastName: {
            get: function() {
                return this.fullName.split(' ')[1];
            },
            set: function(newLastName) {
                this.fullName = this.firstName + ' ' + newLastName;
            }
        }
    });
}
var vasya = new User("Василий Попкин");

// чтение firstName/lastName
alert( vasya.firstName ); // Василий
alert( vasya.lastName ); // Попкин

// запись firstName/lastName
vasya.firstName = 'Аркадий';
vasya.lastName = 'Сидоров';

alert( vasya.fullName ); // Аркадий Сидоров












//-----------------------------------------Статические и фабричные методы------------------------------------------

//-------Статические свойства и методы-----
//Обратим внимание на использование this в примере ниже.
// Несмотря на то, что переменная и метод – статические, он всё ещё полезен.
// В строке (1) он равен Article.
function Article() {
    Article.count++;
}
Article.count = 0; // статическое свойство-переменная
Article.DEFAULT_FORMAT = "html"; // статическое свойство-константа
Article.showCount = function() { //статический метод
    alert( this.count ); // (1)
}

// использование
new Article();
new Article();
Article.showCount(); // (2)
alert(Article.DEFAULT_FORMAT);//"html"


//-----------фабричные методы-----
function User() {
    this.sayHi = function() {
        alert(this.name)
    };
}
User.createAnonymous = function() {
    var user = new User;
    user.name = 'Аноним';
    return user;
};
User.createFromData = function(userData) {
    var user = new User;
    user.name = userData.name;
    user.age = userData.age;
    return user;
};

// Использование
var guest = User.createAnonymous();
guest.sayHi(); // Аноним

var knownUser = User.createFromData({
    name: 'Вася',
    age: 25
});
knownUser.sayHi(); // Вася












//-----------------------------------------Явное указание this: "call", "apply"------------------------------------------

//-----------------------call------------------

//call без аргументов
function showFullName() {
    alert( this.firstName + " " + this.lastName );
}
var user = {
    firstName: "Василий",
    lastName: "Петров"
};
// функция вызовется с this=user
showFullName.call(user) // "Василий Петров"


//call с аргументами
function showFullName(firstPart, lastPart) {
    alert( this[firstPart] + " " + this[lastPart] );
}
var user = {
    firstName: "Василий",
    surname: "Петров",
    patronym: "Иванович"
};
// f.call(контекст, аргумент1, аргумент2, ...)
showFullName.call(user, 'firstName', 'surname') // "Василий Петров"
showFullName.call(user, 'firstName', 'patronym') // "Василий Иванович"


//В JavaScript есть очень простой способ сделать из arguments настоящий массив. Для этого возьмём метод массива: slice.
//По стандарту вызов arr.slice(start, end) создаёт новый массив и копирует в него элементы массива arr от start до end.
// А если start и end не указаны, то копирует весь массив.
//Вызовем его в контексте arguments:
function printArgs() {
    // вызов arr.slice() скопирует все элементы из this в новый массив
    var args = [].slice.call(arguments);
    alert(args.join(', ')); // args - полноценный массив из аргументов
}
printArgs('Привет', 'мой', 'мир'); // Привет, мой, мир


//------2 варианта создать ф-ию суммирующую свои аргументы через call:

//вар 1
function sumArgs() {
    var args = [].slice.call(arguments);
    return args.reduce(function (a, b) {
        return a+b;
    })
}
alert( sumArgs(1, 2, 3) ); // 6

//вар 2
function sumArgs() {
    // запустим reduce из массива напрямую
    return [].reduce.call(arguments, function(a, b) {
        return a + b;
    });
}
alert( sumArgs(4, 5, 6) ); // 15



//-----------------------apply------------------
function showFullName(firstPart, lastPart) {
    alert( this[firstPart] + " " + this[lastPart] );
}
var user = {
    firstName: "Василий",
    surname: "Петров",
    patronym: "Иванович"
};
var arr = ['firstName', 'surname'];
showFullName.apply(user, arr);//тоже что и call, но аргументы массивом


//функция applyAll(func, arg1, arg2...),
// которая получает функцию func и произвольное количество аргументов.
//
//Она должна вызвать func(arg1, arg2...),
// то есть передать в func все аргументы, начиная со второго, и возвратить результат.
function applyAll(func) {
    return func.apply(this, [].slice.call(arguments, 1));
}
function sum() {
    return [].reduce.call(arguments, function(a, b) {
        return a + b;
    });
}
function mul() {
    return [].reduce.call(arguments, function(a, b) {
        return a * b;
    });
}
alert( applyAll(sum, 1, 2, 3) ); // 6
alert( applyAll(mul, 2, 3, 4) ); // 24
alert( applyAll(Math.max, 2, -2, 3) ); // 3
alert( applyAll(Math.min, 2, -2, 3) ); // -2












//-----------------------------------------Привязка контекста и карринг: "bind"------------------------------------------


//Пример ПОТЕРЯННОГО КОНТЕКСТА:
//setTimeout получил функцию user.sayHi, но не её контекст
var user = {
    firstName: "Вася",
    sayHi: function() {
        alert( this.firstName );
    }
};
setTimeout(user.sayHi, 1000); // undefined (не Вася!)


//bind:
//синтаксис: var wrapper = func.bind(context[, arg1, arg2...])
//func - Произвольная функция
//context - Контекст, который привязывается к func
//arg1, arg2, … - Если указаны аргументы arg1, arg2...
// – они будут прибавлены к каждому вызову новой функции,
// причем встанут перед теми, которые указаны при вызове.

//Пример с bind 1:
var user = {
    firstName: "Вася",
    sayHi: function() {
        alert( this.firstName );
    }
};
setTimeout(user.sayHi.bind(user), 1000);

//Пример с bind 2 (с аргументами):
var user = {
    firstName: "Вася",
    sayHi: function(arg1,arg2) {
        alert( "arg1=" + arg1 +", arg2=" + arg2 + ", firstName=" + this.firstName );
    }
};
var f = user.sayHi.bind(user,"АРГ1");
f("АРГ2");//arg1=АРГ1, arg2=АРГ2, firstName=Вася


//-----Повторный bind
function f() {
    alert( this );
}
var user = {
    g: f.bind("Hello") //user уже не привяжется, f останется связана с "Hello"
}
user.g();//Hello


//ф-ия вопроса с исправлением потерянного контекста
"use strict";
function ask(question, answer, ok, fail) {
    var result = prompt(question, '');
    if (result.toLowerCase() == answer.toLowerCase()) ok();
    else fail();
}
var user = {
    login: 'Василий',
    password: '12345',
    loginOk: function() {
        alert( this.login + ' вошёл в сайт' );
    },
    loginFail: function() {
        alert( this.login + ': ошибка входа' );
    },
    checkPassword: function() {
        //ask.bind(this,"Ваш пароль?", this.password, this.loginOk, this.loginFail);//!!!!!!так было
        ask("Ваш пароль?", this.password, this.loginOk.bind(this), this.loginFail.bind(this));//!!!!!!так стало
    }
};
var vasya = user;
user = null;
vasya.checkPassword();













//-----------------------------------------Функции-обёртки, декораторы------------------------------------------

//-------------Декоратор-таймер-------------
var timers = {};
// прибавит время выполнения f к таймеру timers[timer]
function timingDecorator(f, timer) {
    console.log("timingDecorator->this = "+this);//window/undefined
    return function() {//это ф-ия станет ф-ей f() и arguments переданные в f() будем использовать ниже
        var start = performance.now();

        // arguments - переданные в function при вызове той переменной в которую эта function будет сохранена,
        // у той же переменной и возьмется this (см. строку "user.fun2 = timingDecorator(user.fun2, "fun2");")
        console.log("timingDecorator->this->function = "+this);
        var result = f.apply(this, arguments);//форвардинг вызова

        if (!timers[timer]) timers[timer] = 0;
        timers[timer] += performance.now() - start;

        return result;
    }
}

// функция может быть произвольной, например такой:
function fibonacci(n) {
    return (n > 2) ? fibonacci(n - 1) + fibonacci(n - 2) : 1;
}
//или, например, методом объекта:
var user =
    {
        fun2: function() {
            console.log("user->fun2->this =" + this);
        },
        toString: function () {
            return "user";
        }
    };

// использование 1: завернём fibonacci в декоратор
console.log("-------fibonacci-------");
fibonacci = timingDecorator(fibonacci, "fibo");
// неоднократные вызовы...
alert(fibonacci(10));
// в любой момент можно получить общее количество времени на вызовы
alert('times fibo = ' + timers["fibo"] + 'мс');

// использование 2: завернём fun2 в декоратор
console.log("-------fun2-------");
user.fun2 = timingDecorator(user.fun2, "fun2");
user.fun2();
alert('times fun2 = ' + timers["fun2"] + 'мс');



//-------------Декоратор проверяльщик типов (+exception)-------------

// вспомогательная функция для проверки на число
function checkNumber(value) {
    return typeof value == 'number';
}
// декоратор, проверяющий типы для f
// второй аргумент checks - массив с функциями для проверки
function typeCheck(f, checks) {
    return function() {
        for (var i = 0; i < arguments.length; i++) {
            if (!checks[i](arguments[i])) {
                throw new TypeException(arguments[i], i);
            }
        }
        return f.apply(this, arguments);//здесь вновь форвардинг вызова
    }
}
function TypeException(value, position) {
    this.value = value;
    this.name = 'TypeException';
    this.position = position;
    this.message = "Некорректный тип аргумента номер ";
    this.toString = function () {
        return this.value + " : " + this.name + " : " + this.message + this.position;
    }
}
function sum(a, b) {
    return a + b;
}
// обернём в декоратор для проверки
sum = typeCheck(sum, [checkNumber, checkNumber]); // оба аргумента - числа
// пользуемся функцией как обычно
alert( sum(1, 2) ); // 3, все хорошо
// а вот так - будет ошибка
sum(true, null); // uncaught exception: true : TypeException : Некорректный тип аргумента номер 0
// sum(1, ["array", "in", "sum?!?"]); // uncaught exception: array,in,sum?!? : TypeException : Некорректный тип аргумента номер 1
// sum(1, {name:"Dima"}); // uncaught exception: [object Object] : TypeException : Некорректный тип аргумента номер 1

//декораторы можно накладывать друг на друга
//Например, добавим еще предыдущий timingDecorator:
sum = timingDecorator(sum, "sum");
//теперь есть и проверка типов и учет веремени выполнения
alert('times sum = ' + timers.sum + 'мс');



//------Декотратор логгирования аргументов ф-ии--------
function makeLogging(f, log) {
    return function () {
        var args = [];
        //вызываю forEach с this=arguments,
        //что позволяет работать с arguments как с массивом
        [].forEach.call(arguments, function(element) {
            args.push(element);
        });
        log.push(args);
        //log.push([].slice.call(arguments)); //это еще более простое решение
        return f.apply(this, arguments);
    }
}
function work(a, b) {
    alert( a + b ); // work - произвольная функция
}
//использование
var log = [];
work = makeLogging(work, log);
work(1, 2); // 3
work(4, 5); // 9
for (var i = 0; i < log.length; i++) {
    var args = log[i]; // массив из аргументов i-го вызова
    alert( 'Лог:' + args.join() ); // "Лог:1,2", "Лог:4,5"
}



//------Кеширующий декоратор--------
//При первом вызове обертки с определенным аргументом – она вызывает f и запоминает ее результат.
//При втором и последующих вызовах с тем же аргументом возвращается запомненное значение.
function f(x) {
    return Math.random() * x; // random для удобства тестирования
}
function makeCaching(f) {
    var cache = {};
    return function(x) {
        if(!(x in cache)){
            cache[x] = f.call(this, x);
        }
        return cache[x];
    }
}

//использование:
f = makeCaching(f);
var a, b;
a = f(1);
b = f(1);
alert(a == b); // true (значение закешировано)
b = f(2);
alert(a == b); // false, другой аргумент => другое значение













//-----------------------------------------Типы данных: [[Class]], instanceof и утки------------------------------------------

//----------------[[Class]]
function getClass(obj) {
    return {}.toString.call(obj).slice(8, -1);
}
alert( getClass(new Date) ); // Date
alert( getClass([1, 2, 3]) ); // Array


//----------------instanceof
function User() {}
function Pet() {}
var u = new User();
var p = new Pet();
console.log("u instanceof User: "+(u instanceof User));
console.log("p instanceof Pet: "+(p instanceof Pet));


//------------------Пример полиморфной функции 1
//Пример полиморфной функции – sayHi(who), которая будет говорить «Привет» своему аргументу,
// причём если передан массив – то «Привет» каждому:
function sayHi(who) {
    if (who.forEach) {  // если есть forEach
        who.forEach(sayHi); // предполагаем, что он ведёт себя "как надо" - это и есть утиная типизация
    } else {
        alert( 'Привет, ' + who );
    }
}

// Вызов с примитивным аргументом
sayHi("Вася"); // Привет, Вася

// Вызов с массивом
sayHi(["Саша", "Петя"]); // Привет, Саша... Петя

// Вызов с вложенными массивами - тоже работает!
sayHi(["Саша", "Петя", ["Маша", "Юля"]]); // Привет Саша..Петя..Маша..Юля




//------------------Пример полиморфной функции 2
//
//Напишите функцию formatDate(date), которая возвращает дату в формате dd.mm.yy.
//Ее первый аргумент должен содержать дату в одном из видов:
//
//Как объект Date.
//Как строку, например yyyy-mm-dd или другую в стандартном формате даты.
//Как число секунд с 01.01.1970.
//Как массив [гггг, мм, дд], месяц начинается с нуля
function formatDate(date) {
    if (typeof date == 'number') {
        // перевести секунды в миллисекунды и преобразовать к Date
        date = new Date(date * 1000);
    } else if (typeof date == 'string') {
        // строка в стандартном формате автоматически будет разобрана в дату
        date = new Date(date);
    } else if (Array.isArray(date)) {
        date = new Date(date[0], date[1], date[2]);
    }
    var options1 = {day: 'numeric', month: 'numeric', year: '2-digit'};//вариант формата 1
    var options2 = {day: '2-digit', month: '2-digit', year: '2-digit'};//вариант формата 2 (в итоге дают одно и тоже)
    return date.toLocaleString("ru", options1);
}

alert(formatDate('2011-10-02')); // 02.10.11
alert(formatDate(1234567890)); // 14.02.09
alert(formatDate([2014, 0, 1])); // 01.01.14
alert(formatDate(new Date(2014, 0, 1))); // 01.01.14











//-----------------------------------------Формат JSON, метод toJSON------------------------------------------

//-----------JSON.parse----------
//Вызов JSON.parse(str) превратит строку с данными в формате JSON в JavaScript-объект/массив/значение.

//Синтаксис JSON
// Объекты в формате JSON похожи на обычные JavaScript-объекты,
// но отличаются от них более строгими требованиями к строкам – они должны быть именно в двойных кавычках.
var incorrectJSON = {
    name: "Вася",       // ошибка: ключ name без кавычек!
    "surname": 'Петров',// ошибка: одинарные кавычки у значения 'Петров'!
    "age": 35,           // .. а тут всё в порядке.
    "isAdmin": false    // и тут тоже всё ок
}
var user = JSON.parse(incorrectJSON);//SyntaxError: JSON.parse: unexpected character

//Пример 1
var str = '{ "name": "Вася", "age": 35, "isAdmin": false, "friends": ["el0","el1","el2","el3"] }';
var user = JSON.parse(str);
alert( user.name ); // Вася
alert( user.age ); // 35
alert( user.isAdmin ); // false
alert( user.friends[0] ); // el0
alert( user.friends[1] ); // el1

//Пример 2 (с распознаванием дат)
var schedule = '{ \
  "events": [ \
    {"title":"Конференция","date":"2014-11-30T12:00:00.000Z"}, \
    {"title":"День рождения","date":"2015-04-18T12:00:00.000Z"} \
  ]\
}';
schedule = JSON.parse(schedule, function(key, value) {
    if (key == 'date') return new Date(value);
    return value;
});
alert( schedule.events[0].date.getDate() ); // сработает!


//-----------JSON.stringify----------
//Метод JSON.stringify(value, replacer, space) преобразует («сериализует») значение в JSON-строку.
//При сериализации объекта вызывается его метод toJSON.
//Если такого метода нет – перечисляются его свойства, кроме функций.

// Пример использования 1:
var event = {
    title: "Конференция",
    date: "сегодня"
};
var str = JSON.stringify(event);
alert(str); // {"title":"Конференция","date":"сегодня"}
// Обратное преобразование.
event = JSON.parse(str);


// Пример использования 2:
var room = {
    number: 23,
    occupy: function () {
        alert(this.number);
    }
};
var event = {
    title: "Конференция",
    date: new Date(Date.UTC(2014, 0, 1)),
    room: room
};
alert(JSON.stringify(event));
/*
  {
    "title":"Конференция",
    "date":"2014-01-01T00:00:00.000Z",  // (1)
    "room": {"number":23}               // (2)
  }
*/

//---------toJSON---------
//У объекта room нет метода toJSON. Поэтому он сериализуется перечислением свойств.
//Мы, конечно, могли бы добавить такой метод, тогда в итог попал бы его результат:
var room = {
    number: 23,
    toJSON: function() {
        return this.number;
    }
};
alert( JSON.stringify(room) ); // 23



//----------Исключения из сериализации-------------
// Во втором параметре JSON.stringify(value, replacer) можно указать массив свойств,
// которые подлежат сериализации.
//Например:
var user = {
    name: "Вася",
    age: 25,
    window: window
};
alert(JSON.stringify(user, ["name", "age"]));
// {"name":"Вася","age":25}


//Либо указать ф-ю:
var user = {
    name: "Вася",
    age: 25,
    window: window
};
var str = JSON.stringify(user, function(key, value) {
    if (key == 'window') return undefined;
    return value;
});
alert( str ); // {"name":"Вася","age":25}


//-----------Красивое форматирование----------
//В методе JSON.stringify(value, replacer, space) есть ещё третий параметр space.
//Если он является числом – то уровни вложенности в JSON оформляются указанным количеством пробелов,
// если строкой – вставляется эта строка.
//Например:
var user = {
    name: "Вася",
    age: 25,
    roles: {
        isAdmin: false,
        isEditor: true
    }
};
var str = JSON.stringify(user, "", 4);
alert( str );
/* Результат -- красиво сериализованный объект:
{
    "name": "Вася",
    "age": 25,
    "roles": {
        "isAdmin": false,
        "isEditor": true
    }
}
*/


//----------еще примеры----------
var leader = {
    name: "Василий Иванович",
    age: 35
};
var json = JSON.stringify(leader, null, 4);
console.log(json);
var leader2 = JSON.parse(json);
console.log(leader2);









//-----------------------------------------setTimeout и setInterval------------------------------------------

//setTimeout и setInterval
//Рекурсивный setTimeout гарантирует паузу между вызовами, setInterval – нет,
// потому что не дожидается завершения ф-ии для начала отсчета и если ф-ия дольше интервала, то она будет выполняться сразу же.
//Давайте сравним два кода.

// Первый использует setInterval:
var i1 = 1;
var timer1 = setInterval(function () {
    console.log("interval: "+i1++);
    if(i1==20) {
        clearInterval(timer1);
    }
}, 100);

//Второй использует рекурсивный setTimeout:
var i2 = 1;
setTimeout(function run() {
    console.log("recur. timeout: "+i2++);
    var t=setTimeout(run, 100);
    if(i2==20) {
        clearTimeout(t);
    }
}, 2000);










//--------------------------------------------Перехват ошибок, "try..catch"---------------------------------------------

//Пример с ошибкой: при запуске сработают (1) и (3):
try {
    alert('Начало блока try');  // (1) <--
    lalala; // ошибка, переменная не определена!
    alert('Конец блока try');  // (2)
} catch(e) {
    alert('Ошибка ' + e.name + ":" + e.message + "\n" + e.stack); // (3) <--
}
alert("Потом код продолжит выполнение...");


//Используем try..catch, чтобы обработать некорректный ответ:
var data = "Has Error"; // в данных ошибка
try {
    var user = JSON.parse(data); // <-- ошибка при выполнении
    alert(user.name); // не сработает
} catch (e) {
    // ...выполнится catch
    alert("Извините, в данных ошибка, мы попробуем получить их ещё раз");
    alert(e.name);
    alert(e.message);
}


//Используем конструктор new SyntaxError(message).
// Он создаёт ошибку того же типа, что и JSON.parse.
var data = '{ "age": 30 }'; // данные неполны
try {
    var user = JSON.parse(data); // <-- выполнится без ошибок
    if (!user.name) {
        throw new SyntaxError("Данные некорректны");
    }
    alert(user.name);
} catch (e) {
    alert("Извините, в данных ошибка");
}


//В примере ниже catch обрабатывает только ошибки SyntaxError,
// а остальные – выбрасывает дальше:
var data = '{ "name": "Вася", "age": 30 }'; // данные корректны
try {
    var user = JSON.parse(data);
    if (!user.name) {
        throw new SyntaxError("Ошибка в данных");
    }
    blabla(); // произошла непредусмотренная ошибка
    alert(user.name);
} catch (e) {
    if (e.name == "SyntaxError") {
        alert("Извините, в данных ошибка");
    } else {
        throw e;
    }
}


//------Оборачивание исключений---------
function ReadError(message, cause) {
    this.message = message;
    this.cause = cause;
    this.name = 'ReadError';
    this.stack = cause.stack;
}
function readData() {
    var data = '{ bad data }';

    try {
        // ...
        JSON.parse(data);
        // ...
    } catch (e) {
        // ...
        if (e.name == 'URIError') {
            throw new ReadError("Ошибка в URI", e);
        } else if (e.name == 'SyntaxError') {
            throw new ReadError("Синтаксическая ошибка в данных", e);
        } else {
            throw e; // пробрасываем
        }
    }
}
try {
    readData();
} catch (e) {
    if (e.name == 'ReadError') {
        alert(e.message);
        alert(e.cause); // оригинальная ошибка-причина
    } else {
        throw e;
    }
}


//---------finally----------
function func() {
    try {
        // сразу вернуть значение
        return 1;
    } catch (e) {
        /* ... */
    } finally {
        alert('finally');
    }
}
alert(func()); // сначала finally, потом 1


//---------window.onerror----------
window.onerror = function(message, url, lineNumber) {
    alert("Поймана ошибка, выпавшая в глобальную область!\n" +
        "Сообщение: " + message + "\n(" + url + ":" + lineNumber + ")");
};
function readData() {
    error(); // ой, что-то не так
}
readData();











//--------------------------------------------Внутренний и внешний интерфейс--------------------------------------------
//приватный = локальный
//публичный = объектный(this.xxx)

//Пример проблемы с доступом к объекту из внутреннего метода
"use strict";
function CoffeeMachine(power) {
    this.waterAmount = 0;//публичное поле
    // физическая константа - удельная теплоёмкость воды для getBoilTime
    var WATER_HEAT_CAPACITY = 4200;//приватное поле
    // расчёт времени для кипячения
    function getBoilTime() {//приватная ф-ия
        // ошибка! this тут - это объект функции getBoilTime (которого сейчас нет),
        // без строгого режима - это window,
        // а в строгом - undefined
        return this.waterAmount * WATER_HEAT_CAPACITY * 80 / power;
    }
    // что делать по окончании процесса
    function onReady() {//приватная ф-ия
        alert( 'Кофе готов!' );
    }
    this.run = function() {//публичный метод
        setTimeout(onReady, getBoilTime());
        console.log(this);
    };
}
var coffeeMachine = new CoffeeMachine(1000);
coffeeMachine.waterAmount = 200;
coffeeMachine.run();


//Решение этой проблемы через сохранение this в замыкании
"use strict";
function CoffeeMachine(power) {
    this.waterAmount = 0;
    var WATER_HEAT_CAPACITY = 4200;
    var self = this;//сохраняем this во внутреннее свойство
    function getBoilTime() {
        return self.waterAmount * WATER_HEAT_CAPACITY * 80 / power;
    }
    function onReady() {
        alert('Кофе готов!');
    }
    this.run = function () {
        setTimeout(onReady, getBoilTime());
    };
}
var coffeeMachine = new CoffeeMachine(100000);
coffeeMachine.waterAmount = 200;
coffeeMachine.run();


//Та же кофеварка но добавлен стоп
"use strict";
function CoffeeMachine(power) {
    this.waterAmount = 0;
    var WATER_HEAT_CAPACITY = 4200;
    var self = this;//сохраняем this во внутреннее свойство
    var timer;
    function getBoilTime() {
        return self.waterAmount * WATER_HEAT_CAPACITY * 80 / power;
    }
    function onReady() {
        alert('Кофе готов!');
    }
    this.run = function () {
        timer = setTimeout(onReady, getBoilTime());
    };
    this.stop = function () {
        clearTimeout(timer);
    }
}

var coffeeMachine = new CoffeeMachine(50000);
coffeeMachine.waterAmount = 200;
coffeeMachine.run();
coffeeMachine.stop(); // кофе приготовлен не будет



//-------------Геттеры и сеттеры--------------
function User() {
    var firstName;
    var surname;
    this.getFirstName = function () {
        return firstName;
    };
    this.setFirstName = function (value) {
        firstName = value;
    };
    this.getSurname = function () {
        return surname;
    };
    this.setSurname = function (value) {
        surname = value;
    };
    this.getFullName = function () {//не имеет соотв. свойства - это нормально
        return firstName + ' ' + surname;
    };
    this.setFullName = function (value) {//не имеет соотв. свойства - это нормально
        var split = value.split(' ');
        firstName = split[0];
        surname = split[1];
    };
}
var user = new User();
user.setFirstName("Петя");
user.setSurname("Иванов");
alert(user.getFullName()); // Петя Иванов



//кофеварка с сеттерем для onReady
"use strict";
function CoffeeMachine(power, capacity) {
    var waterAmount = 0;
    var WATER_HEAT_CAPACITY = 4200;
    function getBoilTime() {
        return waterAmount * WATER_HEAT_CAPACITY * 80 / power;
    }
    function onReady() {
        alert('Кофе готов!');
    }
    this.setOnReady = function (f) {
        onReady = f;
    };
    //Чтобы setOnReady можно было вызывать в любое время, в setTimeout передаётся не onReady,
    // а анонимная функция function() { onReady() },
    // которая возьмёт текущий (установленный последним) onReady из замыкания.
    this.run = function () {
        setTimeout(function(){onReady();}, getBoilTime());
    };
    this.setWaterAmount = function(amount) {
        if (amount < 0) {
            throw new Error("Значение должно быть положительным");
        }
        if (amount > capacity) {
            throw new Error("Нельзя залить воды больше, чем " + capacity);
        }

        waterAmount = amount;
    };
    this.getWaterAmount = function() {
        return waterAmount;
    };
}
var coffeeMachine = new CoffeeMachine(20000, 500);
coffeeMachine.setWaterAmount(150);
coffeeMachine.setOnReady(function() {
    var amount = coffeeMachine.getWaterAmount();
    alert( 'Готов кофе: ' + amount + 'мл' ); // Кофе готов: 150 мл
});
coffeeMachine.run();












//-------------------------------------Функциональное наследование------------------------------------------------------

//Пример функционального наследования (через реализуется через call)
//Её общая схема (кратко):
//1)
// Объявляется конструктор родителя Machine.
// В нём могут быть приватные (private), публичные (public) и защищённые (protected) свойства:
function Machine(params) {
    // локальные переменные и функции доступны только внутри Machine
    var privateProperty;
    // публичные доступны снаружи
    this.publicProperty = 1;// = ...;
    // защищённые доступны внутри Machine и для потомков
    // мы договариваемся не трогать их снаружи
    this._protectedProperty = 2;// = ...
}
var machine = new Machine(3, 4, 5)
machine.public();

//2)
// Для наследования конструктор потомка вызывает родителя в своём контексте через apply.
// После чего может добавить свои переменные и методы:
function CoffeeMachine(params) {
    // универсальный вызов с передачей любых аргументов
    Machine.apply(this, arguments);
    this.coffeePublicProperty = 1;
}
var coffeeMachine = new CoffeeMachine(3,4,5,6,7)
;
coffeeMachine.publicProperty();
coffeeMachine.coffeePublicProperty();

//3)
// В CoffeeMachine свойства, полученные от родителя, можно перезаписать своими.
// Но обычно требуется не заменить, а расширить метод родителя. Для этого он предварительно копируется в переменную:
function CoffeeMachine(params) {
    Machine.apply(this, arguments);

    var parentProtected = this._protectedProperty;
    this._protectedProperty = function (args) {
        parentProtected.apply(this, args); // можно и напрямую, но тогда через self или у родителя не вызывать this
        // ...расширяем
    };
}



//Пример 1
// чтобы обозначить, что свойство является внутренним, его имя начинают с подчёркивания _.
function Machine() {
    this._enabled = false; // вместо var enabled
    this.enable = function () {
        this._enabled = true;
    };
    this.disable = function () {
        this._enabled = false;
    };
}
function CoffeeMachine(power) {
    Machine.call(this);//наследуемся от Machine
    this.enable();
    alert(this._enabled); // true
}
var coffeeMachine = new CoffeeMachine(10000);


//Пример 2
//наследование с аргументами
function Machine(power) {
    this._power = power;
    this._enabled = false;
    this.enable = function() {
        this._enabled = true;
    };
    this.disable = function() {
        this._enabled = false;
    };
}
function CoffeeMachine(power) {
    Machine.apply(this, arguments); // записываем аргументы
    alert( this._enabled ); // false
    alert( this._power ); // 10000 - берем свойство, в которое записался аругмент родителя
}
var coffeeMachine = new CoffeeMachine(10000);


//Пример 3
//переопределение методов + self
function Machine(power) {
    this._enabled = false;
    var self = this;
    this.enable = function() {
        // используем внешнюю переменную вместо this
        self._enabled = true;
    };
    this.disable = function() {
        self._enabled = false;
    };
}
function CoffeeMachine(power) {
    Machine.apply(this, arguments);
    var waterAmount = 0;
    this.setWaterAmount = function(amount) {
        waterAmount = amount;
    };
    var parentEnable = this.enable;
    this.enable = function() {
        parentEnable(); // теперь можно вызывать как угодно, this не важен
        this.run();
    }
    function onReady() {
        alert( 'Кофе готов!' );
    }
    this.run = function() {
        setTimeout(onReady, 1000);
    };
}

var coffeeMachine = new CoffeeMachine(10000);
coffeeMachine.setWaterAmount(50);
coffeeMachine.enable();



//Пример 4
function Machine(power) {
    this._enabled = false;
    this.enable = function() {
        this._enabled = true;
    };
    this.disable = function() {
        this._enabled = false;
    };
}
function CoffeeMachine(power) {
    Machine.apply(this, arguments);
    var waterAmount = 0;
    var timerId;
    this.setWaterAmount = function(amount) {
        waterAmount = amount;
    }
    function onReady() {
        alert('Кофе готов!');
    }
    var parentDisable = this.disable;
    this.disable = function() {
        parentDisable.call(this);
        clearTimeout(timerId);
    }
    this.run = function() {
        if (!this._enabled) {
            throw new Error("Кофеварка выключена");
        }
        timerId = setTimeout(onReady, 1000);
    };
}
var coffeeMachine = new CoffeeMachine(10000);
coffeeMachine.enable();
coffeeMachine.run();
coffeeMachine.disable(); // остановит работу, ничего не выведет


//Пример 5 Холодильник
function Machine(power) {
    this._power = power;
    this._enabled = false;
    var self = this;
    this.enable = function () {
        self._enabled = true;
    };
    this.disable = function () {
        self._enabled = false;
    };
}
function Fridge(power) {
    // унаследовать
    Machine.apply(this, arguments);
    var parentDisable = this.disable;
    var food = []; // приватное свойство food
    this.disable = function () {
        if (food.length>0) {
            throw new Error("Нельзя выключить: внутри еда");
        }
        parentDisable();
    };
    this.addFood = function () {
        if (!this._enabled) {
            throw new Error("Холодильник выключен");
        }
        if (food.length + arguments.length > this._power / 100) {
            throw new Error("Нельзя добавить, не хватает мощности");
        }
        [].forEach.call(arguments, function (elem) {food.push(elem);}); // добавить всё из arguments
    };
    this.getFood = function () {
        // копируем еду в новый массив, чтобы манипуляции с ним не меняли food
        return food.slice();
    };
    this.filterFood = function (filter) {
        return food.filter(filter);
    };
    this.removeFood = function (value) {
        var i = food.indexOf(value);
        if (i !== -1) {
            food.splice(i, 1);
        }
    }
}
//Использование
// создать холодильник мощностью 500 (не более 5 еды)
var fridge = new Fridge(500);
// fridge.enable();
// fridge.addFood("котлета");
// fridge.addFood("сок", "варенье");
// var fridgeFood = fridge.getFood();
// alert(fridgeFood); // котлета, сок, варенье
// // добавление элементов не влияет на еду в холодильнике
// fridgeFood.push("вилка", "ложка");
// alert(fridge.getFood()); // внутри по-прежнему: котлета, сок, варенье
//
//
// fridge.enable();
// fridge.addFood("котлета");
// fridge.addFood("сок", "зелень");
// fridge.addFood("варенье", "пирог", "торт"); // ошибка, слишком много еды
//

//Использование removeFood и filterFood
// fridge.enable();
// fridge.addFood({
//     title: "котлета",
//     calories: 100
// });
// fridge.addFood({
//     title: "сок",
//     calories: 30
// });
// fridge.addFood({
//     title: "зелень",
//     calories: 10
// });
// fridge.addFood({
//     title: "варенье",
//     calories: 150
// });
// fridge.removeFood("нет такой еды"); // без эффекта
// alert( fridge.getFood().length ); // 4
// var dietItems = fridge.filterFood(function(item) {
//     return item.calories < 50;
// });
// dietItems.forEach(function(item) {
//     alert( item.title ); // сок, зелень
//     fridge.removeFood(item);
// });
// alert( fridge.getFood().length ); // 2

//Использование disable
fridge.enable();
fridge.addFood("кус-кус");
fridge.disable(); // ошибка, в холодильнике есть еда












//-------------------------------------Прототипное наследование------------------------------------------------------



//----------------------__proto__---------------------------

var animal = {
    eats: true
};
var rabbit = {
    jumps: true
};
rabbit.__proto__ = animal;
// в rabbit можно найти оба свойства
alert( rabbit.jumps ); // true
alert( rabbit.eats ); // true


//Запись свойств не работает, только чтение
//записываем свойство в сам rabbit, после чего alert перестаёт брать его у прототипа,
// а берёт уже из самого объекта:
var animal = {
    eats: true
};
var rabbit = {
    jumps: true,
    eats: false
};
rabbit.__proto__ = animal;
alert( rabbit.eats ); // false, свойство взято из rabbit


//Обычный цикл for..in не делает различия между свойствами объекта и его прототипа.
//Он перебирает всё, например:
var animal = {
    eats: true
};
var rabbit = {
    jumps: true,
    __proto__: animal
};
for (var key in rabbit) {
    alert( key + " = " + rabbit[key] ); // выводит и "eats" и "jumps"
}


//Вызов obj.hasOwnProperty(prop) возвращает true,
// если свойство prop принадлежит самому объекту obj, иначе false.
// Например:
var animal = {
    eats: true
};
var rabbit = {
    jumps: true,
    __proto__: animal
};
alert( rabbit.hasOwnProperty('jumps') ); // true: jumps принадлежит rabbit
alert( rabbit.hasOwnProperty('eats') ); // false: eats не принадлежит
for (var key in rabbit) {
    if (!rabbit.hasOwnProperty(key)) continue; // пропустить "не свои" свойства
    alert( key + " = " + rabbit[key] ); // выводит только "jumps"
}

//удаление свойств из __proto__
var animal = {
    jumps: null
};
var rabbit = {
    jumps: true
};
rabbit.__proto__ = animal;
alert( rabbit.jumps ); // true
delete rabbit.jumps;
alert( rabbit.jumps ); // null
delete animal.jumps;
alert( rabbit.jumps ); // undefined


//Вызов метода с this из прототипа
var animal = {
    eat: function() {
        this.full = true;
        //свойство будет записано в rabbit, потому что this будет указывать на rabbit,
        // а прототип при записи не используется.
    }
};
var rabbit = {
    __proto__: animal
};
rabbit.eat();


//цепочка наследования через __proto__
var head = {
    glasses: 1
};
var table = {
    __proto__:head,
    pen: 3
};
var bed = {
    __proto__:table,
    sheet: 1,
    pillow: 2
};
var pockets = {
    __proto__:bed,
    money: 2000
};
alert(pockets.glasses);//1
alert(head.money);//undefined

//---------------Object.create---------------

//Объект, создаваемый при помощи Object.create(null) не имеет прототипа,
// а значит в нём нет лишних свойств. Для коллекции – как раз то, что надо.
var data = Object.create(null);
data.text = "Привет";
alert(data.text); // Привет
alert(data.toString); // undefined


//Пользуемся тем что __proto__ доступен только для чтения, чтобы не менять исходный объект (родитель),
// т.к. он может быть повторно использован во внешнем коде
function Menu(options) {
    options = Object.create(options);//создаем объект, в __proto__ которого будет options
    options.width = 300; //т.к. в __proto__ поле не меняется, то создается новое поле у наследника и ему присв. значение
    alert("width: " + options.width); // возьмёт width из наследника
    alert("height: " + options.height); // возьмёт height из исходного объекта
}
var options = {
    width: 100,
    height: 200
};
new Menu(options);
alert("original width: " + options.width); // width исходного объекта
alert("original height: " + options.height); // height исходного объекта


//----------------------prototype---------------------------
//Чтобы новым объектам автоматически ставить прототип, конструктору ставится свойство prototype.
//При создании объекта через new,
// в его прототип __proto__ записывается ссылка из prototype функции-конструктора.

//если я хочу, чтобы у всех объектов, которые создаются new Rabbit, был прототип animal, я могу сделать так:
//(Недостаток этого подхода – он не работает в IE10-.)
var animal = {
    eats: true
};
function Rabbit(name) {
    this.name = name;
    this.__proto__ = animal;
}
var rabbit = new Rabbit("Кроль");
alert( rabbit.eats ); // true, из прототипа


// код ниже полностью аналогичен предыдущему, но работает всегда и везде:
var animal = {
    eats: true
};
function Rabbit(name) {
    this.name = name;
}
Rabbit.prototype = animal;
var rabbit = new Rabbit("Кроль"); //  rabbit.__proto__ == animal
alert( rabbit.eats ); // true


//тоже самое, но в прототип пишем объект ф-ию-конструктор
function Animal() {
    this.eats = true;
}
function Rabbit(name) {
    this.name = name;
}
Rabbit.prototype = new Animal();
var rabbit = new Rabbit("Кроль"); // rabbit.__proto__ == animal
alert( rabbit.eats ); // true




//В примерах ниже создаётся объект new Rabbit, а затем проводятся различные действия с prototype.
//Каковы будут результаты выполнения? Почему?
// 1--- Начнём с этого кода. Что он выведет?
function Rabbit() {
}
Rabbit.prototype = {
    eats: true
};
var rabbit = new Rabbit();
alert(rabbit.eats);//Результат: true
//Берется из прототипа


// 2--- Добавили строку (выделена), что будет теперь?
function Rabbit() {
}
Rabbit.prototype = {
    eats: true
};
var rabbit = new Rabbit();
Rabbit.prototype = {};
alert(rabbit.eats);//Результат: true.
// Свойство prototype всего лишь задаёт __proto__ у новых объектов.
// Так что его изменение не повлияет на rabbit.__proto__.
// Свойство eats будет получено из прототипа.


// 3--- А если код будет такой? (заменена одна строка):
function Rabbit(name) {
}
Rabbit.prototype = {
    eats: true
};
var rabbit = new Rabbit();
Rabbit.prototype.eats = false;
alert(rabbit.eats);//Результат: false.
// Свойство Rabbit.prototype и rabbit.__proto__ указывают на один и тот же объект.
// В данном случае изменения вносятся в сам объект.


// 4--- А такой? (заменена одна строка)
function Rabbit(name) {
}
Rabbit.prototype = {
    eats: true
};
var rabbit = new Rabbit();
delete rabbit.eats;
alert(rabbit.eats);//Результат: true,
// так как delete rabbit.eats попытается удалить eats из rabbit, где его и так нет.
// А чтение в alert произойдёт из прототипа.


// 5--- И последний вариант:
function Rabbit(name) {
}
Rabbit.prototype = {
    eats: true
};
var rabbit = new Rabbit();
delete Rabbit.prototype.eats;
alert(rabbit.eats);//Результат: undefined.
// Удаление осуществляется из самого прототипа,
// поэтому свойство rabbit.eats больше взять неоткуда.



//----------------------constructor---------------------------

//У каждой функции по умолчанию уже есть свойство prototype.
//Оно содержит объект такого вида:
function Rabbit() {}
Rabbit.prototype = {
    constructor: Rabbit
};

//Можно его использовать для создания объекта с тем же конструктором, что и данный:
function Rabbit(name) {
    this.name = name;
    alert( name );
}
var rabbit = new Rabbit("Кроль");
var rabbit2 = new rabbit.constructor("Крольчиха");
//Эта возможность бывает полезна, когда, получив объект, мы не знаем в точности,
// какой у него был конструктор (например, сделан вне нашего кода), а нужно создать такой же.



//Но если кто-то, к примеру, перезапишет User.prototype и забудет указать constructor,
// то такой фокус не пройдёт, например:
function User(name) {
    this.name = name;
}
User.prototype = {}; // (*)
var obj = new User('Вася');
var obj2 = new obj.constructor('Петя');
alert( obj2.name ); // undefined

//Почему obj2.name равен undefined? Вот как это работает:

//1. При вызове new obj.constructor('Петя'), obj ищет у себя свойство constructor – не находит.

//2. Обращается к своему свойству __proto__, которое ведёт к прототипу.

//3. Прототипом будет (*), пустой объект.

//4. Далее здесь также ищется свойство constructor – его нет.

//5. Где ищем дальше? Правильно – у следующего прототипа выше, а им будет Object.prototype.

//6. Свойство Object.prototype.constructor существует, это встроенный конструктор объектов, который,
// вообще говоря, не предназначен для вызова с аргументом-строкой,
// поэтому создаст совсем не то, что ожидается, но то же самое, что вызов new Object('Петя'), и у такого объекта не будет name.


//this в prototype
function Rabbit(name) {
    this.name = name;
    this.toString = function () {
        return "Object Rabbit";
    }
}
Rabbit.prototype.sayHi = function() {
    alert("this = " + this + ", this.name = " + this.name );
};
var rabbit = new Rabbit("Rabbit");
rabbit.sayHi();//this = Object Rabbit, this.name = Rabbit (вызвался у rabbit и дернул метод из proto)
Rabbit.prototype.sayHi();//this = [object Object], this.name = undefined (вызвался у proto и взял его метод)
Object.getPrototypeOf(rabbit).sayHi();//this = [object Object], this.name = undefined (вызвался у proto и взял его метод)
rabbit.__proto__.sayHi();//this = [object Object], this.name = undefined (вызвался у proto и взял его метод)



//----------------------Object, __proto__, prototype-----------------------

var obj = {};
// метод берётся из прототипа?
alert( obj.toString == Object.prototype.toString ); // true, да
// проверим, правда ли что __proto__ это Object.prototype?
alert( obj.__proto__ == Object.prototype ); // true
// А есть ли __proto__ у Object.prototype?
alert( obj.__proto__.__proto__ ); // null, нет


//Вызов методов через call и apply из прототипа
//Ранее мы говорили о применении методов массивов к «псевдомассивам», например, можно использовать [].join для arguments:
function showList() {
    alert([].join.call(arguments, " - "));
}
showList("Вася", "Паша", "Маша"); // Вася - Паша - Маша


//Так как метод join находится в Array.prototype, то можно вызвать его оттуда напрямую, вот так:
function showList() {
    alert(Array.prototype.join.call(arguments, " - "));//Это эффективнее, потому что не создаётся лишний объект массива []
}
showList("Вася", "Паша", "Маша"); // Вася - Паша - Маша


//----------------Изменение встроенных прототипов---------------------

//Встроенные прототипы можно изменять. В том числе – добавлять свои методы.
//Мы можем написать метод для многократного повторения строки, и он тут же станет доступным для всех строк:
String.prototype.repeat = function (times) {
    return new Array(times + 1).join(this);
};
alert("ля".repeat(3)); // ляляля



//--------------Object.create------------------
//Имеет примерно такую реализацию
Object.create = function(proto) {
    function F() {}
    F.prototype = proto;
    return new F;
};









//------------------------------------------Класс через прототип--------------------------------------------------------

//А теперь создадим класс, используя прототипы, наподобие того, как сделаны классы Object, Date и остальные.
//
//Чтобы объявить свой класс, нужно:
//
//Объявить функцию-конструктор.
//    Записать методы и свойства, нужные всем объектам класса, в prototype.
//    Опишем класс Animal:

// конструктор
function Animal(name) {
    this.name = name;
    this.speed = 0;
}
// методы в прототипе
Animal.prototype.run = function(speed) {
    this.speed += speed;
    alert( this.name + ' бежит, скорость ' + this.speed );
};
Animal.prototype.stop = function() {
    this.speed = 0;
    alert( this.name + ' стоит' );
};
var animal = new Animal('Зверь');
alert( animal.speed ); // 0, свойство взято из прототипа
animal.run(5); // Зверь бежит, скорость 5
animal.run(5); // Зверь бежит, скорость 10
animal.stop(); // Зверь стоит




//Задача: переписать CoffeeMachine в виде класса с использованием прототипа.
// Исходный код:
function CoffeeMachine(power) {
    var waterAmount = 0;
    var WATER_HEAT_CAPACITY = 4200;
    function getTimeToBoil() {
        return waterAmount * WATER_HEAT_CAPACITY * 80 / power;
    }
    this.run = function () {
        setTimeout(function () {
            alert('Кофе готов!');
        }, getTimeToBoil());
    };
    this.setWaterAmount = function (amount) {
        waterAmount = amount;
    };

}
var coffeeMachine = new CoffeeMachine(10000);
coffeeMachine.setWaterAmount(50);
coffeeMachine.run();


//Решение
function CoffeeMachine(power) {
    // свойства конкретной кофеварки
    this._power = power;
    this._waterAmount = 0;
}
// свойства и методы для всех объектов класса
CoffeeMachine.prototype.WATER_HEAT_CAPACITY = 4200;

CoffeeMachine.prototype._getTimeToBoil = function() {
    return this._waterAmount * this.WATER_HEAT_CAPACITY * 80 / this._power;
};
CoffeeMachine.prototype.run = function() {
    setTimeout(function() {
        alert( 'Кофе готов!' );
    }, this._getTimeToBoil());
};
CoffeeMachine.prototype.setWaterAmount = function(amount) {
    this._waterAmount = amount;
};
var coffeeMachine = new CoffeeMachine(10000);
coffeeMachine.setWaterAmount(50);
coffeeMachine.run();



//-------------Наследование классов---------------

// --------- Класс-Родитель ------------
// Конструктор родителя пишет свойства конкретного объекта
function Animal(name) {
    this.name = name;
    this.speed = 0;
}

// Методы хранятся в прототипе
Animal.prototype.run = function() {
    alert(this.name + " бежит!");
};

// --------- Класс-потомок 1-----------
// Конструктор потомка
function Rabbit(name) {
    //вызываем конструктор родителя чтобы не дублировать код, но можем и не вызывать - в отличии от ф-го наследования
    Animal.apply(this, arguments);
}
// Унаследовать
Rabbit.prototype = Object.create(Animal.prototype);
//если написать так:
//Rabbit.prototype = Animal.prototype;
//то это приведёт к тому, что Rabbit.prototype и Animal.prototype – один и тот же объект.
// В результате методы Rabbit будут помещены в него и, при совпадении, перезапишут методы Animal

// Желательно и constructor сохранить
Rabbit.prototype.constructor = Rabbit;
// Методы потомка
Rabbit.prototype.run = function() {
    // Вызов метода родителя внутри своего
    Animal.prototype.run.apply(this);
    alert( this.name + " подпрыгивает!" );
};

// --------- Класс-потомок 2-----------
function Cat(name) {
    Animal.apply(this, arguments);
}
Cat.prototype = Object.create(Animal.prototype);
Cat.prototype.constructor = Rabbit;
Cat.prototype.run = function() {
    Animal.prototype.run.apply(this);
    alert( this.name + " мяучит!" );
};


// Готово, можно создавать объекты
var rabbit = new Rabbit('Кроль');
rabbit.run();
var cat = new Cat('Кот');
cat.run();








//------------------------------------------Свои ошибки, наследование от Error------------------------------------------


//---------Пример 1----------

// Объявление
function PropertyError(property) {
    this.name = "PropertyError";
    this.property = property;
    this.message = "Ошибка в свойстве " + property;
    if (Error.captureStackTrace) {
        Error.captureStackTrace(this, PropertyError);
    } else {
        this.stack = (new Error()).stack;
    }
}
PropertyError.prototype = Object.create(Error.prototype);

// Генерация ошибки
function readUser(data) {
    var user = JSON.parse(data);
    if (!user.age) {
        throw new PropertyError("age");
    }
    if (!user.name) {
        throw new PropertyError("name");
    }
    return user;
}

// Запуск и try..catch
try {
    var user = readUser('{ "age": 25 }');
} catch (err) {
    if (err instanceof PropertyError) {
        if (err.property == 'name') {
            // если в данном месте кода возможны анонимы, то всё нормально
            alert( "Здравствуйте, Аноним!" );
        } else {
            alert( err.message ); // Ошибка в свойстве ...
        }
    } else if (err instanceof SyntaxError) {
        alert( "Ошибка в синтаксисе данных: " + err.message );
    } else {
        throw err; // неизвестная ошибка, не знаю что с ней делать
    }
}



//-------Пример 2---------

// общего вида "наша" ошибка
function CustomError(message) {
    this.name = "CustomError";
    this.message = message;
    if (Error.captureStackTrace) {
        Error.captureStackTrace(this, this.constructor);
    } else {
        this.stack = (new Error()).stack;
    }
}
CustomError.prototype = Object.create(Error.prototype);
CustomError.prototype.constructor = CustomError;

// наследник
function PropertyError(property) {
    CustomError.call(this, "Ошибка в свойстве " + property)
    this.name = "PropertyError";
    this.property = property;
}
PropertyError.prototype = Object.create(CustomError.prototype);
PropertyError.prototype.constructor = PropertyError;

// и ещё уровень
function PropertyRequiredError(property) {
    PropertyError.call(this, property);
    this.name = 'PropertyRequiredError';
    this.message = 'Отсутствует свойство ' + property;
}
PropertyRequiredError.prototype = Object.create(PropertyError.prototype);
PropertyRequiredError.prototype.constructor = PropertyRequiredError;

// использование
var err = new PropertyRequiredError("age");
// пройдёт проверку
alert( err instanceof PropertyRequiredError ); // true
alert( err instanceof PropertyError ); // true
alert( err instanceof CustomError ); // true
alert( err instanceof Error ); // true



//-------Пример 3---------

function FormatError(message) {
    this.name = "FormatError";
    this.message = message;
    if (Error.captureStackTrace) {
        Error.captureStackTrace(this, this.constructor);
    } else {
        this.stack = (new Error()).stack;
    }
}
FormatError.prototype = Object.create(SyntaxError.prototype);
FormatError.prototype.constructor = FormatError;

var err = new FormatError("ошибка форматирования");

alert( err.message ); // ошибка форматирования
alert( err.name ); // FormatError
alert( err.stack ); // стек на момент генерации ошибки

alert( err instanceof SyntaxError ); // true









//------------------------------------------------------Примеси---------------------------------------------------------

//------------примесь-------
var sayHiMixin = {
    sayHi: function() {
        alert("Привет " + this.name);
    },
    sayBye: function() {
        alert("Пока " + this.name);
    }
};

// использование:
function User(name) {
    this.name = name;
}

// передать методы примеси
for(var key in sayHiMixin) User.prototype[key] = sayHiMixin[key];

// User "умеет" sayHi
new User("Вася").sayHi(); // Привет Вася



//-----------Реальный пример на событиях-----------
var eventMixin = {
    /**
     * Подписка на событие
     * Использование:
     *  menu.on('select', function(item) { ... }
     */
    on: function(eventName, handler) {
        if (!this._eventHandlers) this._eventHandlers = {};
        if (!this._eventHandlers[eventName]) {
            this._eventHandlers[eventName] = [];
        }
        this._eventHandlers[eventName].push(handler);
    },
    /**
     * Прекращение подписки
     *  menu.off('select',  handler)
     */
    off: function(eventName, handler) {
        var handlers = this._eventHandlers && this._eventHandlers[eventName];
        if (!handlers) return;
        for(var i=0; i<handlers.length; i++) {
            if (handlers[i] == handler) {
                handlers.splice(i--, 1);
            }
        }
    },
    /**
     * Генерация события с передачей данных
     *  this.trigger('select', item);
     */
    trigger: function(eventName /*, ... */) {
        if (!this._eventHandlers || !this._eventHandlers[eventName]) {
            return; // обработчиков для события нет
        }
        // вызвать обработчики
        var handlers = this._eventHandlers[eventName];
        for (var i = 0; i < handlers.length; i++) {
            handlers[i].apply(this, [].slice.call(arguments, 1));
        }
    }
};

// Класс Menu с примесью eventMixin
function Menu() {
    // ...
}
for(var key in eventMixin) {
    Menu.prototype[key] = eventMixin[key];
}
// Генерирует событие select при выборе значения
Menu.prototype.choose = function(value) {
    this.trigger("select", value);
};
// Создадим меню
var menu = new Menu();
// При наступлении события select вызвать эту функцию
menu.on("select", function(value) {
    alert("Выбрано значение " + value);
});
// Запускаем выбор (событие select вызовет обработчики)
menu.choose("123");









//-------------------------------------Сравнение массивов, объектов, циклы in, of-----------------------------------------------
//использовать только в forBundle.js потому что юзаем импорт

import {log} from "./resources/js/helpers/consoleLog";

//----------------сравнение массивов------------------
let arr1 = [1.4, 35, 6, 34.5];
let arr2 = [1.4, 35, 34.5, 6];
const arr1C = [...arr1].sort();
const arr2C = [...arr2].sort();
//отсортированные массивы проще всего сравнить так
const comparedArrs = arr1C.every((el, i)=>el===arr2C[i]);
log('comparedArrs=', comparedArrs);
log('arr1=', arr1);
log('arr2=', arr2);
log('arr1C=', arr1C);
log('arr2C=', arr2C);

//----------------сравнение объектов------------------
//сделаем такой вариант
const parentObj = {
    equals: function (obj) {
        return !!(obj
            && this.name === obj.name
            && this.lastName === obj.lastName
            && this.height === obj.height);
    }
};
const obj1 = {name: 'gsdf', lastName: 'hoho', height: 174, __proto__: parentObj};
const obj2 = {name: 'gsdf', lastName: 'hoho', height: 174, __proto__: parentObj};
log('comparedObjs (equals)=', obj1.equals(obj2));

//или такой, более общий
const comparedObjs = Object.keys(obj1).every((key) => obj1[key] === obj2[key]);
log('comparedObjs (Object.keys)=', comparedObjs);


//----------------Циклы in, of, метод forEach------------------
//for of - работает только с итерируемыми объектами (содержат метод с названием Symbol.iterator).
// по умолчанию итерируемые Array и string
for(let el of arr1){
    log('arr1 of=', el);
}
//for in - для массивов лучше не использовать, он менее эффективен
for(let el in arr1){
    log('arr1 in=', el);
}
//for in - хорош для объектов, el - это имя свойств (включая наследуемые)
for(let el in obj1){
    log('obj1 in=', el);
}
//по массиву проще всего пройтись так
arr1.forEach((el)=>{log('arr1 forEach=', el)});
//по объекту так (не включает наследуемые поля)
Object.keys(obj1).forEach((key)=>{log('obj1 forEach=', obj1[key])});