import axios from 'axios'


const $host = axios.create({
    baseURL: process.env.REACT_APP_API_URL
})

//Для запросов с авторизацией
const $authHost = axios.create({
    baseURL: process.env.REACT_APP_API_URL
})

const $authHostRefresh = axios.create({
    baseURL: process.env.REACT_APP_API_URL
})


const authInterceptor = config => {

    config.headers.JWToken = `${localStorage.getItem('token')}`
    return config
}

const authInterceptorRefresh = config => {
    config.headers.ExpiredJWT = `${localStorage.getItem('token')}`
    config.headers.RefreshToken =  `${localStorage.getItem('RefreshToken')}`
    return config
}



$authHost.interceptors.request.use(authInterceptor)

$authHostRefresh.interceptors.request.use(authInterceptorRefresh)

export {
    $host,
    $authHost,
    $authHostRefresh,
}