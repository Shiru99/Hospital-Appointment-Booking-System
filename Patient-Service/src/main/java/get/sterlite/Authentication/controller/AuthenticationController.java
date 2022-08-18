package get.sterlite.Authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.model.AuthenticationRequest;
import get.sterlite.Authentication.model.AuthenticationResponse;
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

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

		boolean passwordMatch = passwordEncoder.matches(
				authenticationRequest.getPassword(), userService.getUserPassword(authenticationRequest.getMobileNum()));

		if (passwordMatch) {
			final String jwt = jwtTokenUtil.generateToken(authenticationRequest.getMobileNum());
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new AuthenticationFailResponse("Invalid Credentials"));
		}

	}

}