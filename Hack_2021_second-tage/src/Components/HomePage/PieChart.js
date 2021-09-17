import React, {useContext, useEffect, useState} from 'react';
import ReactApexChart from "react-apexcharts";
import './PieChart.scss'
import styled from "styled-components";
import {Context} from "../../index";
import {observer} from "mobx-react-lite";
import ReactTooltip from "react-tooltip";
import {PSc} from "./WeekExpenses";


const Div = styled.div`
  background-color: ${({colorDiv}) => colorDiv};
`


class ApexChart extends React.Component {
    constructor(props) {
        super(props);

        this.state = {

            series: [44, 55, 13, 43, 22, 33, 55],
            height: 50,
            options: {
                dataLabels: {
                    enabled: false,
                },
                height: 50,
                chart: {
                    type: 'pie',
                    height: 50,
                },
                colors: ['#EA5616', '#2C2D84', '#403179', '#52356E', '#663963', '#783D58', '#9E4642',],
                labels: ['Супермаркеты', 'Переводы', 'Рестораны', 'Развлечения', 'Одежда', 'Транспорт', 'Развлечения'],
                legend: {
                    show: false,
                },
                stroke:{
                    width: 0,
                },
                responsive: [{
                    breakpoint: 200,
                    options: {
                        chart: {
                            width: 200
                        },
                        legend: {
                            position: 'bottom'
                        }
                    }
                }]
            },


        };
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if (prevProps.data.length !== this.props.data.length) {
            const curMas = this.props.data.map(el => el.summary)
            const categoryMas = this.props.data.map(el => el.category)
            this.setState({
                series: curMas,
                options: {
                    labels: categoryMas,
                },
            })
        }
    }


    render() {
        return (

            <div className={"chartPie"}>
                <ReactApexChart options={this.state.options} series={this.state.series} type="pie" width={270}
                                height={240}
                />
            </div>


        )
    }
}

const PieChart = observer(() => {
        const colors = ['#EA5616', '#2C2D84', '#403179', '#52356E', '#663963', '#783D58', '#9E4642',]

        const {homePage, login} = useContext(Context)
        const [infoCategory, setInfoCategory] = useState({index: 0})
        useEffect(() => {
            const getData = () => homePage.topExpensesForTheMonth()

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

        return (<>
                <ApexChart data={homePage.PieData}/>

                <div data-tip data-for='pie' className={'categories'}>
                    {homePage.PieData.map((el, index) =>
                        <div onMouseEnter={() => setInfoCategory({index: index })} key={index} className={'categories__item'}>
                            <Div colorDiv={colors[index]} className={'categories__circle'}></Div>

                            <div className={'categories__blockText'}>
                                <p className={'categories__text'}>{el.category}
                                </p>
                                {/*<div className={'categories__percent'}>
                                {Math.round(el.summary * 1000 / homePage.PieDataSum)/10} %
                            </div>*/}
                            </div>
                        </div>
                    )}

                    <ReactTooltip className={'tooltip'} type={'light'} id='pie'>
                        <div className={'tooltip__tooltipContent'}>
                            <div className={'tooltip__tooltipInfo'}>
                                <PSc color={'rgba(20, 24, 52, 0.5);'}>
                                    Сумма расходов
                                </PSc>
                                <p className={'tooltip__tooltipInfo_price'}>
                                    {homePage.PieData[infoCategory.index]?.summary.toLocaleString()}
                                    &nbsp;
                                    <span className={'tooltip__price_span'}>₽</span>
                                </p>
                            </div>
                            <div className={'tooltip__tooltipInfo'}>
                                <PSc
                                    color={colors[infoCategory.index]}>
                                    Категория
                                </PSc>
                                <p className={'tooltip__tooltipInfo_price'}>
                                    {homePage.PieData[infoCategory.index]?.category}

                                </p>
                            </div>
                        </div>
                    </ReactTooltip>

                </div>

            </>
        );
    }
);

export default PieChart