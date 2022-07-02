<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="医生id " prop="doctorId">
      <el-input v-model="dataForm.doctorId" placeholder="医生id "></el-input>
    </el-form-item>
    <el-form-item label="病人id" prop="patientId">
      <el-input v-model="dataForm.patientId" placeholder="病人id"></el-input>
    </el-form-item>
    <el-form-item label="病症描述 （冗余字段）" prop="illness">
      <el-input v-model="dataForm.illness" placeholder="病症描述 （冗余字段）"></el-input>
    </el-form-item>
    <el-form-item label="问诊费用（冗余字段）" prop="price">
      <el-input v-model="dataForm.price" placeholder="问诊费用（冗余字段）"></el-input>
    </el-form-item>
    <el-form-item label="确认费用上链哈希" prop="hash">
      <el-input v-model="dataForm.hash" placeholder="确认费用上链哈希"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
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
          doctorId: '',
          patientId: '',
          illness: '',
          price: '',
          hash: '',
          sort: '',
          createTime: '',
          updateTime: '',
          createUser: '',
          updateUser: '',
          isDeleted: ''
        },
        dataRule: {
          doctorId: [
            { required: true, message: '医生id 不能为空', trigger: 'blur' }
          ],
          patientId: [
            { required: true, message: '病人id不能为空', trigger: 'blur' }
          ],
          illness: [
            { required: true, message: '病症描述 （冗余字段）不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '问诊费用（冗余字段）不能为空', trigger: 'blur' }
          ],
          hash: [
            { required: true, message: '确认费用上链哈希不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/mphealth/doctorpatient/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.doctorId = data.doctorPatient.doctorId
                this.dataForm.patientId = data.doctorPatient.patientId
                this.dataForm.illness = data.doctorPatient.illness
                this.dataForm.price = data.doctorPatient.price
                this.dataForm.hash = data.doctorPatient.hash
                this.dataForm.sort = data.doctorPatient.sort
                this.dataForm.createTime = data.doctorPatient.createTime
                this.dataForm.updateTime = data.doctorPatient.updateTime
                this.dataForm.createUser = data.doctorPatient.createUser
                this.dataForm.updateUser = data.doctorPatient.updateUser
                this.dataForm.isDeleted = data.doctorPatient.isDeleted
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
              url: this.$http.adornUrl(`/mphealth/doctorpatient/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'doctorId': this.dataForm.doctorId,
                'patientId': this.dataForm.patientId,
                'illness': this.dataForm.illness,
                'price': this.dataForm.price,
                'hash': this.dataForm.hash,
                'sort': this.dataForm.sort,
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
