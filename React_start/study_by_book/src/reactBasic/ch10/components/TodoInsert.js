import React, {useCallback, useRef, useState} from 'react';
import '../style/TodoInsert.scss'
import {MdAdd} from "react-icons/md";

const TodoInsert = ({onInsert}) => {
    const [value, setValue] = useState('');
    const input = useRef();

    const onChange = useCallback(e => {
        setValue(e.target.value)
    }, [])

    const onSubmit = useCallback(
        e => {
            if(value) {
                onInsert(value);
                setValue('');
                input.current.focus();
            }

            e.preventDefault();
        }, [onInsert, value]
    )
    return (
        <form className={"TodoInsert"} onSubmit={onSubmit}>
            <input type="text" value={value} placeholder={"할 일을 입력하세요"} onChange={onChange} ref={input}/>
            <button type={"submit"}>
                <MdAdd/>
            </button>
        </form>
    );
};

export default TodoInsert;