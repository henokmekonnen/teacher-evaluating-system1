package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="question")
public class Question extends BaseModel{
private Integer questionId;
private String question;
private String typeLookLp;
private Boolean allowMultipleChoice;
private String description;
    private String questionType;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="questionId",nullable = false)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }


    @Override
    @Transient
    public Integer getId() {
        return getQuestionId();
    }

    @Override
    public void setId(Integer id) {
        setQuestionId(id);
    }

@Basic
@Column(name="question",nullable=false)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name="typeLookLp",nullable=false)
    public String getTypeLookLp() {
        return typeLookLp;
    }

    public void setTypeLookLp(String typeLookLp) {
        this.typeLookLp = typeLookLp;
    }

    private String createdBy;
    @Basic
    @Column(name = "CreatedBy", nullable = true, length = 50)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    @Basic
    @Column(name="allowMultipleChoice",nullable=false)
    public Boolean getAllowMultipleChoice() {
        return allowMultipleChoice;
    }

    public void setAllowMultipleChoice(Boolean allowMultipleChoice) {
        this.allowMultipleChoice = allowMultipleChoice;
    }

    @Basic
    @Column(name="description",nullable=false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Basic
    @Column(name="questionType",nullable=false)
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question that = (Question) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(question, that.question) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(questionType, that.questionType) &&
                Objects.equals(description, that.description);

    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId,question, createdDate, createdBy, description,questionType);
    }
}