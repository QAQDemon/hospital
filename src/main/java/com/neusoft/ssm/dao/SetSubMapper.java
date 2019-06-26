package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.SetSub;
import com.neusoft.ssm.bean.SetSubExample;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface SetSubMapper {

    /*
     * @Description 将列表插入，使用自增id
     * @Param [list]
     * @return int
     **/
    int insertForeach(@Param("list") List<SetSub> list);







    int countByExample(SetSubExample example);

    int deleteByExample(SetSubExample example);

    @Delete({
        "delete from set_sub",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into set_sub (id, set_id, ",
        "response_id, entrust)",
        "values (#{id,jdbcType=INTEGER}, #{setId,jdbcType=INTEGER}, ",
        "#{responseId,jdbcType=INTEGER}, #{entrust,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(SetSub record);

    int insertSelective(SetSub record);

    List<SetSub> selectByExample(SetSubExample example);

    @Select({
        "select",
        "id, set_id, response_id, entrust",
        "from set_sub",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SetSub selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SetSub record, @Param("example") SetSubExample example);

    int updateByExample(@Param("record") SetSub record, @Param("example") SetSubExample example);

    int updateByPrimaryKeySelective(SetSub record);

    @Update({
        "update set_sub",
        "set set_id = #{setId,jdbcType=INTEGER},",
          "response_id = #{responseId,jdbcType=INTEGER},",
          "entrust = #{entrust,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SetSub record);
}