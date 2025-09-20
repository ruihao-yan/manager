<template>
  <div class="login-container">
      <component
        :is="currentComponent"
        @switch-component="handleSwitchComponent"
      />
  </div>
</template>

<script setup>
import login from "@/components/login.vue"
import reg from "@/components/reg.vue"
import {ref} from 'vue'

const currentComponent = ref(login);

function handleSwitchComponent(target) {
  if (target === "reg") {
    currentComponent.value = reg;
  } else if (target === "login") {
    currentComponent.value = login;
  }
}
</script>


<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #f2f2f3 0%, #764ba2 100%);
  animation: gradientShift 10s ease infinite;
  background-size: 400% 400%;
}


</style>
