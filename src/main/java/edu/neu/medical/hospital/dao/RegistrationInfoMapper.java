package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.RegistrationInfo;

import java.util.List;

import edu.neu.medical.hospital.bean.RegistrationInfoExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface RegistrationInfoMapper {

    int countByExample(RegistrationInfoExample example);

    int deleteByExample(RegistrationInfoExample example);

    @Delete({
        "delete from registration_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into registration_info (id, medical_record_no, ",
        "patient_id, registration_category, ",
        "medical_category, registration_date, ",
        "see_doctor_date, depart_id, ",
        "doctor_id, registration_source, ",
        "settle_accounts_category, is_seen_docator, ",
        "status, expense, refund_amount)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordNo,jdbcType=INTEGER}, ",
        "#{patientId,jdbcType=VARCHAR}, #{registrationCategory,jdbcType=CHAR}, ",
        "#{medicalCategory,jdbcType=CHAR}, #{registrationDate,jdbcType=TIMESTAMP}, ",
        "#{seeDoctorDate,jdbcType=TIMESTAMP}, #{departId,jdbcType=INTEGER}, ",
        "#{doctorId,jdbcType=INTEGER}, #{registrationSource,jdbcType=CHAR}, ",
        "#{settleAccountsCategory,jdbcType=CHAR}, #{isSeenDocator,jdbcType=CHAR}, ",
        "#{status,jdbcType=CHAR}, #{expense,jdbcType=DECIMAL}, #{refundAmount,jdbcType=DECIMAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(RegistrationInfo record);

    int insertSelective(RegistrationInfo record);

    List<RegistrationInfo> selectByExample(RegistrationInfoExample example);

    @Select({
        "select",
        "id, medical_record_no, patient_id, registration_category, medical_category, ",
        "registration_date, see_doctor_date, depart_id, doctor_id, registration_source, ",
        "settle_accounts_category, is_seen_docator, status, expense, refund_amount",
        "from registration_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    RegistrationInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RegistrationInfo record, @Param("example") RegistrationInfoExample example);

    int updateByExample(@Param("record") RegistrationInfo record, @Param("example") RegistrationInfoExample example);

    int updateByPrimaryKeySelective(RegistrationInfo record);

    @Update({
        "update registration_info",
        "set medical_record_no = #{medicalRecordNo,jdbcType=INTEGER},",
          "patient_id = #{patientId,jdbcType=VARCHAR},",
          "registration_category = #{registrationCategory,jdbcType=CHAR},",
          "medical_category = #{medicalCategory,jdbcType=CHAR},",
          "registration_date = #{registrationDate,jdbcType=TIMESTAMP},",
          "see_doctor_date = #{seeDoctorDate,jdbcType=TIMESTAMP},",
          "depart_id = #{departId,jdbcType=INTEGER},",
          "doctor_id = #{doctorId,jdbcType=INTEGER},",
          "registration_source = #{registrationSource,jdbcType=CHAR},",
          "settle_accounts_category = #{settleAccountsCategory,jdbcType=CHAR},",
          "is_seen_docator = #{isSeenDocator,jdbcType=CHAR},",
          "status = #{status,jdbcType=CHAR},",
          "expense = #{expense,jdbcType=DECIMAL},",
          "refund_amount = #{refundAmount,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RegistrationInfo record);
}