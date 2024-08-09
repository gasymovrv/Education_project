//---------- Скопируй сюда скрипт чтобы протестить, например из dom.js ----------------

let buffer;
let base64Portions = [];
let file = null;

function run() {

    //При движении мыши показываются координаты и инфа о  теге на котором мышь сейчас
    document.body.addEventListener('mousemove', mouseMoveInfo);

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
        div.style.top = `${event.pageY-div.offsetHeight-3}px`;
        document.body.appendChild(div);
    }

    //Определяем высоту страницы с учетом прокрутки
    var scrollHeight = Math.max(
        document.body.scrollHeight, document.documentElement.scrollHeight,
        document.body.offsetHeight, document.documentElement.offsetHeight,
        document.body.clientHeight, document.documentElement.clientHeight
    );
    console.log('высота страницы : scrollHeight=' + scrollHeight);
    console.log('высота страницы : document.body.scrollHeight='+document.body.scrollHeight);
    console.log('высота страницы : document.body.offsetHeight='+document.body.offsetHeight);
    console.log('положение верт скролла : window.pageYOffset=' + window.pageYOffset);
    console.log('document.documentElement.clientHeight=' + document.documentElement.clientHeight);
    console.log('scrollHeight - document.documentElement.clientHeight=' + (scrollHeight - document.documentElement.clientHeight));
    window.onscroll = function () {
        //Когда прокрутили до самого низа
        if (window.pageYOffset >= (scrollHeight - document.documentElement.clientHeight)) {
            alert('---------------------------end of page-----------------------------')
        }
    }

    document.getElementById('id20_1').addEventListener('change', onAddFiles);
    document.getElementById('id20_2').addEventListener('click', onHandleFile);
    document.getElementById('id20_3').addEventListener('click', onDownloadFile);


    const instanceOne = new SingletonClass('One');
    const instanceTwo = new SingletonClass('Two');
    console.log(`Name of instanceOne is "${instanceOne.getName()}"`);
    console.log(`Name of instanceTwo is "${instanceTwo.getName()}"`);
}

function onAddFiles(event) {
    file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.addEventListener('loadend', () => {
            buffer = reader.result;
            console.log('buffer from id20_1: ', buffer);
        });
        reader.readAsArrayBuffer(file);
    }
}

function onHandleFile() {
    let byteOffset = 0;
    let portionSize = 32768;
    while (true) {
        let length;
        if (buffer.byteLength <= byteOffset + portionSize) {
            length = buffer.byteLength - byteOffset;
        } else {
            length = portionSize;
        }
        if (length <= 0) {
            break;
        }
        console.log('byteOffset: ', byteOffset);

        let uint8Array = new Uint8Array(buffer, byteOffset, length);
        byteOffset += portionSize;

        base64Portions.push(window.btoa(String.fromCharCode.apply(null, uint8Array)));
    }
}

function onDownloadFile() {
    let t = base64Portions
        .map((v) => window.atob(v))
        .map((v) => toUi8Array(v))
        .reduce(
            (u, v) => {
                u.push(v);
                return u;
            },
            []
        );
    const blob = new Blob(t);
    base64Portions = [];
    const href = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = href;
    link.setAttribute('download', file.name);
    document.body.appendChild(link);
    link.click();
}

function toUi8Array(slice) {
    const byteNumbers = new Array(slice.length);
    for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
    }
    return new Uint8Array(byteNumbers);
}

class SingletonClass {
    constructor(name = '', age = 0) {
        if (!SingletonClass._instance) {
            SingletonClass._instance = this;
            this.name = name;
            this.age = age;
        }
        return SingletonClass._instance;
    }

    getName() {
        return this.name;
    }
}