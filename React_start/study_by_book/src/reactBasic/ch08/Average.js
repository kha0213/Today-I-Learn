import React, {useCallback, useMemo, useRef, useState} from 'react';

const getAverage = numbers => {
    console.log('평균값 계산 중..')
    if (numbers.length === 0) return 0;
    const sum = numbers.reduce((a,b) => a + b);
    return sum / numbers.length
}

const Average = () => {
    const [list, setList] = useState([])
    const [number, setNumber] = useState('')
    const inputEl = useRef(null);


    const avg = useMemo(() => getAverage(list), [list])

    const onChange = useCallback(e => {
        console.log('onChange 함수 ')
        setNumber(e.target.value)
    }, [])
    const onInsert = useCallback(() => {
        console.log('onInsert 함수 ')
        if(!isNaN(parseInt(number))) {
            const nextList = list.concat(parseInt(number))
            setList(nextList)
            setNumber('')
            inputEl.current.focus();
        }
    }, [number, list])
    return (
        <div>
            <input type="text" value={number} ref={inputEl} onChange={onChange}/>
            <button onClick={onInsert}>등록</button>
            <ul>
                {list.map((value, index) => (
                    <li key={index}>{value}</li>
                ))}
            </ul>
            <div>
                <b>평균값:</b> {avg}
            </div>

        </div>
    );
};

export default Average;