package com.neusoft.ssm.service;

import com.neusoft.ssm.bean.SetGroup;
import com.neusoft.ssm.bean.SetSub;

import java.util.List;

public interface SetManageService {
    char type=0;
    char category=0;
    int belongId=0;
    void setType(char type);
    void setCategory(char category);
    void setBelongId(int belongId);
    int addSetAndSub(SetGroup set, List<SetSub> setSubList);
    Boolean judgeControlSetGroup(int userId,SetGroup setGroup);
    int updateSetGroup(SetGroup setGroup, List<SetSub> setSubList);
    int cancelSetGroup(int setId);
    List<SetGroup> searchSetGroupList(String key);
    List<SetSub> getSetSubListById(int setId);
    List<Object> getSubInfoList(List<SetSub> setSubList);
    SetGroup getSetGroupById(int setId);
    List<SetSub> initeSetSubList(int[] fmeitemId,String[] setSubEntrust);
}
