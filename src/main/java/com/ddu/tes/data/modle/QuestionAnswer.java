package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@Table(name = "question_answer")

public class QuestionAnswer extends BaseModel {
    private Integer questionAnswerId;
    private Integer answer;
    private Date createdDate;
    private String createdBy;
    private String description;
    private Integer questionId;

    @Id
    @Column(name = "QuestionAnswerId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    @Override
    @Transient
    public Integer getId() {
        return getQuestionAnswerId();
    }

    @Override
    public void setId(Integer id) {
        setQuestionAnswerId(id);
    }

    @Basic
    @Column(name = "Answer", nullable = true)
    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "Created_date", nullable = false)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "Created_by", nullable = false, length = 20)
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

    @Basic
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
        QuestionAnswer that = (QuestionAnswer) o;
        return Objects.equals(questionAnswerId, that.questionAnswerId) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(description, that.description) &&
                Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionAnswerId, answer, createdDate, createdBy, description, questionId);
    }
}
