package by.zloy.auth.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import by.zloy.auth.api.SecurityService;

public class SecurityServiceImpl implements SecurityService {

	private AuthenticationManager authenticationManager = null;

	@Override
	public boolean authenticate(String username, String password) {
		Authentication aut = new UsernamePasswordAuthenticationToken(username, password);
		try {
			aut = authenticationManager.authenticate(aut);
			SecurityContextHolder.getContext().setAuthentication(aut);
		} catch (Exception e) {
			return false;
		}
		return aut.isAuthenticated();
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

}
