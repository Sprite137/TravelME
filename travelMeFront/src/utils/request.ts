// utils/request.ts

import axios from "axios";

//创建一个axios实例
const request = axios.create({
    baseURL: 'http://localhost:8001/',
    timeout: 20000,
});


// 添加请求拦截器
request.interceptors.request.use(
    function (config) {
        // 请求地址携带时间戳
        const _t = new Date().getTime()
        config.url += `?${_t}`
        
        // 请求头携带token
        config.headers['token'] = localStorage.getItem('token') || ''
        config.headers['Content-Type'] = 'application/json'

        return config;
    },
    function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
    }
);

// 添加响应拦截器
request.interceptors.response.use(
    function (response) {
        // 对响应数据做点什么
        if (response.status === 200) {
            return Promise.resolve(response)
        } else {
            return Promise.reject(response)
        }
    },
    function (error) {
        // 对响应错误做点什么
        if (error && error.response) {
            switch (error.response.status) {
                case 400:
                    error.message = '错误请求';
                    break;
                case 401:
                    error.message = '未授权，请重新登录';
                    break;
                case 403:
                    error.message = '拒绝访问';
                    break;
                case 404:
                    error.message = '请求错误,未找到该资源';
                    break;
                case 405:
                    error.message = '请求方法未允许';
                    break;
                case 408:
                    error.message = '请求超时';
                    break;
                case 500:
                    error.message = '服务器端出错';
                    break;
                case 501:
                    error.message = '网络未实现';
                    break;
                case 502:
                    error.message = '网络错误';
                    break;
                case 503:
                    error.message = '服务不可用';
                    break;
                case 504:
                    error.message = '网络超时';
                    break;
                case 505:
                    error.message = 'http版本不支持该请求';
                    break;
                default:
                    error.message = `未知错误${error.response.status}`;
            }
        } else {
            error.message = "连接到服务器失败";
        }
        return Promise.reject(error);
    }
);


/*
 *  get请求:从服务器端获取数据
 *  url:请求地址
 *  params:参数
 * */
export function get(url:string, params = {}) {
    return new Promise((resolve, reject) => {
        request({
            url: url,
            method: 'get',
            params: params
        }).then(response => {
            resolve(response.data);
        }).catch(error => {
            reject(error);
        });
    });
}

/*
 *  post请求:向服务器端提交数据
 *  url:请求地址
 *  params:参数
 * */
export function post(url:string, params = {}) {
    return new Promise((resolve, reject) => {
        request({
            url: url,
            method: 'post',
            data: params
        }).then(response => {
            resolve(response.data);
        }).catch(error => {
            reject(error);
        });
    });
}

// 对外暴露请求方法
export default {
    get,
    post
}