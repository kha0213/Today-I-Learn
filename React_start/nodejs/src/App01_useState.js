import './App.css';
import {useState} from "react";

function App() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const onSubmit = (event) => {
        event.preventDefault();
        console.log(username, password)
        console.log('222')
    }

  return (
    <div className="App">
      Hello world!
      <br/>
        <form onSubmit={onSubmit}>
        <input placeholder="Username" value={username}
        onChange={(e) => setUsername(e.target.value)}/>
        <br/>
        <input type="password" placeholder="Password" value={password}
        onChange={(e) => setPassword(e.target.value)}/>
        <br/>
        <button type="submit">Login</button>
        </form>
        <br/>
        <br/>
    </div>
  );
}

export default App;
