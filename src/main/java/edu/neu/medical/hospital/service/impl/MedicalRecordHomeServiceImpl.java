package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.*;
import edu.neu.medical.hospital.service.MedicalRecordHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MedrecTemplateMapper medrecTemplateMapper;
    @Autowired
    private DepartMapper departMapper;

    /*
     * @Description 根据病历号找到病历信息 未看诊：状态是暂存或完成初诊，需唯一且优先（不存在的情况?）；已看诊：状态是诊毕且显示最近
     * @Param [isSeen 1：未看诊；2：已看诊,medicalRecordId]
     * @return edu.neu.medical.hospital.bean.MedicalRecordInfo
     **/
    public MedicalRecordInfo getMedicalRecordInfoById(char isSeen,int medicalRecordNo) {
        return medicalRecordInfoMapper.getMedicalRecordInfoById(isSeen,medicalRecordNo);
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
    public Boolean setMedicalRecordInfoAndDiagnosisList(MedicalRecordInfo medicalRecordInfo, List<Diagnosis> diagnosisList) {
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
     * @Description 根据常用选项存储的id在疾病表获得常用诊断列表(常用选项列表在门诊医生工作站服务类)
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
     * @Description 删除常用诊断,真删
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

    /*
     * @Description 增加病历模板,判断存在
     * @Param [templateName, category 1全院 2科室 3个人,belongId 类别为1：空；2：科室id；3：医生id, medicalRecordInfo]
     * @return java.lang.Boolean ：false已存在，失败；true成功
     **/
    public Boolean addMedrecTemplate(String templateName,char category,int belongId,MedicalRecordInfo medicalRecordInfo){
        MedrecTemplateExample medrecTemplateExample=new MedrecTemplateExample();
        MedrecTemplateExample.Criteria criteria=medrecTemplateExample.createCriteria();
        criteria.andTemplateNameEqualTo(templateName);
        criteria.andCategoryEqualTo(category+"");
        criteria.andBelongIdEqualTo(belongId);
        if(medrecTemplateMapper.countByExample(medrecTemplateExample)>0){//存在或异常
            return false;
        }
        MedrecTemplate medrecTemplate=new MedrecTemplate();
        medrecTemplate.setTemplateName(templateName);
        medrecTemplate.setCategory(category+"");
        medrecTemplate.setBelongId(belongId);
        medrecTemplate.setChiefComplaint(medicalRecordInfo.getChiefComplaint());
        medrecTemplate.setCurrentMedicalHistory(medicalRecordInfo.getCurrentMedicalHistory());
        medrecTemplate.setStatus("1");
        medrecTemplateMapper.insertSelective(medrecTemplate);
        return true;
    }

    /*
     * @Description 更新病历模板
     * @Param [medrecTemplate]
     * @return int
     **/
    public int updateMedrecTemplate(MedrecTemplate medrecTemplate){
        return medrecTemplateMapper.updateByPrimaryKeySelective(medrecTemplate);
    }

    /*
     * @Description （删除）将病历模板设为无效状态//TODO 权限控制的删除？
     * @Param [medrecTemplate]
     * @return int
     **/
    public int cancelMedrecTemplate(MedrecTemplate medrecTemplate){
        medrecTemplate.setStatus("2");
        return updateMedrecTemplate(medrecTemplate);
    }

    /*
     * @Description 获得指定病历模板列，状态为有效
     * @Param [category 1全院 2科室 3个人, belongId 类别为1：空；2：科室id；3：医生id]
     * @return java.util.List<edu.neu.medical.hospital.bean.MedrecTemplate>
     **/
    public List<MedrecTemplate> getMedrecTemplateList(char category,int belongId){
        MedrecTemplateExample medrecTemplateExample=new MedrecTemplateExample();
        MedrecTemplateExample.Criteria criteria=medrecTemplateExample.createCriteria();
        criteria.andBelongIdEqualTo(belongId);
        criteria.andCategoryEqualTo(category+"");
        criteria.andStatusEqualTo("1");
        return medrecTemplateMapper.selectByExample(medrecTemplateExample);
    }

    /*
     * @Description 根据病历号获得历史病历，所有诊毕status3的
     * @Param [medicalRecordNo]
     * @return java.util.Map<java.lang.String,edu.neu.medical.hospital.bean.MedicalRecordInfo>：String是json格式的（time:诊毕时间，name:科室名）
     **/
    public Map<String,MedicalRecordInfo> getHistoryMedicalRecordInfo(int medicalRecordNo){
        MedicalRecordInfoExample medicalRecordInfoExample=new MedicalRecordInfoExample();
        MedicalRecordInfoExample.Criteria criteria=medicalRecordInfoExample.createCriteria();
        criteria.andMedicalRecordNoEqualTo(medicalRecordNo);
        criteria.andStatusEqualTo("3");
        List<MedicalRecordInfo> list=medicalRecordInfoMapper.selectByExample(medicalRecordInfoExample);
        Map<String,MedicalRecordInfo> map= new HashMap<>();
        for(MedicalRecordInfo medicalRecordInfo:list){
            //日期转换
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String label="{\"time\":\""+formatter.format(medicalRecordInfo.getVisitTime())+"\",\"name\":\"";
            //获得科室名
            DepartExample departExample=new DepartExample();
            DepartExample.Criteria criteria1=departExample.createCriteria();
            criteria1.andIdEqualTo(medicalRecordInfo.getDepartId().shortValue());
            label=label+departMapper.selectByExample(departExample).get(0).getDeptname()+"\"}";
            map.put(label,medicalRecordInfo);
        }
        return map;
    }
}
