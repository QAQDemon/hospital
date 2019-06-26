package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.Prescription;
import com.neusoft.ssm.bean.PrescriptionExample;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface PrescriptionMapper {

    /*
     * @Description 根据病历信息获得刚插入的申请单号
     * @Param [medicalRecordInfoId]
     * @return int
     **/
    int getLastId(@Param("medicalRecordInfoId")int medicalRecordInfoId);






    int countByExample(PrescriptionExample example);

    int deleteByExample(PrescriptionExample example);

    @Delete({
        "delete from prescription",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into prescription (id, medical_record_info_id, ",
        "type, prescription_type, ",
        "prescription_name, build_time, ",
        "prescription_in_amount, prescription_out_amount, ",
        "status, fee_status, execution_status)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordInfoId,jdbcType=INTEGER}, ",
        "#{type,jdbcType=CHAR}, #{prescriptionType,jdbcType=CHAR}, ",
        "#{prescriptionName,jdbcType=VARCHAR}, #{buildTime,jdbcType=TIMESTAMP}, ",
        "#{prescriptionInAmount,jdbcType=DECIMAL}, #{prescriptionOutAmount,jdbcType=DECIMAL}, ",
        "#{status,jdbcType=CHAR}, #{feeStatus,jdbcType=CHAR}, #{executionStatus,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(Prescription record);

    int insertSelective(Prescription record);

    List<Prescription> selectByExample(PrescriptionExample example);

    @Select({
        "select",
        "id, medical_record_info_id, type, prescription_type, prescription_name, build_time, ",
        "prescription_in_amount, prescription_out_amount, status, fee_status, execution_status",
        "from prescription",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Prescription selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Prescription record, @Param("example") PrescriptionExample example);

    int updateByExample(@Param("record") Prescription record, @Param("example") PrescriptionExample example);

    int updateByPrimaryKeySelective(Prescription record);

    @Update({
        "update prescription",
        "set medical_record_info_id = #{medicalRecordInfoId,jdbcType=INTEGER},",
          "type = #{type,jdbcType=CHAR},",
          "prescription_type = #{prescriptionType,jdbcType=CHAR},",
          "prescription_name = #{prescriptionName,jdbcType=VARCHAR},",
          "build_time = #{buildTime,jdbcType=TIMESTAMP},",
          "prescription_in_amount = #{prescriptionInAmount,jdbcType=DECIMAL},",
          "prescription_out_amount = #{prescriptionOutAmount,jdbcType=DECIMAL},",
          "status = #{status,jdbcType=CHAR},",
          "fee_status = #{feeStatus,jdbcType=CHAR},",
          "execution_status = #{executionStatus,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Prescription record);
}