### Тестирование примеров из ```advanced.js```, ```base.js``` или ```ES6.js```
+ Для тестирования выбранного куска кода,
необходимо перенести его из указанных файлов
в ```test.js```
и открыть в браузере ```index.html```

### Тестирование примеров из ```dom.js```
+ Для тестирования выбранного куска кода, 
необходимо перенести его из указанного файла
в ```testDOM.js``` в глобальную ф-ию ```run()```
и открыть в браузере ```indexDOM.html```

### Тестирование примеров из ```advanced.js```, ```base.js``` или ```ES6.js``` через webpack-сборку
+ Для использования нужен ```Node.JS```
+ Если папки ```webpack_node_simple/node_modules``` нет, то нужно запустить ```npm install``` в ```src\main\webapp\webpack_node_simple```
+ Все собирается в файл ```dist/bundle.js```
+ Запускаем команду ```npm run dev-watch``` - сборка происходит автоматически каждый раз при изменении файлов (заимпорченных в ```forBundle.js```)
+ Запускаем команду ```npm run dev``` - единовременная сборка
+ Для тестирования выбранного куска кода, необходимо перенести его  в ```forBundle.js``` и открыть в браузере ```index.html```
+ Как все делалось 
    + Создаем ```package.json```
    + Ставим webpack ```npm i -g webpack```
    + Создаем файл настроек дял webpack ```webpack.config.js```
    + Создаем основной JS файл ```forBundle.js```
    + Подключаем в ```index.html``` файл сборки ```bundle.js```
    + Запускаем через ```npm run ...```
+ Комментарии к настройкам в ```package.json```:
```    
"dev": "webpack --mode development", //форматированный файл, ручная сборка
"dev-watch": "webpack --watch --mode development", //форматированный файл, авто сборка
"build": "webpack --mode production", //сжатый файл, ручная сборка
"watch": "webpack --watch --mode production" //сжатый файл, авто сборка
```

### Запуск примеров css модулей
+ Для использования нужен ```Node.JS```
+ Если папки ```webpack_node_react_cssmodules/node_modules``` нет, то нужно запустить ```npm install``` в ```src\main\webapp\webpack_node_react_cssmodules```
+ Запуск сборки и дев-сервера: ```npm start & open http://localhost:8080```