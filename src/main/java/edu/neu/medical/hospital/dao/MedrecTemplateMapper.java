package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.MedrecTemplate;
import edu.neu.medical.hospital.bean.MedrecTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface MedrecTemplateMapper {
    int countByExample(MedrecTemplateExample example);

    int deleteByExample(MedrecTemplateExample example);

    @Delete({
        "delete from medrec_template",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into medrec_template (id, template_name, ",
        "category, belong_id, ",
        "chief_complaint, current_medical_history, ",
        "current_treatment_situation, past__history, ",
        "allergies_history, physical_examination, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{templateName,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{belongId,jdbcType=INTEGER}, ",
        "#{chiefComplaint,jdbcType=VARCHAR}, #{currentMedicalHistory,jdbcType=VARCHAR}, ",
        "#{currentTreatmentSituation,jdbcType=VARCHAR}, #{pastHistory,jdbcType=VARCHAR}, ",
        "#{allergiesHistory,jdbcType=VARCHAR}, #{physicalExamination,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(MedrecTemplate record);

    int insertSelective(MedrecTemplate record);

    List<MedrecTemplate> selectByExample(MedrecTemplateExample example);

    @Select({
        "select",
        "id, template_name, category, belong_id, chief_complaint, current_medical_history, ",
        "current_treatment_situation, past__history, allergies_history, physical_examination, ",
        "status",
        "from medrec_template",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    MedrecTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MedrecTemplate record, @Param("example") MedrecTemplateExample example);

    int updateByExample(@Param("record") MedrecTemplate record, @Param("example") MedrecTemplateExample example);

    int updateByPrimaryKeySelective(MedrecTemplate record);

    @Update({
        "update medrec_template",
        "set template_name = #{templateName,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "belong_id = #{belongId,jdbcType=INTEGER},",
          "chief_complaint = #{chiefComplaint,jdbcType=VARCHAR},",
          "current_medical_history = #{currentMedicalHistory,jdbcType=VARCHAR},",
          "current_treatment_situation = #{currentTreatmentSituation,jdbcType=VARCHAR},",
          "past__history = #{pastHistory,jdbcType=VARCHAR},",
          "allergies_history = #{allergiesHistory,jdbcType=VARCHAR},",
          "physical_examination = #{physicalExamination,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MedrecTemplate record);
}