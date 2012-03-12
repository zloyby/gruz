package by.zloy.osgi.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import by.zloy.model.Log;
import by.zloy.osgi.jpa.api.LogService;

public class LogServiceImpl extends AbstractService implements LogService {
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getLogs() {
		try {
            Query query = getEntityManager().createQuery("select l from Log l");
            System.out.println(query);
            return query.getResultList();
        } catch (Throwable e) {
            e.printStackTrace();
            return new ArrayList<Log>();
        }
	}

	
}
