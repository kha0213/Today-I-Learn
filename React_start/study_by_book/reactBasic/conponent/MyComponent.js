
import React from "react";
import PropTypes from 'prop-types';
const MyComponent = ({ name, favoriteNumber ,children }) => {
    return (
        <>
        <div>나의 이름은 {name} 입니다.</div>
        <div>나의 자식이름은 {children} 입니다.</div>
            <br />
        <div>제가 좋아하는 숫자는 {favoriteNumber} 입니다.</div>

        </>);
}
MyComponent.propTypes = {
    name: PropTypes.string,
    favoriteNumber: PropTypes.number.isRequired
}

export default MyComponent;
