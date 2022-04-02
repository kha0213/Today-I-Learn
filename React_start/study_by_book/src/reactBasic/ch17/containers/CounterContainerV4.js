import {connect} from "react-redux";
import Counter from '../components/Counter'
import {decrease, increase} from "../module/counter";

const CounterContainer = ({number, increase, decrease}) => {
    return <Counter number={number}
    onIncrease={increase}
    onDecrease={decrease}/>
};

export default connect(
    state => ({ number: state.counter.number }),
            {
                increase,
                decrease
            }
)(CounterContainer);