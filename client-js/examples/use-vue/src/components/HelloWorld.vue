<script setup>
import { ref, onMounted } from 'vue'
import { proxy } from 'ajax-hook'
import axios from 'axios'

defineProps({
  msg: String
})

proxy({
  //请求发起前进入
  onRequest: (config, handler) => {
    console.log(config.url)
    handler.next(config);
  },
  //请求发生错误时进入，比如超时；注意，不包括http状态码错误，如404仍然会认为请求成功
  onError: (err, handler) => {
    console.log(err.type)
    handler.next(err)
  },
  //请求成功后进入
  onResponse: (response, handler) => {
    console.log(response.response)
    handler.next(response)
  }
})

onMounted(_ => {

})
const req1 = () => {
  axios.get('https://bird.ioliu.cn/v1').then(res => {
    alert(JSON.stringify(res.data))
  })
}

const req2 = () => {
  axios.get('https://xxx.https://bird.ioliu.cn/xxx/').then(res => {
    alert(JSON.stringify(res.data))
  }).catch(error => {
    alert(JSON.stringify(error))
  })
}
const count = ref(0)
</script>

<template>
  <h1>{{ msg }}</h1>

  <div class="card">
    <button type="button" @click="count++">count is {{ count }}</button>
    <p>
      Edit
      <code>components/HelloWorld.vue</code> to test HMR
    </p>

    <button type="button" @click="req1">发起一个成功的请求</button>
    <button type="button" @click="req2">发起一个失败的请求</button>
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
