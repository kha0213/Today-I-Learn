import React, {Component} from 'react';

class Counter_class extends Component {
    // constructor(props) {
    //     super(props);
    //     //state 의 초깃값 설정하기
    //     this.state = {
    //         number: 0,
    //         fixedNumber: 0
    //     };
    // }
    state = {
        number: 0,
        fixedNumber: 1
    }

    render() {
        const {number, fixedNumber} = this.state;
        return (
            <div>
                <h1>{number}</h1>
                <h1>바뀌지 않는 값 : {fixedNumber}</h1>
                <button
                    // onClick을 통해 버튼이 클릭되었을 때 호출할 함수를 지정합니다.
                    onClick={() => {
                        // this.setState를 사용하여 state에 새로운 값을 넣을 수 있습니다.
                        this.setState({number: number + 1})
                    }}
                    >
                    +1
                </button>
            </div>
        );
    }
}


export default Counter_class;