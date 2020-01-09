package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

public class GetAllQuestionType  extends AbstractResponseModel {
    public List<Map<String, Object>> questionTypeList;

    public List<Map<String, Object>> getQuestionTypeList() {
        return questionTypeList;
    }

    public void setQuestionTypeList(List<Map<String, Object>> questionTypeList) {
        this.questionTypeList = questionTypeList;
    }
}
