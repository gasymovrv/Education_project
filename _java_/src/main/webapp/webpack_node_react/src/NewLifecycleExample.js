import React from 'react';
import {log} from './helpers/consoleLog';

/**
 * Это просто образец с примерами работы методов жизненного цикла
 * и реализации state-full компонента.
 * Используется только в Header
 */
export default class NewLifecycleExample extends React.Component {


    //--------------------------Методы жизненного цикла с версии 16.3---------------------------------



    //--------------------------Инициализация------------

    // 1.
    // срабатывает при создании объекта текущего класса (компонента)
    // constructor = componentWillMount
    constructor(props){
        super(props);
        this.state = {
            time: new Date(),
            isActiveWatch: false,
            watchText: 'Включить часы'
        };
        this.updatesCounter = 0;
        this.mouseCoord = {};
        log('constructor');
    }


    // 2.
    //Типо замена componentWillReceiveProps, но
    //с ним стало все сложней, this=undefined(хотя для static должно быть this = NewLifecycleExample)
    //и он уже не подходит для реализации как в LifecycleExample.js
    static getDerivedStateFromProps(nextProps, prevState){
        log('getDerivedStateFromProps, this', this);
        return null;
    }

    // 3.
    // вызывается автоматически для отрисовки компонента
    render() {
        const {isActiveWatch, watchText, time} = this.state;
        log('render');
        return (
            <div className='box'>
                <h4>NewLifecycleExample</h4>
                <button onClick={this.onToggleWatch}>{watchText}</button>
                <Watch isActive={isActiveWatch} time={time} onRef={this.watchRefHandler}/>
            </div>
        )
    }

    // 4.
    // срабатывает после появления текущего компонента в DOM (вызова render())
    //нужно для 1-го запуска часов
    componentDidMount(){
        log('componentDidMount');
        log('-----------------------end init cycle---------------------------------------');
    }



    //--------------------------Обновление----------
    // 1.
    // static getDerivedStateFromProps(props, state)

    // 2.
    //вызовется при setState **родителей** или **внутри** самого компонента
    shouldComponentUpdate(nextProps, nextState){
        log('shouldComponentUpdate');
        if(this.updatesCounter >= 5){
            clearInterval(this.interval);
            this.updatesCounter = 0;
            return false;
        } else {
            return true;
        }
    }

    // 3.
    //render()

    // 4.
    //вызовется прямо перед изменениями из VDOM, которые должны быть отображены в DOM
    // Например у вас есть приложение, в котором новые сообщения добавляются сверху экрана –
    // если пользователь будет скроллить вниз, и добавится новое сообщение, экран будет «прыгать» и это сделает UI тяжелее в использовании.
    // Добавлением getSnapshotBeforeUpdate вы сможете рассчитать текущее положение скролла и восстанавливать его через апдейт DOM-а.
    getSnapshotBeforeUpdate(prevProps, prevState){
        log('getSnapshotBeforeUpdate, snapshot=', this.mouseCoord);
        //Создаем какой-то snapshot, он затем попадет в componentDidUpdate
        //Я сделал перемещение часов на последнее место где была мышь,
        // чтобы это имело смысл нужно добавить зависимостей от текущего состояния элемента или пропсов..
        return this.mouseCoord;
    }

    // 5.
    //после отрисовки
    componentDidUpdate(prevProps, prevState, snapshot){
        this.updatesCounter = this.updatesCounter+1;
        log('componentDidUpdate, snapshot=', snapshot);
        if(this.state.isActiveWatch && snapshot && this.watchRef){
            this.watchRef.style.position = "absolute";
            this.watchRef.style.border = "solid 2px";
            this.watchRef.style.left = snapshot.pageX;
            this.watchRef.style.top = snapshot.pageY;
        }
        log(this.updatesCounter + '-----------------------end update cycle---------------------------------------');
    }



    //--------------------------Удаление----------

    // 1.
    // срабатывает после удаления текущего компонента из DOM
    // setState - НЕЛЬЗЯ
    componentWillUnmount(){
        document.removeEventListener("mousemove", this.mouseMoveInfo);
        clearInterval(this.interval);
        log('-------------------componentWillUnmount-------------------------------');
    }







    //--------------------------Обработчики и прочее---------------------------------

    onToggleWatch = () => {
        const {isActiveWatch} = this.state;
        this.watchRef.removeAttribute("style");
        if (!isActiveWatch) {
            this.setState({
                isActiveWatch: true,
                watchText: 'Выключить часы'
            });
            this.interval= setInterval(()=>this.setState({time: new Date()},() => log('setState=', this.state)), 1000);
            document.addEventListener("mousemove", this.mouseMoveInfo);
        } else {
            this.setState({
                isActiveWatch: false,
                watchText: 'Включить часы'
            });
            clearInterval(this.interval);
            document.removeEventListener("mousemove", this.mouseMoveInfo);
        }
    };

    mouseMoveInfo =(event) => {
        this.mouseCoord = {pageX : `${event.pageX}px`, pageY : `${event.pageY}px`};
    };

    watchRefHandler = (node)=>{
        this.watchRef = node;
    }
}


function Watch({isActive, time, onRef}) {
    if(isActive === undefined ){
        return {};
    }
    let sec = time.getSeconds();
    let min = time.getMinutes();
    let hour = time.getHours();
    if(sec < 10){
        sec='0'+sec;
    }
    if(min < 10){
        min='0'+min;
    }
    if(hour < 10){
        hour='0'+hour;
    }
    return <div ref={onRef} className={isActive ? '' : 'disabled-watch'}>{hour} : {min} : {sec}</div>
}
