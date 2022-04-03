import React, {useCallback} from 'react';
import Counter from "../components/Counter";
import {useDispatch, useSelector} from "react-redux";
import {decrease, increase} from "../modules/counter";

const CounterContainer = () => {
    const number = useSelector(state => state)
    const dispatch = useDispatch();
    const onIncrease = useCallback(() => dispatch(increase()), [dispatch])
    const onDecrease = useCallback(() => dispatch(decrease()), [dispatch])
    return (
        <Counter
            onIncrease={onIncrease}
            onDecrease={onDecrease}
            number={number}
        />
    );
};

export default CounterContainer;