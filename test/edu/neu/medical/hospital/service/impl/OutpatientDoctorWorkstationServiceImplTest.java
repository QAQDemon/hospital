package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;


@WebAppConfiguration("src/main/webapp/static")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OutpatientDoctorWorkstationServiceImplTest {

    @Resource
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;

    @Test
    public void setCompleteVisit() {
//        outpatientDoctorWorkstationService.setCompleteVisit(1);
    }
}