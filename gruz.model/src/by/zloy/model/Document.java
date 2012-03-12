package by.zloy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="document")
public class Document implements Serializable {

	private static final long serialVersionUID = 4237483111210790743L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String document;
	
	@Column(nullable = false, name = "document_date")
	@Temporal(TemporalType.DATE)
	private Date documentDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}
	
}
