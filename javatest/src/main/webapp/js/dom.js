//---------------------------------------УЗЛЫ и ЭЛЕМЕНТЫ-----------------------------------------------------
var list = document.getElementById('testId14');
var firstChild = document.querySelector('ol#testId14 :first-child');

//NODES - УЗЛЫ - они могут содержать и тэги и всякую шалуху (переносы, текст, пробелы)
console.log('list.childNodes[0]',list.childNodes[0]);//#text "
console.log('list.firstChild',list.firstChild);//#text "

//ELEMENTS - ЭЛЕМЕНТЫ - содержат только тэги
console.log('list.firstElementChild',list.firstElementChild);//<li>
console.log('list.children[0]',list.children[0]);//<li>
console.log('document.querySelector(...)',firstChild);//<li>



//---------------------------------------Навигация по DOM-элементам-----------------------------------------------------

for (var i = 0; i < document.body.childNodes.length; i++) {
//Список детей – только для чтения!
    alert( document.body.childNodes[i] ); // Text, DIV, Text, UL, ..., SCRIPT
}
//При наличии дочерних узлов всегда верно:
elem.childNodes[0] === elem.firstChild
elem.childNodes[elem.childNodes.length - 1] === elem.lastChild

//При помощи Array.prototype.slice сделать из коллекции массив.
var elems = document.documentElement.childNodes;
elems = Array.prototype.slice.call(elems); // теперь elems - массив
elems.forEach(function(elem) {
    alert( elem.tagName ); // HEAD, текст, BODY
});


//Навигация только по элементам
//Навигационные ссылки, описанные выше, равно касаются всех узлов в документе.
// В частности, в childNodes сосуществуют и текстовые узлы и узлы-элементы и узлы-комментарии, если есть.
//
//Но для большинства задач текстовые узлы нам не интересны.
//
//Поэтому посмотрим на дополнительный набор ссылок, которые их не учитывают:
//Эти ссылки похожи на те, что раньше, только в ряде мест стоит слово Element:
//
//children – только дочерние узлы-элементы, то есть соответствующие тегам.
//firstElementChild, lastElementChild – соответственно, первый и последний дети-элементы.
//previousElementSibling, nextElementSibling – соседи-элементы.
//parentElement – родитель-элемент.
//
alert( document.documentElement.parentNode ); // document
alert( document.documentElement.parentElement ); // null

for (var i = 0; i < document.body.children.length; i++) {
    alert( document.body.children[i] ); // DIV, UL, DIV, SCRIPT
}
//Всегда верны равенства:
elem.firstElementChild === elem.children[0]
elem.lastElementChild === elem.children[elem.children.length - 1]




//--------------------------------Поиск: getElement* и querySelector* и не только-------------------------------------------------

var elem = document.getElementById('testId1');
elem.style.background = 'red';
alert( elem == testId ); // true
testId.style.background = ""; // один и тот же элемент

// получить все div-элементы
var elements = document.getElementsByTagName('div');

// получить все элементы документа
document.getElementsByTagName('*');
// получить всех потомков элемента elem:
elem.getElementsByTagName('*');

var links = document.getElementsByClassName('a-head');
alert( links.length ); // 2, найдёт оба элемента

//querySelectorAll
//Вызов elem.querySelectorAll(css) возвращает все элементы внутри elem, удовлетворяющие CSS-селектору css.
//Следующий запрос получает все элементы LI, которые являются последними потомками в UL
var elements = document.querySelectorAll('ul > li:last-child');
for (var i = 0; i < elements.length; i++) {
    alert( elements[i].innerHTML );
}

//querySelector
//Вызов elem.querySelector(css) возвращает не все, а только первый элемент, соответствующий CSS-селектору css.
var element = document.querySelector('ul > li:last-child');
alert(element.innerHTML);//<a href="https://learn.javascript.ru/forms-controls">Формы, элементы управления</a>
alert(element.textContent);//Формы, элементы управления




