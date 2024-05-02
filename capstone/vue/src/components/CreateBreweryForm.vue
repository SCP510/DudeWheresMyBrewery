<template>
    <form class="create-brewery-form" v-on:submit.prevent="createBrewery()">
        <label for="breweryName" class="form-label">Brewery Name</label>
        <input class="form-control" type="text" placeholder="Enter Brewery Name" v-model="newBrewery.breweryName" required>

        <label for="streetAddress" class="form-label">Street Address</label>
        <input class="form-control" type="text" placeholder="Enter Street Address" v-model="newBrewery.streetAddress" required>

        <label for="city" class="form-label">City</label>
        <input class="form-control" type="text" placeholder="Enter City" v-model="newBrewery.city" required>

        <label for="state" class="form-label">State</label>
        <select class="form-control" v-model="newBrewery.state" required>
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
        <input class="form-control" type="text" placeholder="Enter ZipCode" v-model="newBrewery.zipcode" >

        <label for="phoneNumber" class="form-label">Phone Number</label>
        <input class="form-control" type="tel" placeholder="Enter Phone Number, example '(555) 555-5555'" v-model="newBrewery.phoneNumber" required>

        <label for="aboutUs" class="form-label">About Us</label>
        <textarea class="form-control" rows="5" v-model="newBrewery.aboutUs" required></textarea>

        <label for="website" class="form-label">Website URL</label>
        <input class="form-control" type="text" placeholder="Enter Web URL, example 'https://yourwesbiturl.com/'" v-model="newBrewery.website" required>


        <button type="submit" class="btn btn-primary" @click="toggleCreateBreweryForm">Submit</button>
    </form>
</template>
  
<script>

import BreweryService from '../services/BreweryService'

    export default {
        data() {
            return {
                newBrewery: {
                    breweryId: 0,
                    breweryName: '',
                    streetAddress: '',
                    city: '',
                    state: '',
                    zipcode: '',
                    dateEst: new Date(99, 5, 24),
                    phoneNumber: '',
                    aboutUs: '',
                    website: '',
                    logoImage: 'logo',
                    founderId: this.$store.state.user.id
                },
                myId: this.getFounderId
            }
        },
    
        methods: {
            createBrewery() {
                BreweryService.createBrewery(this.newBrewery)
                .then (response => {
                    if (response.status == 200) {
                        this.$store.commit('SET_NOTIFICATION',
                        {
                            message: 'Your brewery has been created.',
                            type: 'success'
                        })
                    }
                })
                .catch(error => {
                    if (error.response.status) {
                        this.$store.commit('SET_NOTIFICATION',
                        {
                            message: 'There was an error creating your brewery. Please try again later.',
                            type: 'error'
                        })
                    }  
                });
                this.resetForm();
            },

            resetForm() {
              this.newBrewery = {
                breweryId: 0,
                    breweryName: '',
                    streetAddress: '',
                    city: '',
                    state: '',
                    zipcode: '',
                    dateEst: Date.now,
                    phoneNumber: '',
                    aboutUs: '',
                    website: '',
                    logoImage: '',
                  founderId: this.$store.state.user.id,
              };
            },

            toggleCreateBreweryForm() {
              this.$store.commit('FLIP_CREATE_BREWERY_FORM');
            }
        }, 

        computed: {
            getFounderId() {
                const user = this.$store.state.user;
                return user.id;
            },

            checkFounder() {
                let user = JSON.parse(window.localStorage.getItem('user'));
                return user.id == this.newBrewery.founderId;
            },
        },
        created() {
            this.getFounderId;
            this.checkFounder;
            this.getId;
        }
    }
</script>
  
<style>
.create-brewery-form {
    color:black;
  width: 85vh;
  background-color: brown;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  font-size: 20px;
  margin: auto;
  padding: 25px;
  border: double 3px #e79115;
  border-width: 3px;
}
</style>