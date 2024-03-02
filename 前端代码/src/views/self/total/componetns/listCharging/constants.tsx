export const columns = [
  {
    name: 'id',
    label: '编号',
    search: true,
    valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name: 'userId',
    label: '用户编号',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name:'userName',
    label:'用户姓名',
    search: true,
    span: 8,
  },
  {
    name:'carId',
    label:'用户车辆',
    search: true,
    span: 8,
  },
  {
    name: 'pileId',
    label: '充电桩编号',
    span: 8,
  },
  {
    name:'pointId',
    label:'充电点编号',
    span: 8,
  },
  {
    name:'money',
    label:'花费',
    search: true,
    span: 8,
  },
  {
    name: 'startTime',
    label: '开始时间',
    span: 8,
  },
  {
    name: 'endTime',
    label: '结束时间',
    align: 'center',
    span: 4,
  },
  {
    name: 'operation',
    slot: true,
    fixed: 'right',
    label: '操作',
    width: 160,
  },
]
