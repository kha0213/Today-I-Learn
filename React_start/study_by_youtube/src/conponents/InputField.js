import React from 'react';

const InputField = ({
    type,
    value,
    placeholder,
    onChange,
    errorMessage
                    }) => {
    return (
        <>
            <input type={type}
                   placeholder={placeholder}
                   value={value}
                   onChange={onChange}
            />
            <div className={"error"}>{errorMessage}</div>
        </>
    );
}

export default InputField;