## JS-тесты для react (jest, enzyme и т.д.).
#### Здесь 2 типа тестов:
+ Первые коммиты - создание снэпшотов (html компонента) и сравнение их.
    + Пример простого теста. 
    Он будет падать при любом изменении компонента Ticker.
    Нужно вручную смотреть результат и если получился корректный рендер - можно обновлять снэпшот
    ```jsx harmony
        it('Correctly renders 1', () => {
          //Подготовка
          const component = renderer.create(<Ticker price={1} pair="DEMO/DEMO2"/>);   
          //Рендер
          const tree = component.toJSON();   
          //Проверка
          expect(tree).toMatchSnapshot();
        });
    ```
+ Далее меняем тип тестов на сравнение переданных пропсов и результата в определенном отрисованном DOM-элементе
```jsx harmony
    it('Correctly renders buyIndicator less 1000', () => {
        const tree = shallow(<Ticker price={700} pair="DEMO/DEMO2"/>);
        //вместо tree.find('input').simulate('change'); задаем state напрямую
        tree.setState({agreeGiven: true});
        expect(tree.find('.buyIndicator')).toHaveLength(1);
    });
```