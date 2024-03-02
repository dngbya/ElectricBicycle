export const columns = [
  {
    name: 'id',
    label: '订单编号',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'userId',
    label: '充值用户',
    search: true,
    valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name: 'money',
    label: '充值金额',
    search: true,
    //valueType: 'input',
    span: 8,
  },
  {
    name: 'time',
    label: '充值时间',
    sorter: true,
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'operation',
    slot: true,
    fixed: 'right',
    label: '操作',
    width: 200,
  },
]
