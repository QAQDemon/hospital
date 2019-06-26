package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.Depart;
import com.neusoft.ssm.bean.DepartExample;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DepartMapper {
    int countByExample(DepartExample example);

    int deleteByExample(DepartExample example);

    @Delete({
        "delete from depart",
        "where id = #{id,jdbcType=SMALLINT}"
    })
    int deleteByPrimaryKey(Short id);

    @Insert({
        "insert into depart (id, DeptCode, ",
        "DeptName, DeptCategoryID, ",
        "DeptType)",
        "values (#{id,jdbcType=SMALLINT}, #{deptcode,jdbcType=VARCHAR}, ",
        "#{deptname,jdbcType=VARCHAR}, #{deptcategoryid,jdbcType=SMALLINT}, ",
        "#{depttype,jdbcType=SMALLINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Short.class)
    int insert(Depart record);

    int insertSelective(Depart record);

    List<Depart> selectByExample(DepartExample example);

    @Select({
        "select",
        "id, DeptCode, DeptName, DeptCategoryID, DeptType",
        "from depart",
        "where id = #{id,jdbcType=SMALLINT}"
    })
    @ResultMap("BaseResultMap")
    Depart selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") Depart record, @Param("example") DepartExample example);

    int updateByExample(@Param("record") Depart record, @Param("example") DepartExample example);

    int updateByPrimaryKeySelective(Depart record);

    @Update({
        "update depart",
        "set DeptCode = #{deptcode,jdbcType=VARCHAR},",
          "DeptName = #{deptname,jdbcType=VARCHAR},",
          "DeptCategoryID = #{deptcategoryid,jdbcType=SMALLINT},",
          "DeptType = #{depttype,jdbcType=SMALLINT}",
        "where id = #{id,jdbcType=SMALLINT}"
    })
    int updateByPrimaryKey(Depart record);
}