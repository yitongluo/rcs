import http from "../http-common";

class CaseDataService {
  getAll() {
    return http.get("/Cases");
  }

  get(id) {
    return http.get(`/Cases/${id}`);
  }

  create(data) {
    return http.post("/Cases", data);
  }

  update(id, data) {
    return http.put(`/Cases/${id}`, data);
  }

  delete(id) {
    return http.delete(`/Cases/${id}`);
  }

  deleteAll() {
    return http.delete(`/Cases`);
  }

  findByName(name) {
    return http.get(`/Cases?candidateName=${name}`);
  }
}

export default new CaseDataService();