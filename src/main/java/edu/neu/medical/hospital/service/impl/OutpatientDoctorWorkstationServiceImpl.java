package edu.neu.medical.hospital.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.*;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import edu.neu.medical.hospital.bean.RegistrationInfo;
import edu.neu.medical.hospital.bean.RegistrationInfoExample;
import edu.neu.medical.hospital.dao.RegistrationInfoMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class OutpatientDoctorWorkstationServiceImpl implements OutpatientDoctorWorkstationService {

    @Resource
    private PatientMapper patientMapper;
    @Resource
    private CommonOptionMapper commonOptionMapper;
    @Resource
    private FmeditemMapper  fmeditemMapper;
    @Resource
    private DrugsMapper drugsMapper;
    @Resource
    private DiagnosisMapper diagnosisMapper;
    @Resource
    private MedicalRecordInfoMapper medicalRecordInfoMapper;
    @Resource
    private RegistrationInfoMapper registrationInfoMapper;

    private final int pageShow=10;//一页显示的数量

    /*
     * @Description 将已诊或待诊病人列表传回，已诊中会去掉待诊
     * @Param [doctorId id为-1不查, dapartId id为-1不查, isSeenDocator 1：未看诊；2：已看诊, key搜索关键词]
     * @return java.util.List<edu.neu.medical.hospital.bean.Patient>
     **/
    public List<Patient> searchPatientList(int doctorId, int departId, char isSeenDocator, String key) {
        List<Patient> notSeenList;
        int intKey=-1;
        //关键词整数判断
        try{
            intKey= Integer.parseInt(key);
        }catch (Exception e){}
        notSeenList= patientMapper.searchPatientList(doctorId,  departId,  '1',  key,intKey);
        if(isSeenDocator=='1'){
            return notSeenList;
        }
        //返回已看诊，并去重
        List<Patient> isSeenList = patientMapper.searchPatientList(doctorId,  departId,  '2',  key,intKey);
        isSeenList.removeAll(notSeenList);
        return isSeenList;
    }

    /*
     * @Description 根据类型和医生id获得常用选项
     * @Param [type 1西医诊断 2中医诊断 3检查 4检验 5处置 6成药处方 7中药处方, doctorId]
     * @return java.util.List<edu.neu.medical.hospital.bean.CommonOption>
     **/
    public List<CommonOption> getCommonOptionById(char type,int doctorId) {
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andDoctorIdEqualTo(doctorId);
        criteria.andTypeEqualTo(type+"");
        return commonOptionMapper.selectByExample(commonOptionExample);
    }

    /*
     * @Description 增加常用选项
     * @Param [doctorId, visitItemDetail] 诊断type+"" ，项目 String.valueOf(type-46) ，处方 String.valueOf(type-43)
     * @return java.lang.Boolean String.valueOf(type-46)
     **/
    public int addCommonOption(int doctorId,String type,int optionId){
        //判断是否存在
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andBelongIdEqualTo(optionId);
        criteria.andDoctorIdEqualTo(doctorId);
        int count=commonOptionMapper.countByExample(commonOptionExample);
        if(count==0){
            CommonOption commonOption=new CommonOption();
            commonOption.setDoctorId(doctorId);
            commonOption.setBelongId(optionId);
            commonOption.setType(type);
            return commonOptionMapper.insertSelective(commonOption);
        }else{
            return 0;
        }
    }

    /*
     * @Description 删除常用选项,真删
     * @Param [doctorId, type, diseaseId]诊断type+"" ，项目 String.valueOf(type-46) ，处方 String.valueOf(type-43)
     * @return int
     **/
    public int deleteCommonOption(int doctorId,String type,int optionId){
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andDoctorIdEqualTo(doctorId);
        criteria.andBelongIdEqualTo(optionId);
        criteria.andTypeEqualTo(type);
        return commonOptionMapper.deleteByExample(commonOptionExample);
    }

    /*
     * @Description 根据type搜索非药品项目，key为空获得全部(可用在组套和申请)
     * @Param [type 1检查 2检验 3处置,key 拼音首字母转换成大写]
     * @return java.util.List<edu.neu.medical.hospital.bean.Fmeditem>
     **/
    public PageInfo<Fmeditem> searchFmeditemList(char type, String key,int pageNum){
        PageHelper.startPage(pageNum,pageShow);
        FmeditemExample fmeditemExample=new FmeditemExample();
        FmeditemExample.Criteria criteria=fmeditemExample.createCriteria();
        criteria.andRecordtypeEqualTo((short)(type-48));
        criteria.andMnemoniccodeLike("%"+key.toUpperCase()+"%");
        return new PageInfo<>(fmeditemMapper.selectByExample(fmeditemExample));
    }

    /*
     * @Description 根据type搜索药品，key为空获得全部(可用在组套和处方)
     * @Param [type 1西药（101） 2中药（102、103）,key 拼音首字母转换成大写]
     * @return java.util.List<edu.neu.medical.hospital.bean.Drugs>
     **/
    public PageInfo<Drugs> searchDrugsList(char type,String key,int pageNum){
        PageHelper.startPage(pageNum,pageShow);
        DrugsExample drugsExample = new DrugsExample();
        DrugsExample.Criteria criteria = drugsExample.createCriteria();
        if(type=='1')
            criteria.andDrugstypeidEqualTo((short) 101);
        else
            criteria.andDrugstypeidNotEqualTo((short) 101);
        criteria.andMnemoniccodeLike("%"+key.toUpperCase() + "%");
        return new PageInfo<>(drugsMapper.selectByExample(drugsExample));
    }

    /*
     * @Description 门诊确诊，事先设置final和category
     * @Param [diagnosisList]
     * @return java.lang.Boolean
     **/
    public Boolean setFinalDiagnosisList(List<Diagnosis> diagnosisList) {
        diagnosisMapper.insertForeach(diagnosisList);
        return true;
    }

    /*
     * @Description 诊毕，设置状态、就诊时间
     * @Param [medicalRecordInfoNo]
     * @return java.lang.Boolean
     **/
    public Boolean setCompleteVisit(int medicalRecordInfoId){
        //更新病历信息表
        MedicalRecordInfo medicalRecordInfo = new MedicalRecordInfo();
        medicalRecordInfo.setId(medicalRecordInfoId);
        medicalRecordInfo.setStatus("3");
        Date visitTime=new Date(System.currentTimeMillis());
        medicalRecordInfo.setVisitTime(visitTime);
        medicalRecordInfoMapper.updateByPrimaryKeySelective(medicalRecordInfo);
        //更新挂号表
        int medicalRecordNo = medicalRecordInfoMapper.selectByPrimaryKey(medicalRecordInfoId).getMedicalRecordNo();
        RegistrationInfoExample registrationInfoExample = new RegistrationInfoExample();
        RegistrationInfoExample.Criteria criteria = registrationInfoExample.createCriteria();
        criteria.andMedicalRecordNoEqualTo(medicalRecordNo);
        criteria.andIsSeenDocatorEqualTo("1");
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setIsSeenDocator("2");
        registrationInfo.setSeeDoctorDate(visitTime);
        registrationInfoMapper.updateByExampleSelective(registrationInfo,registrationInfoExample);
        return true;
    }
}
