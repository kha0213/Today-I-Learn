import {connect} from "react-redux";
import Todos from "../components/Todos";
import {changeInput, insert, remove, toggle} from "../module/todos";

const TodosContainer = ({
    input,
    todos,
    changeInput,
    insert,
    toggle,
    remove
}) => {
    return <Todos
    input={input}
    todos={todos}
    onChangeInput={changeInput}
    onInsert={insert}
    onToggle={toggle}
    onRemove={remove}
    />;
};

export default connect(
    // 비구조화 할당을 통해 todos를 분리하여
    // state.todos.input = todos.input 을 사용
    ({todos}) => ({
        input: todos.input,
        todos: todos.todos
    }),
    {
        changeInput,
        insert,
        toggle,
        remove,
    },

)(TodosContainer);