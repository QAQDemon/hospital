package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.PrescriptionDetail;
import com.neusoft.ssm.bean.PrescriptionDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface PrescriptionDetailMapper {



    /*
     * @Description 将列表插入，使用自增id
     * @Param [list]
     * @return int
     **/
    int insertForeach(@Param("list") List<PrescriptionDetail> list);




    int countByExample(PrescriptionDetailExample example);

    int deleteByExample(PrescriptionDetailExample example);

    @Delete({
        "delete from prescription_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into prescription_detail (id, prescription_id, ",
        "drug_id, usage_method, ",
        "consumption, frequent, ",
        "days, amount, entrustment, ",
        "is_return_medicine, back_number)",
        "values (#{id,jdbcType=INTEGER}, #{prescriptionId,jdbcType=INTEGER}, ",
        "#{drugId,jdbcType=INTEGER}, #{usageMethod,jdbcType=VARCHAR}, ",
        "#{consumption,jdbcType=DECIMAL}, #{frequent,jdbcType=CHAR}, ",
        "#{days,jdbcType=INTEGER}, #{amount,jdbcType=INTEGER}, #{entrustment,jdbcType=VARCHAR}, ",
        "#{isReturnMedicine,jdbcType=CHAR}, #{backNumber,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(PrescriptionDetail record);

    int insertSelective(PrescriptionDetail record);

    List<PrescriptionDetail> selectByExample(PrescriptionDetailExample example);

    @Select({
        "select",
        "id, prescription_id, drug_id, usage_method, consumption, frequent, days, amount, ",
        "entrustment, is_return_medicine, back_number",
        "from prescription_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("PrescriptionDetailBaseResultMap")
    PrescriptionDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PrescriptionDetail record, @Param("example") PrescriptionDetailExample example);

    int updateByExample(@Param("record") PrescriptionDetail record, @Param("example") PrescriptionDetailExample example);

    int updateByPrimaryKeySelective(PrescriptionDetail record);

    @Update({
        "update prescription_detail",
        "set prescription_id = #{prescriptionId,jdbcType=INTEGER},",
          "drug_id = #{drugId,jdbcType=INTEGER},",
          "usage_method = #{usageMethod,jdbcType=VARCHAR},",
          "consumption = #{consumption,jdbcType=DECIMAL},",
          "frequent = #{frequent,jdbcType=CHAR},",
          "days = #{days,jdbcType=INTEGER},",
          "amount = #{amount,jdbcType=INTEGER},",
          "entrustment = #{entrustment,jdbcType=VARCHAR},",
          "is_return_medicine = #{isReturnMedicine,jdbcType=CHAR},",
          "back_number = #{backNumber,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PrescriptionDetail record);
}