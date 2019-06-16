package edu.neu.medical.hospital.controller;

import com.github.pagehelper.PageInfo;
import edu.neu.medical.hospital.bean.*;
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
     * @Description 保存病历信息和初诊
     * @Param [type 1暂存 2提交,infoId -1则不存在,medicalRecordInfo, diagnosisFormDTO]
     * @return string 1成 2 失败
     **/
    @RequestMapping("saveMedicalRecordInfo/{type}/{medicalRecordNo}/{infoId}/{patientId}")
    public String searchDiagnosis(@PathVariable("type")char type,@PathVariable("medicalRecordNo")int medicalRecordNo,@PathVariable("infoId")int infoId
            ,@PathVariable("patientId")int patientId,MedicalRecordInfo medicalRecordInfo, DiagnosisFormDTO diagnosisFormDTO){
        int doctorId=1;//todo
        int departID=1;//

        if (type == '1')
            medicalRecordInfo.setStatus("1");
        else
            medicalRecordInfo.setStatus("2");
        if(infoId!=-1)
            medicalRecordInfo.setId(infoId);
        medicalRecordInfo.setDoctorId(doctorId);
        medicalRecordInfo.setDepartId(departID);
        medicalRecordInfo.setMedicalRecordNo(medicalRecordNo);
        medicalRecordInfo.setPatientId(patientId);
        //获得初诊
        List<Diagnosis> diagnosesList = diagnosisFormDTO.getInitialDiagnosisList(infoId);
        if(medicalRecordHomeService.setMedicalRecordInfoAndDiagnosisList(medicalRecordInfo, diagnosesList))
            return "1";
        else
            return "2";
    }

    /*
     * @Description 获得对应类别的模板的名字//TODO
     * @Param [category 1全院 2科室 3个人]
     * @return void
     **/
    @RequestMapping("getMedrecTemplate/{category}")
    public Map<Integer ,String> getMedrecTemplate(@PathVariable("category")char category){
        int doctorId=1;//todo
        int departID=1;//

        int belongId=0;//1：0；2：科室id；3：医生id
        if(category=='2')
            belongId=departID;
        else if(category=='3')
            belongId = doctorId;
        Map<Integer ,String> map=new HashMap<>();
        List<MedrecTemplate> list=medicalRecordHomeService.searchMedrecTemplateList(category, belongId, "");
        for (MedrecTemplate medrecTemplate : list) {
            map.put(medrecTemplate.getId(),medrecTemplate.getTemplateName());
        }
        return map;
    }
}
