<template>
  <div class="brerwer-list-container">
    <brewery-card v-for="brewery in breweries" v-bind:brewery="brewery" v-bind:key="brewery.breweryId"/>
  </div>
</template>

<script>
import BreweryCard from './BreweryCard.vue';

export default {
  components: {
    BreweryCard
  },
  props: ['breweries'],
  data() {
    return {
    };
  },
  created() {
  }
}
</script>