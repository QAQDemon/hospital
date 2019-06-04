package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.*;

import java.util.List;
import java.util.Map;

public interface MedicalRecordHomeService {
    MedicalRecordInfo getMedicalRecordInfoById(char isSeen, int medicalRecordId);
    List<Diagnosis> getNewDiagnosisListById(char type,int medicalRecordInfoId);
    Boolean setMedicalRecordInfoAndDiagnosisListById(MedicalRecordInfo medicalRecordInfo, List<Diagnosis> diagnosisList) ;
    List<Disease> searchDiseaseListByCode(String diseaseCode);
    List<Disease> getCommonDiagnosisList(List<CommonOption> commonOptionList);
    int deleteCommonDiagnosis(int doctorId,char type,int diseaseId);
    List<Disease> getDiagnosisDiseaseList(List<Diagnosis> diagnosisList);
    Boolean addCommonDiagnosis(int doctorId,Diagnosis diagnosis);
    Boolean addMedrecTemplate(String templateName,char category,int belongId,MedicalRecordInfo medicalRecordInfo);
    int updateMedrecTemplate(MedrecTemplate medrecTemplate);
    int cancelMedrecTemplate(MedrecTemplate medrecTemplate);
    List<MedrecTemplate> getMedrecTemplateList(char category,int belongId);
    Map<String,MedicalRecordInfo> getHistoryMedicalRecordInfo(int medicalRecordNo);
}
