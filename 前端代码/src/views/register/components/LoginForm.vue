<template>
  <div class="login-title">
    <img class="icon" src="@/assets/image/logo.png" alt="logo" />
    <h2 class="title">高校-电动单车-管理系统</h2>
  </div>
  <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules">
    <el-form-item label="" prop="id">
      <el-input
        placeholder="请输入用户账号"
        autoComplete="on"
        style="position: relative"
        v-model="ruleForm.id"
        @keyup.enter.native="submitForm(ruleFormRef)"
      >
        <template #prefix>
          <el-icon class="el-input__icon"><UserFilled /></el-icon>
        </template>
      </el-input>
    </el-form-item>

    <el-form-item label="" prop="password">
      <el-input
        placeholder="请输入密码"
        autoComplete="on"
        @keyup.enter.native="submitForm(ruleFormRef)"
        v-model="ruleForm.password"
        :type="passwordType"
      >
        <template #prefix>
          <el-icon class="el-input__icon"><GoodsFilled /></el-icon>
        </template>
        <template #suffix>
          <div class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </div>
        </template>
      </el-input>
    </el-form-item>

    <el-form-item style="width: 100%">
      <el-button
        :loading="loading"
        class="login-btn"
        type="primary"
        @click="submitForm(ruleFormRef)"
        >注册</el-button
      >
    </el-form-item>
    <!-- <div style="display: flex">
            <div style="flex: 1">还没有账号？请 <span style="color: #110f98; cursor: pointer" @click="$router.push('/register')">注册</span></div>
           <div style="flex: 1; text-align: right"><span style="color: #0f9876; cursor: pointer">忘记密码</span></div> 
          </div> -->
  </el-form>
</template>
<script lang="ts" setup>
import axios from 'axios'
import { ref, reactive } from 'vue'
import type { FormInstance } from 'element-plus'
import { ElNotification } from 'element-plus'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/modules/user'
import { getTimeStateStr } from '@/utils/index'

const router = useRouter()
const UserStore = useUserStore()
const ruleFormRef = ref<FormInstance>()
const passwordType = ref('password')
const loading = ref(false)

const rules = reactive({
  id: [{ required: true, message: '请输入用户账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
})

// 表单数据
/* const ruleForm = reactive({
  username: 'admin',
  password: '123456',
}) */
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

// 显示密码图标
const showPwd = () => {
  passwordType.value = passwordType.value === 'password' ? '' : 'password'
}

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      loading.value = true
      // 登录
      setTimeout(async () => {
        const response = await axios.post('http://localhost:8086/user/login', ruleForm.value)
        console.log('response===', response)
        if (response.data.msg === '登录失败') {
          ElNotification({
            title: getTimeStateStr(),
            message: '账号或密码错误，请重新输入',
            type: 'error',
            duration: 3000,
          })
          loading.value = false
        } 
        else if(response.data.data.status==false){
          ElNotification({
            title: getTimeStateStr(),
            message: '该用户已被禁用，请等待解封',
            type: 'error',
            duration: 3000,
          })
          loading.value = false
        }
        else {
          ruleForm.value=response.data.data
          await UserStore.login(ruleForm.value)
          await router.push({
            path: '/',
          })
          ElNotification({
          title: getTimeStateStr(),
          message: '欢迎登录高校电动单车管理系统',
          type: 'success',
          duration: 3000,
        })
        loading.value = true
        }
      }, 1000)
    } else {
      console.log('error submit!')
      return false
    }
  })
}
</script>

<style lang="scss" scoped>
@import '../index.scss';
</style>
