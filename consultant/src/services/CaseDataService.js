import http from "../http-common";

class CaseDataService {
  getAll() {
    return http.get("/cases");
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