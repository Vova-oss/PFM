import React, {useState, useEffect} from 'react';
import img from "../assets/images/loginPage/sideImage.png";
import LockIconInput from "../Components/common/LoginPage/svg/LockIconInput";
import UserIconInput from "../Components/common/LoginPage/svg/UserIconInput";
import Logo from "../Components/common/LoginPage/svg/Logo";
import {observer} from "mobx-react-lite";
import {useContext} from "react";
import {Context} from "../index";
import {Redirect} from "react-router-dom";
import './../Styles.scss'
import {motion} from "framer-motion";
import { toast } from 'react-toastify';



function Input({type, ...props}) {
    const [isPasswordHidden, setPasswordHidden] = useState(false)


    const toggleHiddenPassword = () => {
        if (type = 'password') {
            setPasswordHidden(!isPasswordHidden)
        }
    }


    return (
        <div className={'input'}>
            <div className={'input__wrapper'}>
                <input onChange={(e)=>props.handleOnchange(e.target.value)} name={type} {...props} className={'input__input'} type={isPasswordHidden ? 'text' : type}/>
                <div className={'input__icon'} onClick={()=>toggleHiddenPassword()}>
                    {type === 'password'
                        ? <LockIconInput/>
                        : <UserIconInput/>}
                </div>
            </div>
        </div>
    );
}

const LoginPage = observer( function() {


    const {login} = useContext(Context)

    const [name, setName] = useState('')
    const [password, setPassword] =useState('')

    const handleName =(value)=>{
        setName(value)
    }
    const handlePassword = (value)=>{
        setPassword(value)
    }

    const handleSubmit = (e) =>{

        e.preventDefault()

        login.doAutorizate(name,password ).then(()=>{
            toast('Успешно')
        }).catch(()=>{
            toast('Ошибка регистрации')
        })
    }





    if(login.IsAuth){
        return <Redirect to={'/home'}/>
    }

    return (
        <div className={'background background-authorisation'}>

            <motion.div  className={'box form-wrapper'}
                        onClick={() => {

                        }}

                        animate={{
                            opacity: 1,
                        }}

                        initial={{
                            opacity: 0.1,
                        }}

                        transition={{
                            type: 'spring',
                            stiffness: 60,
                        }
                        }
            >

                <div className="form-wrapper__leftside">
                    <div className={'form-wrapper__logo'}>
                        <Logo/>
                    </div>
                    <p className={'form-wrapper__text'}>Авторизация</p>
                    <form onSubmit={handleSubmit} className={'form-wrapper__form'}>

                        <div  className="form-wrapper__input">
                            <Input handleOnchange={handleName} value={name} placeholder={'text'} type="text"/>
                        </div>
                        <div className="form-wrapper__input">
                            <Input handleOnchange={handlePassword} value={password} placeholder={'password'} type="password"/>
                        </div>


                        <button  className={'button_login button_login_orn'}>
                            Войти
                        </button>

                    </form>

                </div>

                <div className="form-wrapper__rightside">
                    <img className={'form-wrapper__img'} src={img} alt="Картинка"/>
                </div>

            </motion.div>


        </div>
    );
});

export default LoginPage;