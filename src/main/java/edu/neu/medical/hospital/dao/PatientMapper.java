package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.Patient;

import java.util.List;

import edu.neu.medical.hospital.bean.PatientExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface PatientMapper {

    /*
     * @Description 根据医生科室id和关键词，搜索符合的名字的前几位或绝对符合的病历号，将已诊或待诊病人列表传回，联表病人和挂号表
     * @Param [doctorId, dapartId,isSeenDocator, key,intKey]
     * @return java.util.List<edu.neu.medical.hospital.bean.Patient>
     **/
    List<Patient> searchPatientList(@Param("doctorId")int doctorId,@Param("departId") int departId,
                                    @Param("isSeenDocator") char isSeenDocator, @Param("key") String key,
                                    @Param("intKey") int intKey);






    int countByExample(PatientExample example);

    int deleteByExample(PatientExample example);

    @Delete({
        "delete from patient",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into patient (id, medical_record_no, ",
        "name, age, gender, ",
        "birthday, identity_card_no, ",
        "family_address)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordNo,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{gender,jdbcType=CHAR}, ",
        "#{birthday,jdbcType=DATE}, #{identityCardNo,jdbcType=CHAR}, ",
        "#{familyAddress,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(Patient record);

    int insertSelective(Patient record);

    List<Patient> selectByExample(PatientExample example);

    @Select({
        "select",
        "id, medical_record_no, name, age, gender, birthday, identity_card_no, family_address",
        "from patient",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Patient selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Patient record, @Param("example") PatientExample example);

    int updateByExample(@Param("record") Patient record, @Param("example") PatientExample example);

    int updateByPrimaryKeySelective(Patient record);

    @Update({
        "update patient",
        "set medical_record_no = #{medicalRecordNo,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "gender = #{gender,jdbcType=CHAR},",
          "birthday = #{birthday,jdbcType=DATE},",
          "identity_card_no = #{identityCardNo,jdbcType=CHAR},",
          "family_address = #{familyAddress,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Patient record);
}