//---------------------------------------Свойства узлов: тип, тег и содержимое-----------------------------------------------------
//
//Прямо от Node наследуют текстовые узлы Text, комментарии Comment и элементы Element.
//Элементы Element – это ещё не HTML-элементы, а более общий тип, который используется в том числе в XML.
// От него наследует SVGElement для SVG-графики и, конечно, HTMLElement.
//От HTMLElement уже наследуют разнообразные узлы HTML:
//Для <input> – HTMLInputElement
//Для <body> – HTMLBodyElement
//Для <a> – HTMLAnchorElement… и так далее.
//Узнать класс узла очень просто – достаточно привести его к строке, к примеру, вывести:
alert( document.body ); // [object HTMLBodyElement]
//Можно и проверить при помощи instanceof:
alert( document.body instanceof HTMLBodyElement ); // true
alert( document.body instanceof HTMLElement ); // true
alert( document.body instanceof Element ); // true
alert( document.body instanceof Node ); // true

//console.log выводит элемент в виде, удобном для исследования HTML-структуры.
//console.dir выводит элемент в виде JavaScript-объекта, удобно для анализа его свойств.


//Тип: nodeType
//Тип узла содержится в его свойстве nodeType.
//
//Как правило, мы работаем всего с двумя типами узлов:
//Элемент.
//Текстовый узел.

//  // Всевозможные значения nodeType
//  const unsigned short ELEMENT_NODE = 1;
//  const unsigned short ATTRIBUTE_NODE = 2;
//  const unsigned short TEXT_NODE = 3;
//  const unsigned short CDATA_SECTION_NODE = 4;
//  const unsigned short ENTITY_REFERENCE_NODE = 5;
//  const unsigned short ENTITY_NODE = 6;
//  const unsigned short PROCESSING_INSTRUCTION_NODE = 7;
//  const unsigned short COMMENT_NODE = 8;
//  const unsigned short DOCUMENT_NODE = 9;
//  const unsigned short DOCUMENT_TYPE_NODE = 10;
//  const unsigned short DOCUMENT_FRAGMENT_NODE = 11;
//  const unsigned short NOTATION_NODE = 12;
//

alert( document.body.nodeType ); // 1
alert( document.body.nodeName ); // BODY
alert( document.body.tagName ); // BODY

// для комментария
alert( document.body.firstChild.nodeName ); // #comment
alert( document.body.firstChild.tagName ); // undefined (в IE8- воскл. знак "!")
// для документа
alert( document.nodeName ); // #document, т.к. корень DOM -- не элемент
alert( document.tagName ); // undefined


//innerHTML: содержимое элемента
alert( document.body.innerHTML ); // читаем текущее содержимое
document.body.innerHTML = 'Новый BODY!'; // заменяем содержимое

document.body.innerHTML = '<b>тест'; // незакрытый тег
alert( document.body.innerHTML ); // <b>тест</b> (исправлено)

alert(document.body.children[0].children[0].innerHTML);
alert(document.body.getElementsByTagName("div")[0].getElementsByClassName("a-head")[0].innerHTML);

//Текст: textContent
//elem.textContent возвращает конкатенацию всех текстовых узлов внутри elem.

// Документ и объекты страницы
// Основы работы с событиями
// Формы, элементы управления
alert(document.body.getElementsByTagName("div")[0].getElementsByTagName("ul")[0].textContent);

document.body.getElementsByTagName("div")[0].getElementsByTagName("ul")[0].textContent = "новый текст";//<ul>новый текст</ul>




//---------------------------------------Современный DOM: полифиллы-----------------------------------------------------

//«Полифилл» (англ. polyfill) – это библиотека, которая добавляет в старые браузеры поддержку возможностей, которые в современных браузерах являются встроенными.

