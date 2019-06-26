package com.neusoft.ssm.dao;

import com.neusoft.ssm.bean.VisitItemResult;
import com.neusoft.ssm.bean.VisitItemResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface VisitItemResultMapper {
    int countByExample(VisitItemResultExample example);

    int deleteByExample(VisitItemResultExample example);

    @Delete({
        "delete from visit_item_result",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into visit_item_result (id, visit_record_detail_id, ",
        "describetion, picture)",
        "values (#{id,jdbcType=INTEGER}, #{visitRecordDetailId,jdbcType=INTEGER}, ",
        "#{describetion,jdbcType=VARCHAR}, #{picture,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(VisitItemResult record);

    int insertSelective(VisitItemResult record);

    List<VisitItemResult> selectByExample(VisitItemResultExample example);

    @Select({
        "select",
        "id, visit_record_detail_id, describetion, picture",
        "from visit_item_result",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    VisitItemResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitItemResult record, @Param("example") VisitItemResultExample example);

    int updateByExample(@Param("record") VisitItemResult record, @Param("example") VisitItemResultExample example);

    int updateByPrimaryKeySelective(VisitItemResult record);

    @Update({
        "update visit_item_result",
        "set visit_record_detail_id = #{visitRecordDetailId,jdbcType=INTEGER},",
          "describetion = #{describetion,jdbcType=VARCHAR},",
          "picture = #{picture,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VisitItemResult record);
}