package com.devops.dev.domainObject;
// Generated 1 May, 2017 6:29:41 PM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users")
public class Users implements java.io.Serializable {

	private String username;
	private String password;
	private byte enabled;
	@JsonBackReference(value="userRoles")
	private Set<UserRoles> userRoleses = new HashSet<UserRoles>(0);
	private Set<Feedback> feedbacksForGivenBy = new HashSet<Feedback>(0);
	private Set<StudentDetail> studentDetails = new HashSet<StudentDetail>(0);
	private Set<Feedback> feedbacksForGivenTo = new HashSet<Feedback>(0);
	private Set<LectureSchedule> lectureSchedules = new HashSet<LectureSchedule>(0);
	@JsonBackReference(value="chapters")
	private Set<Chapter> chapters = new HashSet<Chapter>(0);
	private Set<TakeUpDefaulterCalendar> takeUpDefaulterCalendars = new HashSet<TakeUpDefaulterCalendar>(0);

	public Users() {
	}

	public Users(String username, String password, byte enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Users(String username, String password, byte enabled, Set<UserRoles> userRoleses,
			Set<Feedback> feedbacksForGivenBy, Set<StudentDetail> studentDetails, Set<Feedback> feedbacksForGivenTo,
			Set<LectureSchedule> lectureSchedules, Set<Chapter> chapters,
			Set<TakeUpDefaulterCalendar> takeUpDefaulterCalendars) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRoleses = userRoleses;
		this.feedbacksForGivenBy = feedbacksForGivenBy;
		this.studentDetails = studentDetails;
		this.feedbacksForGivenTo = feedbacksForGivenTo;
		this.lectureSchedules = lectureSchedules;
		this.chapters = chapters;
		this.takeUpDefaulterCalendars = takeUpDefaulterCalendars;
	}

	@Id

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<UserRoles> getUserRoleses() {
		return this.userRoleses;
	}

	public void setUserRoleses(Set<UserRoles> userRoleses) {
		this.userRoleses = userRoleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByGivenBy")
	public Set<Feedback> getFeedbacksForGivenBy() {
		return this.feedbacksForGivenBy;
	}

	public void setFeedbacksForGivenBy(Set<Feedback> feedbacksForGivenBy) {
		this.feedbacksForGivenBy = feedbacksForGivenBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<StudentDetail> getStudentDetails() {
		return this.studentDetails;
	}

	public void setStudentDetails(Set<StudentDetail> studentDetails) {
		this.studentDetails = studentDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByGivenTo")
	public Set<Feedback> getFeedbacksForGivenTo() {
		return this.feedbacksForGivenTo;
	}

	public void setFeedbacksForGivenTo(Set<Feedback> feedbacksForGivenTo) {
		this.feedbacksForGivenTo = feedbacksForGivenTo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<LectureSchedule> getLectureSchedules() {
		return this.lectureSchedules;
	}

	public void setLectureSchedules(Set<LectureSchedule> lectureSchedules) {
		this.lectureSchedules = lectureSchedules;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Chapter> getChapters() {
		return this.chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<TakeUpDefaulterCalendar> getTakeUpDefaulterCalendars() {
		return this.takeUpDefaulterCalendars;
	}

	public void setTakeUpDefaulterCalendars(Set<TakeUpDefaulterCalendar> takeUpDefaulterCalendars) {
		this.takeUpDefaulterCalendars = takeUpDefaulterCalendars;
	}

}
