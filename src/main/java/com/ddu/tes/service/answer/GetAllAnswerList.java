package com.ddu.tes.service.answer;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

public class GetAllAnswerList extends AbstractResponseModel {

    public List<Map<String, Object>> answerList;

    public List<Map<String, Object>> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Map<String, Object>> answerList) {
        this.answerList = answerList;
    }
}
