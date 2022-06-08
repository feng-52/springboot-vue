<template>
  <nav>
    <!--头部-->
    <Header></Header>
    <!--主题-->
    <div style="display: flex">
      <!-- 侧边栏-->
      <Aside></Aside>
      <!--内容区域-->
      <router-view style="flex: 1;"></router-view>
    </div>
  </nav>1

</template>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
  name: "App",
  components: {
    Header,
    Aside
  }
}
</script>
