package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.*;
import edu.neu.medical.hospital.service.MedicalRecordHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordHomeServiceImpl implements MedicalRecordHomeService {
    @Autowired
    private MedicalRecordInfoMapper medicalRecordInfoMapper;
    @Autowired
    private DiagnosisMapper diagnosisMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private DiseaseMapper diseaseMapper;
    @Autowired
    private CommonOptionMapper commonOptionMapper;

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
     * @Description 获得诊断中的疾病信息
     * @Param [diagnosisList]
     * @return java.util.List<edu.neu.medical.hospital.bean.Disease>
     **/
    public List<Disease> getDiagnosisDiseaseList(List<Diagnosis> diagnosisList){
        List<Disease> list=new ArrayList<>();
        for(Diagnosis diagnosis:diagnosisList){
            list.add(diseaseMapper.selectByPrimaryKey(diagnosis.getDiseaseId().shortValue()));
        }
        return list;
    }

    /*
     * @Description 暂存或提交病历信息和诊断，需判断是否已存在信息
     * @Param [ medicalRecordInfo 应包含状态（1暂存 2提交），病历号，医生id，科室id, diagnosisList 无病历信息id]
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
            int medicalRecordInfoId=medicalRecordInfoMapper.selectByExample(medicalRecordInfoExample).get(0).getId();
            for (Diagnosis d:diagnosisList) {
                d.setMedicalRecordInfoId(medicalRecordInfoId);
            }
            //更新初诊，先删后加
            DiagnosisExample diagnosisExample=new DiagnosisExample();
            DiagnosisExample.Criteria criteria1=diagnosisExample.createCriteria();
            criteria1.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
            diagnosisMapper.deleteByExample(diagnosisExample);
            diagnosisMapper.insertForeach(diagnosisList);
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

    /*
     * @Description 根据疾病编码,转换大写（拼音首字母）模糊搜索
     * @Param [diseaseCode]
     * @return java.util.List<edu.neu.medical.hospital.bean.Disease>
     **/
    public List<Disease> searchDiseaseListByCode(String diseaseCode) {
        DiseaseExample diseaseExample=new DiseaseExample();
        DiseaseExample.Criteria criteria=diseaseExample.createCriteria();
        criteria.andDiseasecodeLike(diseaseCode.toUpperCase()+"%");
        return diseaseMapper.selectByExample(diseaseExample);
    }

    /*
     * @Description 根据常用选项存储的id在疾病表获得常用诊断列表
     * @Param [commonOptionList]
     * @return java.util.List<edu.neu.medical.hospital.bean.Disease>
     **/
    public List<Disease> getCommonDiagnosisList(List<CommonOption> commonOptionList){
        List<Disease> list=new ArrayList<>();
        for(CommonOption commonOption:commonOptionList){
            list.add(diseaseMapper.selectByPrimaryKey(commonOption.getBelongId().shortValue()));
        }
        return list;
    }

    /*
     * @Description 删除常用诊断
     * @Param [doctorId, type, diseaseId]
     * @return int
     **/
    public int deleteCommonDiagnosis(int doctorId,char type,int diseaseId){
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andDoctorIdEqualTo(doctorId);
        criteria.andBelongIdEqualTo(diseaseId);
        criteria.andTypeEqualTo(type+"");
        return commonOptionMapper.deleteByExample(commonOptionExample);
    }

    /*
     * @Description 增加常用诊断，在常用选项表上
     * @Param [doctorId, diagnosis]
     * @return java.lang.Boolean
     **/
    public Boolean addCommonDiagnosis(int doctorId,Diagnosis diagnosis){
        //判断是否存在
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andTypeEqualTo(diagnosis.getType());
        criteria.andBelongIdEqualTo(diagnosis.getDiseaseId());
        criteria.andDoctorIdEqualTo(doctorId);
        int count=commonOptionMapper.countByExample(commonOptionExample);
        if(count==0){
            CommonOption commonOption=new CommonOption();
            commonOption.setDoctorId(doctorId);
            commonOption.setBelongId(diagnosis.getDiseaseId());
            commonOption.setType(diagnosis.getType());
            commonOptionMapper.insertSelective(commonOption);
            return true;
        }else{
            return false;
        }
    }

}
