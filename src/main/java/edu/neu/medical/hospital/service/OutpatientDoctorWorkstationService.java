package edu.neu.medical.hospital.service;

import com.github.pagehelper.PageInfo;
import edu.neu.medical.hospital.bean.*;

import java.util.List;

public interface OutpatientDoctorWorkstationService {
    List<Patient> searchPatientList(int doctorId,int departId,char isSeenDocator,String key);
    List<CommonOption> getCommonOptionById(char type,int doctorId);
    PageInfo<Fmeditem> searchFmeditemList(char type, String key, int pageNum);
    PageInfo<Drugs> searchDrugsList(char type,String key,int pageNum);
    int setFinalDiagnosisList(List<Diagnosis> diagnosisList);
    Boolean setCompleteVisit(int medicalRecordInfoId);
    int deleteCommonOption(int doctorId,String type,int optionId);
    int addCommonOption(int doctorId,String type,int optionId);
    List<Diagnosis> getFinalDiagnosisList(char type,int medicalInfoId);
    List<Diagnosis> initeFinalDiagnosis(int medicalInfoId,int[] diagnosis0,int[] diagnosis1);
}
