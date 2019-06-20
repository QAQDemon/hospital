package edu.neu.medical.hospital.controller;

import com.github.pagehelper.PageInfo;
import edu.neu.medical.hospital.bean.Fmeditem;
import edu.neu.medical.hospital.bean.SetGroup;
import edu.neu.medical.hospital.bean.VisitItem;
import edu.neu.medical.hospital.bean.VisitItemDetail;
import edu.neu.medical.hospital.service.ApplyForFmeditemService;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import edu.neu.medical.hospital.service.SetManageService;
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
    @Resource
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;
    @Resource
    SetManageService setManageService;

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
     * @Description 删除或作废项目
     * @Param [method, visitItemId]
     * @return int 1成功 0更新失败 2已登记
     **/
    @RequestMapping("cancleVisitItem/{method}/{visitItemId}")
    public int cancleVisitItem(@PathVariable("method")char method,@PathVariable("visitItemId")int visitItemId){
        return applyForFmeditemService.cancleVisitItem(method, visitItemId);
    }

    /*
     * @Description 搜索项目，分页
     * @Param [type, pageNum]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("searchItem/{type}/{pageNum}")
    public Map<String,Object> searchItem(@PathVariable("type")char type,@PathVariable("pageNum")int pageNum){
        return searchItemMethod(type,pageNum,"");
    }
    @RequestMapping("searchItem/{type}/{pageNum}/{key}")
    public Map<String,Object> searchItem1(@PathVariable("type")char type,@PathVariable("pageNum")int pageNum,@PathVariable("key")String key){
        return searchItemMethod(type,pageNum,key);
    }
    private Map<String,Object> searchItemMethod(char type,int pageNum,String key){
        Map<String,Object> map = new HashMap<>();
        PageInfo pageInfo=outpatientDoctorWorkstationService.searchFmeditemList(type,key,pageNum);
        map.put("pages",pageInfo.getPages());
        map.put("itemList",pageInfo.getList());
        return map;
    }

    /*
     * @Description  获得项目常用选项，1检查 2检验 3处置
     * @Param []
     * @return void
     **/
    @RequestMapping("getCommonOption/{type}")
    public List<Fmeditem> getCommonOption(@PathVariable("type")char type){
        int doctorId=1;//todo

        return applyForFmeditemService.getCommonFmeditemList(outpatientDoctorWorkstationService.getCommonOptionById((char)(type+2), doctorId));//3,4,5
    }

    /*
     * @Description 删除常用项目
     * @Param [type 1检查 2检验 3处置, fmeditemId]
     * @return void
     **/
    @RequestMapping("deleteCommonItem/{type}/{fmeditemId}")
    public int deleteCommonDItem(@PathVariable("type")char type,@PathVariable("fmeditemId")int fmeditemId){
        int doctorId=1;//todo

        return outpatientDoctorWorkstationService.deleteCommonOption(doctorId,String.valueOf(type-46),fmeditemId);
    }

    /*
     * @Description 增加常用项目
     * @Param [type 1检查 2检验 3处置, fmeditemId]
     * @return java.lang.Boolean
     **/
    @RequestMapping("addCommonFmeditem/{type}/{fmeditemId}")
    public int addCommonFmeditem(@PathVariable("type")char type,@PathVariable("fmeditemId")int fmeditemId){
        int doctorId=1;//todo

        return outpatientDoctorWorkstationService.addCommonOption(doctorId, String.valueOf(type-46), fmeditemId);
    }

    /*
     * @Description 获得对应类别的组套的名字
     * @Param [category 1全院 2科室 3个人,type 1检查 2检验3 处方
     * @return void
     **/
    @RequestMapping("getSetGroup/{category}/{type}")
    public Map<Integer ,String> getSetGroup(@PathVariable("category")char category,@PathVariable("type")char type){
        return getSetGroupMethod(category,type, "");
    }
    @RequestMapping("getSetGroup/{category}/{type}/{key}")
    public Map<Integer ,String> getMedrecTemplate1(@PathVariable("category")char category,@PathVariable("type")char type,@PathVariable("key")String key){
        return getSetGroupMethod(category,type, key);
    }
    private Map<Integer ,String> getSetGroupMethod(char category,char type,String key){
        int doctorId=1;//todo
        int departID=2;//

        int belongId=0;//1：0；2：科室id；3：医生id
        if(category=='2')
            belongId=departID;
        else if(category=='3')
            belongId = doctorId;
        Map<Integer ,String> map=new HashMap<>();
        setManageService.setType(type);
        setManageService.setCategory(category);
        setManageService.setBelongId(belongId);
        List<SetGroup> list=setManageService.searchSetGroupList(key);
        for (SetGroup setGroup : list) {
            map.put(setGroup.getId(),setGroup.getSetName());
        }
        return map;
    }
}
