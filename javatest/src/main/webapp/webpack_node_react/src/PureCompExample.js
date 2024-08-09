import React from 'react';

/**
 * Пример когда PureComponent не перерисует компонент:
 * состояние родителя поменялось, но в детей он передает одно и тоже
 */
export default class PureCompExample extends React.Component {
    state = {value: 0};

    componentDidMount() {
        setInterval(() => {
            this.setState({value: Math.random()})
        }, 1000)
    }

    render() {
        return (
            <div>
                <h4>PureCompExample</h4>
                <PureChild value="fixed value"/>
                <ImpureChild value="fixed value"/>
            </div>
        )
    }
}

class PureChild extends React.PureComponent {
    render() {
        console.log('rendering PureChild');
        return <div>PureChild value: <b>{this.props.value}</b></div>
    }
}

class ImpureChild extends React.Component {
    render() {
        console.log('rendering ImpureChild');
        return <div className='box'>ImpureChild value:  <b>{this.props.value}</b></div>
    }
}