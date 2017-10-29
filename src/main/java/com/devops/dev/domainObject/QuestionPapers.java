package com.devops.dev.domainObject;
// Generated 28 Oct, 2017 7:09:10 PM by Hibernate Tools 3.2.2.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * QuestionPapers generated by hbm2java
 */
@Entity
@Table(name="question_papers")
public class QuestionPapers  implements java.io.Serializable {


	 private int id;
	 private Subject subject;
	 private ClassLevelType classLevelType;
	 private Board board;
	 private String paperName;
	 private int boardId;
	 private byte[] content;
	 private String createdBy;
	 private Date created;
	 private Date updated;

	public QuestionPapers() {
	}

	
	public QuestionPapers(int id, Subject subject, ClassLevelType classLevelType, Board board, String paperName, int boardId, byte[] content) {
		this.id = id;
		this.subject = subject;
		this.classLevelType = classLevelType;
		this.board = board;
		this.paperName = paperName;
		this.boardId = boardId;
		this.content = content;
	}
	public QuestionPapers(int id, Subject subject, ClassLevelType classLevelType, Board board, String paperName, int boardId, byte[] content, String createdBy, Date created, Date updated) {
	   this.id = id;
	   this.subject = subject;
	   this.classLevelType = classLevelType;
	   this.board = board;
	   this.paperName = paperName;
	   this.boardId = boardId;
	   this.content = content;
	   this.createdBy = createdBy;
	   this.created = created;
	   this.updated = updated;
	}
   
	@Id
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subject_id", nullable=false)
	public Subject getSubject() {
		return this.subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="standard_id", nullable=false)
	public ClassLevelType getClassLevelType() {
		return this.classLevelType;
	}
	
	public void setClassLevelType(ClassLevelType classLevelType) {
		this.classLevelType = classLevelType;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id", unique=true, nullable=false, insertable=false, updatable=false)
	public Board getBoard() {
		return this.board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	@Column(name="paper_name", nullable=false, length=45)
	public String getPaperName() {
		return this.paperName;
	}
	
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
	@Column(name="board_id", nullable=false)
	public int getBoardId() {
		return this.boardId;
	}
	
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	@Column(name="content", nullable=false)
	public byte[] getContent() {
		return this.content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@Column(name="created_by", length=45)
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="created", length=10)
	public Date getCreated() {
		return this.created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="updated", length=10)
	public Date getUpdated() {
		return this.updated;
	}
	
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}


