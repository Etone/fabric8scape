const { default: axios } = require("axios");


export default class AdminService {
    
    async getDataPools() {
        var response = axios.get(`${this.getAdminUrl()}/datapool`);
        return response;
    }

    async deployDataPool(id) {
        var response = axios.get(`${this.getAdminUrl()}/kubernetes/deploy/${id}`);
        return response;
    }


    async undeployDataPool(id) {
        var response = axios.get(`${this.getAdminUrl()}/kubernetes/undeploy/${id}`);
        return response;
    }

    async deleteDataPool(id) {
        var response = axios.delete(`${this.getAdminUrl()}/datapool/${id}`);
        return response;
    }

    async createDataPool(pool) {
        var response = axios.put(`${this.getAdminUrl()}/datapool`, pool);
        return response;
    }

    getAdminUrl() {
        return `http://${window.location.host}/admin`
    }
}