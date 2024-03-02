<template>
  <div class="header">
    <el-avatar :size="150" class="avatar" :src="userInfo.avator" />
    <div class="information">
      <h3>{{ userInfo.name }}</h3>
      <template class="logo">
        <el-icon :size="20"><Avatar /></el-icon>
        <p>{{ userInfo.dept }}</p>
      </template>
      <template class="logo1">
        <el-icon :size="20"><School /></el-icon>
        <p>江西师范大学</p>
      </template>
      <template class="logo2">
        <el-icon :size="20"><CollectionTag /></el-icon>
        <p>账号：{{ userInfo.id }}</p>
      </template>
    </div>
    <div class="btn">
        <el-button icon="Edit" @click="edit">编辑</el-button>
        <el-button icon="DocumentAdd" @click="save">充值</el-button>
    </div>
    <el-dialog v-model="dialogVisible" :title="title" width="50%">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password" />
        </el-form-item>
        <el-form-item label="余额" prop="password">
          <el-text v-model="ruleForm.money" >{{ ruleForm.money }}</el-text>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="ruleForm.age" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="ruleForm.phone" /> </el-form-item
        ><el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email" /> </el-form-item
        ><el-form-item label="头像" prop="avator">
          <el-upload
            class="avatar-uploader"
            action=""
            :show-file-list="false"
            :before-upload="beforeAvatarUpload"
            :on-preview="handlePictureCardPreview"
            :on-change="UploadImage"
          >
            <img v-if="ruleForm.avator" :src="ruleForm.avator" style="width: 178px; height: 178px" class="avatar" />
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



    <el-dialog v-model="dialog2Visible" :title="title" width="50%">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="充值金额" prop="money">
          <el-input v-model="money.qian" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleClose2(ruleFormRef)">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import axios from 'axios'
import { computed,ref } from 'vue'
import { useUserStore } from '@/store/modules/user'
import {ElMessage, ElMessageBox} from "element-plus";
import type { FormInstance } from 'element-plus'
const UserStore = useUserStore()
const dialogVisible = ref(false)
const dialog2Visible = ref(false)
const title = ref('')
const rowObj = ref({})
const ruleFormRef = ref<FormInstance>()
const money=ref({
    qian:null
})
const ruleForm = ref({
  id: null,
  name: '',
  sex: null,
  phone: '',
  password: '',
  avator: '',
  age: null,
  dept: '',
  email: '',
  money:null,
  status:null
})
const saveForm=ref({
  userId:null,
  money:null,
})
// 用户信息
const userInfo = computed(() => UserStore.userInfo)
const edit = (row) => {
  title.value = '用户信息'
  rowObj.value = row
  dialogVisible.value = true
  ruleForm.value = userInfo.value
}
const save = (row) => {
  title.value = '充值'
  rowObj.value = row
  dialog2Visible.value = true
  saveForm.value.userId = userInfo.value.id
}
//确认新增或修改
const handleClose = async (done: () => void) => {
const response = await axios.put('http://localhost:8086/user', ruleForm.value)
 ElMessage.success('修改成功')
  dialogVisible.value = false
}
const handleClose2 = async (done: () => void) => {
ruleForm.value.money=Number(ruleForm.value.money)+Number(money.value.qian)
const response = await axios.put('http://localhost:8086/user', ruleForm.value)
saveForm.value.money=money.value.qian
const res=await axios.post('http://localhost:8086/topUpOrder', saveForm.value)
 ElMessage.success('充值成功')
 money.value.qian=null
  dialog2Visible.value = false
}
</script>

<style scoped>
.header {
  width: 100%;
  display: flex;
  /* background-image: url(@/assets/image/sky.jpg);
  background-size:cover; */
  /* background-color: rgb(203, 198, 198); */
}
.information {
  padding: 1%;
  color: grey;
}
.information h3 {
  font-size: 30px;
  font-weight: 300px;
  color: rgb(226, 153, 165);
  margin-top: -1%;
}
.logo {
  margin-top: 70%;
  display: flex;
}
.logo
p{
    margin-top: 2%;
    margin-left: 5%;
}
.logo1 {
  margin-left: 95%;
  margin-top:-30%;
  display: flex;
  width:200%;
}
.logo1
p{
margin-top:0;
}
p {
  font-size: 15px;
  margin-left: 5%;
  margin-right: 5%;
}
.logo2{
  margin-left: 210%;
  margin-top:-30%;
  display: flex;
  width:200%;
}
.logo2
p{
margin-top:0;
}
.btn{
    margin-left:55%;
}
</style>