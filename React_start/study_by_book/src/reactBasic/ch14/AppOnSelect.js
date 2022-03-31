import React, {useCallback, useState} from 'react';
import NewsList from "./NewsList";
import Categories from "./component/Categories";

const App = () => {
    const [category, setCategory] = useState('all');
    const onSelect = useCallback(category => setCategory(category), []);
    return (
        <>
            <Categories category={category} onSelect={onSelect}/>
            <NewsList category={category} />
        </>
    );
};

export default App;


