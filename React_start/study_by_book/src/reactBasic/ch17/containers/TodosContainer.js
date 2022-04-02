import Todos from "../components/Todos";
import {useDispatch, useSelector} from "react-redux";
import {changeInput, insert, remove, toggle} from "../module/todos";
import {useCallback} from "react";

const TodosContainer = () => {
    const input = useSelector(state => state.todos.input);
    const todos = [...useSelector(state => state.todos.todos)]
    const dispatch = useDispatch();

    const onChangeInput = useCallback(input =>
        dispatch(changeInput(input)), [dispatch])
    const onRemove = useCallback(id =>
        dispatch(remove(id)), [dispatch])
    const onToggle = useCallback(id =>
        dispatch(toggle(id)), [dispatch])
    const onInsert = useCallback(text =>
        dispatch(insert(text)), [dispatch])
    return <Todos
    input={input}
    todos={todos}
    onChangeInput={ onChangeInput }
    onInsert={ onInsert }
    onToggle={ onToggle }
    onRemove={ onRemove }
    />;
};

export default TodosContainer;