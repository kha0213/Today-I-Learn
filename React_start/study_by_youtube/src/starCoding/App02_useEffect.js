import React, {useState} from 'react';

const App02UseEffect = () => {
    const [count, setCount] = useState(1);
    const [name, setName] = useState('');

    const handleCountUpdate = () => {
        setCount(count + 1);
    }

    const handleInputChange = e => {
        setName(e.target.value);
    }

    return (
        <div>
            <button onClick={handleCountUpdate}>Update</button>

            <div>count: {count}</div>
            <input type="text" onChange={handleInputChange} />
            <br/>
            <div>name: {name}</div>
        </div>
    );
};

export default App02UseEffect;