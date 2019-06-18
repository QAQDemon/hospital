package edu.neu.medical.hospital.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.*;
import edu.neu.medical.hospital.service.MedicalRecordHomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicalRecordHomeServiceImpl implements MedicalRecordHomeService {
    @Resource
    private MedicalRecordInfoMapper medicalRecordInfoMapper;
    @Resource
    private DiagnosisMapper diagnosisMapper;
    @Resource
    private DiseaseMapper diseaseMapper;
    @Resource
    private CommonOptionMapper commonOptionMapper;
    @Resource
    private MedrecTemplateMapper medrecTemplateMapper;
    @Resource
    private DepartMapper departMapper;

    private final int diagnosisPageShow=10;//诊断一页显示的数量

    /*
     * @Description 根据病历号找到病历信息 未看诊：状态是暂存或完成初诊，需唯一且优先（不存在的情况?）；已看诊：状态是诊毕且显示最近
     * @Param [isSeen 1：未看诊；2：已看诊,medicalRecordId]
     * @return edu.neu.medical.hospital.bean.MedicalRecordInfo
     **/
    public MedicalRecordInfo getMedicalRecordInfoById(char isSeen,int medicalRecordNo) {
        return medicalRecordInfoMapper.getMedicalRecordInfoById(isSeen,medicalRecordNo);
    }

    /*
     * @Description 获得初诊断列表，去掉终诊，分中西(也可用在模板）
     * @Param [category 1正常 2模板,type 1西医 2中医, medicalRecordInfoId]
     * @return java.util.List<edu.neu.medical.hospital.bean.Diagnosis>
     **/
    public List<Diagnosis> getNewDiagnosisListById(char category,char type,int medicalRecordInfoId) {
        DiagnosisExample diagnosisExample=new DiagnosisExample();
        DiagnosisExample.Criteria criteria=diagnosisExample.createCriteria();
        criteria.andIsFinalDiagnosisIsNull();//非终诊
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        criteria.andTypeEqualTo(type+"");//确定中西
        criteria.andCategoryEqualTo(category+"");
        return diagnosisMapper.selectByExample(diagnosisExample);
    }

    /*
     * @Description 获得诊断中的疾病信息(可用在模板病历的诊断)
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
     * @Param [ medicalRecordInfo 应包含状态（1暂存 2提交），病单号可能null,病历号，患者id，医生id，科室id, diagnosisList 可能病单号null]
     * @return java.lang.Boolean
     **/
    public Boolean setMedicalRecordInfoAndDiagnosisList(MedicalRecordInfo medicalRecordInfo, List<Diagnosis> diagnosisList) {
//        //是否有暂存
//        int medicalRecordId=medicalRecordInfo.getMedicalRecordNo();
//        MedicalRecordInfoExample medicalRecordInfoExample=new MedicalRecordInfoExample();
//        MedicalRecordInfoExample.Criteria criteria=medicalRecordInfoExample.createCriteria();
//        criteria.andMedicalRecordNoEqualTo(medicalRecordId);
//        criteria.andStatusEqualTo('1'+"");
//        int count=medicalRecordInfoMapper.countByExample(medicalRecordInfoExample);
        if(medicalRecordInfo.getId()!=null){//有暂存，update
//            medicalRecordInfoMapper.updateByExampleSelective(medicalRecordInfo,medicalRecordInfoExample);
            medicalRecordInfoMapper.updateByPrimaryKeySelective(medicalRecordInfo);
//            int medicalRecordInfoId=medicalRecordInfo.getId();
//            for (Diagnosis d:diagnosisList) {
//                d.setMedicalRecordInfoId(medicalRecordInfoId);
//            }
            //更新初诊，先删后加
            DiagnosisExample diagnosisExample=new DiagnosisExample();
            DiagnosisExample.Criteria criteria1=diagnosisExample.createCriteria();
            criteria1.andMedicalRecordInfoIdEqualTo(medicalRecordInfo.getId());
            criteria1.andCategoryEqualTo("1");
            diagnosisMapper.deleteByExample(diagnosisExample);
            diagnosisMapper.insertForeach(diagnosisList);
        }else {//无暂存，insert
//            //搜索患者id
//            PatientExample patientExample =new PatientExample();
//            PatientExample.Criteria criteria1=patientExample.createCriteria();
//            criteria1.andMedicalRecordNoEqualTo(medicalRecordId);
//            medicalRecordInfo.setPatientId(patientMapper.selectByExample(patientExample).get(0).getId());
            //增加病历信息和初诊
            medicalRecordInfoMapper.insertSelective(medicalRecordInfo);
            int medicalRecordInfoId=medicalRecordInfoMapper.getLastId(medicalRecordInfo.getMedicalRecordNo());
            for (Diagnosis d:diagnosisList) {
                d.setMedicalRecordInfoId(medicalRecordInfoId);
            }
            diagnosisMapper.insertForeach(diagnosisList);
        }
        return true;
    }

    /*
     * @Description 根据疾病编码,转换大写（拼音首字母）模糊搜索（判断中西病）,分页
     * @Param [type 1西病 2中病(472),diseaseCode,pageNum]
     * @return java.util.List<edu.neu.medical.hospital.bean.Disease>
     **/
    public PageInfo<Disease> searchDiseaseListByCode(char type,String diseaseCode,int pageNum) {
        PageHelper.startPage(pageNum,diagnosisPageShow);
        DiseaseExample diseaseExample=new DiseaseExample();
        DiseaseExample.Criteria criteria=diseaseExample.createCriteria();
        criteria.andDiseasecodeLike("%"+diseaseCode.toUpperCase()+"%");
        if(type=='2')
            criteria.andDisecategoryidEqualTo((short)472);
        else
            criteria.andDisecategoryidNotEqualTo((short)472);
        return new PageInfo<>(diseaseMapper.selectByExample(diseaseExample));
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
     * @return java.lang.Boolean 0失败 1成功
     **/
    public int addCommonDiagnosis(int doctorId,char type,int diseaseId){
        //判断是否存在
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andTypeEqualTo(type+"");
        criteria.andBelongIdEqualTo(diseaseId);
        criteria.andDoctorIdEqualTo(doctorId);
        int count=commonOptionMapper.countByExample(commonOptionExample);
        if(count==0){
            CommonOption commonOption=new CommonOption();
            commonOption.setDoctorId(doctorId);
            commonOption.setBelongId(diseaseId);
            commonOption.setType(type+"");
            return commonOptionMapper.insertSelective(commonOption);
        }else{
            return 0;
        }
    }

    /*
     * @Description 根据疾病id获得诊断列表
     * @Param [xDiseases, zDiseases]
     * @return java.util.List<edu.neu.medical.hospital.bean.Diagnosis>
     **/
    public List<Diagnosis> getMedrecTempDiagnosisList(int[] xDiseases,int[] zDiseases){
        List<Diagnosis> list = new ArrayList<>();
        for (int i=1;i<xDiseases.length;i++) {
            list.add(initeTempDiagnosis(xDiseases[i],"1"));
        }
        for (int i=1;i<zDiseases.length;i++) {
            list.add(initeTempDiagnosis(zDiseases[i],"2"));
        }
        return list;
    }
    /*
     * @Description 初始化模板诊断
     * @Param [diseaseId, type 1西 2中]
     * @return edu.neu.medical.hospital.bean.Diagnosis
     **/
    private Diagnosis initeTempDiagnosis(int diseaseId,String type){
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiseaseId(diseaseId);
        diagnosis.setType(type);
        diagnosis.setCategory("2");
        return diagnosis;
    }

    /*
     * @Description 增加病历模板和诊断列表,判断存在
     * @Param [medrecTemplate 类别为全院时，belongid要为0,diagnosisList]
     * @return java.lang.Boolean ：2已存在，失败；1成功
     **/
    public int addMedrecTemplate(MedrecTemplate medrecTemplate,List<Diagnosis> diagnosisList){
        MedrecTemplateExample medrecTemplateExample=new MedrecTemplateExample();
        MedrecTemplateExample.Criteria criteria=medrecTemplateExample.createCriteria();
        criteria.andTemplateCodeEqualTo(medrecTemplate.getTemplateCode());
        criteria.andCategoryEqualTo(medrecTemplate.getCategory()+"");
        criteria.andBelongIdEqualTo(medrecTemplate.getBelongId());
        if(medrecTemplateMapper.countByExample(medrecTemplateExample)>0){//存在或异常
            return 2;
        }
        medrecTemplateMapper.insertSelective(medrecTemplate);
        if(diagnosisList.size()==0)
            return 1;
        //获得插入id
        int lastId=medrecTemplateMapper.selectByExample(medrecTemplateExample).get(0).getId();
        for(Diagnosis diagnosis:diagnosisList){
            diagnosis.setMedicalRecordInfoId(lastId);//设置模板id
        }
        diagnosisMapper.insertForeach(diagnosisList);
        return 1;
    }

    /*
     * @Description 判断是否能更新删除病历模板
     * @Param [doctorId, medrecTemplate]
     * @return java.lang.Boolean true有权限 false无
     **/
    public Boolean judgeControlMedrecTemplate(int doctorId,MedrecTemplate medrecTemplate){
        return doctorId == medrecTemplate.getCreaterId();
    }

    /*
     * @Description 更新病历模板,删除并重输入模板诊断
     * @Param [medrecTemplate,diagnosisList]
     * @return Boolean 0失败 1成功
     **/
    public int updateMedrecTemplate(MedrecTemplate medrecTemplate, List<Diagnosis> diagnosisList){
        int num=medrecTemplateMapper.updateByPrimaryKeySelective(medrecTemplate);
        if(num==0)
            return num;
        DiagnosisExample diagnosisExample=new DiagnosisExample();
        DiagnosisExample.Criteria criteria=diagnosisExample.createCriteria();
        criteria.andCategoryEqualTo("2");
        criteria.andMedicalRecordInfoIdEqualTo(medrecTemplate.getId());
        diagnosisMapper.deleteByExample(diagnosisExample);
        if(diagnosisList.size()==0)
            return num;
        for (Diagnosis diagnosis : diagnosisList) {
            diagnosis.setMedicalRecordInfoId(medrecTemplate.getId());
        }
        diagnosisMapper.insertForeach(diagnosisList);
        return num;
    }

    /*
     * @Description （删除）将病历模板设为无效状态
     * @Param [medrecTemplate]
     * @return int 1成功 2失败
     **/
    public int cancelMedrecTemplate(int medrecTemplateId){
        MedrecTemplate medrecTemplate=new MedrecTemplate();
        medrecTemplate.setId(medrecTemplateId);
        medrecTemplate.setStatus("2");
        return medrecTemplateMapper.updateByPrimaryKeySelective(medrecTemplate);
    }

    /*
     * @Description 搜索指定病历模板列，状态为有效(诊断获取方法在上面)
     * @Param [category 1全院 2科室 3个人, belongId 类别为1：0；2：科室id；3：医生id ，key为“”获得全部,搜索名称，编码，创建人]
     * @return java.util.List<edu.neu.medical.hospital.bean.MedrecTemplate>
     **/
    public List<MedrecTemplate> searchMedrecTemplateList(char category,int belongId,String key){
        return medrecTemplateMapper.searchMedrecTemplate(belongId, category, '1', key);
    }

    /*
     * @Description 通过id获得病历模板
     * @Param [medrecTemplateId]
     * @return edu.neu.medical.hospital.bean.MedrecTemplate
     **/
    public MedrecTemplate getMedrecTemplateById(int medrecTemplateId){
        return medrecTemplateMapper.selectByPrimaryKey(medrecTemplateId);
    }
    
    /*
     * @Description 根据病历号获得历史病历的标签和infoId，所有诊毕status3的
     * @Param [medicalRecordNo]
     * @return java.util.Map<java.lang.String,edu.neu.medical.hospital.bean.MedicalRecordInfo>：String是json格式的（time:诊毕时间，name:科室名）
     **/
    public Map<Integer,String> getHistoryMedicalRecordInfo(int medicalRecordNo){
        MedicalRecordInfoExample medicalRecordInfoExample=new MedicalRecordInfoExample();
        MedicalRecordInfoExample.Criteria criteria=medicalRecordInfoExample.createCriteria();
        criteria.andMedicalRecordNoEqualTo(medicalRecordNo);
        criteria.andStatusEqualTo("3");
        List<MedicalRecordInfo> list=medicalRecordInfoMapper.selectByExample(medicalRecordInfoExample);
        Map<Integer,String> map= new HashMap<>();
        for(MedicalRecordInfo medicalRecordInfo:list){
            //日期转换
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String label="{\"time\":\""+formatter.format(medicalRecordInfo.getVisitTime())+"\",\"name\":\"";
            //获得科室名
            DepartExample departExample=new DepartExample();
            DepartExample.Criteria criteria1=departExample.createCriteria();
            criteria1.andIdEqualTo(medicalRecordInfo.getDepartId().shortValue());
            label=label+departMapper.selectByExample(departExample).get(0).getDeptname()+"\"}";
            map.put(medicalRecordInfo.getId(),label);
        }
        return map;
    }

    /*
     * @Description 获得历史病历内容
     * @Param [medicalRecordInfoId]
     * @return edu.neu.medical.hospital.bean.MedicalRecordInfo
     **/
    public MedicalRecordInfo getHistoryMedicalInfoRecordContext(int medicalRecordInfoId){
        return medicalRecordInfoMapper.selectByPrimaryKey(medicalRecordInfoId);
    }

    /*
     * @Description 返回终诊结果的字符串，以，分割，不分中西
     * @Param [medicalRecordInfoId]
     * @return java.lang.String
     **/
    public String getHistoryMedicalInfoRecordFinalDiagnosis(int medicalRecordInfoId){
        DiagnosisExample diagnosisExample=new DiagnosisExample();
        DiagnosisExample.Criteria criteria=diagnosisExample.createCriteria();
        criteria.andCategoryEqualTo("1");
        criteria.andIsFinalDiagnosisEqualTo("2");
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        List<Diagnosis> diagnosisList = diagnosisMapper.selectByExample(diagnosisExample);
        List<Disease> diseaseList=getDiagnosisDiseaseList(diagnosisList);
        StringBuilder str = new StringBuilder();
        for (Disease disease : diseaseList) {
            str.append(" ").append(disease.getDiseasename()).append(",");
        }
        if(!str.toString().equals(""))
            str.deleteCharAt(str.length()-1);
        return str.toString();
    }
}
