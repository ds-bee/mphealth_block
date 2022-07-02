<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="医生部门id " prop="departmentId">
      <el-input v-model="dataForm.departmentId" placeholder="医生部门id "></el-input>
    </el-form-item>
    <el-form-item label="医生名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="医生名称"></el-input>
    </el-form-item>
    <el-form-item label="顺序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="顺序"></el-input>
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
          departmentId: '',
          name: '',
          sort: '',
          createTime: '',
          updateTime: '',
          createUser: '',
          updateUser: ''
        },
        dataRule: {
          departmentId: [
            { required: true, message: '医生部门id 不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '医生名称不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '顺序不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/mphealth/doctor/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.departmentId = data.doctor.departmentId
                this.dataForm.name = data.doctor.name
                this.dataForm.sort = data.doctor.sort
                this.dataForm.createTime = data.doctor.createTime
                this.dataForm.updateTime = data.doctor.updateTime
                this.dataForm.createUser = data.doctor.createUser
                this.dataForm.updateUser = data.doctor.updateUser
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
              url: this.$http.adornUrl(`/mphealth/doctor/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'departmentId': this.dataForm.departmentId,
                'name': this.dataForm.name,
                'sort': this.dataForm.sort,
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
