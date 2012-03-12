package by.zloy.osgi.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import by.zloy.model.Record;
import by.zloy.model.User;
import by.zloy.osgi.jpa.api.UserService;

public class UserServiceImpl extends AbstractService implements UserService {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
        Query query = getEntityManager().createQuery("select u from User u");
        return query.getResultList();
	}

	@Override
	public void save(Record record) {
		Query query = getEntityManager().createQuery("select u from User u");
        User user = (User) query.getSingleResult();
		if (user != null) {
			List<Record> records = new ArrayList<Record>();
			records.add(record);
			user.setRecords(records);
			em.persist(user);
			commit();
        }
	}
}
