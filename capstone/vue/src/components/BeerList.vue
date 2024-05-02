<template>
  <div class="beer-list-container">
    <beer-card v-for="beer in beers" v-bind:key="beer.beerId" v-bind:beer="beer"/>
  </div>
</template>

<script>
import BeerCard from "./BeerCard.vue";

export default {
  components: {
    BeerCard,
  },
  props: ['beers'],
  data() {
    
    return {
      
    };
  },
  methods: {
    
  },
  created (){
  }
};
</script>

<style>
.beer-list-container {
  display: flex;
  flex-wrap: wrap;
  flex-grow: 1;
  justify-content: space-evenly;
}
</style>