package by.zloy.osgi.jpa.api;

import java.util.List;

import by.zloy.model.Record;
import by.zloy.model.User;

public interface UserService {
	
	List<User> getUsers();

	void save(Record record);
}
