import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Ticker from './components/Ticker';

class App extends Component {
  render() {
    return (
        <Ticker price={0.75} pair="BTC/USD"/>
    );
  }
}

export default App;
