<template>
  <div class="main">
    <div class="header">
      <h1>欢迎进入电动单车系统</h1>
    </div>
    <h2>停车点</h2>
    <div class="parking">
      <template v-for="i in list" :key="i.id">
        <transition-group
        appear
        name="animate__animated animate__bounce"
        enter-active-class="animate__swing"
			  leave-active-class="animate__backOutUp"
        >
        <img
          :src="i.image"
          style="width: 350px"
          class="image"
          v-show="!i.visible"
          :key="i.id"
          @click="i.visible = true"
        />
          <el-card class="box-card" v-show="i.visible"  :key="i.id" @mouseleave="i.visible = false">
            <template #header>
              <div class="card-header">
                <span>{{ i.position }}</span>
              </div>
            </template>
            <div class="text item">停车点编号：{{ i.id }}</div>
            <div class="text item">停车点位置：{{ i.position }}</div>
            <div class="text item">停车点容量：{{ i.number }}</div>
            <template #footer>Footer content</template>
          </el-card>
        </transition-group>
      </template>
    </div>
    <h2>充电点</h2>
    <div class="charging">
      <template v-for="i in list2" :key="i.id">
        <img
          :src="i.image"
          style="width: 350px"
          class="image"
          v-if="!i.visible"
          @click="i.visible = true"
        />
        <transition-group
          appear
          enter-active-class="animate__fadeInUp"
          leave-active-class="animate__backOutUp"
        >
          <el-card class="box-card" v-show="i.visible" @mouseleave="i.visible = false">
            <template #header>
              <div class="card-header">
                <span>{{ i.position }}</span>
                <el-button class="button" text @click="charge(i)">充电</el-button>
                <el-button class="button" text @click="broke(i)">报修</el-button>
              </div>
            </template>
            <div class="text item">充电点点编号：{{ i.id }}</div>
            <div class="text item">充电点位置：{{ i.position }}</div>
            <div class="text item">充电点容量：{{ i.number }}</div>
            <div class="text item">充电点剩余数量：{{ i.freeNumber }}</div>
            <template #footer>Footer content</template>
          </el-card>
        </transition-group>
      </template>
    </div>
    <el-dialog v-model="form.electricVisible" :title="title" width="30%">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="充电桩编号" prop="password">
          <el-input v-model="ruleForm.pileId" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="form.electricVisible = false">取消</el-button>
          <el-button type="primary" @click="handleClose(ruleFormRef)">确定</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="form.breakVisible" :title="title" width="40%">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm2"
        label-width="200px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="充电桩编号" prop="pileId">
          <el-input v-model="ruleForm2.pileId">{{ ruleForm2.pileId }}</el-input>
        </el-form-item>
        <!-- <el-form-item label="充电桩地点" prop="pilePosition">
          <el-input v-model="ruleForm2.pilePosition">{{ ruleForm2.pilePosition }}</el-input>
        </el-form-item> -->
        <el-form-item label="备注" prop="message">
          <el-input v-model="ruleForm2.message">{{ ruleForm2.message }}</el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="form.breakVisible = false">取消</el-button>
          <el-button type="primary" @click="handleClose2(ruleFormRef)">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import {useUserStore} from "@/store/modules/user"
import router from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import 'animate.css'
import axios from 'axios'
import { onMounted, ref,reactive } from 'vue'
const title = ref('')
const formSize = ref('default')
const UserStore = useUserStore();
//充电列表
//表单
const ruleForm = ref({
  id: null,
  userId: null,
  pileId:null,
  carId:null,
  pointId:null,
  money:null,
  startTime:'',
  endTime:''
})
//充电
const charge=(i)=>{
  title.value="充电订单"
  ruleForm.value={}
  ruleForm.value.pointId=i.id
  ruleForm.value.userId=UserStore.token
  form.electricVisible=true;
}
//确认新增充电信息
const handleClose = async (done: () => void) => {
    const response = await axios.post('http://localhost:8086/chargingOrder', ruleForm.value)
    console.log(response.data.msg)
    if(response.data.msg=='余额不足，请充值'){
      ElMessage.warning('余额不足，请充值')
    }else{
      ElMessage.success('添加成功')
    }
    form.electricVisible = false
}
//维修表单
const ruleForm2 = ref({
  id: null,
  userId: null,
  pileId: null,
  pointId:null,
  pilePosition:'',
  message:'',
  time:''
})
//维修
const broke=(i)=>{
  title.value="维修订单"
  ruleForm2.value={}
  ruleForm2.value.pointId=i.id
  ruleForm2.value.userId=UserStore.token
  form.breakVisible=true;
}
//确认新增维修信息
const handleClose2=async (done: () => void) => {
  const response=await axios.post('http://localhost:8086/breakdown', ruleForm2.value)
  ElMessage.success('添加成功')
  form.breakVisible = false
}
//表单的显示
const form=reactive({
  electricVisible:false,
  breakVisible:false
})
//获取停车点信息
const list = ref([])

const getParking = async () => {
  const response = await axios.get('http://localhost:8086/parking', {})
  list.value = response.data.data.records.map((item) => {
    return item
  })
  list.value.visible = false
  console.log('list===', list)
}
//获取充电点信息
const list2 = ref([])
const getCharing = async () => {
  const response = await axios.get('http://localhost:8086/charging', {})
  list2.value = response.data.data.records.map((item) => {
    return item
  })
  list2.value.visible = false
  console.log('list===', list2)
}
onMounted(() => {
  getParking()
  getCharing()
})
</script>

<style scoped>
.header{
  text-align: center;
  font-size:20xp;
  color: rgb(41, 138, 194);
}
.main {
  display: flex;
  flex-direction: column;
}
.parking {
  display: flex;
  /* flex-direction: column; */
}
.charging {
  display: flex;
}
.image {
  margin: 5px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  text-align: center;
  font-size: 20px;
}

.text {
  font-size: 18px;
}

.item {
  margin-bottom: 18px;
  margin-left: 5px;
}

.box-card {
  width: 350px;
  height: 268px;
}

</style>