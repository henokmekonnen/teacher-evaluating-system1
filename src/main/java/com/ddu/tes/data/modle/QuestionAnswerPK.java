package com.ddu.tes.data.modle;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author GHabtamu
 */
public class QuestionAnswerPK implements Serializable {
    private Integer questionAnswerId;
    private Integer questionId;

    @Column(name = "QuestionAnswerId", nullable = false)
    @Id
    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAnswerPK that = (QuestionAnswerPK) o;
        return Objects.equals(questionAnswerId, that.questionAnswerId) &&
                Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionAnswerId, questionId);
    }
}
