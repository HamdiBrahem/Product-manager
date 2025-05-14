package com.gestion.customerservice.security;

import com.gestion.customerservice.entities.Customer;
import com.gestion.customerservice.entities.Role;
import com.gestion.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public JwtResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

		if (authentication.isAuthenticated()) {
			Customer customer = customerRepository.findByUsername(authRequestDTO.getUsername())
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));

			TokenObject tokenObject = new TokenObject();
			tokenObject.setIdUtilisateur(Math.toIntExact(customer.getId()));
			tokenObject.setUsername(customer.getUsername());

			return new JwtResponseDTO(jwtService.GenerateToken(tokenObject));
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}

	@PostMapping("/register")
	public Customer register(@RequestBody Customer customer) {
		// Encode the password
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));

		// Set default role if not provided
		if (customer.getRole() == null) {
			customer.setRole(Role.USER); // Default role is USER
		}

		return customerRepository.save(customer);
	}

	@GetMapping("/test")
	public String test() {
		return "CustomerService authentication works!";
	}
}
