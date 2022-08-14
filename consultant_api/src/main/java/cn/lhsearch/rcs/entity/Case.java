package cn.lhsearch.rcs.entity;

import javax.persistence.*;

@Entity
@Table(name = "cases")
public class Case {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "candidate_name")
	private String candidateName;

	@Column(name = "access_code")
	private String accessCode;

	@Column(name = "published")
	private boolean published;

	public Case() {

	}

	public Case(String candidateName, String accessCode, boolean published) {
		this.candidateName = candidateName;
		this.accessCode = accessCode;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String name) {
		this.candidateName = name;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String code) {
		this.accessCode = code;
	}


	public boolean getPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", candidate_name=" + candidateName + ", code=" + accessCode + ", published=" + published + "]";
	}

}
