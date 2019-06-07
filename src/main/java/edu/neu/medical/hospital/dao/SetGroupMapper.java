package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.SetGroup;
import edu.neu.medical.hospital.bean.SetGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface SetGroupMapper {
    int countByExample(SetGroupExample example);

    int deleteByExample(SetGroupExample example);

    @Delete({
        "delete from set_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into set_group (id, set_code, ",
        "business_classify, set_name, ",
        "use_scope, belong_id, ",
        "build_date, creater_id, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{setCode,jdbcType=VARCHAR}, ",
        "#{businessClassify,jdbcType=CHAR}, #{setName,jdbcType=VARCHAR}, ",
        "#{useScope,jdbcType=CHAR}, #{belongId,jdbcType=INTEGER}, ",
        "#{buildDate,jdbcType=TIMESTAMP}, #{createrId,jdbcType=INTEGER}, ",
        "#{status,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(SetGroup record);

    int insertSelective(SetGroup record);

    List<SetGroup> selectByExample(SetGroupExample example);

    @Select({
        "select",
        "id, set_code, business_classify, set_name, use_scope, belong_id, build_date, ",
        "creater_id, status",
        "from set_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SetGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SetGroup record, @Param("example") SetGroupExample example);

    int updateByExample(@Param("record") SetGroup record, @Param("example") SetGroupExample example);

    int updateByPrimaryKeySelective(SetGroup record);

    @Update({
        "update set_group",
        "set set_code = #{setCode,jdbcType=VARCHAR},",
          "business_classify = #{businessClassify,jdbcType=CHAR},",
          "set_name = #{setName,jdbcType=VARCHAR},",
          "use_scope = #{useScope,jdbcType=CHAR},",
          "belong_id = #{belongId,jdbcType=INTEGER},",
          "build_date = #{buildDate,jdbcType=TIMESTAMP},",
          "creater_id = #{createrId,jdbcType=INTEGER},",
          "status = #{status,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SetGroup record);
}