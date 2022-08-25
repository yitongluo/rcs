<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand"></router-link>
      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/login" class="nav-link">Login</router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/cases" class="nav-link">背调管理</router-link>
        </li>
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            用户
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <button class="m-3 btn btn-sm btn-danger" @click.prevent="logOut">
              LogOut
            </button>
            <button class="m-3 btn" @click.prevent="viewProfile">
              View Profile
            </button>
          </div>
        </div>
        
      </div>
    </nav>
    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>
<script>
export default {
  name: "app",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },
    viewProfile() {
      this.$router.push('/profile')
    }
  }
};
</script>