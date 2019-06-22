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
     * @Description 初始化组套子项
     * @Param [fmeitemId, setSubEntrust]
     * @return java.util.List<edu.neu.medical.hospital.bean.SetSub>
     **/
    public List<SetSub> initeSetSubList(int[] objectId,String[] setSubEntrust){
        List<SetSub> setSubList = new ArrayList<>();
        for (int i = 1; i < objectId.length; i++) {
            SetSub setSub = new SetSub();
            setSub.setResponseId(objectId[i]);
            setSub.setEntrust(setSubEntrust[i]);
            setSubList.add(setSub);
        }
        return setSubList;
    }

    /*
     * @Description 增加组套和项目列表,判断存在
     * @Param [set,setSubList]
     * @return java.lang.Boolean ：2已存在，失败；1成功
     **/
    public int addSetAndSub(SetGroup setGroup, List<SetSub> setSubList) {
        SetGroupExample setGroupExample=new SetGroupExample();
        SetGroupExample.Criteria criteria=setGroupExample.createCriteria();
        criteria.andBelongIdEqualTo(belongId);
        criteria.andBusinessClassifyEqualTo(type + "");
        criteria.andUseScopeEqualTo(category + "");
        criteria.andSetCodeEqualTo(setGroup.getSetCode());
        if (setGroupMapper.countByExample(setGroupExample)>0) {//存在或异常
            return 2;
        }
        setGroupMapper.insertSelective(setGroup);
        if(setSubList.size()==0)
            return 1;
        //获得插入id
        int lastId=setGroupMapper.selectByExample(setGroupExample).get(0).getId();
        for (SetSub setSub : setSubList) {
            setSub.setSetId(lastId);//设置组套id
        }
        setSubMapper.insertForeach(setSubList);
        return 1;
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
     * @return Boolean 0失败 1成功
     **/
    public int updateSetGroup(SetGroup setGroup, List<SetSub> setSubList){
        int num=setGroupMapper.updateByPrimaryKeySelective(setGroup);
        if(num==0)
            return num;
        SetSubExample setSubExample = new SetSubExample();
        SetSubExample.Criteria criteria = setSubExample.createCriteria();
        criteria.andSetIdEqualTo(setGroup.getId());
        setSubMapper.deleteByExample(setSubExample);
        if(setSubList.size()==0)
            return num;
        for (SetSub setSub : setSubList) {
            setSub.setSetId(setGroup.getId());
        }
        setSubMapper.insertForeach(setSubList);
        return num;
    }

    /*
     * @Description （删除）将组套设为无效状态
     * @Param [setGroup]
     * @return Boolean
     **/
    public int cancelSetGroup(int setId){
        SetGroup setGroup = new SetGroup();
        setGroup.setId(setId);
        setGroup.setStatus("2");
        return setGroupMapper.updateByPrimaryKeySelective(setGroup);
    }

    /*
     * @Description 搜索指定组套列，状态为有效
     * @Param [key为空获得全部,搜索名称，编码，创建人]
     * @return java.util.List<edu.neu.medical.hospital.bean.MedrecTemplate>
     **/
    public List<SetGroup> searchSetGroupList(String key){
        return setGroupMapper.searchSetGroup(type,category, belongId, '1', key);
    }

    /*
     * @Description 根据组套id获得组套
     * @Param [setId]
     * @return edu.neu.medical.hospital.bean.SetGroup
     **/
    public SetGroup getSetGroupById(int setId) {
        return setGroupMapper.selectByPrimaryKey(setId);
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
