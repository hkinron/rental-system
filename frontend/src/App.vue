<template>
  <div id="app">
    <div id="nav">
      <router-link to="/room">Room</router-link> |
      <router-link to="/user">User</router-link> |
      <router-link to="/record">Record</router-link> |
      <router-link to="/bill">Bill</router-link>
    </div>
    <router-view :hellomsg="msg"></router-view>
  </div>
</template>

<script>
  export default {
    name: 'app',
    data () {
      return {
        msg: 'Welcome to your Vue.js powered Spring Boot App'
      }
    }
  }
</script>

<style>
  @import "common/fonts/stylus/style.css";
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}


</style>
