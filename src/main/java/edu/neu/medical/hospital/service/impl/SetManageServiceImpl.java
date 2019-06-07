package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.SetGroup;
import edu.neu.medical.hospital.bean.SetGroupExample;
import edu.neu.medical.hospital.bean.SetSub;
import edu.neu.medical.hospital.dao.SetGroupMapper;
import edu.neu.medical.hospital.dao.SetSubMapper;
import edu.neu.medical.hospital.service.SetManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SetManageServiceImpl implements SetManageService {
    @Resource
    SetGroupMapper setGroupMapper;
    @Resource
    SetSubMapper setSubMapper;

    private char type;//1检查 2检验 3处置 4成药处方 5中药处方
    private char category;//1全院 2科室 3个人
    private int belongId;//所属_id 根据类别对应id 1为0 2科室 3个人

    public void setType(char type) {
        this.type=type;
    }
    public void setCategory(char category) {
        this.category=category;
    }
    public void setBelongId(int belongId){
        this.belongId=belongId;
    }

    /*
     * @Description 增加组套和项目列表,判断存在
     * @Param [set,setSubList]
     * @return java.lang.Boolean ：false已存在，失败；true成功
     **/
    public Boolean addSetAndSub(SetGroup setGroup, List<SetSub> setSubList) {
        SetGroupExample setGroupExample=new SetGroupExample();
        SetGroupExample.Criteria criteria=setGroupExample.createCriteria();
        criteria.andBelongIdEqualTo(belongId);
        criteria.andBusinessClassifyEqualTo(type + "");
        criteria.andUseScopeEqualTo(category + "");
        criteria.andSetCodeEqualTo(setGroup.getSetCode());
        if (setGroupMapper.countByExample(setGroupExample)>0) {//存在或异常
            return false;
        }
        setGroupMapper.insertSelective(setGroup);
        //获得插入id
        int lastId=setGroupMapper.selectByExample(setGroupExample).get(0).getId();
        for (SetSub setSub : setSubList) {
            setSub.setSetId(lastId);//设置组套id
        }
        setSubMapper.insertForeach(setSubList);
        return true;
    }



//    /*
//     * @Description 判断是否能更新删除病历模板
//     * @Param [doctorId, medrecTemplate]
//     * @return java.lang.Boolean true有权限 false无
//     **/
//    public Boolean judgeControlMedrecTemplate(int doctorId,MedrecTemplate medrecTemplate){
//        return doctorId == medrecTemplate.getCreaterId();
//    }
//
//    /*
//     * @Description 更新病历模板,删除并重输入模板诊断
//     * @Param [medrecTemplate,diagnosisList]
//     * @return Boolean
//     **/
//    public Boolean updateMedrecTemplate(MedrecTemplate medrecTemplate, List<Diagnosis> diagnosisList){
//        medrecTemplateMapper.updateByPrimaryKeySelective(medrecTemplate);
//        DiagnosisExample diagnosisExample=new DiagnosisExample();
//        DiagnosisExample.Criteria criteria=diagnosisExample.createCriteria();
//        criteria.andCategoryEqualTo("2");
//        criteria.andMedicalRecordInfoIdEqualTo(medrecTemplate.getId());
//        diagnosisMapper.deleteByExample(diagnosisExample);
//        diagnosisMapper.insertForeach(diagnosisList);
//        return true;
//    }
//
//    /*
//     * @Description （删除）将病历模板设为无效状态
//     * @Param [medrecTemplate]
//     * @return int
//     **/
//    public Boolean cancelMedrecTemplate(MedrecTemplate medrecTemplate){
//        medrecTemplate.setStatus("2");
//        medrecTemplateMapper.updateByPrimaryKeySelective(medrecTemplate);
//        return true;
//    }
//
//    /*
//     * @Description 搜索指定病历模板列，状态为有效(诊断获取方法在上面)
//     * @Param [category 1全院 2科室 3个人, belongId 类别为1：空；2：科室id；3：医生id ，key为空获得全部,搜索名称，编码，创建人]
//     * @return java.util.List<edu.neu.medical.hospital.bean.MedrecTemplate>
//     **/
//    public List<MedrecTemplate> searchMedrecTemplateList(char category,int belongId,String key){
//        return medrecTemplateMapper.searchMedrecTemplate(belongId, category, '1', key);
//    }
}
