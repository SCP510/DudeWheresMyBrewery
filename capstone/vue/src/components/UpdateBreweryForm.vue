<template>
    <form class="update-brewery-form" v-on:submit.prevent="updateBrewery()">
        <label for="breweryName" class="form-label">Brewery Name</label>
        <input class="form-control" type="text" placeholder="Update Brewery Name" v-model="updatedBrewery.breweryName">

        <label for="streetAddress" class="form-label">Street Address</label>
        <input class="form-control" type="text" placeholder="Update Street Address" v-model="updatedBrewery.streetAddress">

        <label for="city" class="form-label">City</label>
        <input class="form-control" type="text" placeholder="Update City" v-model="updatedBrewery.city">

        <label for="state" class="form-label">State</label>
        <select class="form-control" v-model="updatedBrewery.state" required>
            <option selected disabled>Select A State</option>
            <option value="AL">AL</option>
            <option value="AK">AK</option>
            <option value="AR">AR</option>
            <option value="AZ">AZ</option>
            <option value="CA">CA</option>
            <option value="CO">CO</option>
            <option value="CT">CT</option>
            <option value="DC">DC</option>
            <option value="DE">DE</option>
            <option value="FL">FL</option>
            <option value="GA">GA</option>
            <option value="HI">HI</option>
            <option value="IA">IA</option>
            <option value="ID">ID</option>
            <option value="IL">IL</option>
            <option value="IN">IN</option>
            <option value="KS">KS</option>
            <option value="KY">KY</option>
            <option value="LA">LA</option>
            <option value="MA">MA</option>
            <option value="MD">MD</option>
            <option value="ME">ME</option>
            <option value="MI">MI</option>
            <option value="MN">MN</option>
            <option value="MO">MO</option>
            <option value="MS">MS</option>
            <option value="MT">MT</option>
            <option value="NC">NC</option>
            <option value="NE">NE</option>
            <option value="NH">NH</option>
            <option value="NJ">NJ</option>
            <option value="NM">NM</option>
            <option value="NV">NV</option>
            <option value="NY">NY</option>
            <option value="ND">ND</option>
            <option value="OH">OH</option>
            <option value="OK">OK</option>
            <option value="OR">OR</option>
            <option value="PA">PA</option>
            <option value="RI">RI</option>
            <option value="SC">SC</option>
            <option value="SD">SD</option>
            <option value="TN">TN</option>
            <option value="TX">TX</option>
            <option value="UT">UT</option>
            <option value="VT">VT</option>
            <option value="VA">VA</option>
            <option value="WA">WA</option>
            <option value="WI">WI</option>
            <option value="WV">WV</option>
            <option value="WY">WY</option>
        </select>

        <label for="zipcode" class="form-label">ZipCode</label>
        <input class="form-control" type="text" placeholder="Update ZipCode" v-model="updatedBrewery.zipcode">

        <label for="phoneNumber" class="form-label">Phone Number</label>
        <input class="form-control" type="tel" placeholder="Update Phone Number, example '(555) 555-5555'"
            v-model="updatedBrewery.phoneNumber">

        <label for="aboutUs" class="form-label">About Us</label>
        <textarea class="form-control" rows="5" v-model="updatedBrewery.aboutUs"></textarea>

        <label for="website" class="form-label">Website URL</label>
        <input class="form-control" type="text" placeholder="Update Web URL, example 'https://yourwesbiturl.com/'"
            v-model="updatedBrewery.website">

        <button type="submit" class="btn btn-primary" @click="toggleUpdateBreweryForm">Submit Changes</button>
    </form>
</template>
  
<script>

import BreweryService from '../services/BreweryService'

export default {
    props: ['brewery'],

    data() {
        return {
            updatedBrewery: {
                // breweryId: this.brewery.breweryId,
                // breweryName: this.brewery.breweryName,
                // streetAddress: this.brewery.streetAddress,
                // city: this.brewery.city,
                // state: this.brewery.state,
                // zipcode: this.brewery.zipcode,
                // dateEst: this.brewery.dateEst,
                // phoneNumber: this.brewery.phoneNumber,
                // aboutUs: this.brewery.aboutUs,
                // website: this.brewery.website,
                // logoImage: this.brewery.logoImage,
                // founderId: 1
            }
        }
    },

    methods: {
        updateBrewery() {
            BreweryService.updateBrewery(this.updatedBrewery)
                .then(response => {
                    if (response.status == 200) {
                        this.$store.commit('SET_NOTIFICATION',
                        {
                            messages: 'Your brewery has been updated.',
                            type: 'success'
                        })
                    }
                })
                .catch(error => {
                    if (error.response.status) {
                        this.store.commit('SET_NOTIFICATION',
                        {
                            message: 'There was an error updating your brewery. Please try again later.',
                            type: 'error'
                        })
                    }
                })
            // Look at setNotification from Lecture
        },
        getBrewery() {
            BreweryService.getBreweryById(this.$route.params.breweryId)
                .then(response => {
                    const currentBrewery = response.data;
                    this.updatedBrewery = currentBrewery;
                    this.$store.commit('SET_BREWERY', currentBrewery);
                })
        },

        toggleUpdateBreweryForm() {
            this.$store.commit('FLIP_UPDATE_BREWERY_FORM');
        }
    },
    created() {
        this.getBrewery();
    }
}
</script>
  
<style>
.update-brewery-form {
    color:black;
  width: 40vh;
  background-color: brown;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  font-size: 20px;
  margin: auto;
  padding: 25px;
  border: double #e79115;
  border-width: 3px;
}
</style>