import axios from 'axios';

const http = axios.create({
    baseURL: "https://maps.googleapis.com/maps/api/geocode/json?address="
});

export default{
    getLocation(addressString) {
        return http.get(`${addressString}`);
    }
}