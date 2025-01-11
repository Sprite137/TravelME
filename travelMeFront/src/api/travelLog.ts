import { get } from '../utils/request';


const api = {
    test: '/travelLog/test',
    homeBooks: '/book/homeBooks',
    getBookDetail:"/book/bookDetail"
}

const test = (params: any) => {
    return get(api.test, params).then((res: any) => {
        if (res.code === 200) {
            return Promise.resolve(res);
        }
        return Promise.resolve(res);
    })
    
}

export {
    test
}