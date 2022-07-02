package com.huang.mphealth.model;

import com.huang.mphealth.entity.PatientConditionEntity;
import lombok.Data;

@Data
public class PatientDTO extends PatientConditionEntity {

    private String dorctorName;

}
