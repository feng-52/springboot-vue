<template>
  <el-row class="tac">
    <el-col :span="24">
      <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          class="el-menu-vertical-demo"
          default-active="2"
          style="width: 200px;min-height:100vh"
          text-color="#fff"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span>选项1</span>
          </template>
          <el-menu-item index="1-1">
            <el-icon><setting /></el-icon>
            <span>选项1-1</span>
          </el-menu-item>
        </el-sub-menu>


        <el-sub-menu index="2">
          <template #title>
            <el-icon><location /></el-icon>
            <span>选项2</span>
          </template>
          <el-menu-item index="2-1">
            <el-icon><setting /></el-icon>
            <span>选项2-1</span>
          </el-menu-item>
        </el-sub-menu>

        <el-menu-item index="3">
          <el-icon><icon-menu /></el-icon>
          <span>3</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>
