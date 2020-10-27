
const { default: axios } = require("axios");


export default class RegistryService {
    
    getDeployedPools() {
        return axios.get(`${this.getRegistryUrl()}`);
    }

    getRegistryUrl() {
        return `http://${window.location.host}/registry`
    }
}