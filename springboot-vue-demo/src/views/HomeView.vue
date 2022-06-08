<template>
  <div class="home" style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" style="width: 20%;" placeholder="请输入关键字"></el-input>
      <el-button type="primary" style="margin-left: 5px">查询</el-button>
    </div>
    <!--    表格区域-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="nickName" label="昵称"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="sex" label="性别"/>

      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
          >编辑
          </el-button>

          <el-popconfirm title="确定删除吗?">
            <template #reference>
              <el-button
                  size="small"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)"
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
    </div>
  </div>
</template>

<script>

export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
      form:{},
      dialogVisible: false,
      serch: '',
      currentPage: 1,
      pageSize:"10",
      total:  0,
      tableData: [
        {
          id: '1',
          name: 'Tom',
          nickName: '189号',
          age: 18,
          sex: "男",

        },
        {
          id: '1',
          name: 'Tom',
          nickName: '189号',
          age: 18,
          sex: "男",

        },
        {
          id: '1',
          name: 'Tom',
          nickName: '189号',
          age: 18,
          sex: "男",

        },
        {
          id: '1',
          name: 'Tom',
          nickName: '189号',
          age: 18,
          sex: "男",

        },
      ]
    }
  },
  methods: {
    handleEdit() {
    },
    handleDelete() {
    },
    handleSizeChange(){},
    handleCurrentChange(){}
  }
}
</script>

