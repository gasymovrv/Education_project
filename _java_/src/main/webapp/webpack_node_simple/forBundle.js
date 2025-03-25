import {log} from './helpers/consoleLog';

log('--------------------------------------------begin code from bundle.js---------------------------------------------------');







var oscar = {
    name: 'Oscar',
    meow: function() {
        // console.log(this.name + ' meows!');
        log('this=', this);
    }
};

var m = oscar.meow;
oscar.meow();//здесь this=oscar
m();//а здесь будет undefined, т.к. webpack добавляет в bundle.js "use strict"








log('--------------------------------------------end code from bundle.js---------------------------------------------------');
