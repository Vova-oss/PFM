import {$authHost, $authHostRefresh, $host} from "./index";


export const registration = async (obj) => {
    const response = await $host.post('user/registration', obj)

    return response
}

export const login = async (login, password) => {
    const response = await $host.post('login', {login: login, password: password})

    return response
}

export const getTopThreeMonthly = async () => {
    const response = await $authHost.get('/transactions/monthlyExpensesAndTopThreeCategories')
    return response
}

export const getExpectedDay = async () => {
    const response = await $authHost.get('/transactions/expensesByDay')
    return response
}

export const getExpensesByMonth = async () => {
    const response = await $authHost.get('/transactions/expensesByMonth')
    return response
}

export const getWeekGroup = async () => {
    const response = await $authHost.get('/transactions/expensesPerWeekByCategory')
    return response
}
export const getHistoryExpenses = async (query='') => {
    const response = await $authHost.get('transactions/historyOfOperations'+query)
    return response
}

export const getTopExpensesForTheMonth = async () => {
    const response = await $authHost.get('transactions/topExpensesForTheMonth')
    return response
}

export const getOffer = async () => {
    const response = await $authHost.get('/transactions/advertisingProducts')
    return response
}


export const refresh = async () => {
    const response = await $authHostRefresh.post('refreshToken')
    return response
}











