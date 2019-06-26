package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.VisitItem;
import com.neusoft.ssm.bean.VisitItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface VisitItemMapper {

    /*
     * @Description 根据病历信息获得刚插入的申请单号
     * @Param [medicalRecordInfoId]
     * @return int
     **/
    int getLastId(@Param("medicalRecordInfoId")int medicalRecordInfoId);





    int countByExample(VisitItemExample example);

    int deleteByExample(VisitItemExample example);

    @Delete({
        "delete from visit_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into visit_item (id, medical_record_info_id, ",
        "type, purpose_requirement, ",
        "status, application_time, ",
        "application_doctor_id, fee_status, ",
        "execution_status, fee)",
        "values (#{id,jdbcType=INTEGER}, #{medicalRecordInfoId,jdbcType=INTEGER}, ",
        "#{type,jdbcType=CHAR}, #{purposeRequirement,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR}, #{applicationTime,jdbcType=TIMESTAMP}, ",
        "#{applicationDoctorId,jdbcType=INTEGER}, #{feeStatus,jdbcType=CHAR}, ",
        "#{executionStatus,jdbcType=CHAR}, #{fee,jdbcType=DECIMAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(VisitItem record);

    int insertSelective(VisitItem record);

    List<VisitItem> selectByExample(VisitItemExample example);

    @Select({
        "select",
        "id, medical_record_info_id, type, purpose_requirement, status, application_time, ",
        "application_doctor_id, fee_status, execution_status, fee",
        "from visit_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    VisitItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitItem record, @Param("example") VisitItemExample example);

    int updateByExample(@Param("record") VisitItem record, @Param("example") VisitItemExample example);

    int updateByPrimaryKeySelective(VisitItem record);

    @Update({
        "update visit_item",
        "set medical_record_info_id = #{medicalRecordInfoId,jdbcType=INTEGER},",
          "type = #{type,jdbcType=CHAR},",
          "purpose_requirement = #{purposeRequirement,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=CHAR},",
          "application_time = #{applicationTime,jdbcType=TIMESTAMP},",
          "application_doctor_id = #{applicationDoctorId,jdbcType=INTEGER},",
          "fee_status = #{feeStatus,jdbcType=CHAR},",
          "execution_status = #{executionStatus,jdbcType=CHAR},",
          "fee = #{fee,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VisitItem record);
}