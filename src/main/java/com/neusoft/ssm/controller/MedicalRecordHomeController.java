package com.neusoft.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssm.bean.*;
import com.neusoft.ssm.service.MedicalRecordHomeService;
import com.neusoft.ssm.service.OutpatientDoctorWorkstationService;
import com.neusoft.ssm.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("medicalRecordHome")
public class MedicalRecordHomeController {
    @Resource
    MedicalRecordHomeService medicalRecordHomeService;
    @Resource
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;

    /*
     * @Description 返回病历单信息，初诊信息
     * @Param [isSeen, medicalRecordNo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getMedicalRecordInfo/{isSeen}/{medicalRecordNo}")
    public Map<String,Object> getMedicalRecordInfo(@PathVariable("isSeen")char isSeen, @PathVariable("medicalRecordNo")int medicalRecordNo){
        Map<String,Object> map = new HashMap<>();
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
     * @return java.util.List<Disease>
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
        Map<String,Object> map = new HashMap<>();
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
    public String searchDiagnosis(@PathVariable("type")char type, @PathVariable("medicalRecordNo")int medicalRecordNo, @PathVariable("infoId")int infoId
            , @PathVariable("patientId")int patientId, MedicalRecordInfo medicalRecordInfo, DiagnosisFormDTO diagnosisFormDTO, HttpServletRequest request){
        int doctorId = JwtUtil.getHeaderDoctorId(request);
        int departID = JwtUtil.getHeaderDepartmentId(request);
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
     * @Description 获得对应类别的模板的名字
     * @Param [category 1全院 2科室 3个人]
     * @return void
     **/
    @RequestMapping("getMedrecTemplate/{category}")
    public Map<Integer ,String> getMedrecTemplate(HttpServletRequest request,@PathVariable("category")char category){
        return getMedrecTemplate1Method(request,category, "");
    }
    @RequestMapping("getMedrecTemplate/{category}/{key}")
    public Map<Integer ,String> getMedrecTemplate1(HttpServletRequest request,@PathVariable("category")char category,@PathVariable("key")String key){
        return getMedrecTemplate1Method(request,category, key);
    }
    private Map<Integer ,String> getMedrecTemplate1Method(HttpServletRequest request,char category,String key){
        int doctorId = JwtUtil.getHeaderDoctorId(request);
        int departID = JwtUtil.getHeaderDepartmentId(request);
        int belongId=0;//1：0；2：科室id；3：医生id
        if(category=='2')
            belongId=departID;
        else if(category=='3')
            belongId = doctorId;
        Map<Integer ,String> map=new HashMap<>();
        List<MedrecTemplate> list=medicalRecordHomeService.searchMedrecTemplateList(category, belongId, key);
        for (MedrecTemplate medrecTemplate : list) {
            map.put(medrecTemplate.getId(),medrecTemplate.getTemplateName());
        }
        return map;
    }

    /*
     * @Description 根据id获得病历模板内容
     * @Param [medrecTemplateId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getMedrecTemplateContent/{medrecTemplateId}")
    public Map<String,Object> getMedrecTemplateContent(@PathVariable("medrecTemplateId")int medrecTemplateId){
        Map<String,Object> map = new HashMap<>();
        map.put("medrecTemplate", medicalRecordHomeService.getMedrecTemplateById(medrecTemplateId));
        map.put("xDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(medicalRecordHomeService.getNewDiagnosisListById('2','1',medrecTemplateId)));
        map.put("zDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(medicalRecordHomeService.getNewDiagnosisListById('2','2',medrecTemplateId)));
        return map;
    }

    /*
     * @Description  获得诊断常用选项，1西医诊断 2中医诊断
     * @Param []
     * @return void
     **/
    @RequestMapping("getCommonOption")
    public Map<String,List<Disease>> getCommonOption(HttpServletRequest request){
        int doctorId = JwtUtil.getHeaderDoctorId(request);
        Map<String,List<Disease>> map = new HashMap<>();
        map.put("xDiseaseCommonOptionList",medicalRecordHomeService.getCommonDiagnosisList(outpatientDoctorWorkstationService.getCommonOptionById('1', doctorId)));
        map.put("zDiseaseCommonOptionList",medicalRecordHomeService.getCommonDiagnosisList(outpatientDoctorWorkstationService.getCommonOptionById('2', doctorId)));
        return map;
    }

