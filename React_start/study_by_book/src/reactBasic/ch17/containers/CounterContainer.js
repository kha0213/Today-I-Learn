import Counter from '../components/Counter'
import {useDispatch, useSelector} from "react-redux";
import {decrease, increase} from "../module/counter";

const CounterContainer = () => {
    const number = useSelector(state => state.counter.number
    )
    const dispatch = useDispatch();

    return <Counter number={number}
    onIncrease={() => dispatch(increase())}
    onDecrease={() => dispatch(decrease())}/>
};

export default CounterContainer