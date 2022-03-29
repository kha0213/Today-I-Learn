import React, {useEffect, useReducer} from 'react';

const reducer = (state, action) => {
    console.log('action: ' ,action)
    return {
        ...state,
        [action.name]: action.value
    }
}

const Info = () => {
    const [state, dispatch] = useReducer(reducer, {
        name: '',
        nickname: ''
    })
    const { name, nickname } = state;
    useEffect(() => {
        console.log('렌더링 끝 ', name, nickname)
        return () => {
            console.log('cleanup')
            console.log(name)
        }
    }, [name, nickname]);

    const onChange = e => {
        dispatch(e.target)
    }

    return (
        <div>
            <div>
                <input name='name' value={name} onChange={onChange}/>
                <input name='nickname' value={nickname} onChange={onChange}/>
            </div>
            <div>
                <div>
                    <b>이름: </b> {name}
                </div>
                <div>
                    <b>닉네임: </b> {nickname}
                </div>
            </div>
        </div>
    );
};

export default Info;