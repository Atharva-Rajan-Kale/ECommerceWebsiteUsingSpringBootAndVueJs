<template>
<NavBar :cartCount="cartCount"
@resetCartCount="resetCartCount"
v-if="!['Signup', 'Signin'].includes($route.name)"></NavBar>

  <router-view v-if="categories && products" style="min-height: 60vh;"
    :baseURL="baseURL"
    :categories="categories"
    :products="products"
    @fetchData="fetchData"
    >
  </router-view>
  
  <FooTer v-if="!['Signup', 'Signin'].includes($route.name)"></FooTer>

</template>

<script>

  import axios from "axios";
import NavBar from "./components/Navbar.vue";
import FooTer from "./components/Footer.vue";
  
  export default {
    components: {FooTer, NavBar},
    data() {
    return {
      baseURL : "https://limitless-lake-55070.herokuapp.com/",
      products: null,
      categories: null,
      token: null,
      cartCount:0,
      };
    },
    methods:{
      async fetchData(){
        await axios
        .get(this.baseURL + 'product/')
        .then((res) => (this.products = res.data))
        .catch((err) => console.log(err));

        await axios
        .get(this.baseURL+"category/")
        .then(res=>{
          this.categories=res.data
        }).catch((err)=>console.log("err",err));

        if (this.token) {
        await axios.get(`${this.baseURL}cart/?token=${this.token}`).then(
          (response) => {
            if (response.status == 200) {
              // update cart
              this.cartCount = Object.keys(response.data.cartItems).length;
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    resetCartCount() {
      this.cartCount = 0;
    },
  },
    mounted(){
      this.token=localStorage.getItem("token");
      this.fetchData();
    }
  };
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
