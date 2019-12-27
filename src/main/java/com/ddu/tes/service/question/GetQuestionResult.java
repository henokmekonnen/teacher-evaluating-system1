package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetQuestionResult extends  AbstractResponseModel{
   private boolean questionExists;


        public boolean isQuestionExists() {
            return questionExists;
        }

        public void setQuestionExists(boolean questionExists) {
            this.questionExists = questionExists;
        }
    }

