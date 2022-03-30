import React from 'react';
import {NavLink, Outlet} from "react-router-dom";
import './style.scss'

const App = () => {
    return (
        <div>
            <ul>
                <li><NavLink to={"/home"}>홈</NavLink></li>
                <li><NavLink to={"/about"}>어바웃</NavLink></li>
                <li><NavLink to={"/profiles"}>프로필</NavLink></li>
                <li><NavLink to={"/history"}>히스토리 예제</NavLink></li>
            </ul>
            <hr/>
            <Outlet/>
        </div>
    );
};

export default App;