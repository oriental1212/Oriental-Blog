const getCurrentTime = () => {
    let date = new Date();//当前时间
    let month = zeroFill(date.getMonth() + 1);//月
    let day = zeroFill(date.getDate());//日
    let hour = zeroFill(date.getHours());//时
    let minute = zeroFill(date.getMinutes());//分
    let second = zeroFill(date.getSeconds());//秒

    let curTime = date.getFullYear() + "-" + month + "-" + day
        + " " + hour + ":" + minute + ":" + second;
    return curTime;
}

/**
 * 补零
 */
function zeroFill(i){
    if (i >= 0 && i <= 9) {
        return "0" + i;
    } else {
        return i;
    }
}

export default getCurrentTime
