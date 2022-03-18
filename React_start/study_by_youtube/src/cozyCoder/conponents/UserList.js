import React from "react";
import {Link} from "react-router-dom";


const UserList = ({users}) => {
    const renderUser =
        users.map(user => {
            return (

            <Link to = {`/users/${user.id}`} >
                <div className="card m-3"
                     key={user.id}
                >
                    <div className="card-body p-3">
                         {user.name}
                    </div>
                </div>
            </Link>
                    );
        });

    return (
        <>
            {renderUser}
        </>
    );
}
export default UserList;