import React, {useState} from 'react';

const EventPractice = () => {
    const [form, setForm] = useState({
       username: '',
       message: ''
    });
    const {username, message} = form;


    const handleChange = e => setForm({
        ...form,
        [e.target.name]: e.target.value
    });

    const handleClick = () => {
        console.log(username , ':' , message);
        setForm({
            username: '',
            message: ''
        });
    }

    const handleKeyPress = e => {
        if (e.key === 'Enter') {
            handleClick();
        }
    }

    return (
        <div>
            <h1>이벤트 연습</h1>
            <input type="text"
                   name={"username"}
                   placeholder={"이름"}
                   value={username}
                   onChange={handleChange}
            />
            <input type="text"
                name={"message"}
                placeholder={"아무거나 입력해 보세요"}
                value={message}
                onChange={handleChange}
                onKeyPress={handleKeyPress}
            />
            <button onClick={handleClick}>확인</button>
        </div>
    );

}

export default EventPractice;