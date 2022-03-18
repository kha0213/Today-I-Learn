import "../../App.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Navbar from "../conponents/Navbar";
import Movies from "../page/Movies";
import Users from "../page/Users";
import Main from "../page/Main";
import User from "../page/User";

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
