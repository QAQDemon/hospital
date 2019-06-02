package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.Diagnosis;

import java.util.List;

import edu.neu.medical.hospital.bean.DiagnosisExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DiagnosisMapper {
    int countByExample(DiagnosisExample example);

    int deleteByExample(DiagnosisExample example);

    @Delete({
        "delete from diagnosis",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into diagnosis (id, medical_record_info_id, ",
        "disease_id, type, is_new_major_diagnosis, ",
        "is_new_suspect, is_final_diagnosis, ",
        "date_of_onset)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordInfoId,jdbcType=INTEGER}, ",
        "#{diseaseId,jdbcType=INTEGER}, #{type,jdbcType=CHAR}, #{isNewMajorDiagnosis,jdbcType=CHAR}, ",
        "#{isNewSuspect,jdbcType=CHAR}, #{isFinalDiagnosis,jdbcType=CHAR}, ",
        "#{dateOfOnset,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(Diagnosis record);

    int insertSelective(Diagnosis record);

    List<Diagnosis> selectByExample(DiagnosisExample example);

    @Select({
        "select",
        "id, medical_record_info_id, disease_id, type, is_new_major_diagnosis, is_new_suspect, ",
        "is_final_diagnosis, date_of_onset",
        "from diagnosis",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Diagnosis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Diagnosis record, @Param("example") DiagnosisExample example);

    int updateByExample(@Param("record") Diagnosis record, @Param("example") DiagnosisExample example);

    int updateByPrimaryKeySelective(Diagnosis record);

    @Update({
        "update diagnosis",
        "set medical_record_info_id = #{medicalRecordInfoId,jdbcType=INTEGER},",
          "disease_id = #{diseaseId,jdbcType=INTEGER},",
          "type = #{type,jdbcType=CHAR},",
          "is_new_major_diagnosis = #{isNewMajorDiagnosis,jdbcType=CHAR},",
          "is_new_suspect = #{isNewSuspect,jdbcType=CHAR},",
          "is_final_diagnosis = #{isFinalDiagnosis,jdbcType=CHAR},",
          "date_of_onset = #{dateOfOnset,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Diagnosis record);
}