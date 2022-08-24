import axios from "axios";
import router from "./router";

const baseURL = 'http://localhost:8080'
const service = axios.create({
    baseURL: baseURL, // 请求地址前缀，将自动加在 url 前面
    timeout: 6000 // 请求超时时间
})

// axios请求拦截器
service.interceptors.request.use(config => {
    let user = JSON.parse(localStorage.getItem('user'))
    if  (user && user.accessToken) {
        config.headers.Authorization = 'Bearer ' + user.accessToken 
    }
    return config
}, error => {
    return Promise.reject(error)
})

// axios响应拦截器
service.interceptors.response.use(response => {
    return response.data
}, error => {
    // console.log('看一看', error.response)
    const { status } = error.response
    if (status === 401) { // token失效
        console.log('token expired')
        localStorage.removeItem('user')
        router.push('/')
    }
    return Promise.reject(error)
})

export default service


// export default axios.create({
//   baseURL: "http://localhost:8080/",
//   headers: {
//     "Content-type": "application/json"
//   }
// });