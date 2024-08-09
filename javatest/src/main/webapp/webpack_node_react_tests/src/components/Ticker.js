import React from 'react';
import PropTypes from 'prop-types'

class Ticker extends React.Component {
    static propTypes = {
        pair: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        buy: PropTypes.func
    };

    static defaultProps = {
        buy: () => {
        }
    };

    state = {
        agreeGiven: false
    };

    giveAgreement = () => {
        this.setState({agreeGiven: true})
    };

    render() {
        const {pair, price, buy} = this.props;
        const {agreeGiven} = this.state;
        return (
            <div className="ticker">
                <h1>{pair}</h1>
                <h3>Price {price}</h3>
                <hr/>
                <div>
                    {
                        !agreeGiven && (
                            <label>
                                <input type="checkbox" onChange={this.giveAgreement}/>
                                I agree
                            </label>
                        )
                    }
                    {
                        (price < 1000) && agreeGiven && (
                            <button className="buyIndicator" onClick={buy}>
                                buy
                            </button>
                        )
                    }
                </div>
            </div>
        );
    }

}

export default Ticker;