package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.SetGroup;
import edu.neu.medical.hospital.bean.SetGroupExample;
import edu.neu.medical.hospital.bean.SetSub;
import edu.neu.medical.hospital.bean.SetSubExample;
import edu.neu.medical.hospital.dao.*;
import edu.neu.medical.hospital.service.SetManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SetManageServiceImpl implements SetManageService {
    @Resource
    SetGroupMapper setGroupMapper;
    @Resource
    SetSubMapper setSubMapper;
    @Resource
    FmeditemMapper fmeditemMapper;
    @Resource
    DrugsMapper drugsMapper;

    private char type;//business_classify 1检查 2检验 3处置 4成药处方 5中药处方
    private char category;//use_scope 1全院 2科室 3个人
    private int belongId;//所属_id 根据类别对应id 1为0 2科室 3个人

    /*
     * @Description business_classify 1检查 2检验 3处置 4成药处方 5中药处方
     * @Param [type]
     * @return void
     **/
    public void setType(char type) {
        this.type=type;
    }
    /*
     * @Description use_scope 1全院 2科室 3个人
     * @Param [category]
     * @return void
     **/
    public void setCategory(char category) {
        this.category=category;
    }
    /*
     * @Description 所属_id 根据类别对应id 1为0 2科室 3个人
     * @Param [belongId]
     * @return void
     **/
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

    /*
     * @Description 判断是否能更新删除组套
     * @Param [userId, setGroup]
     * @return java.lang.Boolean true有权限 false无
     **/
    public Boolean judgeControlSetGroup(int userId,SetGroup setGroup){
        return userId == setGroup.getCreaterId();
    }

    /*
     * @Description 更新组套,删除并重输入组套子项
     * @Param [setGroup,setSubList]
     * @return Boolean
     **/
    public Boolean updateSetGroup(SetGroup setGroup, List<SetSub> setSubList){
        setGroupMapper.updateByPrimaryKeySelective(setGroup);
        SetSubExample setSubExample = new SetSubExample();
        SetSubExample.Criteria criteria = setSubExample.createCriteria();
        criteria.andSetIdEqualTo(setGroup.getId());
        setSubMapper.deleteByExample(setSubExample);
        setSubMapper.insertForeach(setSubList);
        return true;
    }

    /*
     * @Description （删除）将组套设为无效状态
     * @Param [setGroup]
     * @return Boolean
     **/
    public Boolean cancelSetGroup(SetGroup setGroup){
        setGroup.setStatus("2");
        setGroupMapper.updateByPrimaryKeySelective(setGroup);
        return true;
    }

    /*
     * @Description 搜索指定组套列，状态为有效(诊断获取方法在上面)
     * @Param [key为空获得全部,搜索名称，编码，创建人]
     * @return java.util.List<edu.neu.medical.hospital.bean.MedrecTemplate>
     **/
    public List<SetGroup> searchSetGroupList(String key){
        return setGroupMapper.searchSetGroup(type,category, belongId, '1', key);
    }

    /*
     * @Description 根据组套id获得组套子项
     * @Param [setId]
     * @return java.util.List<edu.neu.medical.hospital.bean.SetSub>
     **/
    public List<SetSub> getSetSubListById(int setId){
        SetSubExample setSubExample = new SetSubExample();
        SetSubExample.Criteria criteria = setSubExample.createCriteria();
        criteria.andSetIdEqualTo(setId);
        return setSubMapper.selectByExample(setSubExample);
    }

    /*
     * @Description 获得组套子项中的项目信息或药品信息
     * @Param [setSubList]
     * @return Object
     **/
    public List<Object> getSubInfoList(List<SetSub> setSubList) {
        List<Object> list = new ArrayList<>();
        for (SetSub setSub : setSubList) {
            if ((type == '1') || (type == '2') || (type == '3'))
                list.add(fmeditemMapper.selectByPrimaryKey(setSub.getResponseId().shortValue()));
            else
                list.add(drugsMapper.selectByPrimaryKey(setSub.getResponseId().shortValue()));
        }
        return list;
    }
}
