import Mock from 'mockjs'

const data = Mock.mock({
  'items|15': [{
    id: '@id',
    name: '@sentence(1, 3)',
    price: '@integer(300, 5000)',
    tenants: []
  }]
})

export default [
  {
    url: '/rooms',
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
