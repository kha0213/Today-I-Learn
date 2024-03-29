import "./App.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Navbar from "./cozyCoder/conponents/Navbar";
import Movies from "./cozyCoder/page/Movies";
import Users from "./cozyCoder/page/Users";
import Main from "./cozyCoder/page/Main";
import User from "./cozyCoder/page/User";

function App() {

    return (
        <BrowserRouter>
            <div className={"APP container"}>
                <Navbar/>
                    <Routes>
                        <>
                        <Route path={"/movies"} element={<Movies/>}/>
                        <Route path={"/users"}  element={<Users/>}/>
                        <Route path={"/users/:id"}  element={<User/>}/>
                        <Route path="/"   element={<Main/>}/>
                        </>
                    </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
