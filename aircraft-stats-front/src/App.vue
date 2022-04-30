<template>
  <div id="app">
    <p>
      <router-link class="link" to="/">Home</router-link>
      <router-link class="link" to="/history">History</router-link>      
    </p>
    <h1>Aircraft Stats</h1>
    <router-view></router-view>
  </div>
</template>
 
<script>
 
export default {
  name: 'App'
}
</script>
 
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.link {
  margin-right: 10px;
  margin-left: 10px;
}
</style>