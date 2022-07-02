package com.huang.mphealth.model;

import com.huang.mphealth.entity.DoctorEntity;
import lombok.Data;

@Data
public class DoctorDto extends DoctorEntity {

    private String departmentName;

    private String teamName;
}
