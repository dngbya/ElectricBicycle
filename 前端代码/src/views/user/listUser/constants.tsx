export const columns = [
  {
    type: 'selection',
    span: 8,
    fixed: 'left',
  },
  {
    name: 'id',
    label: '账号',
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'password',
    label: '密码',
    search: true,
    //valueType: 'input',
    span: 8,
  },
  {
    name: 'avator',
    label: '头像',
    slot: true,
    align: 'center',
    //search: true,
    span: 8,
  },
  {
    name: 'name',
    label: '姓名',
    width: 70,
    search: true,
    align: 'center',
    valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name: 'age',
    label: '年龄',
    width: 70,
    align: 'center',
    span: 4,
  },
  {
    name: 'sex',
    label: '性别',
    width: 70,
    slot: true,
    search: true,
    span: 8,
    options: [
      {
        value: 1,
        label: '男',
      },
      {
        value: 0,
        label: '女',
      },
    ],
    //valueType: 'select',
  },
  {
    name: 'phone',
    label: '手机号',
    search: true,
    //sortable: true,
    //valueType: 'input',
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name: 'email',
    label: '邮箱',
    sorter: true,
    search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'money',
    label: '账户余额',
    align: 'center',
    //sorter: true,
    //search: true,
    //valueType: 'input',
    span: 8,
    /* attrs: {
      placeholder: '请输入',
    }, */
  },
  {
    name: 'dept',
    label: '职位',
    options: [
      {
        value: "用户",
        label: '用户',
      },
      {
        value: "管理员",
        label: '管理员',
      },
      {
        value: "超级管理员",
        label: '超级管理员',
      },
    ],
    valueType: 'select',
    search: true,
    span: 8,
    attrs: {
      placeholder: '请输入',
    },
  },
  {
    name:'status',
    label:'状态',
    slot: true,
    options: [
      {
        value: 1,
        label: '启用',
      },
      {
        value: 0,
        label: '禁用',
      },
    ],
  },
  {
    name: 'operation',
    slot: true,
    fixed: 'right',
    label: '操作',
    width: 200,
  },
]
