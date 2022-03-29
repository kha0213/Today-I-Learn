import React from 'react';
import '../style/TodoList.scss'
import TodoListItem from "./TodoListItem";

const TodoList = ({todos, onRemove, onToggle}) => {
    return (
        <div className={"TodoList"}>
            {todos.map(todo => (
                    <TodoListItem
                        key={todo.id}
                        todo={todo}
                        onRemove={onRemove}
                        onToggle={onToggle}
                    />
                ))}
        </div>
    );
};

export default TodoList;