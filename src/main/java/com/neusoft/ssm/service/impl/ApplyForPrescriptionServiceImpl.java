package com.neusoft.ssm.service.impl;

import com.neusoft.ssm.bean.*;
import com.neusoft.ssm.service.ApplyForPrescriptionService;
import com.neusoft.ssm.dao.DrugsMapper;
import com.neusoft.ssm.dao.PrescriptionDetailMapper;
import com.neusoft.ssm.dao.PrescriptionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyForPrescriptionServiceImpl implements ApplyForPrescriptionService {
    @Resource
    private PrescriptionMapper prescriptionMapper;
    @Resource
    private PrescriptionDetailMapper prescriptionDetailMapper;
    @Resource
    private DrugsMapper drugsMapper;

    //1成药 2草药
    private char type;

    public void setType(char type) {
        this.type = type;
    }

    /*
     * @Description 获得处方列表
     * @Param [medicalRecordInfoId]
     * @return java.util.List<Prescription>
     **/
    public List<Prescription> getPrescriptionListById(int medicalRecordInfoId){
        PrescriptionExample prescriptionExample = new PrescriptionExample();
        PrescriptionExample.Criteria criteria = prescriptionExample.createCriteria();
        criteria.andTypeEqualTo(type + "");
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        criteria.andStatusNotEqualTo("3");
        return prescriptionMapper.selectByExample(prescriptionExample);
    }

    /*
     * @Description 获得指定处方明细列表
     * @Param [prescriptionId]
     * @return java.util.List<PrescriptionDetail>
     **/
    public List<PrescriptionDetail> getPrescriptionDetailListById(int prescriptionId){
        PrescriptionDetailExample prescriptionDetailExample = new PrescriptionDetailExample();
        PrescriptionDetailExample.Criteria criteria = prescriptionDetailExample.createCriteria();
        criteria.andPrescriptionIdEqualTo(prescriptionId);
        return prescriptionDetailMapper.selectByExample(prescriptionDetailExample);
    }

    /*
     * @Description 获得处方明细中的药品信息
     * @Param [prescriptionDetailList]
     * @return java.util.List<Drugs>
     **/
    public List<Drugs> getDrugsListByList(List<PrescriptionDetail> prescriptionDetailList){
        List<Drugs> list=new ArrayList<>();
        for(PrescriptionDetail prescriptionDetail:prescriptionDetailList){
            list.add(drugsMapper.selectByPrimaryKey(prescriptionDetail.getDrugId().shortValue()));
        }
        return list;
    }

    /*
     * @Description 初始化处方明细列表
     * @Param [fmeditemIds, doctorEntrustments]
     * @return java.util.List<VisitItemDetail>
     **/
    public List<PrescriptionDetail> initePrescriptionDetailList(int[] drugsId,String[] usageMethod,Double[] consumption,char[] frequent,int[] days,int[] amount,String[] entrustment){
        List<PrescriptionDetail> prescriptionDetailList = new ArrayList<>();
        for (int i = 0; i < drugsId.length; i++) {
            PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
            prescriptionDetail.setDrugId(drugsId[i]);
            prescriptionDetail.setUsageMethod(usageMethod[i]);
            prescriptionDetail.setConsumption(BigDecimal.valueOf(consumption[i]));
            prescriptionDetail.setFrequent(frequent[i]+"");
            prescriptionDetail.setDays(days[i]);
            prescriptionDetail.setAmount(amount[i]);
            prescriptionDetail.setEntrustment(entrustment[i]);
            prescriptionDetail.setIsReturnMedicine("1");
            prescriptionDetailList.add(prescriptionDetail);
        }
        return prescriptionDetailList;
    }

    /*
     * @Description 设置处方项目和明细，暂存开立
     * @Param [prescription id为null则需要创建, prescriptionDetailList 前者为null需要获取对应id]
     * @return java.lang.Boolean
     **/
    public int setPrescriptionAndDetailList(Prescription prescription,List<PrescriptionDetail> prescriptionDetailList){
        int result=0;
        if(prescription.getId()==null){//不存在,插入
            result=prescriptionMapper.insertSelective(prescription);
            if(result==0)
                return result;
            //获得处方号
            int lastId=prescriptionMapper.getLastId(prescription.getMedicalRecordInfoId());
            for (PrescriptionDetail prescriptionDetail:prescriptionDetailList){
                prescriptionDetail.setPrescriptionId(lastId);
            }
            prescriptionDetailMapper.insertForeach(prescriptionDetailList);
        }else {
            result=prescriptionMapper.updateByPrimaryKeySelective(prescription);
            if(result==0)
                return result;
            int prescriptionId=prescription.getId();
            for (PrescriptionDetail prescriptionDetail:prescriptionDetailList){
                prescriptionDetail.setPrescriptionId(prescriptionId);
            }
            //删除原有明细
            PrescriptionDetailExample prescriptionDetailExample = new PrescriptionDetailExample();
            PrescriptionDetailExample.Criteria criteria=prescriptionDetailExample.createCriteria();
            criteria.andPrescriptionIdEqualTo(prescriptionId);
            prescriptionDetailMapper.deleteByExample(prescriptionDetailExample);
            prescriptionDetailMapper.insertForeach(prescriptionDetailList);
        }
        return result;
    }

    /*
     * @Description 删除或作废处方，作废需要判断是否收费
     * @Param [method, prescriptionId]
     * @return int 1成功 0更新失败 2已付费或退费
     **/
    public int canclePrescription(char method,int prescriptionId){
        if(method=='4'){//作废，获得付费状态
            if(!prescriptionMapper.selectByPrimaryKey(prescriptionId).getFeeStatus().equals("1"))
                return 2;
        }
        Prescription prescription = new Prescription();
        prescription.setId(prescriptionId);
        prescription.setStatus(method+"");
        return prescriptionMapper.updateByPrimaryKeySelective(prescription);
    }

    /*
     * @Description 根据常用选项存储的id在药品表获得常用药品列表(常用选项列表在门诊医生工作站服务类)
     * @Param [commonOptionList]
     * @return java.util.List<Drugs>
     **/
    public List<Drugs> getCommonDrugsList(List<CommonOption> commonOptionList){
        List<Drugs> list=new ArrayList<>();
        for(CommonOption commonOption:commonOptionList){
            list.add(drugsMapper.selectByPrimaryKey(commonOption.getBelongId().shortValue()));
        }
        return list;
    }

}
