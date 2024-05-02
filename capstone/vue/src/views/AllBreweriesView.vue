<template>
  <h2 class="bcs">Breweries</h2>
  <!-- BUTTONS ON CARDS TO ADD SAVED -->
  <!-- ADD BREWERY BUTTON/FORM for ROLE_BREWER -->
  <!-- Brewery Search Bar/Filter fields -->
  <!-- Container with Brewery Cards / Brewery List-->
  <brewery-list id="brewery-list" v-bind:breweries="breweries" />
</template>

<script>
import BreweryService from '../services/BreweryService';
import BreweryList from '../components/BreweryList.vue'

export default {
  components: {
    BreweryList
  },
  // GET ALL BREWERIES
  // FILTER BREWERIES
  // CHECK USER ROLE
  data() {
    return {
      breweries: [],
      beers: []
    }
  },
  created() {
    BreweryService.getBreweries()
    .then(response => {
      this.breweries = response.data;
      this.$store.commit('SET_BREWERIES', this.breweries);
      this.$store.dispatch('getBreweries');
    })
  }
}
</script>

<style>

.bcs {
  background-size: cover;
  height: 200px;
  font-size: 80px;
  color: #e79115;
  padding-top: 3rem;
  margin: auto;

  font-size: 60px;
  text-shadow: 1px 1px 2px #e79115;
  color: black;
  font-family:Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}



</style>