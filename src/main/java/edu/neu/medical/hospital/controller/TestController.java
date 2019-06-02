package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Patient;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;
    @RequestMapping("/t1")
    public String m1() {
        List<Patient> list=outpatientDoctorWorkstationService.searchPatientList(-1,2,'2',"2");
        for (Patient p:list) {
            System.out.println(p.toString());
        }
        return null;
    }
}
