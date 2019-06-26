package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.CommonOption;
import com.neusoft.ssm.bean.CommonOptionExample;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface CommonOptionMapper {
    int countByExample(CommonOptionExample example);

    int deleteByExample(CommonOptionExample example);

    @Delete({
        "delete from common_option",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into common_option (id, doctor_id, ",
        "belong_id, type)",
        "values (#{id,jdbcType=INTEGER}, #{doctorId,jdbcType=INTEGER}, ",
        "#{belongId,jdbcType=INTEGER}, #{type,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(CommonOption record);

    int insertSelective(CommonOption record);

    List<CommonOption> selectByExample(CommonOptionExample example);

    @Select({
        "select",
        "id, doctor_id, belong_id, type",
        "from common_option",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CommonOption selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommonOption record, @Param("example") CommonOptionExample example);

    int updateByExample(@Param("record") CommonOption record, @Param("example") CommonOptionExample example);

    int updateByPrimaryKeySelective(CommonOption record);

    @Update({
        "update common_option",
        "set doctor_id = #{doctorId,jdbcType=INTEGER},",
          "belong_id = #{belongId,jdbcType=INTEGER},",
          "type = #{type,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CommonOption record);
}