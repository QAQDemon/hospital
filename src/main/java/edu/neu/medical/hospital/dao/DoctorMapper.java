package edu.neu.medical.hospital.dao;

import edu.neu.medical.hospital.bean.Doctor;
import edu.neu.medical.hospital.bean.DoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorMapper {
    int countByExample(DoctorExample example);

    int deleteByExample(DoctorExample example);

    @Delete({
        "delete from doctor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into doctor (id, name, ",
        "level)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(Doctor record);

    int insertSelective(Doctor record);

    List<Doctor> selectByExample(DoctorExample example);

    @Select({
        "select",
        "id, name, level",
        "from doctor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Doctor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Doctor record, @Param("example") DoctorExample example);

    int updateByExample(@Param("record") Doctor record, @Param("example") DoctorExample example);

    int updateByPrimaryKeySelective(Doctor record);

    @Update({
        "update doctor",
        "set name = #{name,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Doctor record);
}