package get.sterlite.Authentication.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import get.sterlite.Authentication.model.AuthenticationFailResponse;
import get.sterlite.Authentication.model.AuthenticationResponse;
import get.sterlite.Authentication.model.LoginUser;
import get.sterlite.Authentication.service.UserService;
import get.sterlite.Authentication.util.JwtUtil;
import get.sterlite.Exception.InvalidInputsException;
import get.sterlite.Exception.LoginException;
import get.sterlite.model.Patient;
import get.sterlite.service.PatientService;

@RestController
class AuthenticationController {

	@Autowired
	protected JwtUtil jwtTokenUtil;

	@Autowired
	protected UserService userService;

	@Autowired
	PatientService patientService;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> loginAndCreateAuthenticationToken(@RequestBody @Validated LoginUser loginUser, BindingResult errors) throws Exception {
		if (errors.hasErrors()) {
			throw new InvalidInputsException(errors);
		}
		boolean passwordMatch = passwordEncoder.matches(
				loginUser.getPassword(),
				userService.getUserPassword(loginUser.getMobileNum()));
		if (passwordMatch) {
			if (!patientService.isPatientExist(loginUser.getMobileNum())) {
				throw new LoginException("Doctor can't login as `Patient`");
			}
			final String jwt = jwtTokenUtil.generateToken(loginUser.getMobileNum());
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		} else {
			throw new LoginException("Invalid Credentials");
		}

	}

	@Transactional
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> signupAndCreateAuthenticationToken(
			@RequestBody @Validated  Patient patient, BindingResult errors
			 ) throws Exception {

		System.out.println("patient : " + patient);

		if (errors.hasErrors()) {
			throw new InvalidInputsException(errors);
		}

		try {
			userService.saveUser(patient);
			patientService.savePatient(patient);
			final String jwt = jwtTokenUtil
					.generateToken(patient.getMobileNum());
			return ResponseEntity.ok(new AuthenticationResponse(jwt));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new AuthenticationFailResponse(e.getMessage()));
		}
	}

}