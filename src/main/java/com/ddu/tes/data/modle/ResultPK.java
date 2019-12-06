package com.ddu.tes.data.modle;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author GHabtamu
 */
public class ResultPK implements Serializable {
    private Integer resultId;
    private Integer userId;
    private Integer questionAnswerId;
    private Integer questionId;

    @Column(name = "ResultId", nullable = false)
    @Id
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    @Column(name = "UserId", nullable = false)
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "QuestionAnswerId", nullable = false)
    @Id
    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    @Column(name = "QuestionId", nullable = false)
    @Id
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
        ResultPK resultPK = (ResultPK) o;
        return Objects.equals(resultId, resultPK.resultId) &&
                Objects.equals(userId, resultPK.userId) &&
                Objects.equals(questionAnswerId, resultPK.questionAnswerId) &&
                Objects.equals(questionId, resultPK.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultId, userId, questionAnswerId, questionId);
    }
}
