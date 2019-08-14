import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/rooms',
    method: 'get',
    params
  })
}
