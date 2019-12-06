package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@IdClass(ResultPK.class)
@Table(name = "result")
public class Result {
    private Integer resultId;
    private String createdBy;
    private Date createdDate;
    private String description;
    private Integer userId;
    private Integer questionAnswerId;
    private Integer questionId;

    @Id
    @Column(name = "ResultId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
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
    @Column(name = "CreatedDate", nullable = false)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column(name = "UserId", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "QuestionAnswerId", nullable = false)
    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    @Id
    @Column(name = "QuestionId", nullable = false)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(resultId, result.resultId) &&
                Objects.equals(createdBy, result.createdBy) &&
                Objects.equals(createdDate, result.createdDate) &&
                Objects.equals(description, result.description) &&
                Objects.equals(userId, result.userId) &&
                Objects.equals(questionAnswerId, result.questionAnswerId) &&
                Objects.equals(questionId, result.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultId, createdBy, createdDate, description, userId, questionAnswerId, questionId);
    }
}
