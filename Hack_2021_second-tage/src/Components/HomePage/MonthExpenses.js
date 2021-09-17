import React, {useEffect, useContext} from 'react';
import './Monthexpenses.scss'
import {observer} from "mobx-react-lite";
import {Context} from "../../index";

const MonthExpenses = observer(() => {

    const {homePage, login} = useContext(Context)
    const colors = ['#EA5616', '#2C2D84', '#646872']

    useEffect(() => {

        const getData = () => homePage.topThreeMonthly()

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
        <div className={'monthExpenses'}>
            <div className={'monthExpenses__card'}>
                <p className={'monthExpenses__title monthExpenses__title_orn'}>
                    Траты за месяц
                </p>
                <p className={'monthExpenses__price monthExpenses__price_big'}>
                    {homePage.MonthExpense.toLocaleString()}
                    <span> ₽</span>
                </p>

            </div>

            {homePage.TopThreeMonth.map((el, index) =>
                <div key={el.category} className={'monthExpenses__card'}>
                    <p title={el.category} className={'monthExpenses__title'}>
                        {el.category}
                    </p>
                    <div className={'monthExpenses__numbers'}>
                        <p className={'monthExpenses__price'}>
                            {(Number(el.price)).toLocaleString()}
                            <span className={'monthExpenses__price_currency'}>&nbsp;₽</span>
                        </p>
                        <p className={'monthExpenses__before'}>

                        </p>
                        <p className={'monthExpenses__percent'}>
                            {el.percent} %
                        </p>

                    </div>

                    <div className={'monthExpenses__line'}>
                        <div className={'monthExpenses__line'} style={{position: 'absolute', left: '0', width: el.percent+'%', backgroundColor: colors[index]}} >
                        </div>
                    </div>

                </div>
            )}

        </div>
    );
});

export default MonthExpenses;