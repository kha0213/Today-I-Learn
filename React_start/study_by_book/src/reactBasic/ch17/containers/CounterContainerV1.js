import {connect} from "react-redux";
import Counter from '../components/Counter'
import {decrease, increase} from "../module/counter";

const CounterContainerV1 = ({number, increase, decrease}) => {
    return <Counter number={number}
    onIncrease={increase}
    onDecrease={decrease}/>
};

const mapStateToProps = state => ({
    number: state.counter.number
})
const mapDispatchToProps = dispatch => ({
    // 임시 함수
    increase: () => {
        dispatch(increase())
    },
    decrease: () => {
        dispatch(decrease())
    }
})

export default connect(
    mapStateToProps,
    mapDispatchToProps)(CounterContainerV1);