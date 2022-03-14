import React, {Component} from "react";
import PropTypes from 'prop-types';

class MyComponent extends Component {
    static defaultProps = {
        name: '기본 이름'
    }
    static propTypes = {
        name: PropTypes.string,
        favoriteNumber: PropTypes.number.isRequired
    }
    render() {
        const { name, favoriteNumber ,children } = this.props;
        return (
            <>
                <div>나의 이름은 {name} 입니다.</div>
                <div>나의 자식이름은 {children} 입니다.</div>
                <br />
                <div>제가 좋아하는 숫자는 {favoriteNumber} 입니다.</div>
            </>);
    }
}

export default MyComponent;