// Один полифилл мы уже видели, когда изучали собственно JavaScript – это библиотека ES5 shim.
// Если её подключить, то в IE8- начинают работать многие возможности ES5.
// Работает она через модификацию стандартных объектов и их прототипов. Это типично для полифиллов.
if (document.documentElement.firstElementChild === undefined) { // (1)

    Object.defineProperty(Element.prototype, 'firstElementChild', { // (2)
        get: function() {
            var el = this.firstChild;
            do {
                if (el.nodeType === 1) {
                    return el;
                }
                el = el.nextSibling;
            } while (el);

            return null;
        }
    });
}
//Если этот код запустить, то firstElementChild появится у всех элементов в IE8.
// Общий вид этого полифилла довольно типичен. Обычно полифилл состоит из двух частей:
    // Проверка, есть ли встроенная возможность.
    // Эмуляция, если её нет.


//Полифилл для matches
(function() {

    // проверяем поддержку
    if (!Element.prototype.matches) {

        // определяем свойство
        Element.prototype.matches = Element.prototype.matchesSelector ||
            Element.prototype.webkitMatchesSelector ||
            Element.prototype.mozMatchesSelector ||
            Element.prototype.msMatchesSelector;

    }

})();







//---------------------------------------Атрибуты и DOM-свойства--------------------------------------------------------
//-----Свои DOM-свойства-----
//Узел DOM – это объект, поэтому, как и любой объект в JavaScript,
// он может содержать пользовательские свойства и методы.

//Например, создадим в document.body новое свойство и запишем в него объект:
document.body.myData = {
    name: 'Петр',
    familyName: 'Петрович'
};
alert( document.body.myData.name ); // Петр


//Можно добавить и новую функцию:
document.body.sayHi = function () {
    alert(this.nodeName);
};
document.body.sayHi(); // BODY, выполнилась с правильным this


//----Атрибуты-----
//В отличие от свойств, атрибуты:

//Всегда являются строками.
//Их имя нечувствительно к регистру (ведь это HTML)
//Видны в innerHTML (за исключением старых IE)

// elem.hasAttribute(name) – проверяет наличие атрибута
// elem.getAttribute(name) – получает значение атрибута
// elem.setAttribute(name, value) – устанавливает атрибут
// elem.removeAttribute(name) – удаляет атрибут

var elem = document.getElementById('testId2');
alert( elem.getAttribute('About') ); // (1) 'Elephant', атрибут получен
elem.setAttribute('Test', 123); // (2) атрибут Test установлен
alert( document.body.innerHTML ); // (3) в HTML видны все атрибуты!
var attrs = elem.attributes; // (4) можно получить коллекцию атрибутов
for (var i = 0; i < attrs.length; i++) {
    alert( attrs[i].name + " = " + attrs[i].value );
}


var a = document.getElementById('testId3');
a.href = '/';
alert( 'атрибут:' + a.getAttribute('href') ); // '/'
alert( 'свойство:' + a.href );  // полный URL


var input = document.getElementById('testId4');
// работа с checked через атрибут
alert( input.getAttribute('checked') ); // пустая строка
input.removeAttribute('checked'); // снять галочку

// работа с checked через свойство
alert( input.checked ); // false <-- может быть только true/false
input.checked = true; // поставить галочку (при этом атрибут в элементе не появится)


//Изменение некоторых свойств обновляет атрибут. Но это скорее исключение, чем правило.
//Чаще синхронизация – односторонняя: свойство зависит от атрибута, но не наоборот.
var input = document.getElementById('testId5');
input.value = 'new'; // поменяли свойство
alert( input.getAttribute('value') ); // 'markup', не изменилось!

input.setAttribute('value', 'new'); // поменяли атрибут
alert( input.value ); // 'new', input.value изменилось!
//Например, можно взять изначальное значение из атрибута и сравнить со свойством,
// чтобы узнать, изменилось ли значение.
// А при необходимости и перезаписать свойство атрибутом, отменив изменения.


//-----Классы в виде строки: className------
var div = document.getElementById('testId6');
// прочитать класс элемента
alert( div.className ); // testClass

