import '../../App.css';
import Counter from '../conponents/Counter';
import {useState} from "react";

function App() {
    const [buttonName, setButtonName] = useState('click');
    return (
    <div className="App">
        <h1>Hello world ! </h1>
        <Counter content={"[click1]"}/>
        <Counter content={"[click2]"}/>
        <Counter content={buttonName}/>
        <button onClick={() => setButtonName('클릭')}>버튼 이름 변경</button>
    </div>
  );
}

export default App;
