package edu.neu.medical.hospital.service.impl;
import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.CommonOptionMapper;
import edu.neu.medical.hospital.dao.DrugsMapper;
import edu.neu.medical.hospital.dao.FmeditemMapper;
import edu.neu.medical.hospital.dao.PatientMapper;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /*
     * @Description 将已诊或待诊病人列表传回，已诊中会去掉待诊
     * @Param [doctorId id为-1不查, dapartId id为-1不查, isSeenDocator 1：未看诊；2：已看诊, key搜索关键词]
     * @return java.util.List<edu.neu.medical.hospital.bean.Patient>
     **/
    public List<Patient> searchPatientList(int doctorId, int dapartId, char isSeenDocator, String key) {
        List<Patient> notSeenList;
        int intKey=-1;
        //关键词整数判断
        try{
            intKey= Integer.parseInt(key);
        }catch (Exception e){}
        notSeenList= patientMapper.searchPatientList(doctorId,  dapartId,  '1',  key,intKey);
        if(isSeenDocator=='1'){
            return notSeenList;
        }
        //返回已看诊，并去重
        List<Patient> isSeenList = patientMapper.searchPatientList(doctorId,  dapartId,  '2',  key,intKey);
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
     * @Description 根据type搜索非药品项目，key为空获得全部(可用在组套和申请)
     * @Param [type 1检查 2检验 3处置,key 拼音首字母转换成大写]
     * @return java.util.List<edu.neu.medical.hospital.bean.Fmeditem>
     **/
    public List<Fmeditem> searchFmeditemList(char type,String key){
        FmeditemExample fmeditemExample=new FmeditemExample();
        FmeditemExample.Criteria criteria=fmeditemExample.createCriteria();
        criteria.andRecordtypeEqualTo((short)type);
        criteria.andMnemoniccodeLike(key.toUpperCase()+"%");
        return fmeditemMapper.selectByExample(fmeditemExample);
    }

    /*
     * @Description 根据type搜索药品，key为空获得全部(可用在组套和处方)
     * @Param [type 1西药（101） 2中药（102、103）,key 拼音首字母转换成大写]
     * @return java.util.List<edu.neu.medical.hospital.bean.Drugs>
     **/
    public List<Drugs> searchDrugsList(char type,String key){
        DrugsExample drugsExample = new DrugsExample();
        DrugsExample.Criteria criteria = drugsExample.createCriteria();
        if(type=='1')
            criteria.andDrugstypeidEqualTo((short) 101);
        else
            criteria.andDrugstypeidNotEqualTo((short) 101);
        criteria.andMnemoniccodeEqualTo(key.toUpperCase() + "%");
        return drugsMapper.selectByExample(drugsExample);
    }
}
