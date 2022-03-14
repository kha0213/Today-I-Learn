import React, {useState} from "react";

const Counter = (props) => {
    const [count, setCount] = useState(0);

    const increment = () => setCount(count + 1);
    console.log("props.content : ", props.content)


    return (
          <button onClick={increment}>
              {props.content || 'Click'} {count}
          </button>
    );
}

export default Counter;