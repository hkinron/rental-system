import Mock from 'mockjs'

const data = Mock.mock({
  'items|15': [{
    id: '@id',
    water: '@integer(100, 1000)',
    electric: '@integer(300, 5000)',
    yeahMonth: '@now(yyyy-MM)',
    room: {
      name: '@integer(400, 409)'
    }
  }]
})

export default [
  {
    url: '/records/yeahMonth',
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
