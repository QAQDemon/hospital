package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.CommonOption;
import edu.neu.medical.hospital.bean.Drugs;
import edu.neu.medical.hospital.bean.Fmeditem;
import edu.neu.medical.hospital.bean.Patient;
import java.util.List;

public interface OutpatientDoctorWorkstationService {
    List<Patient> searchPatientList(int doctorId,int dapartId,char isSeenDocator,String key);
    List<CommonOption> getCommonOptionById(char type,int doctorId);
    List<Fmeditem> searchFmeditemList(char type,String key);
    List<Drugs> searchDrugsList(char type, String key);
}
