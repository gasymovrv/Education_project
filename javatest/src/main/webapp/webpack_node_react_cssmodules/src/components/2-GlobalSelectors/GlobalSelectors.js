import styles from './GlobalSelectors.css';

import React, {Component} from 'react';

export default class GlobalSelectors extends Component {

  render() {
    return (
      <div className={ styles.root }>
        <div className={styles.root2}>
          <p className="text">Global Selectors</p>
          <p className="text">Global Selectors2</p>
          <p className="text">Global Selectors3</p>
        </div>
        <p className="text">Global Selectors4</p>
      </div>
    );
  }

};
