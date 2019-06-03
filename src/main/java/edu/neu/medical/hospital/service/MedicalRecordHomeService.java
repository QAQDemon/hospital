package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.CommonOption;
import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.Disease;
import edu.neu.medical.hospital.bean.MedicalRecordInfo;

import java.util.List;

public interface MedicalRecordHomeService {
    MedicalRecordInfo getMedicalRecordInfoById(char isSeen, int medicalRecordId);
    List<Diagnosis> getNewDiagnosisListById(char type,int medicalRecordInfoId);
    Boolean setMedicalRecordInfoAndDiagnosisListById(MedicalRecordInfo medicalRecordInfo, List<Diagnosis> diagnosisList) ;
    List<Disease> searchDiseaseListByCode(String diseaseCode);
    List<Disease> getCommonDiagnosisList(List<CommonOption> commonOptionList);
    int deleteCommonDiagnosis(int doctorId,char type,int diseaseId);
    List<Disease> getDiagnosisDiseaseList(List<Diagnosis> diagnosisList);
}
