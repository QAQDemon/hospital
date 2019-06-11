package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.*;

import java.util.List;

public interface OutpatientDoctorWorkstationService {
    List<Patient> searchPatientList(int doctorId,int departId,char isSeenDocator,String key);
    List<CommonOption> getCommonOptionById(char type,int doctorId);
    List<Fmeditem> searchFmeditemList(char type,String key);
    List<Drugs> searchDrugsList(char type, String key);
    Boolean setFinalDiagnosisList(List<Diagnosis> diagnosisList);
    Boolean setCompleteVisit(int medicalRecordInfoId);
}
