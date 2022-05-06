const value = require('./var');
const { odd, even } = require('./var');

const checkOddOrEven = (number) => {
    if( number % 2 ) {
        return odd;
    }
    return even;
}

module.exports = checkOddOrEven;