import React from 'react';
import {shallow} from 'enzyme';
import Ticker from './Ticker';

it('Correctly props', () => {
    const tree = shallow(<Ticker price={700} pair="DEMO/DEMO2"/>);
    //так проверяем результат рендеринга (наличие отображенных пропсов)
    expect(tree.contains(700)).toBe(true);
    expect(tree.contains("DEMO/DEMO2")).toBe(true);
});

it('Correctly renders buyIndicator less 1000', () => {
    const tree = shallow(<Ticker price={700} pair="DEMO/DEMO2"/>);
    //вместо tree.find('input').simulate('change'); задаем state напрямую
    tree.setState({agreeGiven: true});
    expect(tree.find('.buyIndicator')).toHaveLength(1);
});

it('Correctly renders buyIndicator more 1000', () => {
    const tree = shallow(<Ticker price={2000} pair="DEMO/DEMO2"/>);
    expect(tree.find('.buyIndicator')).toHaveLength(0);
});

it('does not show buy button by default', () => {
    const tree = shallow(<Ticker price={2000} pair="DEMO/DEMO2"/>);
    expect(tree.find('button')).toHaveLength(0);
});

it('shows buy button if I agreed', () => {
    const tree = shallow(<Ticker price={700} pair="DEMO/DEMO2"/>);
    //симулируем change
    tree.find('input').simulate('change');
    expect(tree.find('button')).toHaveLength(1);
});

it('should call function when buy button clicked', () => {
    const f = jest.fn();
    const tree = shallow(<Ticker price={700} pair="DEMO/DEMO2" buy={f}/>);
    //вместо tree.find('input').simulate('change'); задаем state напрямую
    tree.setState({agreeGiven: true});
    //симулируем клик
    tree.find('button').simulate('click');
    expect(f).toHaveBeenCalled;
});
