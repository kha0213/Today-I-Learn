import React, {useEffect} from 'react';

const Timer = () => {
    useEffect(() => {
        const timer = setInterval(() => {
            console.log("Timer Running!")
        }, 1000);

        return () => {
            clearInterval(timer);
            console.log("정리되었습니다")
        }
    }, []);
    return (
        <div>
            <h2>타이머를 시작합니다.</h2>
        </div>
    );
};

export default Timer;