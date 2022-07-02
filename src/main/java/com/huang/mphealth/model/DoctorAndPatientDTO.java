package com.huang.mphealth.model;

import com.huang.mphealth.entity.DoctorPatientEntity;
import lombok.Data;

@Data
public class DoctorAndPatientDTO extends DoctorPatientEntity {

    private String doctorName;

    private String patientName;
}
