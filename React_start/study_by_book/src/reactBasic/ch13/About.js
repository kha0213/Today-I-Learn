import React from 'react';
import {useLocation} from "react-router-dom";
import qs from 'qs';

const About = () => {
    const { search } = useLocation()
    const parsedQs = qs.parse(search, {
        ignoreQueryPrefix: true
    });
    const showDetail = parsedQs.detail === 'true'
    return (
        <div>
            <h1>소개</h1>
            <p>이 프로젝트는 리액트 라우터 기초를 실습해 보는 예제 프로젝트입니다.</p>
            {showDetail && <p>detail 값이 true</p>}
        </div>
    );
};

export default About;