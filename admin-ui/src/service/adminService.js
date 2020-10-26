const { default: axios } = require("axios");


export default class AdminService {
    
    async getDataPools() {
        var response = axios.get(`${this.getAdminUrl()}/datapool`);
        return response;
    }

    getAdminUrl() {
        return `${window.location.host}/admin`
    }
}