package com.neusoft.ssm.service;

import com.neusoft.ssm.bean.*;
import com.neusoft.ssm.bean.*;

import java.util.List;

public interface ApplyForFmeditemService {
    char type=0;
    void setType(char type);
    List<VisitItem> getVisitItemListById(int medicalRecordInfoId);
    List<VisitItemDetail> getVisitItemDetailListById(int visitUtemId);
    List<Fmeditem> getFmeditemListByList(List<VisitItemDetail> visitItemDetailList);
    int setVisitItemAndDetailList(VisitItem visitItem, List<VisitItemDetail> visitItemDetailList);
    List<Fmeditem> getCommonFmeditemList(List<CommonOption> commonOptionList);
    VisitItemResult getVisitItemResult(int visitItemId, int fmeitemId);
    String[] getApplyForPeopleName(List<VisitItem> visitItems);
    List<VisitItemDetail> initeVisitItemDetailList(int[] fmeditemIds,String[] doctorEntrustments);
    int cancleVisitItem(char method,int visitItemId);
}
