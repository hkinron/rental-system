import Mock from 'mockjs'

const data = Mock.mock({
  'items|30': [{
    id: '@id',
    name: '@sentence(1, 3)',
    phone: '@integer(11)',
    room: {
      name: '401'
    }
  }]
})

export default [
  {
    url: '/tenants',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 20000,
        data: {
          content: items,
          totalElements: 1,
          totalPages: 1,
          last: true,
          size: 15,
          number: 0,
          first: true
        }
      }
    }
  }
]
