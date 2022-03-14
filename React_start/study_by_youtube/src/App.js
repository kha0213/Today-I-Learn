import "./App.css";
import {Component} from "react";

class App extends Component {
    render() {
        const name = '리액트';
        return <div className={"react"}>{name}</div>
    }
}

// function App() {
//     const name = "리액트";
//     return (
//         <div
//             className={"react"}
//         >
//             <h1>{name}</h1>
//             <div>
//                 <div>22222</div>
//             </div>
//         </div>
//     );
// }

export default App;
