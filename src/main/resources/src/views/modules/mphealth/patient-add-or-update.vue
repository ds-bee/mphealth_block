<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="病人名字" prop="name">
      <el-input v-model="dataForm.name" placeholder="病人名字"></el-input>
    </el-form-item>
    <el-form-item label="医生id" prop="categoryId">
      <el-input v-model="dataForm.categoryId" placeholder="医生id"></el-input>
    </el-form-item>
    <el-form-item label="病情描述信息" prop="description">
      <el-input v-model="dataForm.description" placeholder="病情描述信息"></el-input>
    </el-form-item>
    <el-form-item label="医治状态：0 完成 1 未完成" prop="status">
      <el-input v-model="dataForm.status" placeholder="医治状态：0 完成 1 未完成"></el-input>
    </el-form-item>
    <el-form-item label="确认病情上链哈希" prop="patientHash">
      <el-input v-model="dataForm.patientHash" placeholder="确认病情上链哈希"></el-input>
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
    <el-form-item label="是否删除" prop="isDeleted">
      <el-input v-model="dataForm.isDeleted" placeholder="是否删除"></el-input>
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
          categoryId: '',
          description: '',
          status: '',
          patientHash: '',
          createTime: '',
          updateTime: '',
          createUser: '',
          updateUser: '',
          isDeleted: ''
        },
        dataRule: {
          name: [
            { required: true, message: '病人名字不能为空', trigger: 'blur' }
          ],
          categoryId: [
            { required: true, message: '医生id不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '病情描述信息不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '医治状态：0 完成 1 未完成不能为空', trigger: 'blur' }
          ],
          patientHash: [
            { required: true, message: '确认病情上链哈希不能为空', trigger: 'blur' }
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
          ],
          isDeleted: [
            { required: true, message: '是否删除不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/mphealth/patient/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.patient.name
                this.dataForm.categoryId = data.patient.categoryId
                this.dataForm.description = data.patient.description
                this.dataForm.status = data.patient.status
                this.dataForm.patientHash = data.patient.patientHash
                this.dataForm.createTime = data.patient.createTime
                this.dataForm.updateTime = data.patient.updateTime
                this.dataForm.createUser = data.patient.createUser
                this.dataForm.updateUser = data.patient.updateUser
                this.dataForm.isDeleted = data.patient.isDeleted
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
              url: this.$http.adornUrl(`/mphealth/patient/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'categoryId': this.dataForm.categoryId,
                'description': this.dataForm.description,
                'status': this.dataForm.status,
                'patientHash': this.dataForm.patientHash,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'createUser': this.dataForm.createUser,
                'updateUser': this.dataForm.updateUser,
                'isDeleted': this.dataForm.isDeleted
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
