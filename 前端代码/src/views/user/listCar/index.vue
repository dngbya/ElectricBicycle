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
      <template v-slot:btn>
        <div style="display: flex; justify-content: flex-end">
          <el-button type="primary" @click="add"
            ><el-icon><plus /></el-icon> 添加</el-button
          >
          <el-button type="danger" @click="batchDelete"
            ><el-icon><delete /></el-icon>删除</el-button
          >
        </div>
      </template>
      <template v-slot:carImage="scope">
        <el-image 
            style="width: 80px; height: 70px" 
            :src="scope.row.carImage" 
            fit="cover"/>
      </template>
      <template v-slot:operation="scope">
        <el-button type="primary" size="small" icon="Edit" @click="edit(scope.row)">
          编辑
        </el-button>
        <el-button @click="del(scope.row)" type="danger" size="small" icon="Delete">
          删除
        </el-button>
      </template>
    </PropTable>

    <el-dialog v-model="dialogVisible" :title="title" width="50%">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="车牌号" prop="id">
          <el-input v-model="ruleForm.id" />
        </el-form-item>
        <el-form-item label="用户账户" prop="userId">
          <el-input v-model="ruleForm.userId" />
        </el-form-item>
        <el-form-item label="用户姓名" prop="userName">
          <el-input v-model="ruleForm.userName" />
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="ruleForm.color" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-input v-model="ruleForm.type" /> </el-form-item
        ><el-form-item label="车辆图片" prop="carImage">
          <el-upload
            class="avatar-uploader"
            action=""
            :show-file-list="false"
            :before-upload="beforeAvatarUpload"
            :on-preview="handlePictureCardPreview"
            :on-change="UploadImage"
          >
            <img v-if="ruleForm.carImage" :src="ruleForm.carImage" style="width: 178px; height: 178px" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"
              ><Plus
            /></el-icon> </el-upload>

          </el-form-item
        >
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleClose(ruleFormRef)">确定</el-button>
        </span>
      </template>
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
import PropTable from '@/components/Table/PropTable/index.vue'
import type { UploadProps } from 'element-plus'

const imageUrl = ref('')
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
      let fd = new FormData()
      fd.append('image', file.raw) //传给后台接收的名字 file
      //fd=file.value+file.raw
      axios.post('http://localhost:8086/upload', fd, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
        //上传成功后返回的数据,
        //console.log("上传图片到:"+response);
        // 将图片地址给到表单.
        ruleForm.value.carImage=response.data.data
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
const getUserList = async (userName = '') => {
  const response = await axios.get('http://localhost:8086/car', {
    params: {
      //查询参数
      //page: config.page.value, // 默认值是1   可以挨个点  可以 5 4 1 2
      userName, //name其实跟name:name效果是一样的，当key value重名情况下，name就可以缩写
      /* id,
      sex */
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
  userId:null,
  userName:'',
  carImage:'',
  color:'',
  type:''
})
//表单的规则
const rules = reactive({
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  // age: [{ required: true, message: '请输入年龄', trigger: 'blur
})

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
    const response = await axios.post('http://localhost:8086/car', ruleForm.value)
    ElMessage.success('添加成功')
  } else if (title.value === '编辑') {
    console.log('编辑===', title.value)
    console.log('ruleForm===', ruleForm)
    const response = await axios.put('http://localhost:8086/car', ruleForm.value)
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
      axios.delete(`http://localhost:8086/car/${ids}`).then((res) => {
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
  title.value = '编辑'
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
      axios.delete(`http://localhost:8086/car/${ids}`).then((res) => {
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
  getUserList(val.value.userName)
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
  