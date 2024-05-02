<template>
    <div class="rev-form">
        <form v-on:submit.prevent="addReview" >
            <div>
                <div class="stars">
                    <star-rating id="dastars" v-model:rating="newReview.beerRating" 
                    active-color="#daa520" border-color="#a52a2a"
                    :increment=".5"
                        :padding="1"
                        :glow="3" glow-color="#FFC0CB" 
                        :animate="true" :border-width="2"
                        :show-rating="false" />
                </div>
            </div>
            <div>
                <textarea class="review-text" name="reviewText" id="reviewText" cols="30" rows="5" 
                    v-model="newReview.beerReview" required></textarea>
            </div>
            <button type="submit" @click="toggleReviewForm">Submit Review</button>
        </form>
    </div>
</template>

<script>
import StarRating from 'vue-star-rating';
import BeerReviewService from '../services/BeerReviewService';
export default {
    props: ['beer'],
    components: {
        StarRating
    },
    data() {
        return {
            newReview: {
                beerReviewId: 0,
                beerId: this.$route.params.id,
                reviewerId: this.$store.state.user.id,
                beerRating: 0.0,
                beerReview: '',
                datePosted: new Date(2023, 12, 12),
            }
        }
    },
    computed: {
        reviewer() {
            let user = JSON.parse(window.localStorage.getItem('user'));
            return user.id;
        }
    },
    methods: {
        resetForm() {
            this.newReview = {
                beerReviewId: 0,
                beerId: this.$store.state.beer.beerId,
                reviewerId: this.reviewer,
                beerRating: 0.0,
                beerReview: '',
                datePosted: new Date(2023, 12, 12),
            };
        },
        addReview() {
            BeerReviewService.createBeerReview(this.newReview)
                .then(response => {
                    if (response.status == 201) {
                        this.$store.commit('SET_NOTIFICATION',
                        {
                            message: 'Your review has been posted.',
                            type: 'success'
                        })
                        this.$store.dispatch('getUpdatedReviews', this.$store.state.beer.beerId);
                    }
                })
                .catch(error => {
                    if (error) {
                        this.$store.commit('SET_NOTIFICATION',
                        {
                            message: 'There was an error posting your review. Please try again later.',
                            type: 'error'
                        })
                    }
                });
            this.resetForm();
        },

        toggleReviewForm() {
            this.$store.commit('FLIP_REVIEW_FORM');
        }
    }
}
</script>

<style>
#dastars{
    margin: 10px 20px 20px;
    justify-content: center;
}
.review-text {
    border: double 3px #;
}
</style>