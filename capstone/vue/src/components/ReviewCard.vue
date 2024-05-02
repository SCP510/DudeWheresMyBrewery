<template>
  <div class="rev-card">
    <div id="content">
      <star-rating class="stars" :rating="review.beerRating" :increment=".5" :read-only="true" active-color="#daa520"
        border-color="#a52a2a" :padding="10" :glow="3" glow-color="#FFC0CB" :animate="true" :border-width="2"
        :show-rating="false" />
      <p class="par">{{ review.beerReview }}</p>
    </div>
  </div>
</template>

<script>
import StarRating from 'vue-star-rating';

export default {
  props: ['review'],
  components: {
    StarRating

  },
  data() {
    return {
    };
  },
  methods: {
  },
};
</script>

<style>
#content {
  margin: auto;
  justify-self: center;
}
.rev-card {
    display: flex;
    width: 45vw;
    height: 200px;
    background-color: #e79115;
    border: double 3px black;
    border-width: 3px;
    border-radius: 30px;
    border-block-width: 10px;
    margin: 10px;
  
  }

/* .review-card {
  width: 20vw;
  border-color: #a52a2a;
  background-image: url('../assests/images/');
  background-size: cover;
  background-position: center;
  height: 50vh;
  margin: auto;

} */

.rev-text {
  font-size: 1.5rem;
  color: black;
}
.par {
  margin: 10px 20px 10px 20px;
}
.stars {
  justify-content: center;
  margin-bottom: 10px;
}
</style>