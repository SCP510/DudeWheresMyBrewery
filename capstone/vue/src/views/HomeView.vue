<template>
  <div id="main-grid">
    <header id="header">
      <h1 id="headline" class="header">Dude, Where's My Brewery?</h1>
    </header>

      <div id="brewer-options" class="new-brew-form">
        <button class="create-brew-button" @click="toggleCreateBreweryForm">{{ this.$store.state.showCreateBreweryForm ? 'Hide Add New Brewery' : 'Add New Brewery' }}</button>
        <create-brewery-form v-show="this.$store.state.showCreateBreweryForm" />
      </div>

        <router-link id="row-1-brewery" :to="{name: 'breweries'}" >
          <img class="brewery1" src="../assests/images/placeholders/roberta-keiko-kitahara-santana-RfL3l-I1zhc-unsplash-2-1536x639.webp" alt="">
        </router-link>
        
        <router-link id="row-1-beer" :to="{name: 'beers'}"> 
          <img src="../assests/images/placeholders/twobeers.jpg" />
        </router-link>
        <!-- <beer-card v-bind:beer="this.$store.state.beer" /> -->


        <!-- <brewery-card v-bind:brewery="this.$store.state.brewery"/> -->
        <div id="row2">
          <router-link id="row-2-beer" :to="{name: 'beers'}"> 
            <img src="../assests/images/placeholders/beers.jpg" />
          </router-link>

          <router-link id="row-2-brewery" :to="{name: 'breweries'}"> 
            <img class="brewery2" src="../assests/images/placeholders/bar.jpg" />
          </router-link>
        </div>


      <!-- Button for Brewer Dashboard v-show="user.role = ROLE_BREWER localstorage reference" -->
      <!-- Button for BreweryCardSearch View -->
      <!-- Button for BeerCardSearch View -->
      <!-- Random Brewery Display -->
      <!-- Meet the Brew Boys Page -->

    <footer id="footer" class="meet-the-bois">
      <p id="meet-us" class="meet">Meet the Brew Bois!</p>
      <p id="names" class="links">
        <a target="_blank" href="https://www.linkedin.com/in/xhelal-mahmuti/">Xhelal Mahmuti</a>
        <a target="_blank" href="https://www.linkedin.com/in/jeffreybracken/">Jeff Bracken</a>
        <a target="_blank" href="https://www.linkedin.com/in/stevenpickeringscp/">Steven Pickering</a>
        <a target="_blank" href="https://www.linkedin.com/in/spencer-meredith/">Spencer Meredith</a>
      </p>
    </footer>
  </div>
</template>

<script>
import CreateBreweryForm from '../components/CreateBreweryForm.vue';
import BeerService from '../services/BeerService';
import BreweryService from '../services/BreweryService';
import BeerCard from '../components/BeerCard.vue';
import BreweryCard from '../components/BreweryCard.vue';

export default {
  components: { CreateBreweryForm, BeerCard, BreweryCard },
  // GET RANDOM BREWERY/BEER? BOTH

  // CURRENTLY LOADS ALL BREWERIES AND BEERS TO HOME VIEW
  // PROBABLY NOT WHAT WE WANT
  // PULL ONE RANDOM OF EACH???
  data() {
    return {
      beers: [],
      breweries: [],
      isBrewer: false,
      randomBrewery: {},
      randomBeer: {}
    }
  },

  methods: {
    toggleCreateBreweryForm() {
      this.$store.commit('FLIP_CREATE_BREWERY_FORM');
    },
    getRandomBrewery() {
      BreweryService.getRandomBrewery()
      .then(response => {
        const currBrewery = response.data;
        this.randomBrewery = currBrewery;
        this.$store.commit('SET_BREWERY', currBrewery);
      })
    },
    getRandomBeer() {
      BeerService.getRandomBeer()
      .then(response => {
        const currBeer = response.data.
        this.randomBeer = currBeer;
        this.$store.commit('SET_BEER', currBeer);
      })
    }
  },

  created() {
    BeerService.getBeers()
      .then(response => {
        this.beers = response.data;
      });
    BreweryService.getBreweries()
      .then(response => {
        this.breweries = response.data;
      });
      this.getRandomBeer;
      this.getRandomBrewery;

    // this.isBrewer = checkRole('ROLE_BREWER');
  }
}

</script>

<style scoped>

#main-grid {
  display: grid;
  
  grid-template-areas:
    "header header"
    "brewer-options brewer-options"
    "row-1-brewery row-1-beer"
    "row2 row2"
    "footer footer";

  grid-template-columns: 50% 50%;
  row-gap: 30px;
  column-gap: 50px;

  margin-left: 200px;
}

#header {
  grid-area: header;
  margin-right: 200px;
}

#headline {
  font-size: 60px;
  text-shadow: 2px 2px 1px #e79115;
  color: black;
  font-family:Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

#brewer-options {
  grid-area: brewer-options;
}

#row-1-brewery {
  grid-area: row-1-brewery;
}
#row-1-beer {
  grid-area: row-1-beer;
  width: 1em;
}
.brewery1 {
  width: 400px;
}

#row2 {
  grid-area: row2;
}
#row-2-beer {
  /* grid-area: row-2-beer; */
  margin-right: 120px;
}
#row-2-brewery {
  /* grid-area: row-2-brewery; */
  margin-right: 170px;
}
.brewery2 {
  width: 400px;
}

#footer {
  grid-area: footer;
  border-top: 1px solid white;
  margin-top: 5%;
  margin-right: 100px;
}

#meet-us {
  grid-area: meet-us;
}

#names {
  grid-area: names;
}

.links>a{
  text-decoration: underline;
  color:white;
  font-size: 20px;
  margin-right: 20px;
}

.header {
  padding-top: 20px;
}

.meet{
  color: white;
  font-size: 21px;
  margin: auto;
}

img {
  height: 200px;
}

.new-brew-form{
  margin-right: 200px;
}

.create-brew-button {
  color: #e79115;
  width: 40vw;
  background-color: brown;

  border-radius: 10px;
  font-size: 20px;
}

</style>