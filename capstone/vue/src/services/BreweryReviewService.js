import axios from 'axios';

export default {

  getReviewById(reviewId) { 
    return axios.get(`/breweries/reviews/${reviewId}`);
  },

  getMyBreweryReviews() {
    return axios.get('/breweries/myreviews');
  },

  addReview(review) {
    return axios.post('/breweries/reviews', review);
  },

  updateReview(review) {
    return axios.put(`/breweries/reviews/${review.reviewId}`, review)
  },

  deleteReview(reviewId) {
    return axios.delete(`/breweries/review/${reviewId}`);
  }
}