// поменять класс элемента (у класса меняется даже при изменении свойства)
div.className = "testClass1 testClass2";
alert( div.className ); // testClass1 testClass2
alert( div.getAttribute('class') ); // testClass1 testClass2


//-----Классы в виде объекта: classList-------
//Методы classList:
//elem.classList.contains("class") – возвращает true/false, в зависимости от того, есть ли у элемента класс class.
//elem.classList.add/remove("class") – добавляет/удаляет класс class
//elem.classList.toggle("class") – если класса class нет, добавляет его, если есть – удаляет.
var div = document.getElementById('testId7');
var classList = div.classList;
classList.remove('page'); // удалить класс
classList.add('post'); // добавить класс
for (var i = 0; i < classList.length; i++) { // перечислить классы
    alert( classList[i] ); // main, затем post
}
alert( classList.contains('post') ); // проверить наличие класса
alert( div.className ); // main post, тоже работает


//---------Нестандартные атрибуты----------
var div = document.getElementById('testId8');
//Для нестандартных атрибутов DOM-свойство не создаётся.
alert( div.id ); // testId8
alert( div.about ); // undefined
//но можно получить через getAttribute
alert( elem.getAttribute('About') ); // (1) 'Elephant', атрибут получен


//--------Свойство dataset, data-атрибуты--------
//Стандарт HTML5 специально разрешает атрибуты data-* и резервирует их для пользовательских данных.
// При этом во всех браузерах, кроме IE10-, к таким атрибутам можно обратиться не только как к атрибутам,
// но и как к свойствам, при помощи специального свойства dataset:
var elem = document.getElementById('testId9');
alert( elem.dataset.about ); // Elephant

//название data-user-location трансформировалось в dataset.userLocation.
// Дефис превращается в большую букву:
alert( elem.dataset.userLocation ); // street


//--------задачи------
//---------- 1
//Получите div в переменную.
//Получите значение атрибута "data-widget-name" в переменную.
//Выведите его.
var elem = document.getElementById('testId10');
alert(elem.dataset.widgetName);
//или
alert(elem.getAttribute('data-widget-name'));

//---------- 2
//Сделайте желтыми внешние ссылки, добавив им класс external.
//Все ссылки без href, без протокола и начинающиеся с http://internal.com считаются внутренними.
//код может быть таким:
var links = document.getElementById('testId11').querySelectorAll('a');
for (var i = 0; i < links.length; i++) {
    var a = links[i];
    var href = a.getAttribute('href');
    if (!href) continue; // нет атрибута
    if (href.indexOf('://') == -1) continue; // без протокола
    if (href.indexOf('http://internal.com') === 0) continue; // внутренняя
    a.classList.add('external');
}
//Удобнее и эффективнее здесь – указать проверки для href прямо в CSS-селекторе:
// ищем все ссылки, у которых в href есть протокол,
// но адрес начинается не с http://internal.com
var css = '#testId11 a[href*="://"]:not([href^="http://internal.com"])';
var links = document.querySelectorAll(css);
for (var i = 0; i < links.length; i++) {
    links[i].classList.add('external');
}



//----------------------------------Методы contains и compareDocumentPosition-------------------------------------------

//--------Метод contains для проверки на вложенность-------
//Синтаксис:
var result = parent.contains(child);
//Возвращает true, если parent содержит child или parent == child.

//--------Метод compareDocumentPosition для порядка узлов-------
var result = nodeA.compareDocumentPosition(nodeB);
//Возвращаемое значение – битовая маска, биты в которой означают следующее:
//Биты	Число	Значение
//000000	0	nodeA и nodeB -- один и тот же узел
//000001	1	Узлы в разных документах (или один из них не в документе)
//000010	2	nodeB предшествует nodeA (в порядке обхода документа)
//000100	4	nodeA предшествует nodeB
//001000	8	nodeB содержит nodeA
//010000	16	nodeA содержит nodeB



//---------------------------------------Добавление и удаление узлов-----------------------------------------------------

