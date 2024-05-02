import axios from 'axios';

export default {
  getAllBeerReviews() {
    return axios.get('/beers/reviews');
  },

  getReviewById(reviewId) {
    return axios.get(`/beers/reviews/${reviewId}`);
  },

  getReviewsByBeerId(beerId) {
    return axios.get(`/beers/${beerId}/reviews`);
  },

  getMyReviews() {
    return axios.get('/beers/myreviews');
  },

  createBeerReview(review) {
    return axios.post('/beers/reviews', review);
  },

  updateBeerReview(review) {
    return axios.put(`/beers/reviews/${review.reviewId}`)
  },

  deleteBeerReview(reviewId) {
    return axios.delete(`/beers/review/${reviewId}`);
  }
}