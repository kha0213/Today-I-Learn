import React, {useRef, useState} from 'react';

const App03UseRef = () => {
    const [count, setCount] = useState(0);
    const countRef = useRef(0);

    console.log("countRef.current : ", countRef.current)
    console.log('렌더링...')

    const increaseCountState = () => {
        setCount(count + 1);
    }

    const increaseCountRef = () => {
        countRef.current = countRef.current + 1;
    }

    return (
        <div>
            <p>State: {count}</p>
            <button onClick={increaseCountState}>State 올려</button>
            <p>Ref: {countRef.current}</p>
            <button onClick={increaseCountRef}>Ref 올려</button>
        </div>
    );
};

export default App03UseRef;