package com.neusoft.ssm.service;

import com.neusoft.ssm.bean.CommonOption;
import com.neusoft.ssm.bean.Drugs;
import com.neusoft.ssm.bean.Prescription;
import com.neusoft.ssm.bean.PrescriptionDetail;

import java.util.List;

public interface ApplyForPrescriptionService {
    char type=0;
    void setType(char type);
    List<Prescription> getPrescriptionListById(int medicalRecordInfoId);
    List<Drugs> getDrugsListByList(List<PrescriptionDetail> prescriptionDetailList);
    List<PrescriptionDetail> getPrescriptionDetailListById(int prescriptionId);
    int setPrescriptionAndDetailList(Prescription prescription,List<PrescriptionDetail> prescriptionDetailList);
    List<Drugs> getCommonDrugsList(List<CommonOption> commonOptionList);
    List<PrescriptionDetail> initePrescriptionDetailList(int[] drugsId,String[] usageMethod,Double[] consumption,char[] frequent,int[] days,int[] amount,String[] entrustment);
    int canclePrescription(char method,int prescriptionId);
}
