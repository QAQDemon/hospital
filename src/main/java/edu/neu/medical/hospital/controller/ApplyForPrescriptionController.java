package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Prescription;
import edu.neu.medical.hospital.bean.PrescriptionDetail;
import edu.neu.medical.hospital.service.ApplyForPrescriptionService;
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
}
