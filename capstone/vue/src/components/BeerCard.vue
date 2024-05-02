<template>
    <div class="beer-card card">
      <img class="card-img-top" src="../assests/images/placeholders/Asset__4-1080x1080.png" alt="Beer Image">
      <div class="card-body">
        <h2 class="card-title">{{ beer.beerName }}</h2>
        <p class="card-text">{{ beer.beerType }}</p>
        <p class="card-text">{{ beer.abv }}% ABV</p>
        <button class="btn btn-primary" @click="addSaved(); isSaved = true;">Save</button>
        <button class="btn btn-primary" @click="deleteSaved(); isSaved = false"> Unsave </button>
        <router-link :to="{ name: 'beer-details', params: { id: beer.beerId } }" class="btn btn-secondary">View Details</router-link>

         <!-- BUTTON TO ADD/REMOVE FROM SAVED if savedBeers.contains(beer.beerId)-->
        <!-- CLICKING CARD ROUTES TO BEER DETAILS PAGE -->
      </div>
    </div>
  </template>
  
  <script>
  import BeerService from '../services/BeerService';
  
  export default {
    props: ['beer'],
    data() {
      return {
        myBeers: [],
        isSaved: false,
      };
    },
    methods: {
      addSaved() {
        BeerService.addSavedBeer(this.beer.beerId)
        .then(response => {
          if (response.status == 201) {
            this.$store.commit('SET_NOTIFICATION',
            {
              message: `${this.beer.beerName} has been saved.`,
              type: 'success'
            })
          }
        })
        .catch (error => {
          if (error) {
            this.$store.commit('SET_NOTIFICATION', 
            {
              message: `${this.beer.beerName} is already saved.`
            })
          }
        })
      },
      deleteSaved() {
        BeerService.deleteSavedBeer(this.beer.beerId)
        .then(response => {
          if (response.status == 204) {
            this.$store.commit('SET_NOTIFICATION', 
            {
              message: `${this.beer.beerName} removed from favorites.`,
              type: 'success'
            })
          }
          this.$store.dispatch('getUpdatedSaved');
        });
      }
      }
  };
  </script>
  
  <style scoped>
  .card {
    display: flex;
    width: 18rem;
    background-color: #e79115;
    justify-content: center;
    align-items: center;
    min-height: 35vh;
    border-color: brown;
    border-radius: 30px;
    border-block-width: 10px;
    margin: 10px;
  
  }
  .card-img-top {
    height: 200px;
    object-fit: cover;
    border-radius: 12px;
  }
  .card-title {
    margin-top: 10px;
    font-size: 1rem;
    color:red;
    
  }
  .card-text {
    margin-top: 5px;
    font-size: x-large;
    
  }
  .btn {
    margin-top: 10px;
    color:black;
    word-spacing: 10px;
    margin: 10px;
  }
  </style>