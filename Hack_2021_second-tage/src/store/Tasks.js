import {makeAutoObservable} from "mobx";


export default class TaskStore{
    constructor(){
        this.task =[]
        makeAutoObservable(this)
    }



    createTask(message, status=''){
        this.task.push({message, status: status, id: new Date().getTime()})
    }

    deleteTask(id){
        this.task = this.task.filter(el=>el.id !== id)
    }

    get Tasks(){
        return this.task
    }
}