package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    @Test
    public void statisticsList() {
        outpatientDoctorWorkstationService.statisticsList(1,"2019-6-24T20:00","2019-6-24T24:00");
    }
}