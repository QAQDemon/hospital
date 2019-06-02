package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.MedicalRecordInfo;
import edu.neu.medical.hospital.service.impl.MedicalRecordHomeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    MedicalRecordHomeImpl medicalRecordHome;
    @RequestMapping("/t1")
    public String m1() {
        MedicalRecordInfo medicalRecordInfo=new MedicalRecordInfo();
        medicalRecordInfo.setMedicalRecordNo(3);
        medicalRecordInfo.setDoctorId(1);
        medicalRecordInfo.setDepartId(1);
        medicalRecordInfo.setChiefComplaint("777");
        medicalRecordInfo.setStatus("1");
        List<Diagnosis> list=new ArrayList<>();
        Diagnosis diagnosis1=new Diagnosis();
        diagnosis1.setDiseaseId(1);
        diagnosis1.setIsNewMajorDiagnosis("1");
        diagnosis1.setType("1");
        diagnosis1.setIsNewSuspect("1");
        Diagnosis diagnosis2=new Diagnosis();
        diagnosis2.setDiseaseId(2);
        diagnosis2.setIsNewMajorDiagnosis("1");
        diagnosis1.setType("1");
        diagnosis1.setIsNewSuspect("1");
        Diagnosis diagnosis3=new Diagnosis();
        diagnosis3.setDiseaseId(3);
        diagnosis1.setType("1");
        diagnosis1.setIsNewSuspect("1");
        list.add(diagnosis1);
        list.add(diagnosis2);
        list.add(diagnosis3);

        Boolean b=medicalRecordHome.setMedicalRecordInfoAndDiagnosisListById(medicalRecordInfo,list);
//        for (Diagnosis p:list) {
//            System.out.println(p.toString());
//        }
        return b+"";
    }
}
