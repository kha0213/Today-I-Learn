import './App.css';
import Info from "./reactBasic/ch08/Info";
import {useState} from "react";

function App() {
    const [visible, setVisible] = useState(false);
  return (
    <div className="App">
        <button onClick={() => setVisible(!visible) }>
            {visible? '숨기기' : '보이기'}
        </button>
        <hr/>
        {visible && <Info/>}
    </div>
  );
}

export default App;
