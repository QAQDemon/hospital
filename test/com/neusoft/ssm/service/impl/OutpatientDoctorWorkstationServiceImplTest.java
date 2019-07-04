package com.neusoft.ssm.service.impl;

import com.neusoft.ssm.bean.Diagnosis;
import com.neusoft.ssm.service.OutpatientDoctorWorkstationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


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

    @Test
    public void setFinalDiagnosisList() {
        List<Diagnosis> list = new ArrayList<>();
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setMedicalRecordInfoId(90);
        list.add(diagnosis);
        assertEquals(1, outpatientDoctorWorkstationService.setFinalDiagnosisList(list));
    }
}