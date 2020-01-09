package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

public class GetAllQuestion extends AbstractResponseModel {

        public List<Map<String, Object>> questionList;

        public List<Map<String, Object>> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<Map<String, Object>> questionList) {
            this.questionList = questionList;
        }
    }

