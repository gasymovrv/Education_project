import React from 'react';

/**
 * Пример шаблона Render Callback
 */
export default class RenderCallbackExample extends React.Component {

    state = {
        user: null,
    };

    componentDidMount () {
        setTimeout(() => {
                this.setState({user: {name: this.props.username}})
            },
            2000);
    }

    render () {
        return this.props.children(this.state.user)
    }
}

export function Loading() {
    return <h4>Загрузка...</h4>
}

export function Profile({user}) {
    return <h4>Имя: {user? user.name : 'error'}</h4>
}