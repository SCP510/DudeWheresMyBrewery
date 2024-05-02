<template>
        <review-card id="revlist" v-for="review in reviews" v-bind:review="review" v-bind:key="review.beerReviewId" class="rev-card"/>
</template>

<script>
import ReviewCard from '../components/ReviewCard.vue';
export default {
    components: {
        ReviewCard
    },
    props: ['beer', 'reviews'],
    data() {
        return {
        }
    },
    created() {
    }

}
</script>

<style>
#rev{
    display: flex;
    flex-wrap: wrap;
    flex-grow: 1;
    justify-content: space-evenly;
}
</style>