//------Создание сообщения-------
//В нашем случае мы хотим сделать DOM-элемент div, дать ему классы и заполнить текстом:
var div = document.createElement('div');
div.className = "alert";
div.innerHTML = "<strong>Ура!</strong> Вы прочитали это важное сообщение.";
//После этого кода у нас есть готовый DOM-элемент.
// Пока что он присвоен в переменную div, но не виден, так как никак не связан со страницей.


//--------Добавление элемента: appendChild, insertBefore---------
//Все методы вставки автоматически удаляют вставляемый элемент со старого места.

//appendChild
//Добавляет elem в конец дочерних элементов parentElem.
var parent = document.getElementById('testId12');
parent.appendChild(div);
//пример 2
var list = document.getElementById('testId13');
var newLi = document.createElement('li');
newLi.innerHTML = 'Привет, мир!';
list.appendChild(newLi);


//insertBefore
var newLi = document.createElement('li');
newLi.innerHTML = 'Привет, мир!';
var list = document.getElementById('testId14');
list.insertBefore(newLi, list.children[1]);
//Для вставки элемента в начало достаточно указать, что вставлять будем перед первым потомком:
list.insertBefore(newLi, list.firstChild);

//если вторым аргументом указать null, то insertBefore сработает как appendChild
list.insertBefore(newLi, null);
// то же, что и:
list.appendChild(newLi);

//пример с созданием
var div = document.createElement('div');
div.className = "alert";
div.innerHTML = "<strong>Ура!</strong> Вы прочитали это важное сообщение.";
document.body.insertBefore(div, document.body.firstChild);



//---------Клонирование узлов: cloneNode-----------
//Вызов elem.cloneNode(true) создаст «глубокую» копию элемента – вместе с атрибутами, включая подэлементы.
//Если же вызвать с аргументом false, то копия будет сделана без дочерних элементов
var div = document.createElement('div');
div.className = "alert alert-success";
div.innerHTML = "<strong>Ура!</strong> Вы прочитали это важное сообщение.";

document.body.insertBefore(div, document.body.firstChild);
// создать копию узла
var div2 = div.cloneNode(true);
// копию можно подправить
div2.querySelector('strong').innerHTML = 'Супер!';
// вставим её после текущего сообщения
div.parentNode.insertBefore(div2, div.nextSibling);


//--------Удаление узлов: removeChild---------
//---parentElem.removeChild(elem)---
//Удаляет elem из списка детей parentElem.
var div = document.createElement('div');
div.className = "alert alert-success";
div.innerHTML = "<strong>Ура!</strong> Вы прочитали это важное сообщение.";

document.body.insertBefore(div, document.body.firstChild);

setTimeout(function() {
    div.parentNode.removeChild(div);
}, 1000);

//---parentElem.replaceChild(newElem, elem)---
//Среди детей parentElem удаляет elem и вставляет на его место newElem.

//---elem.remove()---
//который удаляет элемент напрямую

//полифилл для remove()
if (!Element.prototype.remove) {
    Element.prototype.remove = function remove() {
        if (this.parentNode) {
            this.parentNode.removeChild(this);
        }
    };
}

//пишем insertAfter
//т.к. insertBefore со вторым аргументом null работает как appendChild, то можно так:
function insertAfter(elem, refElem) {
    return refElem.parentNode.insertBefore(elem, refElem.nextSibling);
}

//пишем removeChildren
function removeChildren(elem) {
    while(elem.lastChild) {
        elem.removeChild(elem.lastChild);
    }
}

//динамический список
var list = document.getElementById('testId15');
var ul = document.createElement('ul');
var text = prompt("Введите элемент списка");
while (text){
    var li = document.createElement('li');
    li.appendChild(document.createTextNode(text));
    ul.appendChild(li);
    text = prompt("Введите следующий элемент списка");
}
list.appendChild(ul);

