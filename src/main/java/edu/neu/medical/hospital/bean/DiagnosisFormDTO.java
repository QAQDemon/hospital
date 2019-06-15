package edu.neu.medical.hospital.bean;

import java.util.List;

public class DiagnosisFormDTO {
    private List<Diagnosis> xDiagnosisList;
    private List<Diagnosis> zDiagnosisList;
    private int[] isNewMajorDiagnosisCheckded;
    private int[] isNewSuspectChecked;

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
