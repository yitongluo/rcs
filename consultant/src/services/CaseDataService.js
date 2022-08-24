import http from "../http-common";
import axios from 'axios';
import authHeader from "./auth-header";
const API_URL = 'http://localhost:8080/';

class CaseDataService {
  getAll() {
    return axios.get(API_URL + "cases", { headers: authHeader() });
  }

  get(id) {
    return http.get(`/cases/${id}`);
  }

  create(data) {
    return http.post("/cases", data);
  }

  update(id, data) {
    return http.put(`/cases/${id}`, data);
  }

  delete(id) {
    return http.delete(`/cases/${id}`);
  }

  deleteAll() {
    return http.delete(`/cases`);
  }

  findByName(name) {
    return http.get(`/cases?candidateName=${name}`);
  }
}

export default new CaseDataService();