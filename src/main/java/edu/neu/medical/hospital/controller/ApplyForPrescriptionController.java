package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Prescription;
import edu.neu.medical.hospital.bean.PrescriptionDetail;
import edu.neu.medical.hospital.service.ApplyForPrescriptionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
}
