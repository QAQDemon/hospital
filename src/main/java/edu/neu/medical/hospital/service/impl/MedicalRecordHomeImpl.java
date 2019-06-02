package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.DiagnosisMapper;
import edu.neu.medical.hospital.dao.MedicalRecordInfoMapper;
import edu.neu.medical.hospital.dao.PatientMapper;
import edu.neu.medical.hospital.service.MedicalRecordHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalRecordHomeImpl implements MedicalRecordHome {
    @Autowired
    private MedicalRecordInfoMapper medicalRecordInfoMapper;
    @Autowired
    private DiagnosisMapper diagnosisMapper;
    @Autowired
    private PatientMapper patientMapper;
    /*
     * @Description 根据病历号找到病历信息 未看诊：状态是暂存或完成初诊，需唯一且优先（不存在的情况?）；已看诊：状态是诊毕且显示最近
     * @Param [isSeen 1：未看诊；2：已看诊,medicalRecordId]
     * @return edu.neu.medical.hospital.bean.MedicalRecordInfo
     **/
    public MedicalRecordInfo getMedicalRecordInfoById(char isSeen,int medicalRecordId) {
        return medicalRecordInfoMapper.getMedicalRecordInfoById(isSeen,medicalRecordId);
    }

    /*
     * @Description 获得初诊断列表，去掉终诊，分中西
     * @Param [type 1西医 2中医, medicalRecordInfoId]
     * @return java.util.List<edu.neu.medical.hospital.bean.Diagnosis>
     **/
    public List<Diagnosis> getNewDiagnosisListById(char type,int medicalRecordInfoId) {
        DiagnosisExample diagnosisExample=new DiagnosisExample();
        DiagnosisExample.Criteria criteria=diagnosisExample.createCriteria();
        criteria.andIsFinalDiagnosisIsNull();//非终诊
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        criteria.andTypeEqualTo(type+"");//确定中西
        return diagnosisMapper.selectByExample(diagnosisExample);
    }

    /*
     * @Description 暂存或提交(修改状态为2)病历信息和诊断，需判断是否已存在信息，诊断可能需要删除，可根据诊断的疾病id只能出现一次//TODO
     *              **medicalRecordInfo应包含状态（1暂存 2提交），病历号，，医生id，科室id,,,病人id需要在service查**
     *              **diagnosisList缺少病历信息id，需处理**
     * @Param [ medicalRecordInfo, diagnosisList]
     * @return java.lang.Boolean
     **/
    public Boolean setMedicalRecordInfoAndDiagnosisListById(MedicalRecordInfo medicalRecordInfo, List<Diagnosis> diagnosisList) {
        //是否有暂存
        int medicalRecordId=medicalRecordInfo.getMedicalRecordNo();
        MedicalRecordInfoExample medicalRecordInfoExample=new MedicalRecordInfoExample();
        MedicalRecordInfoExample.Criteria criteria=medicalRecordInfoExample.createCriteria();
        criteria.andMedicalRecordNoEqualTo(medicalRecordId);
        criteria.andStatusEqualTo('1'+"");
        int count=medicalRecordInfoMapper.countByExample(medicalRecordInfoExample);
        if(count==1){//有暂存，update
            medicalRecordInfoMapper.updateByExampleSelective(medicalRecordInfo,medicalRecordInfoExample);
            //更新初诊，可能会有删除增加修改



        }else if(count==0){//无暂存，insert
            //搜索患者id
            PatientExample patientExample =new PatientExample();
            PatientExample.Criteria criteria1=patientExample.createCriteria();
            criteria1.andMedicalRecordNoEqualTo(medicalRecordId);
            medicalRecordInfo.setPatientId(patientMapper.selectByExample(patientExample).get(0).getId());
            //增加病历信息和初诊
            medicalRecordInfoMapper.insertSelective(medicalRecordInfo);
            int medicalRecordInfoId=medicalRecordInfoMapper.selectByExample(medicalRecordInfoExample).get(0).getId();
            for (Diagnosis d:diagnosisList) {
                d.setMedicalRecordInfoId(medicalRecordInfoId);
            }
            diagnosisMapper.insertForeach(diagnosisList);
        }else{//可能出问题了
            return false;
        }
        return true;
    }
}
