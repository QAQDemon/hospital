package com.neusoft.ssm.controller;

import com.neusoft.ssm.bean.SetGroup;
import com.neusoft.ssm.bean.SetSub;
import com.neusoft.ssm.service.SetManageService;
import com.neusoft.ssm.util.JwtUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("setManage")
public class SetManageController {
    @Resource
    SetManageService setManageService;

    /*
     * @Description 获得对应类别的组套的名字
     * @Param [category 1全院 2科室 3个人,type 1检查 2检验3 处方 4成药处方 5中药处方
     * @return void
     **/
    @RequestMapping("getSetGroup/{category}/{type}")
    public Map<Integer ,String> getSetGroup(HttpServletRequest request, @PathVariable("category")char category, @PathVariable("type")char type){
        return getSetGroupMethod(request,category,type, "");
    }
    @RequestMapping("getSetGroup/{category}/{type}/{key}")
    public Map<Integer ,String> getMedrecTemplate1(HttpServletRequest request,@PathVariable("category")char category,@PathVariable("type")char type,@PathVariable("key")String key){
        return getSetGroupMethod(request,category,type, key);
    }
    private Map<Integer ,String> getSetGroupMethod(HttpServletRequest request,char category,char type,String key){
        int doctorId = JwtUtil.getHeaderDoctorId(request);
        int departID = JwtUtil.getHeaderDepartmentId(request);
        int belongId=0;//1：0；2：科室id；3：医生id
        if(category=='2')
            belongId=departID;
        else if(category=='3')
            belongId = doctorId;
        Map<Integer ,String> map=new HashMap<>();
        setManageService.setType(type);
        setManageService.setCategory(category);
        setManageService.setBelongId(belongId);
        List<SetGroup> list=setManageService.searchSetGroupList(key);
        for (SetGroup setGroup : list) {
            map.put(setGroup.getId(),setGroup.getSetName());
        }
        return map;
    }

    /*
     * @Description 根据id获得组套、子项、项目（药品）内容
     * @Param [1检查 2检验3 处方 4成药处方 5中药处方]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("getSetContent/{type}/{setId}")
    public Map<String,Object> getMedrecTemplateContent(@PathVariable("type")char type,@PathVariable("setId")int setId){
        Map<String,Object> map = new HashMap<>();
        setManageService.setType(type);
        map.put("setGroup", setManageService.getSetGroupById(setId));
        List<SetSub> setSubList=setManageService.getSetSubListById(setId);
        map.put("setSubList",setSubList);
        map.put("objectList",setManageService.getSubInfoList(setSubList));
        return map;
    }

    /*
     * @Description 删除组套
     * @Param [medrecTemplateId]
     * @return int
     **/
    @RequestMapping("cancelSetGroup/{setId}")
    public int cancelSetGroup(@PathVariable("setId")int setId){
        return setManageService.cancelSetGroup(setId);
    }

    /*
     * @Description 新增或修改组套,新增的id为null
     * @Param [method 1新增 2修改,type 1检查2检验3处置,4成药 5草药]
     * @return int 1成功 0更新失败（已删除） 2新增失败（code已存在）
     **/
    @RequestMapping("saveSetGroup/{method}/{type}")
    public int saveSetGroup(HttpServletRequest request,@PathVariable("method")char method,@PathVariable("type")char type,SetGroup setGroup,int[] objectId,String[] setSubEntrust){
        int doctorId = JwtUtil.getHeaderDoctorId(request);
        int departID = JwtUtil.getHeaderDepartmentId(request);
        if(setGroup.getUseScope().equals("1"))
            setGroup.setBelongId(0);
        if(setGroup.getUseScope().equals("2"))
            setGroup.setBelongId(departID);
        if(setGroup.getUseScope().equals("3"))
            setGroup.setBelongId(doctorId);
        setGroup.setStatus("1");
        setGroup.setCreaterId(doctorId);
        setGroup.setBusinessClassify(type+"");
        setGroup.setBuildDate(new Date());
        setManageService.setBelongId(setGroup.getBelongId());
        setManageService.setType(type);
        setManageService.setCategory(setGroup.getUseScope().charAt(0));
        List<SetSub> setSubList=setManageService.initeSetSubList(objectId,setSubEntrust);
        if(method=='2')
            return setManageService.updateSetGroup(setGroup, setSubList);
        else
            return setManageService.addSetAndSub(setGroup, setSubList);
    }
}
