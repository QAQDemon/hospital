package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.Fmeditem;
import com.neusoft.ssm.bean.FmeditemExample;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface FmeditemMapper {
    int countByExample(FmeditemExample example);

    int deleteByExample(FmeditemExample example);

    @Delete({
        "delete from fmeditem",
        "where Id = #{id,jdbcType=SMALLINT}"
    })
    int deleteByPrimaryKey(Short id);

    @Insert({
        "insert into fmeditem (Id, ItemCode, ",
        "ItemName, Format, ",
        "Price, ExpClassID, ",
        "DeptID, MnemonicCode, ",
        "RecordType, CreationDate)",
        "values (#{id,jdbcType=SMALLINT}, #{itemcode,jdbcType=VARCHAR}, ",
        "#{itemname,jdbcType=VARCHAR}, #{format,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=REAL}, #{expclassid,jdbcType=SMALLINT}, ",
        "#{deptid,jdbcType=SMALLINT}, #{mnemoniccode,jdbcType=VARCHAR}, ",
        "#{recordtype,jdbcType=SMALLINT}, #{creationdate,jdbcType=DATE})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Short.class)
    int insert(Fmeditem record);

    int insertSelective(Fmeditem record);

    List<Fmeditem> selectByExample(FmeditemExample example);

    @Select({
        "select",
        "Id, ItemCode, ItemName, Format, Price, ExpClassID, DeptID, MnemonicCode, RecordType, ",
        "CreationDate",
        "from fmeditem",
        "where Id = #{id,jdbcType=SMALLINT}"
    })
    @ResultMap("BaseResultMap")
    Fmeditem selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") Fmeditem record, @Param("example") FmeditemExample example);

    int updateByExample(@Param("record") Fmeditem record, @Param("example") FmeditemExample example);

    int updateByPrimaryKeySelective(Fmeditem record);

    @Update({
        "update fmeditem",
        "set ItemCode = #{itemcode,jdbcType=VARCHAR},",
          "ItemName = #{itemname,jdbcType=VARCHAR},",
          "Format = #{format,jdbcType=VARCHAR},",
          "Price = #{price,jdbcType=REAL},",
          "ExpClassID = #{expclassid,jdbcType=SMALLINT},",
          "DeptID = #{deptid,jdbcType=SMALLINT},",
          "MnemonicCode = #{mnemoniccode,jdbcType=VARCHAR},",
          "RecordType = #{recordtype,jdbcType=SMALLINT},",
          "CreationDate = #{creationdate,jdbcType=DATE}",
        "where Id = #{id,jdbcType=SMALLINT}"
    })
    int updateByPrimaryKey(Fmeditem record);
}