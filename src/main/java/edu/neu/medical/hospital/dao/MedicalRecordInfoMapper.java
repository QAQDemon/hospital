package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.MedicalRecordInfo;

import java.util.List;

import edu.neu.medical.hospital.bean.MedicalRecordInfoExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface MedicalRecordInfoMapper {

    /*
     * @Description 根据病历号找到病历信息 未看诊：状态是暂存或完成初诊，需唯一且优先；已看诊：状态是诊毕且显示最近
     * @Param [isSeen 1：未看诊；2：已看诊, medicalRecordId]
     * @return edu.neu.medical.hospital.bean.MedicalRecordInfo
     **/
    MedicalRecordInfo getMedicalRecordInfoById(@Param("isSeen") char isSeen,@Param("medicalRecordNo") int medicalRecordNo);

    /*
     * @Description 获得刚插入的病单号
     * @Param [medicalRecordNo]
     * @return int
     **/
    int getLastId(@Param("medicalRecordNo")int medicalRecordNo);



    int countByExample(MedicalRecordInfoExample example);

    int deleteByExample(MedicalRecordInfoExample example);

    @Delete({
        "delete from medical_record_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into medical_record_info (id, medical_record_no, ",
        "patient_id, doctor_id, ",
        "depart_id, chief_complaint, ",
        "current_medical_history, current_treatment_situation, ",
        "past_history, allergies_history, ",
        "physical_examination, status, ",
        "visit_time)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordNo,jdbcType=INTEGER}, ",
        "#{patientId,jdbcType=INTEGER}, #{doctorId,jdbcType=INTEGER}, ",
        "#{departId,jdbcType=INTEGER}, #{chiefComplaint,jdbcType=VARCHAR}, ",
        "#{currentMedicalHistory,jdbcType=VARCHAR}, #{currentTreatmentSituation,jdbcType=VARCHAR}, ",
        "#{pastHistory,jdbcType=VARCHAR}, #{allergiesHistory,jdbcType=VARCHAR}, ",
        "#{physicalExamination,jdbcType=VARCHAR}, #{status,jdbcType=CHAR}, ",
        "#{visitTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(MedicalRecordInfo record);

    int insertSelective(MedicalRecordInfo record);

    List<MedicalRecordInfo> selectByExample(MedicalRecordInfoExample example);

    @Select({
        "select",
        "id, medical_record_no, patient_id, doctor_id, depart_id, chief_complaint, current_medical_history, ",
        "current_treatment_situation, past_history, allergies_history, physical_examination, ",
        "status, visit_time",
        "from medical_record_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    MedicalRecordInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MedicalRecordInfo record, @Param("example") MedicalRecordInfoExample example);

    int updateByExample(@Param("record") MedicalRecordInfo record, @Param("example") MedicalRecordInfoExample example);

    int updateByPrimaryKeySelective(MedicalRecordInfo record);

    @Update({
        "update medical_record_info",
        "set medical_record_no = #{medicalRecordNo,jdbcType=INTEGER},",
          "patient_id = #{patientId,jdbcType=INTEGER},",
          "doctor_id = #{doctorId,jdbcType=INTEGER},",
          "depart_id = #{departId,jdbcType=INTEGER},",
          "chief_complaint = #{chiefComplaint,jdbcType=VARCHAR},",
          "current_medical_history = #{currentMedicalHistory,jdbcType=VARCHAR},",
          "current_treatment_situation = #{currentTreatmentSituation,jdbcType=VARCHAR},",
          "past_history = #{pastHistory,jdbcType=VARCHAR},",
          "allergies_history = #{allergiesHistory,jdbcType=VARCHAR},",
          "physical_examination = #{physicalExamination,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=CHAR},",
          "visit_time = #{visitTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MedicalRecordInfo record);
}