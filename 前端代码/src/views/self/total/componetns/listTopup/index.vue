<template>
  <div class="app-container" ref="appContainer">
    <PropTable
      :loading="loading"
      @selection-change="selectionChange"
      :columns="baseColumns"
      :data="list"
      @reset="reset"
      @onSubmit="onSubmit"
    >
      <template v-slot:operation="scope">
        <el-button type="primary" size="big" icon="Edit" @click="edit(scope.row)">
          查看
        </el-button>
       </template> 
    </PropTable>

    <el-dialog v-model="dialogVisible" :title="title" width="40%">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        label-width="200px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="订单编号" prop="name">
          <el-text v-model="ruleForm.id">{{ ruleForm.id }}</el-text>
        </el-form-item>
        <el-form-item label="充值金额" prop="password">
          <!-- <el-input v-model="ruleForm.money" /> -->
          <el-text v-model="ruleForm.money">{{ ruleForm.money }}</el-text>
        </el-form-item>
        <el-form-item label="充值时间" prop="age">
         <!-- <el-input v-model="ruleForm.time" /> -->
          <el-text v-model="ruleForm.time">{{ ruleForm.time }}</el-text>
        </el-form-item>
        <el-form-item label="充值用户" prop="phone">
          <!-- <el-input v-model="ruleForm.userId" />  -->
          <el-text v-model="ruleForm.userId">{{ ruleForm.userId }}</el-text>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
  <script lang="ts" setup name="comprehensive">
import axios from 'axios'
import { ref, reactive, onMounted, nextTick, toRefs } from 'vue'
import * as dayjs from 'dayjs'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { FormInstance } from 'element-plus'
import { columns } from './constants'
const loading = ref(true)
const appContainer = ref(null)
import PropTable from '../table/index.vue'
import type { UploadProps } from 'element-plus'
const imageUrl = ref('')
import {useUserStore} from "@/store/modules/user"
const UserStore = useUserStore()

/* const handleAvatarSuccess: UploadProps['onSuccess'] = (
  response,
  uploadFile
) => {
  uploadUrl.value=response.data.fileUrl
  imageUrl.value = URL.createObjectURL(uploadFile.raw!)
} */

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}


//上传图片的方法
const UploadImage=(file)=> {
      console.log("file=====",file.raw)
      let fd = new FormData()
      fd.append('image', file.raw) //传给后台接收的名字 file
      //fd=file.value+file.raw
      console.log("fd====",fd)
      axios.post('http://localhost:8086/upload', fd, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
        //上传成功后返回的数据,
        console.log("上传图片到:"+response);
        // 将图片地址给到表单.
        ruleForm.value.avator=response.data.data
        imageUrl.value=response.data.data
      })
 
    }
    //预览图片功能
const   handlePictureCardPreview=(file)=> {
      console.log(file.url);
      //dialogVisible = true
      imageUrl.value = file.url
 
    }



const data = []

//获取表格
const getUserList = async () => {
  const response = await axios.get('http://localhost:8086/topUpOrder/getById', {
    params: {
      id:UserStore.token
    },
  })
  list.value = response.data.data.records.map((item) => {
    return item
  })
}
let baseColumns = reactive(columns)
const list = ref([])
//const list = ref(data)

const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
// const ruleForm = reactive({
//表单
const ruleForm = ref({
  id: null,
  money: null,
  time: '',
  userId:''
})
//表单的规则
/* const rules = reactive({
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
}) */

const dialogVisible = ref(false)
const title = ref('新增')
const rowObj = ref({})
const selectObj = ref([])
//确认新增或修改
const handleClose = async (done: () => void) => {
  console.log('title.value===', title.value)
  if (title.value === '新增') {
    console.log('新增===', title.value)
    console.log('ruleForm===', ruleForm)
    const response = await axios.post('http://localhost:8086/user', ruleForm.value)
    ElMessage.success('添加成功')
  } else if (title.value === '编辑') {
    console.log('编辑===', title.value)
    console.log('ruleForm===', ruleForm)
    const response = await axios.put('http://localhost:8086/user', ruleForm.value)
    ElMessage.success('修改成功')
  }
  dialogVisible.value = false
  getUserList()
}
//新增
const add = () => {
  title.value = '新增'
  ruleForm.value = {}
  dialogVisible.value = true
}
//删除
const batchDelete = () => {
  if (!selectObj.value.length) {
    return ElMessage.error('未选中任何行')
  }
  ElMessageBox.confirm('你确定要删除选中项吗?', '温馨提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    draggable: true,
  })
    .then(() => {
      let ids = []
      selectObj.value.map((item) => {
        ids.push(item.id)
      })
      // @ts-ignore
      axios.delete(`http://localhost:8086/user/${ids}`).then((res) => {
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        getUserList()
      })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })
}
const selectionChange = (val) => {
  selectObj.value = val
}

const edit = (row) => {
  title.value = '订单详情'
  rowObj.value = row
  dialogVisible.value = true
  ruleForm.value = row
  /* ruleForm.name = row.name
  ruleForm.sex = row.sex
  ruleForm.phone = row.phone
  ruleForm.email = row.email
  ruleForm.age = row.age
  ruleForm.dept = row.dept
  ruleForm.avator = row.avator
  ruleForm.password = row.password */
}
//删除
const del = (row) => {
  console.log('row==', row)
  ElMessageBox.confirm('你确定要删除当前项吗?', '温馨提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    draggable: true,
  })
    .then(() => {
      let ids = []
      ids.push(row.id)
      axios.delete(`http://localhost:8086/user/${ids}`).then((res) => {
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        getUserList()
      })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })
}
//重置
const reset = () => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
  }, 500)
  ElMessage.success('触发重置方法')
}
//查询
const onSubmit = (val) => {
  //console.log('valname===', val.value.name)
  ElMessage.success('触发查询方法')
  loading.value = true
  // getUserList(val.value.name,val.value.id,val.value.sex)
  getUserList(val.value.name,val.value.dept)
  setTimeout(() => {
    loading.value = false
  }, 500)
}

const getHeight = () => {}

onMounted(() => {
  nextTick(() => {
    // let data = appContainer.value.
  })
  setTimeout(() => {
    loading.value = false
  }, 500)
})
onMounted(getUserList)
</script>
  
  <style scoped>
.edit-input {
  padding-right: 100px;
}
.app-container {
  flex: 1;
  display: flex;
  width: 100%;
  padding: 16px;
  box-sizing: border-box;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
  