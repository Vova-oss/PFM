import './assets/font/Gilroy/stylesheet.scss'
import './App.css';
import './Styles.scss'
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import {Routes} from "./routes";
import {LOGIN_ROUTE} from "./pagePath";
import React, {useEffect, useContext} from 'react'
import {observer} from "mobx-react-lite";
import {Context} from "./index";
import {MoonLoader} from "react-spinners";
import {motion} from 'framer-motion'

import { ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const App = observer(() => {

    const {login} = useContext(Context)


    useEffect(() => {
        login.doRefresh().then(() => {
            setTimeout(() => {
                login.setFirstLoad(false)
            }, 500)
        }).catch(() => {
            setTimeout(() => {
                login.setFirstLoad(false)
            }, 500)
        })
    }, [])

    if (login.FirstLoad) {
        return <div className={'background background-home center'}>
            <MoonLoader/>
        </div>
    }

    return (<>

            {login.FirstLoad ?
                <motion.div className={'box'}
                            initial={{ opacity: 0 }}
                            animate={{ opacity: 1 }}
                            exit={{ opacity: 0 }}
                >
                    <div className={'background background-home center'}>
                        <MoonLoader/>
                    </div>
                </motion.div>
                :

                    <BrowserRouter className="App">
                        <Switch>


                            {Routes.map(({path, Component}) =>
                                <Route exact key={path} path={path} component={Component}/>
                            )}


                            <Redirect to={LOGIN_ROUTE}/>
                        </Switch>


                        <ToastContainer
                            position="bottom-center"
                            Type={'info'}
                            Theme={'dark'}
                        />
                    </BrowserRouter>



            }


        </>
    );
})

export default App;
