package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.*;

import java.util.List;

public interface ApplyForFmeditemService {
    char type=0;
    void setType(char type);
    List<VisitItem> getVisitItemListById(int medicalRecordInfoId);
    List<VisitItemDetail> getVisitItemDetailListById(int visitUtemId);
    List<Fmeditem> getFmeditemListByList(List<VisitItemDetail> visitItemDetailList);
    Boolean setVisitItemAndDetailList(VisitItem visitItem, List<VisitItemDetail> visitItemDetailList);
    List<Fmeditem> getCommonFmeditemList(List<CommonOption> commonOptionList);
    int deleteCommonFmeditem(int doctorId,int fmeditemId);
    Boolean addCommonFmeditem(int doctorId,VisitItemDetail visitItemDetail);
    VisitItemResult getVisitItemResult(VisitItemDetail visitItemDetail);
}