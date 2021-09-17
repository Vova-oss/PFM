import React, {useContext, useState} from 'react';
import './MainPage.scss'
import {Link, Redirect, Switch, useHistory} from "react-router-dom";
import Logo from "../Components/common/HomePage/svg/Logo";
import MonthExpenses from "../Components/HomePage/MonthExpenses";
import Offer from "../Components/HomePage/Offer";
import WeekExpenses from "../Components/HomePage/WeekExpenses";
import {Context, homePage} from "../index";
import ReactApexChart from "react-apexcharts";
import MonthChart from "../Components/HomePage/MonthChart";
import Radar from "../Components/HomePage/Radar";
import PieChart from "../Components/HomePage/PieChart";
import HistoreOperation from "../Components/HomePage/HistoreOperation";
import {LOGIN_ROUTE} from "../pagePath";
import {observer} from "mobx-react-lite";
import {motion} from "framer-motion";


function Paper(props) {
    return (
        <div className={'paper paper_white' + " " + props.title === ''}>
            <p className={'paper__title'}>{props.title}</p>

            <div className="paper__content">
                {props.children()}
            </div>
        </div>

    );
}



    const MainPage = observer(() => {
        const history = useHistory()
        const {login} = useContext(Context)

        if(!login.IsAuth){
            return <Redirect to={'login'}/>
        }

        return (
            <div className={'background background-home'}>
                <motion.div className={'box'}
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
                <div className="home-content">
                    <div className="home-content__header">
                        <div className={'home-content__topLogo topLogo'}>
                            <Logo/>
                            <p className={'topLogo__text'}>Анализ финансов</p>
                        </div>

                        <Link to={'/login'} onClick={()=>{
                            homePage.returnToInitial()
                            login.out()
                        }} className={'home-content__outLink'}>
                            Выход
                        </Link>

                    </div>

                    <div className="section__1 section">
                        <div className={'section__1_left '}>

                            <div className={'paper paper_white'}>
                                <p className={'paper__title'}></p>

                                <div className="paper__content">
                                    <MonthExpenses/>
                                </div>
                            </div>


                        </div>

                        <div className={'section__1_right'}>


                            <div className={'paper paper_dark'}>
                                <p className={'paper__title'}></p>

                                <div className="paper__content">
                                    <Offer/>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div className="section__2 section">
                        <div className={'section__2_left '}>
                            <div className={'paper paper_white'}>
                                <div className="paper__content">
                                    <p className={'paper__title'}>График расхода по дням</p>
                                    <WeekExpenses/>
                                </div>
                            </div>
                        </div>

                        <div className={'section__2_right'}>
                            <div className={'paper paper_white'}>
                                <div className="paper__content">
                                    <p className={'paper__title '}>График расходов за месяц</p>
                                    <MonthChart />
                                </div>
                            </div>
                        </div>
                    </div>


                    <div className="section__3 section">
                        <div className={'section__3_left '}>
                            <div className={'paper paper_white'}>
                                <div className="paper__content">
                                    <p className={'paper__title '}>Диаграмма недельных расходов
                                        по категориям</p>
                                    <Radar/>
                                </div>

                            </div>
                        </div>

                        <div className={'section__3_middle'}>
                            <div className={'paper paper_white'}>
                                <div className="paper__content">
                                    <p className={'paper__title '}>История операций</p>
                                    <HistoreOperation />
                                </div>
                            </div>
                        </div>

                        <div className={'section__3_right'}>
                            <div className={'paper paper_white'}>
                                <div className="paper__content">
                                    <p className={'paper__title '}>Топ расходов за месяц</p>
                                        <PieChart/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                </motion.div>
            </div>
        );
    });

    export
    default
    MainPage;