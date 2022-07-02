<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="phone">
      <el-input v-model="dataForm.phone" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-input v-model="dataForm.sex" placeholder="性别"></el-input>
    </el-form-item>
    <el-form-item label="权限 0：管理员 1：医生 2：其他" prop="authority">
      <el-input v-model="dataForm.authority" placeholder="权限 0：管理员 1：医生 2：其他"></el-input>
    </el-form-item>
    <el-form-item label="状态 0:禁用，1:正常" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态 0:禁用，1:正常"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="createUser">
      <el-input v-model="dataForm.createUser" placeholder="创建人"></el-input>
    </el-form-item>
    <el-form-item label="修改人" prop="updateUser">
      <el-input v-model="dataForm.updateUser" placeholder="修改人"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          name: '',
          username: '',
          password: '',
          phone: '',
          sex: '',
          authority: '',
          status: '',
          createTime: '',
          updateTime: '',
          createUser: '',
          updateUser: ''
        },
        dataRule: {
          name: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
          ],
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          sex: [
            { required: true, message: '性别不能为空', trigger: 'blur' }
          ],
          authority: [
            { required: true, message: '权限 0：管理员 1：医生 2：其他不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态 0:禁用，1:正常不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ],
          createUser: [
            { required: true, message: '创建人不能为空', trigger: 'blur' }
          ],
          updateUser: [
            { required: true, message: '修改人不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/mphealth/employee/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.employee.name
                this.dataForm.username = data.employee.username
                this.dataForm.password = data.employee.password
                this.dataForm.phone = data.employee.phone
                this.dataForm.sex = data.employee.sex
                this.dataForm.authority = data.employee.authority
                this.dataForm.status = data.employee.status
                this.dataForm.createTime = data.employee.createTime
                this.dataForm.updateTime = data.employee.updateTime
                this.dataForm.createUser = data.employee.createUser
                this.dataForm.updateUser = data.employee.updateUser
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/mphealth/employee/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'phone': this.dataForm.phone,
                'sex': this.dataForm.sex,
                'authority': this.dataForm.authority,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'createUser': this.dataForm.createUser,
                'updateUser': this.dataForm.updateUser
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
