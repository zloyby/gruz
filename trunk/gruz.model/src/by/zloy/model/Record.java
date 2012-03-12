package by.zloy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="record")
public class Record implements Serializable {

	private static final long serialVersionUID = 6775610683515765830L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String label;
	
	@Column(name = "max_news")
	private Integer maxNews;
	
	@Column(name = "name_unread")
	private Boolean makeUnread;
	
	@Column(name = "kindle_mail")
	private String kindleMail;
	
	@Column(name = "time_to_send")
	@Temporal(TemporalType.DATE)
	private Date timeToSend;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Document> documents;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getMaxNews() {
		return maxNews;
	}

	public void setMaxNews(Integer maxNews) {
		this.maxNews = maxNews;
	}

	public String getKindleMail() {
		return kindleMail;
	}

	public void setKindleMail(String kindleMail) {
		this.kindleMail = kindleMail;
	}

	public Date getTimeToSend() {
		return timeToSend;
	}

	public void setTimeToSend(Date timeToSend) {
		this.timeToSend = timeToSend;
	}

	public Boolean getMakeUnread() {
		return makeUnread;
	}

	public void setMakeUnread(Boolean makeUnread) {
		this.makeUnread = makeUnread;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
