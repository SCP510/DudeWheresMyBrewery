<template>
  <div id="bdcont">
    <div id="details">
      <h3 class="beer-name">
        {{ beer.beerName }}
      </h3>
      <div id="gu">
        <h4 class="beer-type">
          {{ beer.beerType }} - {{ beer.abv }}% ABV
        </h4>
        <p class="desc" id="descr">{{ beer.description }}</p>
      </div>
    </div>
    <div id="update">
      <button class="update-beer-btn"
      @click="this.$store.state.showUpdateBeerForm = !this.$store.state.showUpdateBeerForm">
      {{ this.$store.state.showUpdateBeerForm ? 'Hide Update Beer' : 'Update Beer Info' }}</button>
      <update-beer-form class="update-form" v-show="this.$store.state.showUpdateBeerForm" />
    </div>
    
    <div class="review-update" id="rev">
      <button class="add-review-btn" @click="this.$store.state.showReviewForm = !this.$store.state.showReviewForm">
        {{ this.$store.state.showReviewForm ? 'Hide Add Review' : 'Add A Review' }}</button>
        <create-review class="review-form" v-bind:beer="this.$store.state.beer" v-show="this.$store.state.showReviewForm" />
      </div>
      
      <div id="reviews">
        <review-list v-bind:beer="this.$store.state.beer" v-bind:reviews="this.$store.state.reviews" />
      </div>
      <div id="delete">
        <button class="delete-beer-btn" @click="deleteBeer()">Delete Beer</button>
      </div>
    </div>
  <!-- <button v-show="isFounder" @click="this.$store.state.showUpdateBeerForm = !this.$store.state.showUpdateBeerForm">
    {{ this.$store.state.showUpdateBeerForm ? 'Hide Update Beer' : 'Update Beer Info'}}</button> -->
</template>

<script>

import BeerService from '../services/BeerService';
import UpdateBeerForm from '../components/UpdateBeerForm.vue';
import ReviewList from '../components/ReviewList.vue';
import BeerReviewService from '../services/BeerReviewService';
import BreweryService from '../services/BreweryService';
import CreateReview from '../components/CreateReview.vue';


export default {
  data() {
    return {
      beer: {},
      reviews: [],
      myBrewery: {
      },
      showUpBeerForm: false,
      showReviewForm: false,
      isFounder: false,
    };
  },

  components: {
    UpdateBeerForm,
    ReviewList,
    CreateReview
  },
  methods: {
    deleteBeer() {
      if (confirm('Are you sure you want to delete this beer?')) {
        BeerService.deleteBeer(this.$route.params.id)
          .then(response => {
            if (response.status == 204) {
              this.$store.commit('SET_NOTIFICATION',
                {
                  message: `${this.beer.beerName} has been deleted.`,
                  type: 'success'
                })
              this.refreshBeers;
              this.$router.go(-1);
            }
          })
          .catch(error => {
            if (error) {
              console.log(error.message)
              this.$store.commit('SET_NOTIFICATION',
                {
                  message: `Unable to delete this beer.`,
                  type: 'error'
                })
            }
          })
      }
    },
    refreshBeers() {
      BeerService.getBeers()
        .then(response => {
          const currBeers = response.data;
          this.$store.commit('SET_BEERS', currBeers);
        })
    }
  },
  created() {
    BeerService.getBeerById(this.$route.params.id)
      .then(response => {
        this.beer = response.data;
        this.$store.commit('SET_BEER', this.beer);
        // needs to run on beer update
        BreweryService.getBreweryById(this.beer.breweryId)
          .then(response => {
            this.myBrewery = response.data;
            this.$store.commit('SET_BREWERY', this.myBrewery);
          });
      });
    BeerReviewService.getReviewsByBeerId(this.$route.params.id)
      .then(response => {
        this.reviews = response.data;
        this.$store.commit('SET_REVIEWS', this.reviews);
      });

  },
  computed: {
    // isFounder() {
    //   let user = JSON.parse(window.localStorage.getItem('user'));
    //   return user.id == this.myBrewery.founderId;
    // },

    brewId() {
      return this.beer.breweryId;
    }
  }
};
</script>

<style>
div#delete {
  grid-area: delete;
}
div#bdcont {
  width: 50vw;
  display: grid;
  grid-template-areas:
    "details details details"
    "update revs delete"
    "reviews reviews reviews";
    justify-content: center;
    margin: auto;
}
#gu {
  width: 30vw;
  margin: auto;
  background: black;
  border: double 3px #E79115;
  border-radius: 20px;
  margin-top: 15px;
  margin-bottom: 35px;
}
#gu>p {
  color: white;
}
div#reviews {
  grid-area: reviews;
  padding: 50px;
  margin: auto;
}

div#details {
  grid-area: details;
}

#revbtn {
  grid-area: revs;
}
div#update {
  grid-area: update;
}

.beer-dets {
  color: brown;
  padding-top: 60px;
}

.desc {
  color: brown;
}

.beer-name {
  font-size: 60px;
  text-shadow: 2px 2px 4px #E79115;
  color: black;
  font-family:Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  margin: auto;
  margin-top: 60px;
}

.beer-type {
  color: #e79115;
}

.form-label {
  font-weight: bold;
}

.form-control {
  margin-bottom: 10px;
}

.update-beer-btn {
  color: #e79115;
  width: 20vw;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
}

.update-form {
  color: #e79115;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
  border: double 3px #E79115;

}

.delete-beer-btn {
  color: #e79115;
  width: 20vw;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
}

.add-review-btn {
  color: #e79115;
  width: 15vw;
  background-color: brown;
  font-size: 20px;
  border-radius: 10px;
  margin: auto;
  margin-right: 30px;
  margin-left: 30px;
}

.review-form {
  color: #e79115;
  background-color: brown;
  border-radius: 10px;
  font-size: 20px;
  width: 20vw;
  margin: auto;
  border: double 3px #E79115;
}</style>