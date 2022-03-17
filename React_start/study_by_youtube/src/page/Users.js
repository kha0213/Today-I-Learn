import React, {useEffect, useState} from 'react';
import axios from "axios";
import UserList from "../conponents/UserList";
import Spinner from "../conponents/Spinner";

const Users = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        axios.get('https://jsonplaceholder.typicode.com/users')
            .then(response => {
                setUsers(response.data);
                setLoading(false);
            });
    }, []);

    useEffect(() => {
        console.log(users);
    }, [users]);

    return (
        <>
            <h1>유저</h1>
            {loading? <Spinner/> : <UserList users={users}/>}
        </>
    );
};

export default Users;