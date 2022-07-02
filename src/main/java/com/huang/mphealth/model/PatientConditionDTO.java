package com.huang.mphealth.model;

import com.huang.mphealth.entity.PatientConditionEntity;
import lombok.Data;

@Data
public class PatientConditionDTO extends PatientConditionEntity {

    private String patientName;

}
