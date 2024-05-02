
<template>
  <div>
    <h2 class="find-us">Where to Find Us</h2>
    <!-- Add your Google Maps API Key on line 6 as api-key attribute to demo functionality -->
    <GoogleMap class="google-map" v-if="showMap" api-key="YOUR%API%KEY%GOES%HERE"
      :center="{ lat: this.latitude, lng: this.longitude }" :zoom="15"
      style="
      height: 350px;
  border-style: double;
  border-color: #e79115;
  border-width: 10px;
      ">
      <Marker :options="{ position: center }" />
      <Marker :options="{ position: { lat: this.latitude, lng: this.longitude } }" />
    </GoogleMap>
  </div>
</template>

<script >
import { defineComponent } from "vue";
import { GoogleMap, Marker } from "vue3-google-map";
import LocationService from '../services/LocationService';




export default defineComponent({
  props: ['brew', 'address'],
  data() {
    return {
      longitude: '',
      latitude: '',
      resp: { meatball: 'sauce' },
      showMap: false,
      test: false,
    }
  },
  components: { GoogleMap, Marker },
  setup() {

    
    const center = { lat: 39.152243090211236, lng: -84.46729087516896 }; // center on TE cincinatti
    

    return { center };

    
  },
  methods: {
    toggleMap() {
      this.showMap = !this.showMap
    },
    setTest() {
      this.test = !this.test;
    },
   

  },
  updated() {
    this.showMap = true;
    LocationService.getLocation(`${this.address}` + '&key=YOUR%API%KEY%GOES%HERE') // INSERT API KEY AFTER = TO DEMO
    // this key would normally be set as an environment variable but was inserted manually for testing during development
      .then(response => {
        if (response.data.results.length > 0) {
          this.resp = response.data;
          this.longitude = response.data.results[0].geometry.location.lng;
          this.latitude = response.data.results[0].geometry.location.lat;
        }
      });
  },
  computed: {
  }
});
</script>

<style>
.find-us {
  color: #e79115;
padding-top: 20px;
}
</style>