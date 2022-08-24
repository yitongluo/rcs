import http from "../http-common";


class AuthApi {
  login(user) {
    return http
      .post('auth/signin', {
        email: user.email,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return http.post('auth/signup', {
      email: user.email,
      password: user.password
    });
  }
}

export default new AuthApi();
