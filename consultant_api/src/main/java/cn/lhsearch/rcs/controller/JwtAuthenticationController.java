package cn.lhsearch.rcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cn.lhsearch.rcs.entity.User;
import cn.lhsearch.rcs.security.jwt.JwtRequest;
import cn.lhsearch.rcs.security.jwt.JwtResponse;
import cn.lhsearch.rcs.security.jwt.JwtTokenUtil;
import cn.lhsearch.rcs.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;


	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		String code = jwtRequest.getCode();
		User user = userService.login(code);
		if (user != null) {
			final String token = jwtTokenUtil.generateToken(user);
			return ResponseEntity.ok(new JwtResponse(token));	
		}
		else {
			throw new Exception("INVALID_CODE");
		}
	}
}