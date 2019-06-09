package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.Drugs;
import edu.neu.medical.hospital.bean.DrugsExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DrugsMapper {
    int countByExample(DrugsExample example);

    int deleteByExample(DrugsExample example);

    @Delete({
        "delete from drugs",
        "where ID = #{id,jdbcType=SMALLINT}"
    })
    int deleteByPrimaryKey(Short id);

    @Insert({
        "insert into drugs (ID, DrugsCode, ",
        "DrugsName, DrugsFormat, ",
        "DrugsUnit, Manufacturer, ",
        "DrugsDosageID, DrugsTypeID, ",
        "DrugsPrice, MnemonicCode, ",
        "CreationDate)",
        "values (#{id,jdbcType=SMALLINT}, #{drugscode,jdbcType=VARCHAR}, ",
        "#{drugsname,jdbcType=VARCHAR}, #{drugsformat,jdbcType=VARCHAR}, ",
        "#{drugsunit,jdbcType=VARCHAR}, #{manufacturer,jdbcType=VARCHAR}, ",
        "#{drugsdosageid,jdbcType=SMALLINT}, #{drugstypeid,jdbcType=SMALLINT}, ",
        "#{drugsprice,jdbcType=REAL}, #{mnemoniccode,jdbcType=VARCHAR}, ",
        "#{creationdate,jdbcType=DATE})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Short.class)
    int insert(Drugs record);

    int insertSelective(Drugs record);

    List<Drugs> selectByExample(DrugsExample example);

    @Select({
        "select",
        "ID, DrugsCode, DrugsName, DrugsFormat, DrugsUnit, Manufacturer, DrugsDosageID, ",
        "DrugsTypeID, DrugsPrice, MnemonicCode, CreationDate",
        "from drugs",
        "where ID = #{id,jdbcType=SMALLINT}"
    })
    @ResultMap("BaseResultMap")
    Drugs selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") Drugs record, @Param("example") DrugsExample example);

    int updateByExample(@Param("record") Drugs record, @Param("example") DrugsExample example);

    int updateByPrimaryKeySelective(Drugs record);

    @Update({
        "update drugs",
        "set DrugsCode = #{drugscode,jdbcType=VARCHAR},",
          "DrugsName = #{drugsname,jdbcType=VARCHAR},",
          "DrugsFormat = #{drugsformat,jdbcType=VARCHAR},",
          "DrugsUnit = #{drugsunit,jdbcType=VARCHAR},",
          "Manufacturer = #{manufacturer,jdbcType=VARCHAR},",
          "DrugsDosageID = #{drugsdosageid,jdbcType=SMALLINT},",
          "DrugsTypeID = #{drugstypeid,jdbcType=SMALLINT},",
          "DrugsPrice = #{drugsprice,jdbcType=REAL},",
          "MnemonicCode = #{mnemoniccode,jdbcType=VARCHAR},",
          "CreationDate = #{creationdate,jdbcType=DATE}",
        "where ID = #{id,jdbcType=SMALLINT}"
    })
    int updateByPrimaryKey(Drugs record);
}