import './App.css';
import {useEffect, useState} from "react";

function App() {
    const [condition, setCondition] = useState(false);
    const toggle = () => setCondition(!condition);

    useEffect(() => {
        console.log("condition : ", condition)
    }, [condition])

    const renderCondition = condition? 'True' : 'False';
    return (
    <div className="App">
        <h1>Hello world ! </h1>
        <div>{renderCondition}</div>
        <button onClick={toggle}>Toggle</button>
    </div>
  );
}

export default App;
