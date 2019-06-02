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
//    @Autowired
//    RegistrationInfoMapper registrationInfoMapper;



    /*
     * @Description 将已诊或待诊病人列表传回，关键词是前几位模糊搜索//TODO
     * @Param [doctorId id为-1不查, dapartId id为-1不查, isSeenDocator 1：未看诊；2：已看诊, key搜索关键词]
     * @return java.util.List<edu.neu.medical.hospital.bean.Patient>
     **/
    public List<Patient> searchPatientList(int doctorId, int dapartId, char isSeenDocator, String key) {
        try{
            Integer intKey=Integer.valueOf(key);
            return patientMapper.searchPatientList(doctorId,  dapartId,  isSeenDocator,  key,intKey);
        }catch (Exception e){
            //key不是整数
            return patientMapper.searchPatientList(doctorId,  dapartId,  isSeenDocator,  key,-1);
        }

    }
}
