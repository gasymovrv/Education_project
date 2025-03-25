import React from 'react';
import {log} from './helpers/consoleLog';

/**
 * Это просто образец с примерами работы методов жизненного цикла
 * и реализации state-full компонента.
 */
class LifecycleExample extends React.Component {


    //--------------------------Методы жизненного цикла---------------------------------



    //--------------------------Инициализация------------

    // 1.
    // срабатывает при создании объекта текущего класса (компонента)
    // constructor = componentWillMount
    constructor(props){
        super(props);
        this.state = {
            time: new Date()
        };
        this.updatesCounter = 0;
        log('constructor');
    }


    //new
    //Совместно со старыми нельзя вызывать
    //static getDerivedStateFromProps(props, state){}

    componentWillMount(){
        log('componentWillMount');
    }

    // 2.
    // вызывается автоматически для отрисовки компонента
    render() {
        const {isActive, onToggleWatch, watchText} = this.props;
        if(typeof onToggleWatch !== 'function' || watchText === undefined || isActive ===undefined ){
            return null;
        }
        const {time} =this.state;
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
        log('render');
        return (
            <div className='box'>
                <h4>LifecycleExample</h4>
                <input type='button' onClick={onToggleWatch} value={watchText}/>
                <p ref={this.handleRef}
                   className={this.props.isActive ? '' : 'disabled-watch'}>{hour} : {min} : {sec}</p>
            </div>
        )
    }

    // 3.
    // срабатывает после появления текущего компонента в DOM (вызова render())
    //нужно для 1-го запуска часов
    componentDidMount(){
        if(this.props.isActive) {
            this.activateWatch(1000);
        } else {
            this.deactivateWatch();
        }
        document.addEventListener('click', this.onDocumentClick);
        log('componentDidMount');
        log('-----------------------end init cycle---------------------------------------');
    }



    //--------------------------Обновление----------
    //new
    //Совместно со старыми нельзя вызывать
    // static getDerivedStateFromProps(props, state)


    // 1.
    //вызовется только при setState **родителей** даже когда пропсы не менялись
    //nextProp - обновленные пропсы
    //this.props - старые пропсы
    //нужно для работы переключателя часов
    componentWillReceiveProps(nextProp) {
        //если стало активно - включаем интервал
        if (nextProp.isActive && !this.props.isActive) {
            this.activateWatch(1000);
        } else if (!nextProp.isActive && this.props.isActive) {
            this.deactivateWatch();
        }
        log('componentWillReceiveProps');
    }

    // 2.
    //вызовется при setState **родителей** или **внутри** самого компонента
    shouldComponentUpdate(nextProps, nextState){
        log('shouldComponentUpdate');
        if(this.updatesCounter >= 5){
            this.deactivateWatch();
            return false;
        } else {
            return true;
        }
    }

    // 3.
    //вызовется при setState **родителей** или **внутри** самого компонента, прямо перед ререндерингом
    componentWillUpdate(nextProps, nextState){
        log('componentWillUpdate');
    }

    // 4.
    //render()

    //new
    //Совместно со старыми нельзя вызывать
    // getSnapshotBeforeUpdate(prevProps, prevState)

    // 5.
    //после отрисовки
    componentDidUpdate(prevProps, prevState){
        this.updatesCounter = this.updatesCounter+1;
        log('componentDidUpdate');
        log(this.updatesCounter + '-----------------------end update cycle---------------------------------------');
    }



    //--------------------------Удаление----------

    // 1.
    // срабатывает после удаления текущего компонента из DOM
    // setState - НЕЛЬЗЯ
    componentWillUnmount(){
        document.removeEventListener('click', this.onDocumentClick);
        this.deactivateWatch();
        log('-------------------componentWillUnmount-------------------------------');
    }









    //--------------------------Обработчики и прочее---------------------------------


    //пишем через стрелочную чтобы связать this
    updateTime = () => {
        this.setState({time: new Date()},() => log('setState=', this.state));
    };

    activateWatch = (interval) => {
        this.updateTime();
        this.interval= setInterval(this.updateTime,interval);
    };

    deactivateWatch = () => {
        this.updatesCounter = 0;
        clearInterval(this.interval);
    };

    //Обработка ref
    handleRef = (node) => {
        this.watch = node;
    };
    //ref исп-ся если нужно по разному реагировать в зависимости от DOM-элемента
    onDocumentClick = (event)=>{
        if(this.watch === event.target){
            alert('Попали на часы!!! (тестируем ref)')
        }
    };

}

/**
 * Это обертка чтобы не писать логику в App,
 * это можно конечно перенести внутрь LifecycleExample и сделать как в NewLifecycleExample.js
 * Но здесь специально именно так чтобы посмотреть работу componentWillReceiveProps
*/
export default class Wrapper extends React.Component {
    state = {
        isActiveWatch: false,
        watchText: 'Включить часы'
    };

    onToggleWatch = () => {
        const {isActiveWatch} = this.state;
        if (!isActiveWatch) {
            this.setState({
                isActiveWatch: true,
                watchText: 'Выключить часы'
            });
        } else {
            this.setState({
                isActiveWatch: false,
                watchText: 'Включить часы'
            });
        }
    };

    render(){
        const {isActiveWatch, watchText} = this.state;
        return <LifecycleExample isActive={isActiveWatch} watchText={watchText} onToggleWatch={this.onToggleWatch}/>
    }
}