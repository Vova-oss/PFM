import React, {useContext, useState } from 'react';
import './WeekExpenses.scss'
import styled from 'styled-components'
import {useEffect} from "react";
import {observer} from "mobx-react-lite";
import {Context, homePage, login} from "../../index";
import ReactTooltip from "react-tooltip";

const Diagram = styled.div`
  background-color: ${({colorBg})=>colorBg};
  height: ${ ({heightSc}) => heightSc+"%"};
  border-radius: 25px;
  min-width: 40%;
`

export const PSc =  styled.p`
  color: ${({color})=>color};
  font-style: normal;
  font-weight: 600;
  font-size: 14px;
  line-height: 22px;
`

const WeekExpenses = observer(() => {
    const {homePage, login} = useContext(Context)
    const weekDays= ['Пн','Вт','Ср','Чт','Пт','Сб','Вс']
    const averageAmount =  homePage.WeekExpenses.averageAmount
    const currentAmount = homePage.WeekExpenses.currentAmount

    const maxPrice =  homePage.WeekExpenses.maxAmount

    const [sumObj, setSumObj] = useState({averageSum: 0,currentSum: 0})

    useEffect(() => {

        const getData = () => homePage.getWeekExpenses()

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



    return (
        <div className={'weekExpenses'}>
            <div className={'weekExpenses__graphic'}>
                <div onMouseLeave={()=> setSumObj({averageSum: 0,currentSum: 0})} data-tip data-for='happyFace' onMouseMove={(e)=>{console.log(e.clientX)}} className={'weekExpenses__items'}>
                    {
                        averageAmount.map((el, index)=>
                               <div key={index} onMouseEnter={()=> setSumObj({averageSum: el.sum, currentSum: currentAmount[index].sum })} className={'weekExpenses__itemCategoryWrapper'}>
                                   <div key={el.data} className={'weekExpenses__itemCategory'}>
                                       <Diagram colorBg={el.sum*100/maxPrice< currentAmount[index].sum*100/maxPrice? '#EA5616' : '#2C2D84'}  color={'rgba(20, 24, 52, 0.09);'} heightSc={currentAmount[index].sum*100/maxPrice}></Diagram>
                                       <Diagram colorBg={'rgba(20, 24, 52, 0.09)'} heightSc={el.sum*100/maxPrice}></Diagram>
                                   </div>
                                   <div className={'weekExpenses__weekDay'}>
                                       {weekDays[index]}
                                   </div>
                               </div>
                        )
                    }

                    <ReactTooltip className={'tooltip'} type={'light'} id='happyFace' >
                        <div className={'tooltip__tooltipContent'}>
                            <div className={'tooltip__tooltipInfo'}>
                                <PSc color={'rgba(20, 24, 52, 0.5);'} >
                                    Средний расход
                                </PSc>
                                <p  className={'tooltip__tooltipInfo_price'}>
                                    {(sumObj.averageSum).toLocaleString()}
                                    &nbsp;
                                    <span className={'tooltip__price_span'}>₽</span>
                                </p>
                            </div>
                            <div className={'tooltip__tooltipInfo'}>
                                <PSc color={sumObj.averageSum*100/maxPrice< sumObj.currentSum*100/maxPrice?'#EA5616'  : '#2C2D84'}>
                                    Сумма расхода
                                </PSc>
                                <p  className={'tooltip__tooltipInfo_price'}>
                                    {(sumObj.currentSum).toLocaleString()}
                                    &nbsp;
                                    <span className={'tooltip__price_span'}>₽</span>
                                </p>
                            </div>
                        </div>
                    </ReactTooltip>
                </div>


            </div>

            <div className="weekExpenses__dataBlock">
                <div className="weekExpenses__data">{currentAmount[0]?.data} - {currentAmount[currentAmount.length-1]?.data}</div>
                <div className="weekExpenses__data"></div>
            </div>
            <div className="weekExpenses__price">
                <p>
                    {(homePage.WeekExpense).toLocaleString()}
                    &nbsp;
                    <span className={'weekExpenses__price_span'}>₽</span>
                </p>
                {}
            </div>
        </div>
    );
});

export default WeekExpenses;