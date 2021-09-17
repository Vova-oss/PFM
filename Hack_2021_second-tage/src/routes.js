import LoginPage from "./Pages/LoginPage";
import {HOME_ROUTE, LOGIN_ROUTE} from "./pagePath";
import MainPage from "./Pages/MainPage";





export const Routes = [
    {
        path: LOGIN_ROUTE,
        Component: LoginPage,
    },
    {
        path: HOME_ROUTE,
        Component: MainPage,
    },
]
