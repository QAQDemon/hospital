package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.CommonOption;
import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.Patient;
import edu.neu.medical.hospital.service.MedicalRecordHomeService;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("outpatientDoctorWorkstation")
public class OutpatientDoctorWorkstationController {
    @Resource
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;
    @Resource
    MedicalRecordHomeService medicalRecordHomeService;

    /*
     * @Description 第一次跳转到主界面
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("first")
    public ModelAndView first(){
        return new ModelAndView("outpatientDoctorWorkstation");
    }

    /*
     * @Description 患者搜索，全部或部分
     * @Param [category 1本人 2科室, key为空搜索全部]
     * @return java.lang.String
     **/
    @RequestMapping("searchPatient/{category}/{key}")
    public Map<String,List<Patient>> searchPatient(@PathVariable("category")char category, @PathVariable("key")String key){
        int doctorId=1;//todo
        int departID=1;//

        return getPatientMap(category,doctorId,departID,key);
    }
    @RequestMapping("searchPatient/{category}")
    public Map<String,List<Patient>> searchPatient1(@PathVariable("category")char category){
        int doctorId=1;//todo
        int departID=1;//

        return getPatientMap(category,doctorId,departID,"");
    }
    private Map<String,List<Patient>> getPatientMap(char category,int doctorId,int departID,String key){
        if(category=='1')
            departID=-1;
        else
            doctorId=-1;
        Map<String,List<Patient>> map=new HashMap<>();
        map.put("isSeenList", outpatientDoctorWorkstationService.searchPatientList(doctorId, departID, '2', key));
        map.put("notSeenList", outpatientDoctorWorkstationService.searchPatientList(doctorId, departID, '1', key));
        return map;
    }

    /*
     * @Description 获得初诊疾病及信息，和终诊的疾病
     * @Param [medicalInfoId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getNewFinalDiagnosis/{medicalInfoId}")
    public Map<String,Object> getNewFinalDiagnosis(@PathVariable("medicalInfoId")int medicalInfoId){
        Map<String,Object> map = new HashMap<>();
        List<Diagnosis> zNewDiagnosisList=medicalRecordHomeService.getNewDiagnosisListById('1','2',medicalInfoId);
        List<Diagnosis> xNewDiagnosisList=medicalRecordHomeService.getNewDiagnosisListById('1','1',medicalInfoId);
        map.put("zNewDiagnosisList",zNewDiagnosisList);
        map.put("xNewDiagnosisList",xNewDiagnosisList);
        map.put("zDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(zNewDiagnosisList));
        map.put("xDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(xNewDiagnosisList));
        map.put("zFinalDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(outpatientDoctorWorkstationService.getFinalDiagnosisList('2',medicalInfoId)));
        map.put("xFinalDiagnosisDiseaseList",medicalRecordHomeService.getDiagnosisDiseaseList(outpatientDoctorWorkstationService.getFinalDiagnosisList('1',medicalInfoId)));
        return map;
    }

    /*
     * @Description 确定终诊//TODO
     * @Param [medicalInfoId, diagnosis0, diagnosis1]
     * @return int
     **/
    @RequestMapping("setFinalDiagnosis/{medicalInfoId}")
    public int setFinalDiagnosis(@PathVariable("medicalInfoId")int medicalInfoId,int[] diagnosis0,int[] diagnosis1){
        return outpatientDoctorWorkstationService.setFinalDiagnosisList(outpatientDoctorWorkstationService.initeFinalDiagnosis(medicalInfoId, diagnosis0, diagnosis1));
    }

}
