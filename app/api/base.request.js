import config from './config'

/**
 * 配置每次请求加上请求头(token)
 */
class HttpRequest {
  constructor() {
    console.log('HttpRequest');
  }

  request(options) {
    // options.url = config.baseURL;
    try {
      const token = uni.getStorageSync('token');
      if (token) {
        options.header = {
          'authorization' : 'Bearer ' + token
        };
      }
    } catch (err) {
      console.log(err)
    }
    return uni.request(options);
  }
}

export default new HttpRequest;