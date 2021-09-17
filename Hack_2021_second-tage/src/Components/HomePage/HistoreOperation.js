import React, {useEffect, useState, createRef, useContext} from 'react';
import './HistoreOperation.scss'
import {Context} from "../../index";
import TransactionOut from "../common/HomePage/svg/TransactionOut";
import TransactionMe from "../common/HomePage/svg/TransactionMe";
import {observer} from "mobx-react-lite";
import {BarLoader, BounceLoader, PuffLoader, SkewLoader} from "react-spinners";
import Toolbar from "../common/HomePage/svg/Toolbar";
import {Input} from "../../Pages/LoginPage";
import {motion} from 'framer-motion'

const SettingBar = observer(({homePage, login}) => {
    const [sum_left, setSum_left] = useState('')
    const [sum_right, setSum_right] = useState('')
    const [date_1, setDate_1] = useState('2021-08-01')
    const [date_2, setDate_2] = useState('')
    const [error, setError] = useState({status: false, message: 'error'})

    const[isChanging, setIsChanging] = useState(false)

    const handleClickSum = (e)=>{
        const element = e.target;
        element.selectionStart = element.value.length - 2;
        element.selectionEnd = element.value.length - 2;
    }

    function doRequest() {

        let returnQuery = ()=>{

            let str = '';
            const s_1= sum_left===''? '' : `&minSum=`+Number(sum_left.match(/\d*/gi).join(''))
            const s_2= sum_right===''? '': '&maxSum='+Number(sum_right.match(/\d*/gi).join(''))
            const d_1=  date_1===''?'':'&from='+date_1.split('-').reverse().join('.')
            const d_2=  date_2===''?'':'&to='+date_2.split('-').reverse().join('.')
            const operation= homePage.Input && homePage.Output ? '&operationType=all': (homePage.Input?'&operationType=input ':'&operationType=output ')
            str= s_1 + s_2 +d_1 + d_2 + operation


            return str
        }


        homePage.setFetchingHistory(true)
        const getData = () => homePage.historyInitialExpenses(returnQuery())
        getData()
            .then(() => {
                homePage.setFetchingHistory(false)
            })
            .catch((status) => {
                login.checkStatus(status).then(() => {
                    getData().then(() => {
                        homePage.setFetchingHistory(false)
                    })
                }).catch(() => {
                    homePage.setFetchingHistory(false)
                })
            })
    }

    function giveValidateValue(elem){

        const regExpFunc = (value) => {
            return value.match(/\d*/gi).join('')
        }

        const target = elem
        let value = Number(regExpFunc(target.value))

        if((''+value).length>7){
            value = Number((''+value).slice(0, 7))

        }
        if (value !== 0) {
            value = value.toLocaleString() + ' ₽'
        } else {
            value=''
        }


        const pointer = target.selectionStart;
        const element = target;
        window.requestAnimationFrame(() => {
            element.selectionStart = value.length - 2;
            element.selectionEnd = value.length - 2;
        });

        return value
    }

    const handleChange_1 = (e) => {

        setSum_left(giveValidateValue(e.target))

        setIsChanging(true)
    }


    const handleChange_2 = (e) => {
        setSum_right(giveValidateValue(e.target))
        setIsChanging(true)
    }
    const handleChange_3 = (e) => {
        setDate_1(e.target.value)
        setIsChanging(true)
    }
    const handleChange_4 = (e) => {
        setDate_2(e.target.value)
        setIsChanging(true)
    }

    return (
        <div className={'history__toolbar'}>
            <div className={'history__dataBlock'}>
                <div className={'history__toolbar_element'}>
                    <label className={'history__label'} htmlFor="price-start">Сумма</label>
                    <div className={'history__inputBlock'}>
                        <div className={'input history__input'}>
                            <div className={'input__wrapper'}>
                                <input onBlur={(e)=>{
                                    const value_left = Number(e.target.value.match(/\d*/gi).join(''))
                                    const value_right = Number(sum_right.match(/\d*/gi).join(''))

                                    if(value_left>value_right && value_right!=''){
                                        setSum_right('')
                                        setError({status: true, message: 'Сумма от должна быть меньше, чем сумма до'})

                                        setTimeout(()=>{setError({status: false, message: 'Сумма от должна быть меньше, чем сумма после'})}, 3000)
                                    } else{

                                        if(isChanging){
                                            doRequest()
                                            setIsChanging(false)
                                        }
                                    }
                                }}  placeholder={'От'} name={'sum_left'} onClick={handleClickSum} onChange={handleChange_1} value={sum_left} id={'price-start'}
                                       className={'input__input'}/>
                            </div>
                        </div>
                        <div className={'input history__input'}>
                            <div className={'input__wrapper'}>
                                <input onBlur={(e)=>{
                                    const value_left = Number( sum_left.match(/\d*/gi).join(''))
                                    const value_right = Number(e.target.value.match(/\d*/gi).join(''))

                                    if(value_left>value_right && value_right!=''){
                                        setSum_right('')
                                        setError({status: true, message: 'Сумма от должна быть меньше, чем сумма до'})

                                        setTimeout(()=>{setError({status: false, message: 'Сумма от должна быть меньше, чем сумма после'})}, 3000)
                                    } else{
                                        if(isChanging){
                                            doRequest()
                                            setIsChanging(false)
                                        }
                                    }
                                }} placeholder={'До'} name={'sum_right'} onClick={handleClickSum}  onChange={handleChange_2} value={sum_right}
                                       className={'input__input'}/>
                            </div>
                        </div>
                    </div>
                </div>

                <div className={'history__toolbar_element'}>
                    <div className={'history__label'} htmlFor="date-start">Дата</div>
                    <div className={'history__inputBlock'}>
                        <div className={'input history__input'}>
                            {date_1==='' && <div className={'labelDate'} htmlFor="date-start">Начало</div>}
                            <div className={'history__input_wrapper'}>
                                <input onBlur={()=>{
                                    if(isChanging){
                                        doRequest()
                                        setIsChanging(false)
                                    }
                                }} onChange={handleChange_3} value={date_1} type="date" name={'date-start'} id={'date-start'} className={'inputDate'}/>
                            </div>
                        </div>
                        <div className={'input history__input'}>
                            {date_2==='' && <div className={'labelDate'} htmlFor="date-end">Конец</div>}

                            <div className={'history__input_wrapper'}>
                                <input onBlur={()=>{
                                    if(isChanging){
                                        doRequest()
                                        setIsChanging(false)
                                    }
                                }}  min={date_1} onChange={handleChange_4} value={date_2}  type="date" name={'date-end'} id={'date-end'} className={'inputDate'}/>
                            </div>
                        </div>
                    </div>
                </div>

                {error.status &&
                <div className={'error'}>
                    <p className={'error__p'}>
                        <span className={'error__span'}></span>
                        {error.message}
                    </p>
                </div>

                }

                <div className={'history__toolbar_element'}>
                    <div className={'history__label'}>Тип операции</div>
                    <div className={'input__wrapper'}>
                        <button onClick={()=>{
                            if(homePage.Input && homePage.Output){
                               homePage.setInput(false)
                                doRequest()

                            } else if(homePage.Output && !homePage.Input ){
                                homePage.setInput(true)
                                doRequest()
                            }
                        }} className={`history__operationButton button button_bigR ${homePage.Input? 'button_blue': 'button_white'}` }>
                            Входящие
                        </button>
                        <button onClick={()=>{
                            if(homePage.Input && homePage.Output){
                                homePage.setOutput(false)
                                doRequest()
                            } else if(!homePage.Output && homePage.Input){
                                homePage.setOutput(true)
                                doRequest()
                            }

                            doRequest()
                        }}
                                className={`history__operationButton button button_bigR ${homePage.Output ? 'button_blue': 'button_white'}`}>
                            Исходящие
                        </button>
                    </div>
                </div>



            </div>

        </div>
    )
})

