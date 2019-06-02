package edu.neu.medical.hospital.service.impl;
import edu.neu.medical.hospital.bean.Patient;
import edu.neu.medical.hospital.dao.PatientMapper;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OutpatientDoctorWorkstationServiceImpl implements OutpatientDoctorWorkstationService {

    @Autowired
    private PatientMapper patientMapper;

    /*
     * @Description 将已诊或待诊病人列表传回，已诊中会去掉待诊
     * @Param [doctorId id为-1不查, dapartId id为-1不查, isSeenDocator 1：未看诊；2：已看诊, key搜索关键词]
     * @return java.util.List<edu.neu.medical.hospital.bean.Patient>
     **/
    public List<Patient> searchPatientList(int doctorId, int dapartId, char isSeenDocator, String key) {
        List<Patient> notSeenList;
        Integer intKey=-1;
        //关键词整数判断
        try{
            intKey=Integer.valueOf(key);
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

}
