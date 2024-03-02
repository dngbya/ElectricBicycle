export const columns = [
  {
    type: 'selection',
    span: 8,
    fixed: 'left',
  },
  {
    name: 'id',
    label: '停车点编号',
    align: 'center',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
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
    name: 'image',
    label: '图片',
    slot:true,
    search: true,
    //valueType: 'input',
    span: 8,
  },
  {
    name: 'number',
    label: '数量',
    //slot: true,
    align: 'center',
    //search: true,
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
