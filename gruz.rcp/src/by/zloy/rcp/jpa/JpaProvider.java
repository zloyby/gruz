package by.zloy.rcp.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import by.zloy.model.Document;
import by.zloy.model.Log;
import by.zloy.model.Record;
import by.zloy.model.User;
import by.zloy.osgi.jpa.api.LogService;
import by.zloy.osgi.jpa.api.UserService;

public class JpaProvider {

	private static LogService logService;
	private static UserService userService;
    
	public synchronized void setLogService(LogService service) {
		System.out.println("LogService was set. Thank you DS!");
		JpaProvider.logService = service;
	}

	public synchronized void unsetLogService(LogService service) {
		System.out.println("LogService was unset. Why did you do this to me?");
		if (JpaProvider.logService == service) {
			JpaProvider.logService = null;
		}
	}
	
	public synchronized void setUserService(UserService service) {
		System.out.println("UserService was set. Thank you DS!");
		JpaProvider.userService = service;
	}

	public synchronized void unsetUserService(UserService service) {
		System.out.println("UserService was unset. Why did you do this to me?");
		if (JpaProvider.userService == service) {
			JpaProvider.userService = null;
		}
	}
	
	public List<Log> getLogs() {
		return logService.getLogs();			
	}

	public List<User> getUsers(){
		return userService.getUsers();			
	}

	public void saveRecord(Calendar cal, String folder, String maxNews, boolean makeUnread, String toMail) {
		Record record = new Record();
		record.setTimeToSend(cal.getTime());
		record.setLabel(folder);
		record.setMaxNews(Integer.valueOf(maxNews));
		record.setMakeUnread(makeUnread);
		record.setKindleMail(toMail);
		
		List<Document> documents = new ArrayList<Document>();
		Document doc = new Document();
		doc.setDocument("First document");
		doc.setDocumentDate(new Date());
		documents.add(doc);
		record.setDocuments(documents);
		
		userService.save(record);
	}
}
