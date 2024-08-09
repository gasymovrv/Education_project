import React, {Component} from 'react';
import NewLifecycleExample from './NewLifecycleExample';

export default class App extends Component {
    render() {
        return (
            <div className="app">
                <div>

                    {/*<LifecycleExample/>*/}

                    {/*<PureCompExample/>*/}

                    {/*<ErrorBoundary>*/}
                        {/*<ErrorExample/>*/}
                    {/*</ErrorBoundary>*/}

                    {/*<RenderCallbackExample username='tylermcginnis33'>*/}
                        {/*{(user) => user === null*/}
                            {/*? <Loading />*/}
                            {/*: <Profile user={user} />}*/}

                    {/*</RenderCallbackExample>*/}

                    {/*<HooksExample/>*/}

                    <NewLifecycleExample/>

                </div>
            </div>
        );
    }
}
