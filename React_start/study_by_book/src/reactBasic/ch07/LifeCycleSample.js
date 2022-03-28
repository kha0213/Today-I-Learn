import React, {Component} from 'react';

class LifeCycleSample extends Component {
    state = {
        number: 0,
        color: null
    };

    myRef = null; //ref를 설정할 부분
    constructor(props) {
        super(props);
        console.log('constructor');

    }

    static getDerivedStateFromProps(nextProps, prevState) {
        console.log('getDerivedStateFromProps')
        if(nextProps.color !== prevState.color) {
            return {color: nextProps.color}
        }
        return null;
    }

    componentWillMount() {
        console.log('componentWillMount')
    }

    componentDidMount() {
        console.log('componentDidMount')
    }

    componentWillReceiveProps(nextProps) {
        console.log('componentWillReceiveProps')
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log('shouldComponentUpdate', nextProps, nextState)
        // 숫자의 마지막 자리가 4면 리렌더링 하지 않습니다.
        return nextState.number % 10 !== 4;
    }

    componentWillUpdate(nextProps, nextState) {
        console.log('componentWillUpdate')
    }



    componentWillUnmount() {
        console.log('componentWillUnmount')
    }

    handleClick = () => {
        this.setState({
            number: this.state.number + 1
        })
    }

    getSnapshotBeforeUpdate(prevProps, prevState) {
        console.log('getSnapshotBeforeUpdate')
        if( prevProps.color !== this.props.color ) {
            return this.myRef.style.color;
        }
        return null;
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log('===componentDidUpdate===')
        console.log('prevProps: ', prevProps);
        console.log('prevState: ', prevState);
        console.log('snapshot: ', snapshot)
        if(snapshot) {
            console.log('업데이트되기 직전 색상: ', snapshot)
        }
    }

    render() {
        console.log('render')
        
        const style = {
            color: this.props.color
        }
        return (
            <div>
                <h1 style={style} ref={ref => this.myRef = ref}>
                    {this.state.number}
                </h1>
                <p>color: {this.state.color}</p>
                <button onClick={this.handleClick}>
                    더하기
                </button>
            </div>
        );
    }
}

export default LifeCycleSample;