export const columns = [
  {
    type: 'selection',
    span: 8,
    fixed: 'left',
  },
  {
    name: 'id',
    label: '车牌号',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'carImage',
    label: '车辆图片',
    search: true,
    slot:true,
    type:'image',
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'userId',
    label: '用户账号',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'userName',
    label: '用户姓名',
    search: true,
    valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name: 'type',
    label: '车辆类型',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'color',
    label: '车辆颜色',
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
