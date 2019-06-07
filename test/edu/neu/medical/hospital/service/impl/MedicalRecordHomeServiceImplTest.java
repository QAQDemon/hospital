package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.MedrecTemplate;
import edu.neu.medical.hospital.service.MedicalRecordHomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@WebAppConfiguration("src/main/webapp/static")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MedicalRecordHomeServiceImplTest {
    @Resource
    MedicalRecordHomeService medicalRecordHomeService;

    @Test
    public void addMedrecTemplate() {
        MedrecTemplate medrecTemplate=new MedrecTemplate();
        medrecTemplate.setId(3);
        medrecTemplate.setTemplateName("xixixi4");
        medrecTemplate.setCategory("2");
        medrecTemplate.setStatus("1");
        medrecTemplate.setBelongId(1);
        medrecTemplate.setTemplateCode("litm");
        List<Diagnosis> list=new ArrayList<>();
        Diagnosis diagnosis1=new Diagnosis();
        diagnosis1.setDiseaseId(1);
        diagnosis1.setIsNewMajorDiagnosis("1");
        diagnosis1.setType("1");
        diagnosis1.setIsNewSuspect("1");
        diagnosis1.setCategory("2");
        Diagnosis diagnosis2=new Diagnosis();
        diagnosis2.setDiseaseId(2);
        diagnosis2.setIsNewMajorDiagnosis("1");
        diagnosis2.setType("1");
        diagnosis2.setIsNewSuspect("1");
        diagnosis2.setCategory("2");
        Diagnosis diagnosis3=new Diagnosis();
        diagnosis3.setDiseaseId(3);
        diagnosis3.setType("1");
        diagnosis3.setIsNewSuspect("1");
        diagnosis3.setCategory("2");
        list.add(diagnosis1);
        list.add(diagnosis2);
        list.add(diagnosis3);
        medicalRecordHomeService.addMedrecTemplate(medrecTemplate, list);
    }

    @Test
    public void updateMedrecTemplate() {
        MedrecTemplate medrecTemplate=new MedrecTemplate();
        medrecTemplate.setId(6);
        medrecTemplate.setTemplateName("xixixiaaa4");
        medrecTemplate.setCategory("2");
        medrecTemplate.setStatus("1");
        medrecTemplate.setBelongId(1);
        medrecTemplate.setTemplateCode("litm");
        List<Diagnosis> list=new ArrayList<>();
        Diagnosis diagnosis1=new Diagnosis();
        diagnosis1.setMedicalRecordInfoId(6);
        diagnosis1.setDiseaseId(1);
        diagnosis1.setIsNewMajorDiagnosis("1");
        diagnosis1.setType("1");
        diagnosis1.setCategory("2");
        Diagnosis diagnosis2=new Diagnosis();
        diagnosis2.setMedicalRecordInfoId(6);
        diagnosis2.setDiseaseId(5);
        diagnosis2.setType("1");
        diagnosis2.setCategory("2");
        list.add(diagnosis1);
        list.add(diagnosis2);
        medicalRecordHomeService.updateMedrecTemplate(medrecTemplate, list);
    }

    @Test
    public void searchMedrecTemplateList(){
        List<MedrecTemplate> list=medicalRecordHomeService.searchMedrecTemplateList('3',1,"");
       // List<MedrecTemplate> list1=medicalRecordHomeService.searchMedrecTemplateList('3',1,"专");//创建人
//        List<MedrecTemplate> list2=medicalRecordHomeService.searchMedrecTemplateList('3',1,"阿");//名称
//        List<MedrecTemplate> list3=medicalRecordHomeService.searchMedrecTemplateList('3',1,"d");//编码
        return;
    }
}