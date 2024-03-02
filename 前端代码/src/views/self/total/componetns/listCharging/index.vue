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
        <template v-if="scope.row.endTime">
          <el-button type="primary" size="big" icon="Edit" @click="edit(scope.row)">
          查看
        </el-button>
        </template>
       <template v-if="!scope.row.endTime">
        <el-button type="warning" size="big" icon="Close" @click="over(scope.row)" >
          结束
        </el-button>
       </template>
      </template>
    </PropTable>

    <el-dialog v-model="dialogVisible" :title="title" width="30%">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
      <el-form-item label="订单编号" prop="id">
          <el-text v-model="ruleForm.id" >{{ ruleForm.id }}</el-text>
        </el-form-item>
        <el-form-item label="用户编号" prop="userId">
          <el-text v-model="ruleForm.userId" >{{ ruleForm.userId }}</el-text>
        </el-form-item>
        <el-form-item label="用户姓名" prop="userName">
          <el-text v-model="ruleForm.userName" >{{ ruleForm.userName }}</el-text>
        </el-form-item>
        <el-form-item label="用户车辆" prop="car">
          <el-text v-model="ruleForm.carId" >{{ ruleForm.carId }}</el-text>
        </el-form-item>
        <el-form-item label="充电桩编号" prop="pileId">
          <el-text v-model="ruleForm.pileId" >{{ ruleForm.pileId }}</el-text>
        </el-form-item>
        <el-form-item label="充电点编号" prop="pilePosition">
          <el-text v-model="ruleForm.pointId" >{{ ruleForm.pointId }}</el-text>
        </el-form-item><el-form-item label="花费金额" prop="money">
          <el-text v-model="ruleForm.money" >{{ ruleForm.money }}</el-text>
        </el-form-item><el-form-item label="开始时间" prop="startTime">
          <el-text v-model="ruleForm.startTime" >{{ ruleForm.startTime }}</el-text>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-text v-model="ruleForm.endTime" >{{ ruleForm.endTime?ruleForm.endTime:'未结束' }}</el-text>
        </el-form-item>
      </el-form>
      <template #footer v-if="overVisible">
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
import { ref, reactive, onMounted, nextTick, toRefs,computed } from 'vue'
import * as dayjs from 'dayjs'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { FormInstance } from 'element-plus'
import { columns } from './constants'
const loading = ref(true)
const appContainer = ref(null)
import PropTable from '../table/index.vue'
import type { UploadProps } from 'element-plus'
import {useUserStore} from "@/store/modules/user"
const UserStore = useUserStore()

//设置是否结束状态
const overVisible=ref(false)

const data = []
// 用户信息
const id = computed(() => UserStore.token)
//获取表格
const getUserList = async () => {
  const response = await axios.get(`http://localhost:8086/chargingOrder/${UserStore.token}`,{
    params:{
      id:UserStore.token
    }
  })
  console.log("response",response)
  list.value = response.data.data.records.map((item) => {
   const name= getName(item.userId)
  const un=ref()
  name.then(res=>{
    un.value=res
  })
  item.userName=un
    return item
  })  
}
//获取姓名
const getName=async(id=null)=>{
  const name=await axios.get('http://localhost:8086/chargingOrder/getName', {
    params: {
      id,
    },
  })
  return name.data.data;
}

let baseColumns = reactive(columns)
const list = ref([])

const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
//表单
const ruleForm = ref({
  id: null,
  userId: null,
  userName:'',
  pileId:null,
  pointId:null,
  carId:null,
  money:null,
  pilePosition:'',
  startTime:'',
  endTime:''
})

const ruleForm2 = ref({
  id: null,
  userId: null,
  pileId:null,
  pointId:null,
  carId:null,
  money:null,
  startTime:'',
  endTime:''
})
//表单的规则
const rules = reactive({
  //name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  //userId: [{ required: true, message: '请输入用户编号', trigger: 'blur' }],
  //userName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  //pileId: [{ required: true, message: '请输入充电桩编号', trigger: 'blur' }],
  //pilePosition: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
})

const dialogVisible = ref(false)
const title = ref('新增')
const rowObj = ref({})
const selectObj = ref([])
//确认新增或修改
const handleClose = async (done: () => void) => {
  ruleForm2.value=ruleForm.value
  const response = await axios.put('http://localhost:8086/chargingOrder', ruleForm2.value)
  ElMessage.success('成功结束')
  dialogVisible.value = false
  overVisible.value=true
  getUserList()
}
const selectionChange = (val) => {
  selectObj.value = val
}

const edit = (row) => {
  title.value = '订单详情'
  rowObj.value = row
  dialogVisible.value = true
  ruleForm.value = row
}
const over = (row) => {
  title.value = '结束订单'
  rowObj.value = row
  dialogVisible.value = true
  ruleForm.value = row
  overVisible.value=true
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
      axios.delete(`http://localhost:8086/chargingOrder/${ids}`).then((res) => {
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
  