import React, {useEffect, useState} from 'react';
import axios from "axios";
import Spinner from "../conponents/Spinner";
import {useParams} from "react-router-dom";

const User = () => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(false);
    const {id} = useParams();

    useEffect(() => {
        console.log(user);
    }, [user]);

    useEffect(() => {
        setLoading(true);
        axios.get('https://jsonplaceholder.typicode.com/users/' + id)
            .then(response => {
                setUser(response.data);
                setLoading(false);
            });
    }, []);

    const userDetail = (loading || !user) ? <Spinner/> :
        (
        <div>
            <div>{user.name} </div>
            <div>{user.email} </div>
            <div>{user.phone} </div>
        </div>
        );

    return (
        <>
            <h1>유저 Detail</h1>
            {userDetail}
        </>
    );
};

export default User;