import React from 'react';
import {useNavigate} from "react-router-dom";

const HistorySample = () => {
    const navigate = useNavigate();
    // 뒤로 가기
    const handleGoBack = () => {
        navigate(-1)
    }
    // 홈으로 이동
    const handleGoHome = () => {
        navigate('/')
    }

    return (
        <div>
            <button onClick={handleGoBack}>뒤로</button>
            <button onClick={handleGoHome}>홈으로</button>
        </div>
    );
};

export default HistorySample;