import './App.css';
import {useEffect, useState} from "react";

function App() {
    const [count, setCount] = useState(0);
    const [num, setNum] = useState(0);
    useEffect(() => {
        console.log("first rendering")
    }, []);

    useEffect(() => {
        console.log("count : ", count)
    }, [count]);
    console.log("rendering")

    const increment = () => {
        setCount(count + 1);
    }

    return (
    <div className="App">
        <h1>Hello world!</h1>
        <button onClick={increment}>Click</button>
        <button onClick={() => setNum(num + 1)}>Click2</button>
        <br/>
    </div>
  );
}

export default App;
