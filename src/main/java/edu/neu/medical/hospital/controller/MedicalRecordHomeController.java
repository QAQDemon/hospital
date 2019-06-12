package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.MedicalRecordInfo;
import edu.neu.medical.hospital.service.MedicalRecordHomeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("medicalRecordHome")
public class MedicalRecordHomeController {
    @Resource
    MedicalRecordHomeService medicalRecordHomeService;

    @RequestMapping("getMedicalRecordInfo/{isSeen}/{medicalRecordNo}")
    public Map<String,Object> getMedicalRecordInfo(@PathVariable("isSeen")char isSeen, @PathVariable("medicalRecordNo")int medicalRecordNo){
        Map map = new HashMap<>();
        MedicalRecordInfo medicalRecordInfo=medicalRecordHomeService.getMedicalRecordInfoById(isSeen,medicalRecordNo);
        if(medicalRecordInfo==null){
            map.put("mes","2");//返回不存在信息
            return map;
        }
        map.put("mes","1");
        map.put("medicalRecordInfo",medicalRecordInfo);
        List<Diagnosis> zDiagnosisList=medicalRecordHomeService.getNewDiagnosisListById('1','2',medicalRecordInfo.getId());
        List<Diagnosis> xDiagnosisList=medicalRecordHomeService.getNewDiagnosisListById('1','1',medicalRecordInfo.getId());
        map.put("zDiagnosisList",zDiagnosisList);
        map.put("xDiagnosisList",xDiagnosisList);
        map.put("zDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(zDiagnosisList));
        map.put("xDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(xDiagnosisList));
        return map;
    }
}
