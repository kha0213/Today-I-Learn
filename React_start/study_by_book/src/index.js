import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "./reactBasic/ch13/App";
import Home from "./reactBasic/ch13/Home";
import About from "./reactBasic/ch13/About";
import Profile from "./reactBasic/ch13/Profile";
import Profiles from "./reactBasic/ch13/Profiles";
import HistorySample from "./reactBasic/ch13/HistorySample";


ReactDOM.render(
    <BrowserRouter>
        <Routes>
            <Route path={"/"} element={<App/>}>
                <Route index path={"home"} element={<Home/>}/>
                <Route path={"about"} element={<About/>}/>
                <Route path={"profiles"} element={<Profiles/>}>
                    <Route path={":username"} element={<Profile/>}/>
                </Route>
                <Route path={"history"} element={<HistorySample/>}/>
            </Route>
        </Routes>
    </BrowserRouter>,
    document.getElementById('root')
);

