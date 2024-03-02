export const columns = [
  {
    type: 'selection',
    span: 8,
    fixed: 'left',
  },
  {
    name: 'pointId',
    label: '充电点',
    align: 'center',
    //search: true,
    span: 8,
  },
  {
    name: 'id',
    label: '编号',
    search: true,
    //valueType: 'input',
    span: 8,
  },
  {
    name: 'position',
    label: '地点',
    search: true,
    valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    }, 
  },
  {
    name: 'status',
    label: '状态',
    align: 'center',
    //search: true,
    span: 8,
    options: [
      {
        value: "空闲",
        label: '空闲',
      },
      {
        value: "使用中",
        label: '使用中',
      },
      {
        value: "故障",
        label: '故障',
      },
    ],
    valueType: 'select',
    search: true,
    span: 8,
    attrs: {
      placeholder: '请选择',
    },
  },
  {
    name: 'operation',
    slot: true,
    fixed: 'right',
    label: '操作',
    width: 200,
  },
]
