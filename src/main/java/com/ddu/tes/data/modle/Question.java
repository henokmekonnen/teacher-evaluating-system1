package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@Table(name = "question")
public class Question extends BaseModel {
    private Integer questionId;
    private String question;
    private Integer typeLookLp;
    private Boolean allowsMultipleAnswer;
    private Date createdDate;
    private String createdBy;
    private String description;

    @Id
    @Column(name = "QuestionId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "Question", nullable = false, length = 100)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
    @Column(name = "TypeLookLp", nullable = true)
    public Integer getTypeLookLp() {
        return typeLookLp;
    }

    public void setTypeLookLp(Integer typeLookLp) {
        this.typeLookLp = typeLookLp;
    }

    @Basic
    @Column(name = "AllowsMultipleAnswer", nullable = true)
    public Boolean getAllowsMultipleAnswer() {
        return allowsMultipleAnswer;
    }

    public void setAllowsMultipleAnswer(Boolean allowsMultipleAnswer) {
        this.allowsMultipleAnswer = allowsMultipleAnswer;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = false)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "CreatedBy", nullable = false, length = 20)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(questionId, question1.questionId) &&
                Objects.equals(question, question1.question) &&
                Objects.equals(typeLookLp, question1.typeLookLp) &&
                Objects.equals(allowsMultipleAnswer, question1.allowsMultipleAnswer) &&
                Objects.equals(createdDate, question1.createdDate) &&
                Objects.equals(createdBy, question1.createdBy) &&
                Objects.equals(description, question1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, question, typeLookLp, allowsMultipleAnswer, createdDate, createdBy, description);
    }
}