    /*
     * @Description 删除常用诊断
     * @Param [type 1西医诊断 2中医诊断, diseaseId]
     * @return void
     **/
    @RequestMapping("deleteCommonDiagnosis/{type}/{diseaseId}")
    public int deleteCommonDiagnosis(HttpServletRequest request,@PathVariable("type")char type,@PathVariable("diseaseId")int diseaseId){
        int doctorId = JwtUtil.getHeaderDoctorId(request);
        return outpatientDoctorWorkstationService.deleteCommonOption(doctorId, type+"", diseaseId);
    }

    /*
     * @Description 增加常用诊断
     * @Param [type 1西 2中, diseaseId]
     * @return java.lang.Boolean
     **/
    @RequestMapping("addCommonDiagnosis/{type}/{diseaseId}")
    public int addCommonDiagnosis(HttpServletRequest request,@PathVariable("type")char type,@PathVariable("diseaseId")int diseaseId){
        int doctorId = JwtUtil.getHeaderDoctorId(request);
        return outpatientDoctorWorkstationService.addCommonOption(doctorId, type+"", diseaseId);
    }

    /*
     * @Description 获得历史病历的标签和infoId
     * @Param [medicalRecordNo]
     * @return java.util.Map<java.lang.String,MedicalRecordInfo>
     **/
    @RequestMapping("getHistoryMedicalRecordInfo/{medicalRecordNo}")
    public Map<Integer,String> getHistoryMedicalRecordInfo(@PathVariable("medicalRecordNo")int medicalRecordNo){
        return medicalRecordHomeService.getHistoryMedicalRecordInfo(medicalRecordNo);
    }

    /*
     * @Description 根据历史病单号获得详细信息，包括终诊
     * @Param [medicalRecordInfoId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getHistoryMedicalRecordContext/{medicalRecordInfoId}")
    public Map<String,Object> getHistoryMedicalRecordContext(@PathVariable("medicalRecordInfoId")int medicalRecordInfoId){
        Map<String, Object> map = new HashMap<>();
        map.put("historyMedicalRecordInfo", medicalRecordHomeService.getHistoryMedicalInfoRecordContext(medicalRecordInfoId));
        map.put("finalDiagnosis", medicalRecordHomeService.getHistoryMedicalInfoRecordFinalDiagnosis(medicalRecordInfoId));
        return map;
    }

    /*
     * @Description 删除病历模板
     * @Param [medrecTemplateId]
     * @return int
     **/
    @RequestMapping("cancelMedrecTemplate/{medrecTemplateId}")
    public int cancelMedrecTemplate(@PathVariable("medrecTemplateId")int medrecTemplateId){
        return medicalRecordHomeService.cancelMedrecTemplate(medrecTemplateId);
    }

    /*
     * @Description 新增或修改病历模板,新增的id为null
     * @Param [type 1新增 2修改,diseaseId 0西 1中]
     * @return int 1成功 0更新失败（已删除） 2新增失败（code已存在）
     **/
    @RequestMapping("saveMedrecTemplate/{type}")
    public int saveMedrecTemplate(HttpServletRequest request,@PathVariable("type")char type,MedrecTemplate medrecTemplate,int[] diseaseId0,int[] diseaseId1){
        int doctorId=JwtUtil.getHeaderDoctorId(request);
        int departID = JwtUtil.getHeaderDepartmentId(request);
        if(medrecTemplate.getCategory().equals("1"))
            medrecTemplate.setBelongId(0);
        if(medrecTemplate.getCategory().equals("2"))
            medrecTemplate.setBelongId(departID);
        if(medrecTemplate.getCategory().equals("3"))
            medrecTemplate.setBelongId(doctorId);
        medrecTemplate.setStatus("1");
        medrecTemplate.setCreaterId(doctorId);
        List<Diagnosis> diagnosisList=medicalRecordHomeService.getMedrecTempDiagnosisList(diseaseId0,diseaseId1);
        if(type=='2')
            return medicalRecordHomeService.updateMedrecTemplate(medrecTemplate, diagnosisList);
        else
            return medicalRecordHomeService.addMedrecTemplate(medrecTemplate, diagnosisList);
    }
}
