import React from 'react';
import {log} from './helpers/consoleLog';

export default class ErrorBoundary extends React.Component {
    constructor(props) {
        super(props);
        this.state = { hasError: false };
    }

    //сначала это
    static getDerivedStateFromError(error) {
        // Update state so the next render will show the fallback UI.
        log('getDerivedStateFromError', error);
        return { hasError: true , error};
    }

    //потом это
    componentDidCatch(error, info) {
        // Example "componentStack":
        //   in ComponentThatThrows (created by App)
        //   in ErrorBoundary (created by App)
        //   in div (created by App)
        //   in App
        log('componentDidCatch', info.componentStack);
        // this.setState({ //т.к. есть getDerivedStateFromError, то здесь setState уже не сработает
        //     error,
        //     errorInfo: info
        // });
    }

    render() {
        if (this.state.hasError) {
            // You can render any custom fallback UI
            return (
                <div>
                    <h1>Something went wrong.</h1>
                    <details>
                        {this.state.error && this.state.error.toString()}
                    </details>
                </div>
            );
        }

        return this.props.children;
    }
}