<template>
  <div class="home" style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" @input="onInput" style="width: 20%;" clearable placeholder="请输入关键字"/>
      <el-button type="primary" @click="load" style="margin-left: 5px">查询</el-button>
    </div>
    <!--    表格区域-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="nickName" label="昵称"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="sex" label="性别"/>
      <el-table-column label="角色">
        <template #default="scope">
          <span v-if="scope.row.role === 1">管理员</span>
          <span v-if="scope.row.role === 2">普通用户</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="260">
        <template #default="scope">
          <el-button type="success" @click="bookVis = true;showBooks(scope.row.bookList)" size="small"
          >查看图书列表
          </el-button>

          <el-button type="primary" size="small" @click="handleEdit(scope.row)"
          >编辑
          </el-button>

          <el-popconfirm title="确定删除吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button
                  size="small"
                  type="danger"
              >删除
              </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    分页-->
    <div style="margin: 10px 0">
      <el-pagination
          :currentPage="currentPage"
          :page-sizes="[5,10,20]"
          :page-size="pageSize"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>

      <!--      <el-dialog-->
      <!--          title="用户的图书列表"-->
      <!--          v-model="bookVis"-->
      <!--          width="30%"-->
      <!--      >-->

      <!--        <el-table :data="bookList" stripe border>-->
      <!--          <el-table-column prop="id" label="ID"></el-table-column>-->
      <!--          <el-table-column prop="name" label="名称"></el-table-column>-->
      <!--          <el-table-column prop="price" label="价格"></el-table-column>-->
      <!--        </el-table>-->
      <!--      </el-dialog>-->

      <el-dialog v-model="bookVis" title="用户的图书列表">
        <el-table :data="bookList">
          <el-table-column property="id" label="ID" width="150" />
          <el-table-column property="name" label="名称" width="200" />
          <el-table-column property="price" label="价格" />
        </el-table>
      </el-dialog>
      <!--      弹窗-->
      <el-dialog
          title="提示"
          v-model="dialogVisible"
          width="30%"
      >

        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%;"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width: 80%;"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%;"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
            <el-radio v-model="form.sex" label="未知">未知</el-radio>
          </el-form-item>


        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      serch: '',
      currentPage: 1,
      pageSize: "10",
      total: 0,
      tableData: [],
      bookList: [],
      bookVis: false
    }
  },
  //生命周期函数
  created() {
    //调用load()
    this.load()
  },
  methods: {
    showBooks(books) {
      this.bookList = books
      this.bookVis = true
    },
    onInput() {
      this.$forceUpdate();
    },
    load() {
      //传入三个参数 get请求不能传对象
      request.get("/user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        this.tableData = res.data.records;
        //实现前端显示总条数
        this.total = res.data.total;
        // console.log(res)
      })
    },
    add() {
      //打开弹窗
      this.dialogVisible = true;
      //清空
      this.form = {}
    },
    //点击确认后保存到后台
    save() {
      //使用axios进行数据交互
      //两个参数，url和请求参数

      /*      注意这样写会有跨域问题
              request.post("/user",this.form).then(res => {
              console.log(res)
            })

            request.post("http://localhost:9090/user",this.form).then(res => {
              console.log(res)
            })
      */
      //如果id存在 执行更新操作  否则执行新增操作
      if (this.form.id) {//更新
        request.put("/user", this.form).then(res => {
          console.log(res)
          //根据状态码判断 0为成功 这里用了elmentui 的提示框
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }

          this.load()//刷新表格数据
          this.dialogVisible = false //关闭弹窗
        })
      } else {//新增
        request.post("/user", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }

          this.load()//刷新表格数据
          this.dialogVisible = false //关闭弹窗
        })
      }

    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      console.log(this.form)
      this.dialogVisible = true
    },
    handleDelete(id) {
      request.delete("/user/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

        this.load()//刷新表格数据
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load();
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage
      this.load();
    }
  }
}
</script>
