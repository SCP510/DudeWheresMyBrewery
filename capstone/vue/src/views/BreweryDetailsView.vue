<template>
  <h1 class="brew-name">{{ brewery.breweryName }}</h1>
   <h4 class="loc">{{ brewery.city }}, {{ brewery.state }}</h4>
  <div id="container">
    <div class="brew-info" id="info">
      
     <h2 class="about">About Us</h2>
      <p class="brew-details">{{ brewery.aboutUs }}</p>
    </div>
    <div id="map">
      <brew-map v-bind:brew="brewery" v-bind:address="fixedAddress" class="map" />
    </div>
    <div id="blist">
      <beer-list v-bind:beers="this.$store.state.beers" class="list" />
    </div>
    <div id="updatebrew">
      <button class="update-brew"
        @click="this.$store.state.showUpdateBreweryForm = !this.$store.state.showUpdateBreweryForm">
        {{ this.$store.state.showUpdateBreweryForm ? 'Hide' : 'Update My Brewery' }}</button>
      <update-brewery-form class="update-brew" v-show="this.$store.state.showUpdateBreweryForm"
        v-bind:brewery="brewery" />
    </div>
<div id="add" v-show="checkFounder">
      <!-- <button @click="showCreateBeer = !showCreateBeer">{{ showCreateBeer ? 'Hide' : 'Add New Beer' }}</button> -->
      <button class="create-burr" @click="this.$store.state.showCreateBeerForm = !this.$store.state.showCreateBeerForm">
        {{ this.$store.state.showCreateBeerForm ? 'Hide' : 'Add a New Beer' }}</button>
      <create-beer-form v-show="this.$store.state.showCreateBeerForm" v-bind:brewery="brewery" class="create-burr" />
    </div>

  </div>
  
</template>

<script>

import BrewMap from '../components/BrewMap.vue';
import BeerService from '../services/BeerService';
import BreweryService from '../services/BreweryService';
import BeerList from '../components/BeerList.vue';

import UpdateBreweryForm from '../components/UpdateBreweryForm.vue';
import CreateBeerForm from '../components/CreateBeerForm.vue';

export default {
  data() {
    return {
      brewery: {},
      beers: [],
      isFounder: false,
      showUpdateBrewery: false,
      showCreateBeer: false,
    }
  },
  // GET CURRENT BREWERY
  // GET BEER LIST FOR BREWERY

  components: {
    BrewMap,
    BeerList,
    UpdateBreweryForm,
    CreateBeerForm
  },

  created() {
    this.getBreweryById();
    this.getBeersByBrewery();

  },
  methods: {
    getBeersByBrewery() {
      BeerService.getBeersByBrewery(this.$route.params.breweryId)
        .then(response => {
          this.beers = response.data;
          this.$store.commit('SET_BEER_LIST', this.beers);
        })
        .catch(error => {
          console.log(error)
        })
    },
    getBreweryById() {
      BreweryService.getBreweryById(this.$route.params.breweryId)
        .then(response => {
          this.brewery = response.data;
          this.$store.commit('SET_BREWERY', this.brewery);
        });
    }
  },

  computed: {
    fixedAddress() {
      let newAddress = this.brewery.streetAddress + ' ' + this.brewery.city + ' ' + this.brewery.state + ' ' + this.brewery.zipcode;
      return newAddress;
    },
    checkFounder() {
      let user = JSON.parse(window.localStorage.getItem('user'));
      return user.id == this.brewery.founderId;
    }
  },
  updated() {
    this.getBeersByBrewery();
  }
}
</script>

<style>
.loc{
  font-style: oblique;
  color: #E79115;
}
.about {
  color: #e79115;
  background: brown;
  width: 10vw;
  margin: auto;
  padding: 10px;
  border-radius: 10px 10px 0 0;
}
div#updatebrew {
  grid-area: updateform;
}

div#add {
  grid-area: addform;
}

div#info {
  grid-area: info;
  margin: auto;
}

div#container {
  margin: auto;
  display: grid;
  width: 80vw;
  height: 80vh;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr ;
  gap: 20px;
  grid-template-areas:
    "info map"
    "updateform addform"
    "list list";
}

div#map {
  grid-area: map;
  height: 100%;
}

div#blist {
  grid-area: list;
}

.brew-name {
  font-size: 60px;
  text-shadow: 1px 1px 2px #E79115;
  color: black;
  font-family:Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  padding-top: 50px;
  width: 40vw;
  margin: auto;
}

.brew-details {
  color: white;
  background: grey;
  padding: 25px;
  border-radius: 25px;
  width: 30vw;
  border: solid 1px brown;
}

.update-brew {
  color: #e79115;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;

}

.create-burr {
  color: #e79115;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
}

.cancel-beer {
  color: #e79115;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
}

.update-brewery {
  color: #e79115;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
}
</style>