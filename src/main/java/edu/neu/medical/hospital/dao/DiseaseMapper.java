package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.Disease;

import java.util.List;

import edu.neu.medical.hospital.bean.DiseaseExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DiseaseMapper {
    int countByExample(DiseaseExample example);

    int deleteByExample(DiseaseExample example);

    @Delete({
        "delete from disease",
        "where id = #{id,jdbcType=SMALLINT}"
    })
    int deleteByPrimaryKey(Short id);

    @Insert({
        "insert into disease (id, DiseaseCode, ",
        "DiseaseName, DiseaseICD, ",
        "DiseCategoryID)",
        "values (#{id,jdbcType=SMALLINT}, #{diseasecode,jdbcType=VARCHAR}, ",
        "#{diseasename,jdbcType=VARCHAR}, #{diseaseicd,jdbcType=VARCHAR}, ",
        "#{disecategoryid,jdbcType=SMALLINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Short.class)
    int insert(Disease record);

    int insertSelective(Disease record);

    List<Disease> selectByExample(DiseaseExample example);

    @Select({
        "select",
        "id, DiseaseCode, DiseaseName, DiseaseICD, DiseCategoryID",
        "from disease",
        "where id = #{id,jdbcType=SMALLINT}"
    })
    @ResultMap("BaseResultMap")
    Disease selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") Disease record, @Param("example") DiseaseExample example);

    int updateByExample(@Param("record") Disease record, @Param("example") DiseaseExample example);

    int updateByPrimaryKeySelective(Disease record);

    @Update({
        "update disease",
        "set DiseaseCode = #{diseasecode,jdbcType=VARCHAR},",
          "DiseaseName = #{diseasename,jdbcType=VARCHAR},",
          "DiseaseICD = #{diseaseicd,jdbcType=VARCHAR},",
          "DiseCategoryID = #{disecategoryid,jdbcType=SMALLINT}",
        "where id = #{id,jdbcType=SMALLINT}"
    })
    int updateByPrimaryKey(Disease record);
}