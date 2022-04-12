import React from 'react';
import ReactDOM from 'react-dom';
import App from "./reactBasic/ch18/App";
import {createStore} from "redux";
import rootReducer from "./reactBasic/ch18/modules";
import {Provider} from "react-redux";


const store = createStore(rootReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__())

ReactDOM.render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>,
    document.getElementById('root')
)

