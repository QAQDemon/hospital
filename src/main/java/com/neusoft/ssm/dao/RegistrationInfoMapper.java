package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.RegistrationInfo;
import com.neusoft.ssm.bean.RegistrationInfoExample;
import java.util.List;
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
        "patient_name, patient_gender, ",
        "age, birth_date, ",
        "settle_accounts_category, registration_category, ",
        "medical_category, depart_id, ",
        "doctor_id, doctor_name, ",
        "registration_date, status, ",
        "registration_source, expense, ",
        "refund_amount, see_doctor_date, ",
        "is_seen_docator, idcard, ",
        "tollmanid, if_book)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordNo,jdbcType=INTEGER}, ",
        "#{patientName,jdbcType=VARCHAR}, #{patientGender,jdbcType=CHAR}, ",
        "#{age,jdbcType=INTEGER}, #{birthDate,jdbcType=TIMESTAMP}, ",
        "#{settleAccountsCategory,jdbcType=CHAR}, #{registrationCategory,jdbcType=CHAR}, ",
        "#{medicalCategory,jdbcType=CHAR}, #{departId,jdbcType=INTEGER}, ",
        "#{doctorId,jdbcType=INTEGER}, #{doctorName,jdbcType=VARCHAR}, ",
        "#{registrationDate,jdbcType=TIMESTAMP}, #{status,jdbcType=CHAR}, ",
        "#{registrationSource,jdbcType=CHAR}, #{expense,jdbcType=DECIMAL}, ",
        "#{refundAmount,jdbcType=DECIMAL}, #{seeDoctorDate,jdbcType=TIMESTAMP}, ",
        "#{isSeenDocator,jdbcType=CHAR}, #{idcard,jdbcType=VARCHAR}, ",
        "#{tollmanid,jdbcType=INTEGER}, #{ifBook,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(RegistrationInfo record);

    int insertSelective(RegistrationInfo record);

    List<RegistrationInfo> selectByExample(RegistrationInfoExample example);

    @Select({
        "select",
        "id, medical_record_no, patient_name, patient_gender, age, birth_date, settle_accounts_category, ",
        "registration_category, medical_category, depart_id, doctor_id, doctor_name, ",
        "registration_date, status, registration_source, expense, refund_amount, see_doctor_date, ",
        "is_seen_docator, idcard, tollmanid, if_book",
        "from registration_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("RegistrationInfoBaseResultMap")
    RegistrationInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RegistrationInfo record, @Param("example") RegistrationInfoExample example);

    int updateByExample(@Param("record") RegistrationInfo record, @Param("example") RegistrationInfoExample example);

    int updateByPrimaryKeySelective(RegistrationInfo record);

    @Update({
        "update registration_info",
        "set medical_record_no = #{medicalRecordNo,jdbcType=INTEGER},",
          "patient_name = #{patientName,jdbcType=VARCHAR},",
          "patient_gender = #{patientGender,jdbcType=CHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "birth_date = #{birthDate,jdbcType=TIMESTAMP},",
          "settle_accounts_category = #{settleAccountsCategory,jdbcType=CHAR},",
          "registration_category = #{registrationCategory,jdbcType=CHAR},",
          "medical_category = #{medicalCategory,jdbcType=CHAR},",
          "depart_id = #{departId,jdbcType=INTEGER},",
          "doctor_id = #{doctorId,jdbcType=INTEGER},",
          "doctor_name = #{doctorName,jdbcType=VARCHAR},",
          "registration_date = #{registrationDate,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=CHAR},",
          "registration_source = #{registrationSource,jdbcType=CHAR},",
          "expense = #{expense,jdbcType=DECIMAL},",
          "refund_amount = #{refundAmount,jdbcType=DECIMAL},",
          "see_doctor_date = #{seeDoctorDate,jdbcType=TIMESTAMP},",
          "is_seen_docator = #{isSeenDocator,jdbcType=CHAR},",
          "idcard = #{idcard,jdbcType=VARCHAR},",
          "tollmanid = #{tollmanid,jdbcType=INTEGER},",
          "if_book = #{ifBook,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RegistrationInfo record);
}