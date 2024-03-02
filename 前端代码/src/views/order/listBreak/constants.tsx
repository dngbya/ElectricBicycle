export const columns = [
  {
    name: 'id',
    label: '维修编号',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'userId',
    label: '申请用户',
    search: true,
    //valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name: 'pileId',
    label: '充电桩编号',
    search: true,
    //valueType: 'input',
    span: 8,
  },
  {
    name: 'pointId',
    label: '充电点编号',
    search: true,
    //valueType: 'input',
    span: 8,
  },
  {
    name: 'pilePosition',
    label: '充电桩地点',
    search: true,
    valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name: 'message',
    label: '备注',
    search: true,
    //valueType: 'input',
    span: 8,
  },
  {
    name: 'time',
    label: '申请时间',
    sorter: true,
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'status',
    label: '状态',
    search: true,
    options: [
      {
        value: "已完成",
        label: '已完成',
      },
      {
        value: "未完成",
        label: '未完成',
      }
    ],
    valueType: 'select',
    span: 8,
  },
  {
    name: 'operation',
    slot: true,
    fixed: 'right',
    label: '操作',
    width: 200,
  },
]
