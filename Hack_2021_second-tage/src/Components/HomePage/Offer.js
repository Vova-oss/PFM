import React, {useContext, useEffect} from 'react';
import {Link} from "react-router-dom";
import './Offer.scss'
import {Context} from "../../index";
import {observer} from "mobx-react-lite";

const Offer = observer(() => {
    const {homePage, login} = useContext(Context)
    const data = homePage.Offer;
    useEffect(() => {
        const getData = () => homePage.offer()
        getData()
            .then(() => {

            })
            .catch((status) => {
                login.checkStatus(status).then(() => {
                    getData()
                }).catch(() => {

                })
            })
    }, [])

    useEffect(()=>{

    }, [homePage.Offer.title])

    return (

            <div className={'recommendedServices'}>
                <div className={'recommendedServices__blockText'}>
                    <p className={'recommendedServices__title'}>{data?.title}</p>
                    <p className={'recommendedServices__subtitle'}>{data?.description}</p>
                </div>
                <a target={'_blank'} href={data?.link}>
                    <button className={'button button_orn recommendedServices__button'}>
                        Подробнее
                    </button>
                </a>
            </div>
    );
});

export default Offer;