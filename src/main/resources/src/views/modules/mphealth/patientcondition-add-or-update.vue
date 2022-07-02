<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="病人id" prop="patientId">
      <el-input v-model="dataForm.patientId" placeholder="病人id"></el-input>
    </el-form-item>
    <el-form-item label="医治方案" prop="condition">
      <el-input v-model="dataForm.condition" placeholder="医治方案"></el-input>
    </el-form-item>
    <el-form-item label="价格" prop="value">
      <el-input v-model="dataForm.value" placeholder="价格"></el-input>
    </el-form-item>
    <el-form-item label="确认病症上链哈希" prop="hash">
      <el-input v-model="dataForm.hash" placeholder="确认病症上链哈希"></el-input>
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
          patientId: '',
          condition: '',
          value: '',
          hash: '',
          createTime: '',
          updateTime: '',
          createUser: '',
          updateUser: '',
          isDeleted: ''
        },
        dataRule: {
          patientId: [
            { required: true, message: '病人id不能为空', trigger: 'blur' }
          ],
          condition: [
            { required: true, message: '医治方案不能为空', trigger: 'blur' }
          ],
          value: [
            { required: true, message: '价格不能为空', trigger: 'blur' }
          ],
          hash: [
            { required: true, message: '确认病症上链哈希不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/mphealth/patientcondition/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.patientId = data.patientCondition.patientId
                this.dataForm.condition = data.patientCondition.condition
                this.dataForm.value = data.patientCondition.value
                this.dataForm.hash = data.patientCondition.hash
                this.dataForm.createTime = data.patientCondition.createTime
                this.dataForm.updateTime = data.patientCondition.updateTime
                this.dataForm.createUser = data.patientCondition.createUser
                this.dataForm.updateUser = data.patientCondition.updateUser
                this.dataForm.isDeleted = data.patientCondition.isDeleted
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
              url: this.$http.adornUrl(`/mphealth/patientcondition/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'patientId': this.dataForm.patientId,
                'condition': this.dataForm.condition,
                'value': this.dataForm.value,
                'hash': this.dataForm.hash,
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
