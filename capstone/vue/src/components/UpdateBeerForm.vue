<template>
  <form class="update-beer-form" v-on:submit.prevent="updateBeer" v-show="this.$store.state.showUpdateBeerForm">
    <div class="mb-3">
      <label for="beerName" class="form-label">Beer Name</label>
      <input v-model="updatedBeer.beerName" type="text" class="form-control" id="beerName" placeholder="Update beer name" required>
    </div>

    <div class="mb-3">
      <label for="beerType" class="form-label">Beer Type</label>
      <input v-model="updatedBeer.beerType" type="text" class="form-control" id="beerType" placeholder="Update beer type" required>
    </div>

    <div class="mb-3">
      <label for="beerDescription" class="form-label">Description</label>
      <textarea v-model="updatedBeer.description" class="form-control" id="beerDescription" rows="6"></textarea>
    </div>

    <div class="mb-3">
      <label for="abv" class="form-label">ABV</label>
      <input v-model="updatedBeer.abv" type="number" step=".1" class="form-control" id="abv" placeholder="Update % ABV, example 5.5" required>
    </div>

    <button type="submit" class="btn btn-primary" @click="toggleUpdateBeerForm">Submit Changes</button>
  </form>
</template>
  
<script>

import BeerService from '../services/BeerService'

export default {
  data() {
    return {
      updatedBeer: {},
      showUpdateBeer: false
    }
  },

  methods: {

    updateBeer() {
      BeerService.updateBeer(this.updatedBeer)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit('SET_NOTIFICATION',
            {
              message: 'Your beer has been updated.',
              type: 'success'
            })
            this.getBeer;
          }
        })
        .catch(error => {
          if (error.response.status) {
            this.$store.commit('SET_NOTIFICATION',
            {
              message: 'There was an error updating your beer. Please try again later.',
              type: 'error'
            })
          }
        })
      // Look at setNotification from Lecture
    },
    getBeer() {
      BeerService.getBeerById(this.$route.params.id)
      .then(response => {
        const currentBeer = response.data;
        this.updatedBeer = currentBeer;
        this.$store.commit('SET_BEER', currentBeer);
      })      
    },

    toggleUpdateBeerForm() {
      this.$store.commit('FLIP_UPDATE_BEER_FORM');
    }
  },
  created() {
    this.getBeer();
  }
}
</script>
  
<style>
.update-beer-form {
  color: #e79115;
  width: 40vh;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
  padding: 35px;
  margin: auto;
}
</style>