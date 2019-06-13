package edu.neu.medical.hospital.service;

import com.github.pagehelper.PageInfo;
import edu.neu.medical.hospital.bean.*;

import java.util.List;
import java.util.Map;

public interface MedicalRecordHomeService {
    MedicalRecordInfo getMedicalRecordInfoById(char isSeen, int medicalRecordId);
    List<Diagnosis> getNewDiagnosisListById(char category,char type,int medicalRecordInfoId);
    Boolean setMedicalRecordInfoAndDiagnosisList(MedicalRecordInfo medicalRecordInfo, List<Diagnosis> diagnosisList) ;
    PageInfo<Disease> searchDiseaseListByCode(char type, String diseaseCode, int pageNum);
    List<Disease> getCommonDiagnosisList(List<CommonOption> commonOptionList);
    int deleteCommonDiagnosis(int doctorId,char type,int diseaseId);
    List<Disease> getDiagnosisDiseaseList(List<Diagnosis> diagnosisList);
    Boolean judgeControlMedrecTemplate(int doctorId,MedrecTemplate medrecTemplate);
    Boolean addCommonDiagnosis(int doctorId,Diagnosis diagnosis);
    Boolean addMedrecTemplate(MedrecTemplate medrecTemplate,List<Diagnosis> diagnosisList);
    Boolean updateMedrecTemplate(MedrecTemplate medrecTemplate, List<Diagnosis> diagnosisList);
    Boolean cancelMedrecTemplate(MedrecTemplate medrecTemplate);
    List<MedrecTemplate> searchMedrecTemplateList(char category,int belongId,String key);
    Map<String,MedicalRecordInfo> getHistoryMedicalRecordInfo(int medicalRecordNo);
}