//Создайте дерево из объекта
var data = {
    "Рыбы": {
        "Форель": {},
        "Щука": {}
    },

    "Деревья": {
        "Хвойные": {
            "Лиственница": {},
            "Ель": {}
        },
        "Цветковые": {
            "Берёза": {},
            "Тополь": {}
        }
    }
};
var list = document.getElementById('testId16');
createTree(list, data);

function createTree(container, data) {
    var ul = document.createElement('ul');
    container.appendChild(ul);
    for(let o in data){
        var li = document.createElement('li');
        li.appendChild(document.createTextNode(o));
        ul.appendChild(li);
        if(Object.keys(data[o]).length > 0){
            createTree(li, data[o]);
        }
    }
}




//------------------------------Мультивставка: insertAdjacentHTML и DocumentFragment----------------------------------------
//Обычные методы вставки работают с одним узлом. Но есть и способы вставлять множество узлов одновременно.


//-----insertAdjacent*-----
elem.insertAdjacentHTML(where, html);
//html: Строка HTML, которую нужно вставить
//where: Куда по отношению к elem вставлять строку. Всего четыре варианта:
//
//1. `beforeBegin` -- перед `elem`.
//2. `afterBegin` -- внутрь `elem`, в самое начало.
//3. `beforeEnd` -- внутрь `elem`, в конец.
//4. `afterEnd` -- после `elem`.

elem.insertAdjacentElement(where, newElem); // – вставляет в произвольное место не строку HTML, а элемент newElem.
elem.insertAdjacentText(where, text); // – создаёт текстовый узел из строки text и вставляет его в указанное место относительно elem.


//--------DocumentFragment--------
//Вставить пачку узлов единовременно поможет DocumentFragment.
// Это особенный кросс-браузерный DOM-объект, который похож на обычный DOM-узел, но им не является.
//Его «Фишка» заключается в том, что когда DocumentFragment вставляется в DOM – то он исчезает,
// а вместо него вставляются его дети
// хотим вставить в список UL много LI

// делаем вспомогательный DocumentFragment
// var fragment = document.createDocumentFragment();
//
// for (цикл по li) {
//     fragment.appendChild(list[i]); // вставить каждый LI в DocumentFragment
// }
//
// ul.appendChild(fragment); // вместо фрагмента вставятся элементы списка




//--------------------------------------------Стили, getComputedStyle-----------------------------------------------------
//--------Стили элемента: свойство style------
//Свойство style содержит лишь тот стиль, который указан в атрибуте элемента, без учёта каскада CSS.
document.body.style.margin = '20px';
alert( document.body.style.marginTop ); // 20px!
document.body.style.color = '#abc';
alert( document.body.style.color ); // rgb(170, 187, 204)

//-------Строка стилей style.cssText---------
//Свойство style.cssText позволяет поставить стиль целиком в виде строки.
//При установке style.cssText все предыдущие свойства style удаляются.
var div = document.getElementById('table');
div.style.cssText="color: red !important; \
    background-color: yellow; \
    width: 100px; \
    text-align: center; \
    blabla: 5; \
  ";
alert(div.style.cssText);
//Браузер разбирает строку style.cssText и применяет известные ему свойства.
// Неизвестные, наподобие blabla, большинство браузеров просто проигнорируют.

//----------Полный стиль из getComputedStyle---------
//Для того, чтобы получить текущее используемое значение свойства,
// используется метод window.getComputedStyle, описанный в стандарте DOM Level 2.

//getComputedStyle(element[, pseudo])
//element - Элемент, значения для которого нужно получить
//pseudo - Указывается, если нужен стиль псевдо-элемента, например "::before".
// Пустая строка или отсутствие аргумента означают сам элемент.

var computedStyle = getComputedStyle(document.body);
alert( computedStyle.marginTop ); // выведет отступ в пикселях
alert( computedStyle.color ); // выведет цвет





//--------------------------------------------Введение в браузерные события----------------------------------------

