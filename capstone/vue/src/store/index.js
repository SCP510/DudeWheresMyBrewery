import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import BeerService from '../services/BeerService';
import BeerReviewService from '../services/BeerReviewService';
import BreweryService from '../services/BreweryService'

const NOTIFICATION_TIMEOUT = 3000;

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},

      breweries: [],
      beers: [],
      reviews: [],
      savedBeers: [],
      brewery: [],
      beer: [],

      showReviewForm: false,
      showCreateBreweryForm: false,
      showUpdateBreweryForm: false,
      showCreateBeerForm: false,
      showUpdateBeerForm: false,
      notification: null,
    },
    getters: {
      getSavedBeers : (state) => {
        return state.savedBeers;
      }
    },
    mutations: {
      SET_NOTIFICATION(state, notification) {
        // clear current if one exists
        if (state.notification) {
          this.commit('CLEAR_NOTIFICATION')
        }
        if (typeof notification === 'string') {
          notification = {
            message: notification,
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          }
        } else {
          // else add default values
          notification.type = notification.type || 'error';
          notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
        }

        //set notification in state
        state.notification = notification;

        // clear message after timeout
        notification.time = window.setTimeout(() => {
          this.commit('CLEAR_NOTIFICATION');
        }, notification.timeout);

      },
      CLEAR_NOTIFICATION(state) {
        if (state.notification && state.notification.timer) {
          window.clearTimeout(state.notification.time);
        }
        state.notification = null;
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },

      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },

      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_SAVED_BEERS(state, mySavedBeers) {
        state.savedBeers = mySavedBeers;
      },
      SET_BEER_LIST(state, beerList) {
        state.beers = beerList
      },
      SET_BREWERY(state, brewery) {
        state.brewery = brewery;
      },
      SET_BREWERIES(state, breweries) {
        state.breweries = breweries;
      },
      SET_BEER(state, beer) {
        state.beer = beer;
      },
      SET_REVIEWS(state, reviews) {
        state.reviews = reviews;
      },
      FLIP_CREATE_BREWERY_FORM(state) {
        state.showCreateBreweryForm = !state.showCreateBreweryForm;
      },
      FLIP_UPDATE_BREWERY_FORM(state) {
        state.showUpdateBreweryForm = !state.showUpdateBreweryForm;
      },
      FLIP_CREATE_BEER_FORM(state) {
        state.showCreateBeerForm = !state.showCreateBeerForm;
      },
      FLIP_UPDATE_BEER_FORM(state) {
        state.showUpdateBeerForm = !state.showUpdateBeerForm;
      },
      FLIP_REVIEW_FORM(state) {
        state.showReviewForm = !state.showReviewForm;
      }
    },
    actions: {
      getUpdatedSaved({ commit }) {
        BeerService.getSavedBeers()
        .then(response => {
          const updatedBeers = response.data;
          commit('SET_SAVED_BEERS', updatedBeers);
        });
      },
      getUpdatedReviews({commit}, beerId) {
        BeerReviewService.getReviewsByBeerId(beerId)
        .then(response => {
          const updatedReviews = response.data;
          commit('SET_REVIEWS', updatedReviews);
        })
      },
      getBreweryById({commit}, breweryId) {
        BreweryService.getBreweryById(breweryId)
        .then(response => {
          const currentBrewery = response.data;
          commit('SET_BREWERY', currentBrewery);
        })
      },
      getBreweries({commit}) {
        BreweryService.getBreweries()
        .then(response => {
          const updatedBreweries = response.data;
          commit('SET_BREWERIES', updatedBreweries);
        })
      },
      getBeersByBrewery({commit}, breweryId) {
        BeerService.getBeersByBrewery(breweryId)
        .then(response => {
          const updatedBeers = response.data;
          commit('SET_BEER_LIST', updatedBeers)
        })
      }
    }
    // set saved beers
    // set brewery beers
    // set all beers

    /* 
    save to service - updates DB
    repopulate list in store
      - SET_BEER_LIST
      -dynamically updates beer list since it's pulling from store
    */
  });
  return store;
}
