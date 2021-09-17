import React, {useContext, useEffect} from 'react';
import ReactApexChart from "react-apexcharts";
import './Radar.scss'
import {Context} from "../../index";
import {observer} from "mobx-react-lite";

class ApexChat extends React.Component {

    constructor(props) {
        super(props);

        this.state = {

            series: [{
                name: 'Средние показатели за месяц',
                data: [80, 50, 30, 22, 22],
            },
                {
                    name: 'Средние показатели за месяц',
                    data: [80, 50, 30, 22, 22],
                },
            ],


            options: {

                chart: {

                    fontFamily: 'Gilroy',
                    fontSize: '12px',
                    type: 'radar',

                    dropShadow: {
                        enabled: true,
                        blur: 1,
                        left: 1,
                        top: 1
                    },
                    toolbar: {
                        show: false
                    },
                },

                colors: ['#2C2D84', '#EA5616'],
                legend: {

                    show: true,
                    showForSingleSeries: false,
                    showForNullSeries: true,
                    showForZeroSeries: true,
                    position: 'bottom',
                    horizontalAlign: 'center',
                    floating: false,
                    fontSize: '12px',
                    fontFamily: 'Gilroy',
                    fontWeight: 600,
                    formatter: undefined,
                    inverseOrder: false,
                    width: '450px',
                    height: '150px',
                    tooltipHoverFormatter: undefined,
                    customLegendItems: [],
                    offsetX: 0,
                    offsetY: 5,
                    labels: {
                        useSeriesColors: false
                    },
                    markers: {
                        width: 12,
                        height: 12,
                        strokeWidth: 0,
                        strokeColor: '#fff',
                        fillColors: undefined,
                        radius: 12,
                        customHTML: undefined,
                        onClick: undefined,
                        offsetX: 0,
                        offsetY: 0
                    },
                    itemMargin: {
                        horizontal: 5,
                        vertical: 0
                    },
                },


                tooltip: {
                    enabled: true,
                },
                markers: {
                    size: 3,
                    hover: {
                        size: 10
                    }
                },


                plotOptions: {
                    radar: {
                        polygons: {
                            strokeColor: '#e8e8e8',
                            fill: {
                                colors: ['#f8f8f8']
                            }
                        }
                    }
                },
                stroke: {
                    width: 2
                },
                fill: {
                    opacity: 0.1
                },
                xaxis: {

                    categories: ['', '', '', '']
                },

                yaxis: {
                    show: false,
                    tickAmount: 6,
                },
                zoom: {
                    enabled: true,
                },
            },


        };
    }


    componentDidUpdate(prevProps, prevState, snapshot) {
        if (prevProps.data.monthlyAverages.length !== this.props.data.monthlyAverages.length) {

            const avrMas = this.props.data.monthlyAverages.map(el => el.summary)
            const curMas = this.props.data.currentIndicators.map(el => el.summary)
            const categoryMas = this.props.data.monthlyAverages.map(el => '')

            this.setState({
                series: [{
                    name: 'Средние показатели за месяц',
                    data: avrMas,
                }, {
                    name: 'Текущие показатели',
                    data: curMas,
                }],
                options: {
                    xaxis: {
                        categories: categoryMas
                    }
                },
            })
        }
    }

    render() {


        const categoryMas = this.props.data.monthlyAverages.map(el => el.category)
        const length = categoryMas.length
        let arrayStyled = []

        if(length===7){
            arrayStyled = [
                {
                    position: 'absolute',
                    top: '-22px',
                    left: '200px',
                },
                {
                    position: 'absolute',
                    top: '50px',
                    left: '300px',
                },
                {
                    position: 'absolute',
                    top: '130px',
                    left: '300px',
                },
                {
                    position: 'absolute',
                    top: '190px',
                    left: '255px',
                },
                {
                    position: 'absolute',
                    top: '200px',
                    left: '140px',
                },
                {
                    position: 'absolute',
                    top: '150px',
                    left: '10px',
                },
                {
                    position: 'absolute',
                    top: '40px',
                    left: '35px',
                },
            ]
        } else if(length===6){
            arrayStyled = [
                {
                    position: 'absolute',
                    top: '-22px',
                    left: '200px',
                },
                {
                    position: 'absolute',
                    top: '50px',
                    left: '300px',
                },
                {
                    position: 'absolute',
                    top: '150px',
                    left: '300px',
                },
                {
                    position: 'absolute',
                    top: '215px',
                    left: '200px',
                },
                {
                    position: 'absolute',
                    top: '180px',
                    left: '40px',
                },
                {
                    position: 'absolute',
                    top: '30px',
                    left: '40px',
                },
            ]
        }  if(length===5){
            arrayStyled = [
                {
                    position: 'absolute',
                    top: '-22px',
                    left: '200px',
                },
                {
                    position: 'absolute',
                    top: '50px',
                    left: '300px',
                },
                {
                    position: 'absolute',
                    top: '200px',
                    left: '270px',
                },
                {
                    position: 'absolute',
                    top: '200px',
                    left: '100px',
                },
                {
                    position: 'absolute',
                    top: '50px',
                    left: '21px',
                },
            ]
        }

        return (


            <div className="radar">
                <ReactApexChart options={this.state.options} series={this.state.series} height={250} width={450}
                                type="radar"/>


                {categoryMas.map((el, index)=>
                    <div title={el} style={arrayStyled[index]} className={'radar__item'}>
                        {el}
                    </div>
                )}

            </div>


        );
    }
}


const Radar = observer(() => {
    const {homePage, login} = useContext(Context)
     useEffect(() => {
         const getData = () => homePage.weekGroupExpenses()

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
        <ApexChat data={homePage.RadarData}/>
    );
});

export default Radar;