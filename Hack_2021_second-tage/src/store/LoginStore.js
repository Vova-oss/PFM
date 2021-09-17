import {makeAutoObservable} from "mobx";
import {login, refresh} from "../http/UserApi";


export default class LoginStore {

    constructor() {
        this._isAuthUser = false

        this._firstLoad = true
        makeAutoObservable(this)
    }

    get IsAuth(){
        return this._isAuthUser
    }

    doAutorizate(name, password) {
        return login(name, password)
            .then((response) => {
                this._isAuthUser = true

                localStorage.setItem('token', response.headers.jwtoken)
                localStorage.setItem('RefreshToken', response.headers.refreshtoken)
                return Promise.resolve(response)
            })
            .catch(() => {

                return Promise.reject()
            })
    }



    doRefresh() {
        return refresh()
            .then((response)=> {
                this._isAuthUser = true
                localStorage.setItem('token', response.headers.jwtoken)
                localStorage.setItem('RefreshToken', response.headers.refreshtoken)
                return Promise.resolve()
            })
            .catch((error)=>{
                this._isAuthUser = false
                localStorage.removeItem('token')
                localStorage.removeItem('RefreshToken')
                this._isAuthUser = false
                return Promise.reject()
            })
    }

    out(){
        this._isAuthUser = false
        localStorage.removeItem('token')
        localStorage.removeItem('RefreshToken')
        this._isAuthUser = false
    }

    setFirstLoad(bool){
        this._firstLoad = bool
    }

    get FirstLoad(){
        return this._firstLoad
    }


    checkStatus(status) {


        if(status===468){
            return this.doRefresh()
        }

        if(status===403){
            return Promise.reject()
        }

        return Promise.reject()

    }

}