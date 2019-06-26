package com.neusoft.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.neusoft.ssm.bean.Drugs;
import com.neusoft.ssm.bean.Prescription;
import com.neusoft.ssm.bean.PrescriptionDetail;
import com.neusoft.ssm.service.ApplyForPrescriptionService;
import com.neusoft.ssm.service.OutpatientDoctorWorkstationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("applyForPrescription")
public class ApplyForPrescriptionController {
    @Resource
    ApplyForPrescriptionService applyForPrescriptionService;
    @Resource
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;

    /*
     * @Description 获得处方内容
     * @Param [type, medicalRecordInfoId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getPrescription/{type}/{medicalRecordInfoId}")
    public List<Prescription> getVisitItemList(@PathVariable("type")char type, @PathVariable("medicalRecordInfoId")int medicalRecordInfoId){
        applyForPrescriptionService.setType(type);
        return applyForPrescriptionService.getPrescriptionListById(medicalRecordInfoId);
    }

    /*
     * @Description 获得处方明细和药品信息
     * @Param [type, visitItemId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getPrescriptionDetail/{prescriptionId}")
    public Map<String,Object> getVisitItemDetail(@PathVariable("prescriptionId")int prescriptionId){
        Map <String, Object> map = new HashMap<>();
        List<PrescriptionDetail> prescriptionDetailList = applyForPrescriptionService.getPrescriptionDetailListById(prescriptionId);
        map.put("prescriptionDetailList",prescriptionDetailList);
        map.put("drugsList",applyForPrescriptionService.getDrugsListByList(prescriptionDetailList));
        return map;
    }

    /*
     * @Description 暂存或发送处方 prescriptionId-1则不放入
     * @Param [type1成药 2草药,method 1暂存 2发送, visitItemId, purposeRequirement, fee, fmeditemId, doctorEntrustment]
     * @return void 1成功 0失败
     **/
    @RequestMapping("setPrescriptionAndDetail/{type}/{method}/{medicalRecordInfoId}/{prescriptionId}/{prescriptionName}/{prescriptionType}/{prescriptionInAmount:.+}")
    public int setPrescriptionAndDetail(@PathVariable("type")char type,@PathVariable("method")char method,@PathVariable("medicalRecordInfoId")int medicalRecordInfoId,
                                     @PathVariable("prescriptionId")int prescriptionId, @PathVariable("prescriptionName")String prescriptionName,
                                     @PathVariable("prescriptionType")char prescriptionType,@PathVariable("prescriptionInAmount")Double prescriptionInAmount,
                                     int[] drugsId,String[] usageMethod,Double[] consumption,char[] frequent,int[] days,int[] amount,String[] entrustment){
        applyForPrescriptionService.setType(type);
        Prescription prescription = new Prescription();
        if(prescriptionId!=-1)
            prescription.setId(prescriptionId);
        prescription.setMedicalRecordInfoId(medicalRecordInfoId);
        prescription.setType(type+"");
        prescription.setPrescriptionType(prescriptionType+"");
        prescription.setPrescriptionName(prescriptionName);
        prescription.setStatus(method+"");
        if(method=='2')
            prescription.setBuildTime(new Date());
        prescription.setPrescriptionInAmount(BigDecimal.valueOf(prescriptionInAmount));
        prescription.setFeeStatus("1");
        prescription.setExecutionStatus("1");
        return applyForPrescriptionService.setPrescriptionAndDetailList(prescription, applyForPrescriptionService.initePrescriptionDetailList(drugsId, usageMethod,consumption,frequent,days,amount,entrustment));
    }

    /*
     * @Description 删除或作废处方
     * @Param [method 3删除 4作废, visitItemId]
     * @return int 1成功 0更新失败 2已付费
     **/
    @RequestMapping("canclePrescription/{method}/{prescriptionId}")
    public int canclePrescription(@PathVariable("method")char method,@PathVariable("prescriptionId")int prescriptionId){
        return applyForPrescriptionService.canclePrescription(method, prescriptionId);
    }

    /*
     * @Description 搜索药品，分页
     * @Param [type 1西药 2中药, pageNum]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("searchDrugs/{type}/{pageNum}")
    public Map<String,Object> searchDrugs(@PathVariable("type")char type,@PathVariable("pageNum")int pageNum){
        return searchDrugsMethod(type,pageNum,"");
    }
    @RequestMapping("searchDrugs/{type}/{pageNum}/{key}")
    public Map<String,Object> searchDrugs1(@PathVariable("type")char type,@PathVariable("pageNum")int pageNum,@PathVariable("key")String key){
        return searchDrugsMethod(type,pageNum,key);
    }
    private Map<String,Object> searchDrugsMethod(char type,int pageNum,String key){
        Map<String,Object> map = new HashMap<>();
        PageInfo pageInfo=outpatientDoctorWorkstationService.searchDrugsList(type,key,pageNum);
        map.put("pages",pageInfo.getPages());
        map.put("drugsList",pageInfo.getList());
        return map;
    }

    /*
     * @Description  获得药品常用选项，1成药 2草药
     * @Param []
     * @return void
     **/
    @RequestMapping("getCommonOption/{type}")
    public List<Drugs> getCommonOption(@PathVariable("type")char type){
        int doctorId=1;//todo

        return applyForPrescriptionService.getCommonDrugsList(outpatientDoctorWorkstationService.getCommonOptionById((char)(type+5), doctorId));//6,7
    }

    /*
     * @Description 删除常用药品
     * @Param [type 1成药 2草药, drugsId]
     * @return void
     **/
    @RequestMapping("deleteCommonDrugs/{type}/{drugsId}")
    public int deleteCommonDrugs(@PathVariable("type")char type,@PathVariable("drugsId")int drugsId){
        int doctorId=1;//todo

        return outpatientDoctorWorkstationService.deleteCommonOption(doctorId,String.valueOf(type-43),drugsId);
    }

    /*
     * @Description 增加常用药品
     * @Param [type 1成药 2草药, drugsId]
     * @return java.lang.Boolean
     **/
    @RequestMapping("addCommonDrugs/{type}/{drugsId}")
    public int addCommonDrugs(@PathVariable("type")char type,@PathVariable("drugsId")int drugsId){
        int doctorId=1;//todo

        return outpatientDoctorWorkstationService.addCommonOption(doctorId, String.valueOf(type-43), drugsId);
    }
}
