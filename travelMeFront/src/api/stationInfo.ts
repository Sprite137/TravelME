import { get,post } from '../utils/request';


const api = {
    findRecommendStationsByName: '/stationInfo/findRecommendStationsByName',
    // addTravelLog:'/travelLog/addTravelLog'
}

const findRecommendStationsByName = (params: any) => {
    return get(api.findRecommendStationsByName, params).then((res: any) => {
        if (res.code === 200) {
            return Promise.resolve(res);
        }
        return Promise.resolve(res);
    })
    
}

export {
    findRecommendStationsByName,
}