import axios from 'axios';

export default {
  getBeers() {
    return axios.get('/beers');
  },
  getBeerById(beerId) {
    return axios.get(`/beers/${beerId}`);
  },
  
  getSavedBeers() {
    return axios.get('/mybeers')
  },

  getRandomBeer() {
    return axios.get('/beers/random')
  },

  getBeersByBrewery(breweryId) {
    return axios.get(`/brewery/beers/${breweryId}`);
  },

  addSavedBeer(beerId) {
    return axios.post(`/mybeers/${beerId}`);
  },

  createBeer(beer) {
    return axios.post('/beers', beer);
  },

  updateBeer(beer) {
    return axios.put(`/beers/${beer.beerId}`, beer);
  },

  deleteSavedBeer(beerId) {
    return axios.delete(`/mybeers/${beerId}`);
  },

  deleteBeer(beerId) {
    return axios.delete(`/beers/${beerId}`);
  }
};