import axios from 'axios'
import {ElNotification} from "element-plus";

const request = axios.create({
    baseURL: 'http://localhost:8000',  // 注意！！ 这里是全局统一加上了 后端接口前缀 前缀，后端必须进行跨域配置！
    timeout: 5000
})
//请求
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // config.headers['token'] = user.token;  // 设置请求头
    return config
}, error => {
    return Promise.reject(error)
});
//响应
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        if(error.response.status !== 200){
            ElNotification({
                type: 'error',
                message: '数据请求失败'
            })
        }
        return Promise.reject(error)
    }
)


export default request
