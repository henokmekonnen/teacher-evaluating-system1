package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "question_type_lookup")
public class QuestionType {
    private String questionTypeId;
    private String description;

    @Id
    @Column(name = "questionTypeId", nullable = false, length = 10)
    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId;
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
        QuestionType questionType = (QuestionType) o;
        return Objects.equals(questionTypeId, questionType.questionTypeId) &&
                Objects.equals(description, questionType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionTypeId, description);
    }
}

