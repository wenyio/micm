import request from "../utils/request";

export function userQrCode() {
    return request({
        url: '/practice/user/qrcode',
        method: 'GET',
    })
}

/**
 * 获取用户二维码
 */
export function getUserQrCode() {
    console.log("获取用户二维码")
    userQrCode().then(res => {
        if (res.code === 20000) {
            localStorage.setItem("userQrCode", res.data.file)
        } else {
            this.$message.error(res.message)
        }
    })
}