//В этом примере создаем несколько обработчиков на несоклько элементов
//
//1) onclick
//2) addEventListener
//3) событие event и его свойства
//      3.1 target
//      3.2 currentTarget
//4)Прекращение всплытия
//      4.1 stopPropagation
//      4.2 stopImmediatePropagation
let div = document.getElementById('testId18');
div.onclick = (e) => {
    console.log('div.onclick');
    console.log('this:');
    console.log(this);//window
    console.log('event:');
    console.log(e);//click{..}
    console.log('target:');
    console.log(e.target);//место куда кликаем
    console.log('currentTarget:');
    console.log(e.currentTarget);//<div id="testId18" class="selectable"> место где установлен обработчик
    console.log('---------------------------------------');

};
div.addEventListener('click', clickHandler);
let div2 = document.getElementById('id18_1');
div2.addEventListener('click', clickHandler);
let div3 = document.getElementById('id18_2');
div3.addEventListener('click', clickHandler);
let a = document.getElementById('id18_3');
a.addEventListener('click', clickHandler);
a.addEventListener('click', clickHandler2);//тут stopImmediatePropagation, хандлеры ниже и всплывающие не выполнятся
a.addEventListener('click', clickHandler3);
a.addEventListener('click', ()=>{
    console.log('a#id18_3 => handler4');
    console.log(this);//window
});

function clickHandler(e) {
    console.log('a#id18_3 handler 1');
    console.log('this:');
    console.log(this);//==currentTarget
    console.log('event:');
    console.log(e);//click{..}
    console.log('target:');
    console.log(e.target);//место куда кликаем
    console.log('currentTarget:');
    console.log(e.currentTarget);// место где установлен обработчик
    console.log('---------------------------------------');
}
function clickHandler2(e) {
    console.log('a#id18_3 handler 2');

    //дальше не будет всплывать/погружаться +
    // не будут выполняться обработичики объявленные после этого, т.е. 3 и 4
    e.stopImmediatePropagation();

    //e.stopPropagation();//а это делает чтобы просто дальше не всплывало/погружалось, но хандлеры 3 и 4 выполнятся
    console.log('---------------------------------------');
}
function clickHandler3(e) {
    console.log('a#id18_3 handler 3');
    console.log('---------------------------------------');
}





//--------------------------------------------Погружение----------------------------------------
//testId19
//Чтобы перехватить событие на погружении необходимо указать true в addEventListener

//Вариант с поиском элементов по селектору
var elems = document.querySelectorAll('#id19_1,#id19_2,#id19_3');
for (var i = 0; i < elems.length; i++) {
    elems[i].addEventListener("click", highlightThisDive, true);
    elems[i].addEventListener("click", highlightThisAscent, false);
}

//Вариант через поиск по селектору внутри тэга
var testId19 = document.getElementById('testId19');
var elems = testId19.querySelectorAll('*');
console.log(elems);
for (var i = 0; i < elems.length; i++) {
    elems[i].addEventListener("click", highlightThisDive, true);
    elems[i].addEventListener("click", highlightThisAscent, false);
}

//Вариант с рекурсивным поиском элементов
var elems = document.querySelector('#testId19').childNodes;
console.log(elems);
recursiveAddListeners(elems);
function recursiveAddListeners(elems) {
    if (!elems.forEach) {
        //если не настоящий массив, то копируем в настоящий
        elems = Array.prototype.slice.call(elems);
    }
    elems.forEach((el)=> {
        if (el instanceof Element) {
            el.addEventListener("click", highlightThisDive, true);
            el.addEventListener("click", highlightThisAscent, false);
        }
        if (el.children && el.children.length>0) {
            recursiveAddListeners(el.children);
        }
    });
}

//Функции для всех вариантов
function highlightThisDive() {
    this.style.backgroundColor = 'yellow';
    alert('Погружение\n'+this.tagName);
    this.style.backgroundColor = '';
}
function highlightThisAscent() {
    this.style.backgroundColor = 'grey';
    alert('Всплытие\n'+this.tagName);
    this.style.backgroundColor = '';
}




