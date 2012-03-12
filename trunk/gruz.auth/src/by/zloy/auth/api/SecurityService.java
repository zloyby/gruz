package by.zloy.auth.api;

public interface SecurityService {
	public boolean authenticate(final String username, final String password);
}
