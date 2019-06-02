package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.MedicalRecordInfo;
import edu.neu.medical.hospital.bean.Patient;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import edu.neu.medical.hospital.service.impl.MedicalRecordHomeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    MedicalRecordHomeImpl medicalRecordHome;
    @RequestMapping("/t1")
    public String m1() {
        List<Diagnosis> list=medicalRecordHome.getNewDiagnosisListById('1',1);
        for (Diagnosis p:list) {
            System.out.println(p.toString());
        }
        return null;
    }
}