const HistoreOperation = observer(() => {

    const {homePage, login} = useContext(Context)
    const ref = createRef()
    const [isSetting, setIsSetting] = useState('false')

    function doRequest() {
        homePage.setFetchingHistory(true)
        const getData = () => homePage.historyExpenses()
        getData()
            .then(() => {
                homePage.setFetchingHistory(false)
            })
            .catch((status) => {
                login.checkStatus(status).then(() => {
                    getData().then(() => {
                        homePage.setFetchingHistory(false)
                    })
                }).catch(() => {
                    homePage.setFetchingHistory(false)
                })
            })
    }

    useEffect(() => {

        doRequest()
    }, [])

    useEffect(() => {


    }, [homePage.HistoryData.length])


    const scrollFunc = (e) => {
        if (e.target.scrollHeight - e.target.scrollTop - e.target.clientHeight < 750 && !homePage.FetchingHistory) {

            if (homePage.HistoryCount > 1 && homePage.HistoryCount !== homePage.HistoryMax) {
                doRequest()
            }
        }
    }

    useEffect(() => {
        const element = ref.current
        element.addEventListener('scroll', scrollFunc)
    }, [])


    return (
        <div className={'history'}>
            <div onClick={() => {
                setIsSetting(!isSetting)
            }} className={'history__toggle'}>
                <Toolbar/>
            </div>





            <div className="history__content">


                <motion.div className={'box'}

                            animate={{
                                opacity: isSetting? 0 : 1,
                                height: isSetting? '0' : 'auto',
                            }}

                            initial={{
                                opacity: 0,
                                display: 'none',
                                height: '0',
                            }}

                            transition={{
                                type: 'spring',
                                stiffness: 60,
                            }
                            }
                >
                    <div >
                        <SettingBar  homePage={homePage} login={login} />
                    </div>
                </motion.div>





                <div ref={ref} className={'history__menu'}>

                    {
                        homePage.HistoryData.map((el, index) =>
                            <div key={index} className={'history__timeBlock'}>
                                <div className="history__time">
                                    {el.date}
                                </div>

                                <div className="history__transArr">
                                    {
                                        el.info.map(({currency, description, sum}, index) =>
                                            <div key={index} className={"history__transItemWrapper"}>
                                                <div className={'history__transItem'}>
                                                    <div className={'history__transItem_left'}>
                                                        {
                                                            sum < 0 ? <TransactionOut/>
                                                                : <TransactionMe/>
                                                        }

                                                        <div className={'history__textBlock_left'}>
                                                            <p className={'history__text'}>{description}</p>
                                                            <p className={'history__text_grey'}>{sum.toLocaleString()} ₽</p>
                                                        </div>
                                                    </div>
                                                    <div className={'history__transItem_right'}>
                                                        <div className={'history__textBlock_right'}>
                                                            <p className={'history__text'}>{sum>0?'Входящая':'Исходящая'}</p>
                                                            <p className={'history__text_grey history__text_uppercase'}>{currency}</p>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div className={'history__hr'}/>
                                            </div>
                                        )
                                    }

                                </div>
                            </div>)
                    }


                    <div className={'history__buttonWrapperWrap'}>
                        {homePage.HistoryCount <= 1 && <div className="history__buttonWrapper">
                            <button onClick={doRequest} className={'history__button button button_blue'}>
                                Посмотреть больще операций
                            </button>
                        </div>}

                    </div>
                </div>
                {homePage.FetchingHistory &&
                <div className={'history__spinner'}>
                    <PuffLoader size={50}/>
                </div>}


            </div>
        </div>
    );
});

export default HistoreOperation;