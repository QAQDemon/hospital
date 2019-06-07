package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.SetGroup;
import edu.neu.medical.hospital.bean.SetSub;

import java.util.List;

public interface SetManageService {
    char type=0;
    char category=0;
    int belongId=0;
    void setType(char type);
    void setCategory(char category);
    void setBelongId(int belongId);
    Boolean addSetAndSub(SetGroup set, List<SetSub> setSubList);
}
