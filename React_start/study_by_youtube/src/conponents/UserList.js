import React from "react";


const UserList = ({users}) => {
    const renderUser =
        users.map(user => {
            return (
            <div className="card m-3"
                 key={user.id}>
                <div className="card-body p-3">
                    {user.name}
                </div>
            </div>);
        });

    return (
        <>
            {renderUser}
        </>
    );
}
export default UserList;