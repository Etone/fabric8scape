const { default: axios } = require("axios");


export default class AdminService {
    
    async getDataPools() {
        var response = axios.get(`${this.getAdminUrl()}/datapool`);
        return response;
    }

    getAdminUrl() {
        return `http://${window.location.host}/admin`
    }
}