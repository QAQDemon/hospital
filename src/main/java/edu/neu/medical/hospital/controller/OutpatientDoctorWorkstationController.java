package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Patient;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("outpatientDoctorWorkstation")
public class OutpatientDoctorWorkstationController {
    @Resource
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;

    @RequestMapping("first")
    public ModelAndView first(){
        return new ModelAndView("outpatientDoctorWorkstation");
    }

    /*
     * @Description 患者搜索，全部或部分//TODO
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
}
