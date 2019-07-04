package com.neusoft.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.ssm.bean.*;
import com.neusoft.ssm.dao.*;
import com.neusoft.ssm.service.OutpatientDoctorWorkstationService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class OutpatientDoctorWorkstationServiceImpl implements OutpatientDoctorWorkstationService {
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private CommonOptionMapper commonOptionMapper;
    @Resource
    private FmeditemMapper fmeditemMapper;
    @Resource
    private DrugsMapper drugsMapper;
    @Resource
    private DiagnosisMapper diagnosisMapper;
    @Resource
    private MedicalRecordInfoMapper medicalRecordInfoMapper;
    @Resource
    private RegistrationInfoMapper registrationInfoMapper;
    @Resource
    private PrescriptionMapper prescriptionMapper;
    @Resource
    private VisitItemMapper visitItemMapper;

    private final int pageShow=10;//一页显示的数量

    /*
     * @Description 将已诊或待诊病人列表传回，已诊中会去掉待诊
     * @Param [doctorId id为-1不查, dapartId id为-1不查, isSeenDocator 1：未看诊；2：已看诊, key搜索关键词]
     * @return java.util.List<Patient>
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
     * @return java.util.List<CommonOption>
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
     * @return java.util.List<Fmeditem>
     **/
    public PageInfo<Fmeditem> searchFmeditemList(char type, String key, int pageNum){
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
     * @return java.util.List<Drugs>
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
     * @Description 获得终诊结果
     * @Param [type 1x 2z, medicalInfoId]
     * @return java.util.List<Diagnosis>
     **/
    public List<Diagnosis> getFinalDiagnosisList(char type,int medicalInfoId){
        DiagnosisExample diagnosisExample=new DiagnosisExample();
        DiagnosisExample.Criteria criteria=diagnosisExample.createCriteria();
        criteria.andIsFinalDiagnosisEqualTo("2");
        criteria.andMedicalRecordInfoIdEqualTo(medicalInfoId);
        criteria.andTypeEqualTo(type+"");//确定中西
        criteria.andCategoryEqualTo("1");
        return diagnosisMapper.selectByExample(diagnosisExample);
    }

    /*
     * @Description 设置终诊list
     * @Param [medicalInfoId, diagnosisArray, type]
     * @return java.util.List<Diagnosis>
     **/
    private List<Diagnosis> setFinalDiagnosis(int medicalInfoId,int[] diagnosisArray,char type){
        List<Diagnosis> diagnosisList = new ArrayList<>();
        for (int i = 1; i < diagnosisArray.length; i++) {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setMedicalRecordInfoId(medicalInfoId);
            diagnosis.setDiseaseId(diagnosisArray[i]);
            diagnosis.setType(type+"");
            diagnosis.setIsFinalDiagnosis("2");
            diagnosis.setCategory("1");
            diagnosisList.add(diagnosis);
        }
        return diagnosisList;
    }
    /*
     * @Description 初始化终诊list
     * @Param [medicalInfoId, diagnosis0, diagnosis1]
     * @return java.util.List<Diagnosis>
     **/
    public List<Diagnosis> initeFinalDiagnosis(int medicalInfoId,int[] diagnosis0,int[] diagnosis1){
        List<Diagnosis> diagnosisList = new ArrayList<>();
        diagnosisList.addAll(setFinalDiagnosis(medicalInfoId, diagnosis0, '1'));
        diagnosisList.addAll(setFinalDiagnosis(medicalInfoId, diagnosis1, '2'));
        return diagnosisList;
    }

    /*
     * @Description 门诊确诊，事先设置final和category
     * @Param [diagnosisList]
     * @return java.lang.Boolean
     **/
    public int setFinalDiagnosisList(List<Diagnosis> diagnosisList) {
        DiagnosisExample diagnosisExample=new DiagnosisExample();
        DiagnosisExample.Criteria criteria = diagnosisExample.createCriteria();
        criteria.andMedicalRecordInfoIdEqualTo(diagnosisList.get(0).getMedicalRecordInfoId());
        criteria.andCategoryEqualTo("1");
        criteria.andIsFinalDiagnosisEqualTo("2");
        if(diagnosisMapper.selectByExample(diagnosisExample).size()>0)//已存在诊毕
            return 0;
        diagnosisMapper.insertForeach(diagnosisList);
        return 1;
    }

    /*
     * @Description 诊毕，设置状态、就诊时间
     * @Param [medicalRecordNo ,medicalRecordInfoId]
     * @return java.lang.int 0出错 1成功
     **/
    public int setCompleteVisit(int medicalRecordNo,int medicalRecordInfoId){
        int num;
        Date visitTime=new Date(System.currentTimeMillis());
        //更新病历信息表
        MedicalRecordInfo medicalRecordInfo = new MedicalRecordInfo();
        medicalRecordInfo.setStatus("3");
        medicalRecordInfo.setVisitTime(visitTime);
        medicalRecordInfo.setId(medicalRecordInfoId);
        num=medicalRecordInfoMapper.updateByPrimaryKeySelective(medicalRecordInfo);
        if(num!=1)
            return 0;
        //更新挂号表
        RegistrationInfoExample registrationInfoExample = new RegistrationInfoExample();
        RegistrationInfoExample.Criteria criteria = registrationInfoExample.createCriteria();
        criteria.andMedicalRecordNoEqualTo(medicalRecordNo);
        criteria.andIsSeenDocatorEqualTo("1");
        criteria.andStatusEqualTo("1");
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setMedicalRecordNo(medicalRecordNo);
        registrationInfo.setIsSeenDocator("2");
        registrationInfo.setSeeDoctorDate(visitTime);
        num=registrationInfoMapper.updateByExampleSelective(registrationInfo,registrationInfoExample);
        if(num!=1)
            return 0;
        return num;
    }


    /*
     * @Description 获得生效的处方，只找发送的且不是退费
     * @Param [type 0全部 1成 2草, medicalRecordInfoId]
     * @return java.util.List<Prescription>
     **/
    public List<Prescription> getActivePrescription(char type,int medicalRecordInfoId){
        PrescriptionExample prescriptionExample = new PrescriptionExample();
        PrescriptionExample.Criteria criteria = prescriptionExample.createCriteria();
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        criteria.andStatusEqualTo("2");
        criteria.andFeeStatusNotEqualTo("3");
        if (type != '0') {
            criteria.andTypeEqualTo(type+"");
        }
        return new ArrayList<>(prescriptionMapper.selectByExample(prescriptionExample));
    }

    /*
     * @Description 获得生效的申请，只找开立
     * @Param [type 0全部 1查 2验 3处, medicalRecordInfoId]
     * @return java.util.List<Prescription>
     **/
    public List<VisitItem> getActiveVisitItem(char type,int medicalRecordInfoId){
        VisitItemExample visitItemExample = new VisitItemExample();
        VisitItemExample.Criteria criteria = visitItemExample.createCriteria();
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        criteria.andStatusEqualTo("2");
        if (type != '0') {
            criteria.andTypeEqualTo(type+"");
        }
        return new ArrayList<>(visitItemMapper.selectByExample(visitItemExample));
    }

    /*
     * @Description 获得医生一段时间内的看诊人数和费用,找诊毕的病单号
     * @Param [doctorId, firstTime, lastTime]
     * @return java.util.List<int[]>
     **/
    public Map<Integer, Double[]> statisticsList(int doctorId,String firstTime,String lastTime){
        MedicalRecordInfoExample medicalRecordInfoExample = new MedicalRecordInfoExample();
        MedicalRecordInfoExample.Criteria criteria = medicalRecordInfoExample.createCriteria();
        criteria.andDoctorIdEqualTo(doctorId);
        criteria.andStatusEqualTo("3");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            criteria.andVisitTimeBetween(sdf.parse(firstTime), sdf.parse(lastTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<MedicalRecordInfo> medicalRecordInfoList = medicalRecordInfoMapper.selectByExample(medicalRecordInfoExample);
        Map<Integer, Double[]> map = new HashMap<>();
        for (MedicalRecordInfo medicalRecordInfo : medicalRecordInfoList) {
            Integer medicalRecordNo = medicalRecordInfo.getMedicalRecordNo();
            Integer infoId=medicalRecordInfo.getId();
            Double[] amount=new Double[5];
            amount[0]=0.0;
            amount[1]=0.0;
            amount[2]=0.0;
            amount[3]=0.0;
            amount[4]=0.0;
            VisitItemExample visitItemExample = new VisitItemExample();
            VisitItemExample.Criteria criteria1 = visitItemExample.createCriteria();
            criteria1.andMedicalRecordInfoIdEqualTo(infoId);
            criteria1.andFeeStatusEqualTo("2");
            criteria1.andApplicationDoctorIdEqualTo(doctorId);
            List<VisitItem> visitItemList = visitItemMapper.selectByExample(visitItemExample);
            //根据type得到和
            for (VisitItem visitItem : visitItemList) {
                if(visitItem.getType().equals("1"))
                    amount[0]+=visitItem.getFee().doubleValue();
                else if(visitItem.getType().equals("2"))
                    amount[1]+=visitItem.getFee().doubleValue();
                else amount[2]+=visitItem.getFee().doubleValue();
            }
            PrescriptionExample prescriptionExample = new PrescriptionExample();
            PrescriptionExample.Criteria criteria2 = prescriptionExample.createCriteria();
            criteria2.andMedicalRecordInfoIdEqualTo(infoId);
            criteria2.andFeeStatusEqualTo("2");
            List<Prescription> prescriptionList = prescriptionMapper.selectByExample(prescriptionExample);
            //求和
            for (Prescription prescription : prescriptionList) {
                if (prescription.getType().equals("1"))
                    amount[3]+=prescription.getPrescriptionInAmount().doubleValue();
                else  amount[4]+=prescription.getPrescriptionInAmount().doubleValue();
            }
            //判断key是否存在
            if (map.containsKey(medicalRecordNo)){//取出来相加
                Double[] oldAmount = map.remove(medicalRecordNo);
                for (int k = 0; k < 5; k++) {
                    amount[k]+=oldAmount[k];
                }
            }
            map.put(medicalRecordNo,amount);
        }
        return map;
    }
}
