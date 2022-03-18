import React, {useState} from 'react';


const havyLoader = () => {
    console.log("heavy!!!!!")
    return ['김영롱', '임상훈'];
}

const App01UseState = () => {
    const [names, setNames] = useState(havyLoader);
    const [input, setInput] = useState('');

    const updateInput = () => {
        setNames([input, ...names]);
        setInput('');
    }
    const onChange = (e) => {
        setInput(e.target.value);
    }
    return (
        <div>
            <input type={"text"} onChange={onChange}
                value={input}
            />
            <button onClick={updateInput}>Update</button>
            <div>
                {names.map((name, index) => {
                    return (<p key={index}>{name}</p>);
                })}
            </div>
        </div>
    );
};

export default App01UseState;