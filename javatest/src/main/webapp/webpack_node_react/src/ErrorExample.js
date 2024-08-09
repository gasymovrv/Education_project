import React from 'react';

/**
 * Пример ошибки для ErrorBoundary.js
 * Пытаемся взять свойство у undefined
 */
export default class ErrorExample extends React.Component {

    state={
      any:'Пока ошибки нет'
    };

    componentDidMount() {
        setTimeout(() => {
                this.setState({any: undefined})
            },
            2000)
    }

    render() {
        return (
            <div>
                <h4>ErrorExample</h4>
                {this.state.any} {this.state.any.length}
            </div>
        )
    }
}