//--------------------------------------------Координаты окна, страницы и евента----------------------------------------

//При движении мыши показываются координаты и инфа о  теге на котором мышь сейчас
document.body.addEventListener("mousemove", mouseMoveInfo);
function mouseMoveInfo(event) {
    var div = document.getElementById('mouseCoord');
    if (!div) {
        div = document.createElement('div');
    }
    div.setAttribute('id', 'mouseCoord');
    div.style.position = "absolute";
    div.style.padding = "3px";
    div.style.border = "solid 2px";
    div.style.backgroundColor = "#333434";
    div.style.color = "#bdbdbd";
    div.innerHTML = `clientX=${event.clientX},<br>
                        clientY=${event.clientY},<br>
                        pageX=${event.pageX},<br>
                        pageY=${event.pageY},<br>
                        targetTag=${event.target.tagName},<br>
                        targetId=${event.target.getAttribute('id')},<br>
                        targetClass=${event.target.getAttribute('class')}`;
    //Ставим координаты элемента
    //Вычитаем div.offsetHeight чтобы сдвинуть див вверх на свой размер
    //и 3px чтобы было повыше курсора
    //Можно и div.clientWidth/div.clientHeight но они не учитывают бордер
    //event.clientX/event.clientY - это координаты окна, не учитывают прокрутку
    div.style.left = `${event.pageX}px`;
    div.style.top = `${event.pageY - div.offsetHeight - 3}px`;
    document.body.appendChild(div);
}

//------------------------------Работа со скроллами---------------------------------------

//не самые корректные способы получить высоту страницы с учетом скролла
console.log('высота страницы : document.body.scrollHeight='+document.body.scrollHeight);
console.log('высота страницы : document.body.offsetHeight='+document.body.offsetHeight);

//корректно определяем высоту страницы с учетом прокрутки
var scrollHeight = Math.max(
    document.body.scrollHeight, document.documentElement.scrollHeight,
    document.body.offsetHeight, document.documentElement.offsetHeight,
    document.body.clientHeight, document.documentElement.clientHeight
);
console.log('высота страницы : scrollHeight=' + scrollHeight);

console.log('положение верт скролла : window.pageYOffset='+window.pageYOffset);
console.log('высота видимой части окна : document.documentElement.clientHeight='+document.documentElement.clientHeight);
console.log('scrollHeight - document.documentElement.clientHeight='+(scrollHeight-document.documentElement.clientHeight));
window.onscroll = function() {
    //Когда прокрутили до самого низа
    if(window.pageYOffset >= (scrollHeight-document.documentElement.clientHeight)){
        alert("---------------------------end of page-----------------------------")
    }
};



//------------------------------------------------Тестовое задание из JL------------------------------------------------
//---------Создаем такой блок и делаем обработку события---------
// <div class="collapse">
//     <a class="collapse-toggle" type="button" href="/">
//          Скрыть
//     </a>
//
//     <div class="collapse-content">
//          Скрываемое содержимое
//     </div>
// </div>

var div = document.createElement('div');
div.setAttribute('class', 'collapse');
var a = document.createElement('a');
a.setAttribute('class', 'collapse-toggle');
a.setAttribute('type', 'button');
a.setAttribute('href', '/');
a.textContent = 'Скрыть';
div.appendChild(a);
var div2 = document.createElement('div');
div2.setAttribute('class', 'collapse-content');
div2.textContent = 'Скрываемое содержимое';
div.appendChild(div2);

// document.body.appendChild(div);
document.getElementById('testId17').appendChild(div);


document.getElementsByClassName('collapse-toggle')[0].addEventListener('click', (e) => {
    e.preventDefault();
    let div = document.getElementsByClassName('collapse-content')[0];
    e.target.textContent = div.hidden ? 'Скрыть' : 'Показать';
    div.hidden = !div.hidden;
});