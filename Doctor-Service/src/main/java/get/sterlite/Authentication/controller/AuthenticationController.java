package get.sterlite.Authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.model.AuthenticationResponse;
import get.sterlite.Authentication.model.LoginRequest;
import get.sterlite.Authentication.model.SignupRequest;
import get.sterlite.Authentication.service.UserService;
import get.sterlite.Authentication.util.JwtUtil;

@RestController
class AuthenticationController {

	@Autowired
	protected JwtUtil jwtTokenUtil;

	@Autowired
	protected UserService userService;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
		try {
			boolean passwordMatch = passwordEncoder.matches(
				loginRequest.getPassword(),
					userService.getUserPassword(loginRequest.getMobileNum()));
			if (passwordMatch) {
				final String jwt = jwtTokenUtil.generateToken(loginRequest.getMobileNum());
				return ResponseEntity.ok(new AuthenticationResponse(jwt));
			} else {
				throw new Exception("Invalid Credentials");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new AuthenticationFailResponse(e.getMessage()));
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> signupAndCreateAuthenticationToken(
			@RequestBody SignupRequest signupRequest) {
		try {
			userService.saveUser(signupRequest);
			final String jwt = jwtTokenUtil
				.generateToken(signupRequest.getMobileNum());
			return ResponseEntity.ok(new AuthenticationResponse(jwt));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new AuthenticationFailResponse(e.getMessage()));
		}
	}

}