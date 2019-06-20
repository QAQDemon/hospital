package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.CommonOptionMapper;
import edu.neu.medical.hospital.dao.DrugsMapper;
import edu.neu.medical.hospital.dao.PrescriptionDetailMapper;
import edu.neu.medical.hospital.dao.PrescriptionMapper;
import edu.neu.medical.hospital.service.ApplyForPrescriptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private CommonOptionMapper commonOptionMapper;

    //1成药 2草药
    private char type;

    public void setType(char type) {
        this.type = type;
    }

    /*
     * @Description 获得处方列表
     * @Param [medicalRecordInfoId]
     * @return java.util.List<edu.neu.medical.hospital.bean.Prescription>
     **/
    public List<Prescription> getPrescriptionListById(int medicalRecordInfoId){
        PrescriptionExample prescriptionExample = new PrescriptionExample();
        PrescriptionExample.Criteria criteria = prescriptionExample.createCriteria();
        criteria.andTypeEqualTo(type + "");
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        return prescriptionMapper.selectByExample(prescriptionExample);
    }

    /*
     * @Description 获得指定处方明细列表
     * @Param [prescriptionId]
     * @return java.util.List<edu.neu.medical.hospital.bean.PrescriptionDetail>
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
     * @return java.util.List<edu.neu.medical.hospital.bean.Drugs>
     **/
    public List<Drugs> getDrugsListByList(List<PrescriptionDetail> prescriptionDetailList){
        List<Drugs> list=new ArrayList<>();
        for(PrescriptionDetail prescriptionDetail:prescriptionDetailList){
            list.add(drugsMapper.selectByPrimaryKey(prescriptionDetail.getDrugId().shortValue()));
        }
        return list;
    }

    /*
     * @Description 设置看诊项目和明细，暂存开立删除作废
     * @Param [prescription id为-1则需要创建, prescriptionDetailList 前者为-1需要获取对应id]
     * @return java.lang.Boolean
     **/
    public Boolean setPrescriptionAndDetailList(Prescription prescription,List<PrescriptionDetail> prescriptionDetailList){
        if(prescription.getId()==-1){//不存在,插入
            prescriptionMapper.insertSelective(prescription);
            //获得申请单号
            int lastId=prescriptionMapper.getLastId(prescription.getMedicalRecordInfoId());
            for (PrescriptionDetail prescriptionDetail:prescriptionDetailList){
                prescriptionDetail.setPrescriptionId(lastId);
            }
            prescriptionDetailMapper.insertForeach(prescriptionDetailList);
        }else {
            prescriptionMapper.updateByPrimaryKeySelective(prescription);
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
        return true;
    }

    /*
     * @Description 根据常用选项存储的id在药品表获得常用药品列表(常用选项列表在门诊医生工作站服务类)
     * @Param [commonOptionList]
     * @return java.util.List<edu.neu.medical.hospital.bean.Drugs>
     **/
    public List<Drugs> getCommonDrugsList(List<CommonOption> commonOptionList){
        List<Drugs> list=new ArrayList<>();
        for(CommonOption commonOption:commonOptionList){
            list.add(drugsMapper.selectByPrimaryKey(commonOption.getBelongId().shortValue()));
        }
        return list;
    }

}
