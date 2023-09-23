import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AddCategory from '../views/Category/AddCategory'
import DisplayCategory from '../views/Category/Category'
import AdminMain from "../views/AdminMain.vue";
import AdminProduct from "../views/Product/AdminProduct.vue"
import AddProduct from "../views/Product/AddProduct.vue"
import EditCategory from "../views/Category/EditCategory.vue"
import EditProduct from "../views/Product/EditProduct"
import ShowDetails from "../views/Product/ShowDetails"
import ListProducts from "../views/Category/ListProducts"
import SignUp from "../views/SignUp"
import SignIn from "../views/SignIn"
import WishList from "../views/Product/Wishlist.vue"
import CaRt from "../views/Cart.vue"

import Success from "../views/Payment/Success"
import Failed from "../views/Payment/Failed"
import CheckOut from "../views/Checkout/CheckOut"




const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path:'/category/show/:id',
    name:'ListProducts',
    component:ListProducts
  },
  {
    path: '/admin/category/add',
    name: 'AddCategory',
    component: AddCategory
  },
  {
    path: '/admin/category',
    name: 'Category',
    component: DisplayCategory
  },
  {
    path:'/admin',
    name:'AdminMain',
    component: AdminMain
  },
  {
    path:'/admin/product',
    name:'AdminProduct',
    component: AdminProduct
  },
  {
    path:'/admin/product/new',
    name:'AddProduct',
    component:AddProduct
  },
  {
    path:'/admin/category/:id',
    name:'EditCategory',
    component:EditCategory
  },
  {
    path:'/admin/product/:id',
    name:'EditProduct',
    component:EditProduct
  },
  {
    path:'/admin/show/:id',
    name:'ShowDetails',
    component:ShowDetails
  },
  {
    path:'/signup',
    name:'Signup',
    component: SignUp
  },
  {
    path:'/signin',
    name:'Signin',
    component: SignIn
  },
  {
    path:'/wishlist',
    name:'Wishlist',
    component:WishList
  },
  {
    path:'/cart',
    name:'Cart',
    component:CaRt
  },
  
  {
    path:'/payment/success',
    name:'PaymentSuccess',
    component:Success
  },
  {
    path:'/payment/failure',
    name:'PaymentFail',
    component:Failed
  },
  {
    path:'/checkout',
    name:'CheckOut',
    component:CheckOut
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
