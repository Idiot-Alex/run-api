<script setup>
import { ref } from 'vue'
// import * as playwright from 'playwright'


defineProps({
  msg: String
})

const count = ref(0)
const url = ref('https://www.baidu.com')
ElMessage.success('this is a message')

const run = async () => {

  console.log(url.value)
}
</script>

<template>
  <h1>{{ msg }}</h1>

  <div class="card">
    <button type="button" @click="count++">count is {{ count }}</button>
    <p>
      Edit
      <code>components/HelloWorld.vue</code> to test HMR
    </p>
    <input type="text" name="url" v-model="url" />
    <button type="button" @click="run">访问</button>
  </div>

  <p>
    Check out
    <a href="https://vuejs.org/guide/quick-start.html#local" target="_blank"
      >create-vue</a
    >, the official Vue + Vite starter
  </p>
  <p>
    Install
    <a href="https://github.com/johnsoncodehk/volar" target="_blank">Volar</a>
    in your IDE for a better DX
  </p>
  <p class="read-the-docs">Click on the Vite and Vue logos to learn more</p>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
