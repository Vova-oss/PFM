import React, {useContext, useEffect} from 'react';
import ReactApexChart from "react-apexcharts";
import './MonthChart.scss'
import {Context} from "../../index";
import {observer} from "mobx-react-lite";


class ApexChat extends React.Component {

    constructor(props) {
        super(props);
        let custom = function ({series, seriesIndex, dataPointIndex, w}) {

            let data = this.props?.dateData[dataPointIndex]?.data

            return `<div class='tooltip monthChart__tooltip'>
                            <div class='tooltip__tooltipContent'>
                                <div class='tooltip__tooltipInfo'>
                                    <p class='tooltip__tooltipInfo_price'>
                                        ${series[0][dataPointIndex]}
                                        <span class='tooltip__tooltipInfo_span'>₽</span>
                                    </p>                           
                                    <p>
                                        ${data}
                                    </p>
                                    
                                </div>
                            </div>   
                        </div>
                        `
        }

        custom = custom.bind(this)
        this.state = {

            series: [{
                name: "Desktops",
                data: [[1, 34000], [2, 5400], [3, 23000], [4, 43000], [5, 34000],
                    [6, 34000], [7, 5400], [8, 2300], [9, 43000], [10, 34000],
                    [11, 34000], [12, 5400], [13, 2300], [14, 43000], [15, 34000],
                    [16, 34000], [17, 5400], [18, 23000], [19, 43000], [20, 34000],
                    [21, 34000], [22, 5400], [23, 2300], [24, 43000], [25, 34000],
                    [26, 34000], [27, 5400], [28, 2300], [29, 43000], [30, 34000]]
            }],
            fill: {
                type: 'solid',
                colors: ['#1A73E8', '#B32824'],
            },
            options: {
                chart: {
                    fontFamily: 'Gilroy',
                    fontSize: '14px',
                    height: 350,
                    type: 'line',
                    zoom: {
                        enabled: false
                    },
                    toolbar: {
                        show: false
                    },
                },


                legend: {
                    show: false,
                },
                colors: ['#2C2D84'],

                dataLabels: {
                    enabled: false
                },
                stroke: {
                    curve: 'straight'
                },
                title: {
                    text: '',
                    align: 'left'
                },

                yaxis: {
                    tickAmount: 3,
                    seriesName: 'asd',
                    labels: {
                        show: true,
                        formatter: (value) => {
                            return value + ' ₽'
                        },
                        style: {
                            colors: ['rgba(20, 24, 52, 0.4)'],
                            fontSize: '14px',
                            fontFamily: 'Gilroy',
                            fontWeight: 600,
                            cssClass: 'apexcharts-yaxis-label',
                        },
                    },
                },


                xaxis: {
                    type: 'category',
                    // tickPlacement: 'between',
                    categories: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31],
                    tickAmount: 6,
                    max: 31,
                    tooltip: {
                        enabled: false,
                    },
                    labels: {
                        show: true,
                        style: {
                            colors: 'rgba(20, 24, 52, 0.4)',
                            fontSize: '14px',
                            fontFamily: 'Gilroy',
                            fontWeight: 600,
                            cssClass: 'apexcharts-yaxis-label',
                        },
                    },


                },

                grid: {
                    show: true,
                    borderColor: 'rgba(20, 24, 52, 0.4)'
                },

                tooltip: {
                    enabled: true,
                    enabledOnSeries: undefined,
                    shared: true,
                    custom: custom,
                    theme: false,
                    style: {
                        fontSize: '12px',
                        fontFamily: 'Gilroy',
                    },

                },


            },




        }
    }

    componentDidMount() {

    }

    componentDidUpdate(prevProps, prevState, snapshot){

        if(prevProps.dateData.length !== this.props.dateData.length){
            const arr = this.props.dateData.map((el,index)=>[index+1 , el.sum])

            this.setState({
                ...this.state,
                series: [{
                    name: this.state.series.name,
                    data: arr,
                    store: [...this.props.dateData]
                }]
            })
        }
    }


    render() {
        return (


            <div className="MonthChart">
                <ReactApexChart options={this.state.options} series={this.state.series} type="area" height={260}/>
            </div>


        );
    }
}


const MonthChart = observer(() => {
    const {homePage, login} = useContext(Context)
    useEffect(() => {
        const getData = () => homePage.expensesByMonth()

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

    useEffect(() => {
    }, [homePage.ExpensesByMonth])

    return (
        <div className={'monthChart'}>
            <ApexChat dateData={homePage?.ExpensesByMonth} />

        </div>
    );
});

export default MonthChart;
