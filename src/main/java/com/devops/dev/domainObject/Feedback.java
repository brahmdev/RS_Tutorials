package com.devops.dev.domainObject;
// Generated 1 May, 2017 6:29:41 PM by Hibernate Tools 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Feedback generated by hbm2java
 */
@Entity
@Table(name = "feedback")
public class Feedback implements java.io.Serializable {

	private int feedbackId;
	private Users usersByGivenBy;
	private Users usersByGivenTo;
	private String comments;
	private String givenOn;
	private String rating;
	private String feedbackType;

	public Feedback() {
	}

	public Feedback(int feedbackId, Users usersByGivenBy, Users usersByGivenTo, String givenOn, String rating,
			String feedbackType) {
		this.feedbackId = feedbackId;
		this.usersByGivenBy = usersByGivenBy;
		this.usersByGivenTo = usersByGivenTo;
		this.givenOn = givenOn;
		this.rating = rating;
		this.feedbackType = feedbackType;
	}

	public Feedback(int feedbackId, Users usersByGivenBy, Users usersByGivenTo, String comments, String givenOn,
			String rating, String feedbackType) {
		this.feedbackId = feedbackId;
		this.usersByGivenBy = usersByGivenBy;
		this.usersByGivenTo = usersByGivenTo;
		this.comments = comments;
		this.givenOn = givenOn;
		this.rating = rating;
		this.feedbackType = feedbackType;
	}

	@Id

	@Column(name = "feedback_id", unique = true, nullable = false)
	public int getFeedbackId() {
		return this.feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "given_by", nullable = false)
	public Users getUsersByGivenBy() {
		return this.usersByGivenBy;
	}

	public void setUsersByGivenBy(Users usersByGivenBy) {
		this.usersByGivenBy = usersByGivenBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "given_to", nullable = false)
	public Users getUsersByGivenTo() {
		return this.usersByGivenTo;
	}

	public void setUsersByGivenTo(Users usersByGivenTo) {
		this.usersByGivenTo = usersByGivenTo;
	}

	@Column(name = "comments", length = 45)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "given_on", nullable = false, length = 45)
	public String getGivenOn() {
		return this.givenOn;
	}

	public void setGivenOn(String givenOn) {
		this.givenOn = givenOn;
	}

	@Column(name = "rating", nullable = false, length = 45)
	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Column(name = "feedback_type", nullable = false, length = 45)
	public String getFeedbackType() {
		return this.feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

}
