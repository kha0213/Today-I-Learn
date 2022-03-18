import React, {useState} from 'react';
import Timer from "./component/Timer";

const App02Timer = () => {
    const [showTimer, setShowTimer] = useState(false);

    return (

        <div>
            {showTimer && <Timer/>}
            <button onClick={() => setShowTimer(!showTimer)}>Toggle Timer</button>
        </div>
    );
};

export default App02Timer;