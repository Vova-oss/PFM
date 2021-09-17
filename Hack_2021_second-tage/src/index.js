import React, {createContext} from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import LoginStore from "./store/LoginStore";
import MainPageStore from "./store/MainPageStore";
import TaskStore from "./store/Tasks";


export const Context = createContext(null)

export const login = new LoginStore()
export const homePage = new MainPageStore()


ReactDOM.render(
  <React.StrictMode>
      <Context.Provider value = {{
          login: login,
          homePage: homePage,
      }}>
          <App/>
      </Context.Provider>

  </React.StrictMode>,
  document.getElementById('root')
);



