import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/tenants',
    method: 'get',
    params
  })
}
