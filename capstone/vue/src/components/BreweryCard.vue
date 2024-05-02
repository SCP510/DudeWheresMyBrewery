<template>
    <div class="brew-card">
        <div class="card-brew-body">
            <h2 class="card-titles">{{brewery.breweryName}}</h2>
            <h4 class="brew-location">{{ brewery.city }}, {{ brewery.state }}</h4>
            <p class="card-test"><a v-bind:href="brewery.website" target="_blank">Visit Us on the Web</a></p>
        <div>
                
            <router-link :to="{ name: 'brewery-details', params: { breweryId: brewery.breweryId } }" class="btn btn-secondary">View Details</router-link>
            </div>

        </div>

    </div>
</template>

<script>
export default {
    props: ['brewery'],
    data() {
        return {
            isSaved: false,
        }
    }
}
</script>

<style scope>
.card-brew-body {
    display: grid;
    
    min-height: 30vh;
    border-radius: 40px;
    
    margin-bottom: 40px;
    background-color: #E79115;
    
    background-size: cover;
    background-position: center;
    height: 100 vh;   
}
.brew-location {
    color: red;
}

.brew-card {
    display: flex;
    width: 35vh;

    min-height: 30vh;
    margin: auto;
    align-content: center;
    justify-content: center;
    border-radius: 30px;
    margin-bottom: 30px;
  }

  .card-titles {
    font-size: 30px;
    color: black;
    background-color: white;
    height: 100px;
    width: 300px;
    border-radius: 5px;
  }

.btn {
    margin-top: 20px;
    margin-bottom: 10px;
}

</style>