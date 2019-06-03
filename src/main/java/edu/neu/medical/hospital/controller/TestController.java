package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.CommonOption;
import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.Disease;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import edu.neu.medical.hospital.service.impl.MedicalRecordHomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    MedicalRecordHomeServiceImpl medicalRecordHome;
    @Autowired
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;

    @RequestMapping("/t1")
    public String m1() {
//        MedicalRecordInfo medicalRecordInfo=new MedicalRecordInfo();
//        medicalRecordInfo.setMedicalRecordNo(3);
//        medicalRecordInfo.setDoctorId(1);
//        medicalRecordInfo.setDepartId(1);
//        medicalRecordInfo.setChiefComplaint("777");
//        medicalRecordInfo.setStatus("1");
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
        diagnosis3.setType("1");
        diagnosis3.setIsNewSuspect("1");
        list.add(diagnosis1);
        list.add(diagnosis2);
        list.add(diagnosis3);
//        List<CommonOption> list=outpatientDoctorWorkstationService.getCommonOptionById('1',1);
//        List<Disease> list1=medicalRecordHome.getCommonDiagnosisList(list);
//        for (CommonOption p:list) {
//            System.out.println(p.toString());
//        }
//        for (Disease p:list1) {
//            System.out.println(p.toString());
//        }

        medicalRecordHome.addCommonDiagnosis(1,diagnosis3);

        return "";
    }
}
