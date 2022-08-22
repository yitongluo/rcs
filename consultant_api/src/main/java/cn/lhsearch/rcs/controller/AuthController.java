package cn.lhsearch.rcs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lhsearch.rcs.entity.User;
import cn.lhsearch.rcs.repository.UserRepository;
import cn.lhsearch.rcs.security.jwt.JwtUtils;
import cn.lhsearch.rcs.service.MessageResponse;
import cn.lhsearch.rcs.service.SignupRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

//   @PostMapping("/signin")
//   public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

//     Authentication authentication = authenticationManager.authenticate(
//         new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//     SecurityContextHolder.getContext().setAuthentication(authentication);
//     String jwt = jwtUtils.generateJwtToken(authentication);
    
//     UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
//     List<String> roles = userDetails.getAuthorities().stream()
//         .map(item -> item.getAuthority())
//         .collect(Collectors.toList());

//     return ResponseEntity.ok(new JwtResponse(jwt, 
//                          userDetails.getId(), 
//                          userDetails.getUsername(), 
//                          userDetails.getEmail(), 
//                          roles));
//   }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));
    if (userRepository.count() == 0)
    {
      user.setIsApproved(true);
    }
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}

