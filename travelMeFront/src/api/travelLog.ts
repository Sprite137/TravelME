import { get,post } from '../utils/request';


const api = {
    queryTravelLogs: '/travelLog',
    addTravelLog:'/travelLog/addTravelLog'
}

const queryTravelLogs = (params: any) => {
    return get(api.queryTravelLogs, params).then((res: any) => {
        if (res.code === 200) {
            return Promise.resolve(res);
        }
        return Promise.resolve(res);
    })
    
}

const addTravelLog = (params: any) => {
    return post(api.addTravelLog, params).then((res: any) => {
        if (res.code === 200) {
            return Promise.resolve(res);
        }
        return Promise.resolve(res);
    })
    
}

export {
    addTravelLog,queryTravelLogs
}