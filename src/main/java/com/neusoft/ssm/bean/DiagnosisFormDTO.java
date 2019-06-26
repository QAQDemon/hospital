package com.neusoft.ssm.bean;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisFormDTO {
    private List<Diagnosis> xDiagnosisList;//第一个-1多余
    private List<Diagnosis> zDiagnosisList;//第一个-1多余
    private int[] isNewMajorDiagnosisCheckded;//-1多余可以不删除
    private int[] isNewSuspectChecked;

    /*
     * @Description 初始化初诊列表，去掉冗余-1，移除空列，设置infoId、类型、分类、主诊和疑似
     * @Param [infoId -1则为null]
     * @return java.util.List<Diagnosis>
     **/
    public List<Diagnosis> getInitialDiagnosisList(int infoId){
        removeRuden();
        removeNullList();
        setAllType();
        if(infoId!=-1){
            setAllInfoId(infoId);
        }
        setAllCategory();
        List<Diagnosis> list=new ArrayList<>();
        list.addAll(xDiagnosisList);
        list.addAll(zDiagnosisList);
        setAllCheckded(list);
        return list;
    }
    private void removeRuden(){
        xDiagnosisList.remove(0);
        zDiagnosisList.remove(0);
    }
    private void setAllCheckded(List<Diagnosis> list){
        for(Diagnosis diagnosis:list){
            for(int diseaseId:isNewMajorDiagnosisCheckded){
                diagnosis.setIsNewMajorDiagnosis("1");
                if(diagnosis.getDiseaseId()==diseaseId) {
                    diagnosis.setIsNewMajorDiagnosis("2");
                    break;
                }
            }
            for(int diseaseId:isNewSuspectChecked){
                diagnosis.setIsNewSuspect("1");
                if(diagnosis.getDiseaseId()==diseaseId) {
                    diagnosis.setIsNewSuspect("2");
                    break;
                }
            }
        }
    }
    private void setAllCategory(){
        for(Diagnosis diagnosis:xDiagnosisList)
            diagnosis.setCategory("1");
        for(Diagnosis diagnosis:zDiagnosisList)
            diagnosis.setCategory("1");
    }
    private void setAllType(){
        for(Diagnosis diagnosis:xDiagnosisList)
            diagnosis.setType("1");
        for(Diagnosis diagnosis:zDiagnosisList)
            diagnosis.setType("2");
    }
    private void setAllInfoId(int infoId){
        for(Diagnosis diagnosis:xDiagnosisList)
            diagnosis.setMedicalRecordInfoId(infoId);
        for(Diagnosis diagnosis:zDiagnosisList)
            diagnosis.setMedicalRecordInfoId(infoId);
    }
    private void removeNullList(){
        xDiagnosisList.removeIf(diagnosis -> diagnosis.getDiseaseId() == null);
        zDiagnosisList.removeIf(diagnosis -> diagnosis.getDiseaseId() == null);
    }


    public List<Diagnosis> getzDiagnosisList() {
        return zDiagnosisList;
    }

    public void setzDiagnosisList(List<Diagnosis> zDiagnosisList) {
        this.zDiagnosisList = zDiagnosisList;
    }

    public List<Diagnosis> getxDiagnosisList() {
        return xDiagnosisList;
    }

    public void setxDiagnosisList(List<Diagnosis> xDiagnosisList) {
        this.xDiagnosisList = xDiagnosisList;
    }

    public int[] getIsNewMajorDiagnosisCheckded() {
        return isNewMajorDiagnosisCheckded;
    }

    public void setIsNewMajorDiagnosisCheckded(int[] isNewMajorDiagnosisCheckded) {
        this.isNewMajorDiagnosisCheckded = isNewMajorDiagnosisCheckded;
    }

    public int[] getIsNewSuspectChecked() {
        return isNewSuspectChecked;
    }

    public void setIsNewSuspectChecked(int[] isNewSuspectChecked) {
        this.isNewSuspectChecked = isNewSuspectChecked;
    }
}
