package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.Diagnosis;
import edu.neu.medical.hospital.bean.DiagnosisExample;
import edu.neu.medical.hospital.bean.MedicalRecordInfo;
import edu.neu.medical.hospital.dao.DiagnosisMapper;
import edu.neu.medical.hospital.dao.MedicalRecordInfoMapper;
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
    /*
     * @Description 根据病历号找到病历信息 未看诊：状态是暂存或完成初诊，需唯一且优先；已看诊：状态是诊毕且显示最近
     * @Param [isSeen 1：未看诊；2：已看诊,medicalRecordId]
     * @return edu.neu.medical.hospital.bean.MedicalRecordInfo
     **/
    public MedicalRecordInfo getMedicalRecordInfoById(char isSeen,int medicalRecordId) {
        return medicalRecordInfoMapper.getMedicalRecordInfoById(isSeen,medicalRecordId);
    }

    /*
     * @Description 获得初诊断列表，去掉终诊，分中西//TODO
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
}
