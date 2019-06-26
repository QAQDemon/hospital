package com.neusoft.ssm.service;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssm.bean.*;
import com.neusoft.ssm.bean.*;

import java.util.List;
import java.util.Map;

public interface MedicalRecordHomeService {
    MedicalRecordInfo getMedicalRecordInfoById(char isSeen, int medicalRecordId);
    List<Diagnosis> getNewDiagnosisListById(char category, char type, int medicalRecordInfoId);
    Boolean setMedicalRecordInfoAndDiagnosisList(MedicalRecordInfo medicalRecordInfo, List<Diagnosis> diagnosisList) ;
    PageInfo<Disease> searchDiseaseListByCode(char type, String diseaseCode, int pageNum);
    List<Disease> getCommonDiagnosisList(List<CommonOption> commonOptionList);
    List<Disease> getDiagnosisDiseaseList(List<Diagnosis> diagnosisList);
    Boolean judgeControlMedrecTemplate(int doctorId, MedrecTemplate medrecTemplate);
    int addMedrecTemplate(MedrecTemplate medrecTemplate,List<Diagnosis> diagnosisList);
    int updateMedrecTemplate(MedrecTemplate medrecTemplate, List<Diagnosis> diagnosisList);
    int cancelMedrecTemplate(int medrecTemplateId);
    List<MedrecTemplate> searchMedrecTemplateList(char category,int belongId,String key);
    Map<Integer,String> getHistoryMedicalRecordInfo(int medicalRecordNo);
    MedrecTemplate getMedrecTemplateById(int medrecTemplateId);
    MedicalRecordInfo getHistoryMedicalInfoRecordContext(int medicalRecordInfoId);
    String getHistoryMedicalInfoRecordFinalDiagnosis(int medicalRecordInfoId);
    List<Diagnosis> getMedrecTempDiagnosisList(int[] xDiseases,int[] zDiseases);
}
