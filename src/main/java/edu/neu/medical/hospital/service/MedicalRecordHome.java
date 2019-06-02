package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.MedicalRecordInfo;

import java.util.List;

public interface MedicalRecordHome {
    MedicalRecordInfo getMedicalRecordInfoById(char isSeen,int medicalRecordId);
    List<Diagnosis> getNewDiagnosisListById(char type,int medicalRecordInfoId);
}
