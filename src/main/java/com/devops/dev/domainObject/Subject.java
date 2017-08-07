package com.devops.dev.domainObject;
// Generated 1 May, 2017 6:29:41 PM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Subject generated by hbm2java
 */
@Entity
@Table(name = "subject")
public class Subject implements java.io.Serializable {

	private int subjectId;
	private ClassLevelType classLevelType;
	private String subjectName;
	private Set<Chapter> chapters = new HashSet<Chapter>(0);

	public Subject() {
	}

	public Subject(int subjectId) {
		this.subjectId = subjectId;
	}

	public Subject(int subjectId, ClassLevelType classLevelType, String subjectName, Set<Chapter> chapters) {
		this.subjectId = subjectId;
		this.classLevelType = classLevelType;
		this.subjectName = subjectName;
		this.chapters = chapters;
	}

	@Id

	@Column(name = "subject_id", unique = true, nullable = false)
	public int getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_level_type_id" , nullable = false)
	public ClassLevelType getClassLevelType() {
		return this.classLevelType;
	}

	public void setClassLevelType(ClassLevelType classLevelType) {
		this.classLevelType = classLevelType;
	}

	@Column(name = "subject_name", length = 45)
	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subject")
	public Set<Chapter> getChapters() {
		return this.chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

}
