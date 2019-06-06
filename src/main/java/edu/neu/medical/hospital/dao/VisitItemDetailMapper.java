package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.VisitItemDetail;
import edu.neu.medical.hospital.bean.VisitItemDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface VisitItemDetailMapper {


    /*
     * @Description 将列表插入，使用自增id
     * @Param [list]
     * @return int
     **/
    int insertForeach(@Param("list") List<VisitItemDetail> list);











    int countByExample(VisitItemDetailExample example);

    int deleteByExample(VisitItemDetailExample example);

    @Delete({
        "delete from visit_item_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into visit_item_detail (id, visit_item_id, ",
        "fmeditem_id, doctor_entrustment, ",
        "add_item, execution_doctor_id, ",
        "execution_status)",
        "values (#{id,jdbcType=INTEGER}, #{visitItemId,jdbcType=INTEGER}, ",
        "#{fmeditemId,jdbcType=INTEGER}, #{doctorEntrustment,jdbcType=VARCHAR}, ",
        "#{addItem,jdbcType=VARCHAR}, #{executionDoctorId,jdbcType=INTEGER}, ",
        "#{executionStatus,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(VisitItemDetail record);

    int insertSelective(VisitItemDetail record);

    List<VisitItemDetail> selectByExample(VisitItemDetailExample example);

    @Select({
        "select",
        "id, visit_item_id, fmeditem_id, doctor_entrustment, add_item, execution_doctor_id, ",
        "execution_status",
        "from visit_item_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    VisitItemDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitItemDetail record, @Param("example") VisitItemDetailExample example);

    int updateByExample(@Param("record") VisitItemDetail record, @Param("example") VisitItemDetailExample example);

    int updateByPrimaryKeySelective(VisitItemDetail record);

    @Update({
        "update visit_item_detail",
        "set visit_item_id = #{visitItemId,jdbcType=INTEGER},",
          "fmeditem_id = #{fmeditemId,jdbcType=INTEGER},",
          "doctor_entrustment = #{doctorEntrustment,jdbcType=VARCHAR},",
          "add_item = #{addItem,jdbcType=VARCHAR},",
          "execution_doctor_id = #{executionDoctorId,jdbcType=INTEGER},",
          "execution_status = #{executionStatus,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VisitItemDetail record);
}