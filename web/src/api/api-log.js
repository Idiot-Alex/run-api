import request from '../utils/request'

export function list(data) {
  return request({
    url: '/api-log/list',
    method: 'get',
    params: data
  })
}
