import {makeAutoObservable, toJS} from "mobx";
import {
    getExpectedDay,
    getExpensesByMonth, getHistoryExpenses, getOffer,
    getTopExpensesForTheMonth,
    getTopThreeMonthly,
    getWeekGroup,
    login
} from "../http/UserApi";
import date from 'date-and-time';
import {useState} from "react";


export const statusCheck = (status) => {

}

export default class MainPageStore {

    constructor() {
        this._isAuthUser = false
        this._monthExpenses = []
        this._weekExpenses = {averageAmount:[], currentAmount: [], maxAmount: 0}
        this._topThree = {list: [], wholeSum: 0}
        this._radarData = {currentIndicators: [], monthlyAverages: []}


        this._historyData = []
        this._requestHistoreCount = 0
        this._maxHistory = null
        this._isFetchingHistory = false
        this._query = ''
        this._input = true
        this._output = true


        this._offer = {"description": "string",
            "link": "string",
            "title": "string"}

        this._pieData = []

        makeAutoObservable(this)
    }

    setInput(bool){
        this._input = bool
    }

    setOutput(bool){
        this._output = bool
    }

    get Input(){
        return this._input
    }

    get Output(){
        return this._output
    }

    topThreeMonthly() {
        return getTopThreeMonthly().then((response)=>{
            this._topThree = {...response.data}
            return Promise.resolve()
        }).catch(({response})=>{

            return Promise.reject(response.data.status)
        })
    }

    getWeekExpenses() {
        return getExpectedDay().then((response) => {
            this._weekExpenses = {...response.data}
            return Promise.resolve()
        }).catch(({response}) => {
            return Promise.reject(response.data.status)
        })
    }

    expensesByMonth(){
        return getExpensesByMonth().then((response) => {
            this._monthExpenses = response.data
            return Promise.resolve()
        }).catch(({response}) => {

            return Promise.reject(response.data.status)
        })
    }

    weekGroupExpenses(){
        return getWeekGroup().then((response) => {
            this._radarData = {...response.data}
            return Promise.resolve()
        }).catch(({response}) => {

            return Promise.reject(response.data.status)
        })
    }

    doArray = (argMas) =>{
        const arr = []
        let i = 0

        argMas.forEach((el, index)=>{

            if(i===0){

                arr.push({
                    date: el.date,
                    info: [{
                        currency: el.currency,
                        description: el.info,
                        sum: el.sum,
                    }]
                })
                i++;
            } else {
                if(arr[i-1].date === el.date){
                    arr[i-1] = {
                        date: el.date,
                        info: [...arr[i-1].info,{
                            currency: el.currency,
                            description: el.info,
                            sum: el.sum,
                        }]
                    }
                } else {
                    arr.push({
                        date: el.date,
                        info: [{
                            currency: el.currency,
                            description: el.info,
                            sum: el.sum,
                        }]
                    })
                    i++
                }
            }





        })



        return arr

    }

    getPage(){
        let str = '';

        str= '?page='+this._requestHistoreCount


        return str
    }


    historyInitialExpenses(query=''){
        this._query = query
        this._requestHistoreCount = 0
        this._maxHistory = null
        return getHistoryExpenses(this.getPage() + this._query).then((response) => {
            this._requestHistoreCount++

            this._historyData = [...this.doArray(response.data)]
            return Promise.resolve()

        }).catch(({response}) => {

            return Promise.reject(response.data.status)
        })
    }

    historyExpenses(){

        return getHistoryExpenses(this.getPage() + this._query ).then((response) => {
            this._requestHistoreCount++

            if(response.data.length === 0){
                this._maxHistory = this._requestHistoreCount
            }


            this._historyData = [...this._historyData, ...this.doArray(response.data)]
            return Promise.resolve()

        }).catch(({response}) => {

            return Promise.reject(response.data.status)
        })
    }


    topExpensesForTheMonth(){
        return getTopExpensesForTheMonth().then((response) => {

            this._pieData = [...response.data]
            return Promise.resolve()
        }).catch(({response}) => {

            return Promise.reject(response.data.status)
        })
    }

    offer(){
        return getOffer().then((response) => {

            this._offer = response.data
            return Promise.resolve()
        }).catch(({response}) => {
            return Promise.reject(response.data.status)
        })
    }

    returnToInitial(){
        this._isAuthUser = false
        this._monthExpenses = []
        this._weekExpenses = {averageAmount:[], currentAmount: [], maxAmount: 0}
        this._topThree = {list: [], wholeSum: 0}
        this._radarData = {currentIndicators: [], monthlyAverages: []}


        this._historyData = []
        this._requestHistoreCount = 0
        this._maxHistory = null
        this._isFetchingHistory = false
        this._query = ''
        this._input = true
        this._output = true
        this._pieData = []
        this._offer = {"description": "string",
            "link": "string",
            "title": "string"}

    }




    get Offer(){
        return this._offer
    }


    get WeekExpenses() {
        return toJS(this._weekExpenses)
    }

    get ExpensesByMonth(){
        return toJS(this._monthExpenses)
    }

    get TopThreeMonth (){
        return toJS(this._topThree.list)
    }

    get MonthExpense (){
        return toJS(this._topThree.wholeSum)
    }

    get WeekExpense(){
        let count = 0;
        this._weekExpenses.currentAmount.forEach((el)=>{
            count+= el.sum
        })

        return + count
    }

    get RadarData(){
        return  toJS(this._radarData)
    }

    get HistoryData(){
        return toJS(this._historyData)
    }

    get HistoryCount(){
        return this._requestHistoreCount
    }

    get HistoryMax(){
        return this._maxHistory
    }

    get FetchingHistory(){
        return this._isFetchingHistory
    }



    setFetchingHistory(bool) {
        this._isFetchingHistory = bool
    }


    get PieData() {
        return toJS(this._pieData)
    }

    get PieDataSum(){

        let arr = this._pieData.map(el=>el.summary)

        const sum = arr.reduce((previousValue, currentValue) => previousValue + currentValue)

        return sum

    }

}