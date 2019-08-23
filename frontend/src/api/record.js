import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/records/yeahMonth/' + params.yeahMonth,
    method: 'get',
    params
  })
}
