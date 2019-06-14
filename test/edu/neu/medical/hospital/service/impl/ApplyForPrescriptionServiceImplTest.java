package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.service.ApplyForPrescriptionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@WebAppConfiguration("src/main/webapp/static")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ApplyForPrescriptionServiceImplTest {
    @Resource
    ApplyForPrescriptionService applyForPrescriptionService;

    @Before
    public void bef(){
        applyForPrescriptionService.setType('1');
    }

    @Test
    public void deleteCommonDrugs() {
        applyForPrescriptionService.deleteCommonDrugs(1,1);
    }
}