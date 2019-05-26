package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.RegistrationInfo;
import edu.neu.medical.hospital.bean.RegistrationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
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
        "patient_name, gender, ",
        "age, birthday, ",
        "registration_category, medical_category, ",
        "identity_card_no, family_address, ",
        "registration_date, see_doctor_date, ",
        "department_id, doctor_id, ",
        "registration_source, settle_accounts_category, ",
        "is_seen_docator, status, ",
        "expense, reserve1, ",
        "reserve2, reserve3)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordNo,jdbcType=CHAR}, ",
        "#{patientName,jdbcType=VARCHAR}, #{gender,jdbcType=CHAR}, ",
        "#{age,jdbcType=INTEGER}, #{birthday,jdbcType=TIMESTAMP}, ",
        "#{registrationCategory,jdbcType=CHAR}, #{medicalCategory,jdbcType=CHAR}, ",
        "#{identityCardNo,jdbcType=CHAR}, #{familyAddress,jdbcType=VARCHAR}, ",
        "#{registrationDate,jdbcType=TIMESTAMP}, #{seeDoctorDate,jdbcType=TIMESTAMP}, ",
        "#{departmentId,jdbcType=INTEGER}, #{doctorId,jdbcType=INTEGER}, ",
        "#{registrationSource,jdbcType=CHAR}, #{settleAccountsCategory,jdbcType=CHAR}, ",
        "#{isSeenDocator,jdbcType=CHAR}, #{status,jdbcType=CHAR}, ",
        "#{expense,jdbcType=DECIMAL}, #{reserve1,jdbcType=VARCHAR}, ",
        "#{reserve2,jdbcType=VARCHAR}, #{reserve3,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(RegistrationInfo record);

    int insertSelective(RegistrationInfo record);

    List<RegistrationInfo> selectByExample(RegistrationInfoExample example);

    @Select({
        "select",
        "id, medical_record_no, patient_name, gender, age, birthday, registration_category, ",
        "medical_category, identity_card_no, family_address, registration_date, see_doctor_date, ",
        "department_id, doctor_id, registration_source, settle_accounts_category, is_seen_docator, ",
        "status, expense, reserve1, reserve2, reserve3",
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
        "set medical_record_no = #{medicalRecordNo,jdbcType=CHAR},",
          "patient_name = #{patientName,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=CHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "birthday = #{birthday,jdbcType=TIMESTAMP},",
          "registration_category = #{registrationCategory,jdbcType=CHAR},",
          "medical_category = #{medicalCategory,jdbcType=CHAR},",
          "identity_card_no = #{identityCardNo,jdbcType=CHAR},",
          "family_address = #{familyAddress,jdbcType=VARCHAR},",
          "registration_date = #{registrationDate,jdbcType=TIMESTAMP},",
          "see_doctor_date = #{seeDoctorDate,jdbcType=TIMESTAMP},",
          "department_id = #{departmentId,jdbcType=INTEGER},",
          "doctor_id = #{doctorId,jdbcType=INTEGER},",
          "registration_source = #{registrationSource,jdbcType=CHAR},",
          "settle_accounts_category = #{settleAccountsCategory,jdbcType=CHAR},",
          "is_seen_docator = #{isSeenDocator,jdbcType=CHAR},",
          "status = #{status,jdbcType=CHAR},",
          "expense = #{expense,jdbcType=DECIMAL},",
          "reserve1 = #{reserve1,jdbcType=VARCHAR},",
          "reserve2 = #{reserve2,jdbcType=VARCHAR},",
          "reserve3 = #{reserve3,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RegistrationInfo record);
}