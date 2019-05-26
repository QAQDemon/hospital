package edu.neu.medical.hospital.controller;

import com.github.pagehelper.PageHelper;
import edu.neu.medical.hospital.bean.Doctor;
import edu.neu.medical.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @RequestMapping("list")
    public void m1() {
        PageHelper.startPage(1, 3);
        List<Doctor> list = doctorService.findAll();
        for (Doctor doctor : list) {
            System.out.println(doctor);
        }
    }
}
