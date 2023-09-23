<template>
    <div class="container py-4">
    <router-link :to="{name:'Category'}">
        <button class="btn btn-primary btn-lg">Admin Category</button>
    </router-link>
</div>
    <br>

    <router-link :to="{name:'AdminProduct'}">
        <button class="btn btn-primary btn-lg">Admin Products</button>
    </router-link>
    <br>
    <router-link :to="{name:'Category'}">
        <button class="btn btn-primary btn-lg">Admin Products</button>
    </router-link>
    
</template>

<script>
    export default{

    }
</script>

<style>
.btn{
    margin-bottom: 20px;

}
</style>