import React, {useEffect, useState} from 'react';

export default function HooksExample() {
    //Деструктурируем массив, похоже useState возвращает именно массив
    const [count, setCount] = useState(0);//0 - это дефолтное значение для count

    // Similar to componentDidMount and componentDidUpdate:
    useEffect(() => {
        // Меняем название вкладки
        document.title = `${count} click`;
    });

    return (
        <div>
            <p>You clicked {count} times</p>
            <button onClick={() => setCount(count + 1)}>
                Click me
            </button>
        </div>
    );
}
