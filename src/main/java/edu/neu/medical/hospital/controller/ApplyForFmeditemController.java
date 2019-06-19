package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.Fmeditem;
import edu.neu.medical.hospital.bean.VisitItem;
import edu.neu.medical.hospital.bean.VisitItemDetail;
import edu.neu.medical.hospital.service.ApplyForFmeditemService;
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
@RequestMapping("applyForFmeditem")
public class ApplyForFmeditemController {
    @Resource
    ApplyForFmeditemService applyForFmeditemService;

    /*
     * @Description 获得项目内容和申请人名字
     * @Param [type, medicalRecordInfoId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getVisitItemList/{type}/{medicalRecordInfoId}")
    public Map<String,Object> getVisitItemList(@PathVariable("type")char type, @PathVariable("medicalRecordInfoId")int medicalRecordInfoId){
        applyForFmeditemService.setType(type);
        Map<String, Object> map = new HashMap<>();
        List<VisitItem> visitItemList =applyForFmeditemService.getVisitItemListById(medicalRecordInfoId);
        map.put("visitItemList", visitItemList);
        map.put("applyForPeople", applyForFmeditemService.getApplyForPeopleName(visitItemList));
        return map;

    }

    /*
     * @Description 获得项目明细和非药品信息
     * @Param [type, visitItemId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getVisitItemDetail/{visitItemId}")
    public Map<String,Object> getVisitItemDetail(@PathVariable("visitItemId")int visitItemId){
        Map <String, Object> map = new HashMap<>();
        List<VisitItemDetail> visitItemDetailList = applyForFmeditemService.getVisitItemDetailListById(visitItemId);
        map.put("visitItemDetailList",visitItemDetailList);
        map.put("fmeditemList",applyForFmeditemService.getFmeditemListByList(visitItemDetailList));
        return map;
    }

    /*
     * @Description 暂存或开立项目 visitItemId-1则不放入
     * @Param [type,method 1暂存 2开立, visitItemId, purposeRequirement, fee, fmeditemId, doctorEntrustment]
     * @return void 1成功 0失败
     **/
    @RequestMapping("setVisitItemAndDetail/{type}/{method}/{medicalRecordInfoId}/{visitItemId}/{purposeRequirement}/{fee:.+}")
    public int setVisitItemAndDetail(@PathVariable("type")char type,@PathVariable("method")char method,@PathVariable("medicalRecordInfoId")int medicalRecordInfoId,
                                      @PathVariable("visitItemId")int visitItemId, @PathVariable("purposeRequirement")String purposeRequirement,
                                      @PathVariable("fee")Double fee, int[] fmeditemId,String[] doctorEntrustment){
        int doctorId=1;//TODO

        applyForFmeditemService.setType(type);
        VisitItem visitItem = new VisitItem();
        if(visitItemId!=-1)
            visitItem.setId(visitItemId);
        visitItem.setMedicalRecordInfoId(medicalRecordInfoId);
        visitItem.setType(type+"");
        visitItem.setPurposeRequirement(purposeRequirement);
        visitItem.setStatus(method+"");
        if(method=='2')
            visitItem.setApplicationTime(new Date());
        visitItem.setApplicationDoctorId(doctorId);
        visitItem.setFeeStatus("1");
        visitItem.setExecutionStatus("1");
        visitItem.setFee(BigDecimal.valueOf(fee));
        return applyForFmeditemService.setVisitItemAndDetailList(visitItem, applyForFmeditemService.initeVisitItemDetailList(fmeditemId, doctorEntrustment));
    }

    /*
     * @Description 删除或作废项目//TODO
     * @Param [method, visitItemId]
     * @return int 1成功 0更新失败 2已登记
     **/
    @RequestMapping("cancleVisitItem/{method}/{visitItemId}")
    public int cancleVisitItem(@PathVariable("method")char method,@PathVariable("visitItemId")int visitItemId){
        return applyForFmeditemService.cancleVisitItem(method, visitItemId);
    }
}
