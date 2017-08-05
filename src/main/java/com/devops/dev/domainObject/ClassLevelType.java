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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ClassLevelType generated by hbm2java
 */
@Entity
@Table(name = "class_level_type")
public class ClassLevelType implements java.io.Serializable {

	private int classLevelTypeId;
	private ClassLevel classLevel;
	private Board board;
	private String className;
	private String language;
	@JsonIgnore
	private Set<Subject> subjects = new HashSet<Subject>(0);
	private Set<ClassCalendar> classCalendars = new HashSet<ClassCalendar>(0);
	private Set<LectureSchedule> lectureSchedules = new HashSet<LectureSchedule>(0);
	private Set<StudentDetail> studentDetails = new HashSet<StudentDetail>(0);

	public ClassLevelType() {
	}

	public ClassLevelType(int classLevelTypeId, ClassLevel classLevel, Board board, String className, String language) {
		this.classLevelTypeId = classLevelTypeId;
		this.classLevel = classLevel;
		this.board = board;
		this.className = className;
		this.language = language;
	}

	public ClassLevelType(int classLevelTypeId, ClassLevel classLevel, Board board, String className, String language,
			Set<Subject> subjects, Set<ClassCalendar> classCalendars, Set<LectureSchedule> lectureSchedules,
			Set<StudentDetail> studentDetails) {
		this.classLevelTypeId = classLevelTypeId;
		this.classLevel = classLevel;
		this.board = board;
		this.className = className;
		this.language = language;
		this.subjects = subjects;
		this.classCalendars = classCalendars;
		this.lectureSchedules = lectureSchedules;
		this.studentDetails = studentDetails;
	}

	@Id

	@Column(name = "class_level_type_id", unique = true, nullable = false)
	public int getClassLevelTypeId() {
		return this.classLevelTypeId;
	}

	public void setClassLevelTypeId(int classLevelTypeId) {
		this.classLevelTypeId = classLevelTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_level_id", nullable = false)
	public ClassLevel getClassLevel() {
		return this.classLevel;
	}

	public void setClassLevel(ClassLevel classLevel) {
		this.classLevel = classLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Column(name = "class_name", nullable = false, length = 45)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "language", nullable = false, length = 45)
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classLevelType")
	public Set<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classLevelType")
	public Set<ClassCalendar> getClassCalendars() {
		return this.classCalendars;
	}

	public void setClassCalendars(Set<ClassCalendar> classCalendars) {
		this.classCalendars = classCalendars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classLevelType")
	public Set<LectureSchedule> getLectureSchedules() {
		return this.lectureSchedules;
	}

	public void setLectureSchedules(Set<LectureSchedule> lectureSchedules) {
		this.lectureSchedules = lectureSchedules;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classLevelType")
	public Set<StudentDetail> getStudentDetails() {
		return this.studentDetails;
	}

	public void setStudentDetails(Set<StudentDetail> studentDetails) {
		this.studentDetails = studentDetails;
	}

}
