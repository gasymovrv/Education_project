//Жадный/Ленивый самый простой пример
var reg1 = /a+/g;
var reg2 = /a+?/g;
var str = 'aaaaaa';
lg( str.match(reg1) ); // [ "aaaaaa" ]
lg( str.match(reg2) ); // [ "a", "a", "a", "a", "a", "a" ]
