package edu.neu.medical.hospital.controller;

import com.github.pagehelper.PageInfo;
import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.DiagnosisFormDTO;
import edu.neu.medical.hospital.bean.Disease;
import edu.neu.medical.hospital.bean.MedicalRecordInfo;
import edu.neu.medical.hospital.service.MedicalRecordHomeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("medicalRecordHome")
public class MedicalRecordHomeController {
    @Resource
    MedicalRecordHomeService medicalRecordHomeService;

    /*
     * @Description 返回病历单信息，初诊信息
     * @Param [isSeen, medicalRecordNo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
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

    /*
     * @Description 返回搜索的诊断信息（疾病）
     * @Param [type]
     * @return java.util.List<edu.neu.medical.hospital.bean.Disease>
     **/
    @RequestMapping("searchDiagnosis/{type}/{pageNum}")
    public Map<String,Object> searchDiagnosis(@PathVariable("type")char type,@PathVariable("pageNum")int pageNum){
        return searchDiagnosisMethod(type,pageNum,"");
    }
    @RequestMapping("searchDiagnosis/{type}/{pageNum}/{key}")
    public Map<String,Object> searchDiagnosis1(@PathVariable("type")char type,@PathVariable("pageNum")int pageNum,@PathVariable("key")String key){
        return searchDiagnosisMethod(type,pageNum,key);
    }
    private Map<String,Object> searchDiagnosisMethod(char type,int pageNum,String key){
        Map map = new HashMap();
        PageInfo pageInfo=medicalRecordHomeService.searchDiseaseListByCode(type,key,pageNum);
        map.put("pages",pageInfo.getPages());
        map.put("diseaseList",pageInfo.getList());
        return map;
    }

    /*
     * @Description //TODO 
     * @Param [type 1暂存 2提交,medicalRecordInfo, diagnosisFormDTO]
     * @return void
     **/
    @RequestMapping("saveMedicalRecordInfo/{type}")
    public void searchDiagnosis(@PathVariable("type")char type,MedicalRecordInfo medicalRecordInfo, DiagnosisFormDTO diagnosisFormDTO){
        System.out.printf("medicalRecordInfo");
        return;
    }
}
