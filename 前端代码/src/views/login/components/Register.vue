<template>
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
    <el-form-item label="" prop="configPassword">
      <el-input
        placeholder="请输入密码"
        autoComplete="on"
        @keyup.enter.native="submitForm(ruleFormRef)"
        v-model="ruleForm.configPassword"
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

    <el-form-item prop="code">
      <div style="display: flex">
        <el-input
          placeholder="请输入验证码"
          prefix-icon="el-icon-circle-check"
          size="medium"
          style="flex: 1"
          v-model="ruleForm.code"
        ></el-input>
        <div style="flex: 1; height: 36px">
          <valid-code @update:value="getCode" />
        </div>
      </div>
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
    <div style="display: flex">
      <div style="flex: 1"
        >已有账号？请 <span style="color: #110f98; cursor: pointer" @click="Login">登录</span></div
      >
    </div>
  </el-form>
</template>
  <script lang="ts" setup>
import axios from 'axios'
import { ref, reactive } from 'vue'
import type { FormInstance } from 'element-plus'
import { ElNotification } from 'element-plus'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/modules/user'
import { getTimeStateStr } from '@/utils/index'
import ValidCode from './ValidCode.vue'

const emit = defineEmits(['account'])
let props = defineProps({
  accountLogin: {
    type: Boolean,
    default: () => false,
  },
})
const router = useRouter()
const UserStore = useUserStore()
const ruleFormRef = ref<FormInstance>()
const passwordType = ref('password')
const loading = ref(false)

const Login = () => {
  console.log('login')
  emit('account')
  console.log('accountLogin', props.accountLogin)
}
const Code = ref('')
//获取验证码
const getCode = (code) => {
  Code.value = code.toLowerCase()
}
// 验证码校验
const validateCode = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入验证码'))
  } else if (value.toLowerCase() !== Code.value) {
    callback(new Error('验证码错误'))
  } else {
    callback()
  }
}
//表单规则
const rules = reactive({
  id: [{ required: true, message: '请输入用户账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' },
  {min:6,message:'密码应大于6位',trigger:'blur'}],
  configPassword: [{ required: true, message: '请再次输入密码', trigger: 'blur' },
  {min:6,message:'密码应大于6位',trigger:'blur'}],
  code: [{ validator: validateCode, trigger: 'blur' }],
  // code: [{  trigger: 'blur' }]
})
//表单
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
  money: null,
  status: null,
  code: '',
  configPassword: '',
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
      if (ruleForm.value.password != ruleForm.value.configPassword) {
        ElMessage.error('俩次密码不一样，请重新输入')
        loading.value = false
      } else if (ruleForm.value.password == ruleForm.value.configPassword) {
        // 注册
        setTimeout(async () => {
          try {
            const response = await axios.post('http://localhost:8086/user', ruleForm.value)
            if (response != null) {
              ElMessage.success('注册成功,请登录')
              loading.value = false
              Login()
            }
          } catch {
            ElMessage.error('账户已存在，请重新输入')
            loading.value = false
          }
        }, 1000)
      }
    } else {
      ElMessage.error('请输入')
      return false
    }
  })
}
</script>
  
  <style lang="scss" scoped>
@import '../index.scss';
</style>
  