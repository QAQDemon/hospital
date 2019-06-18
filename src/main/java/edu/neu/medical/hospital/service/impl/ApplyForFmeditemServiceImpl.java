package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.dao.*;
import edu.neu.medical.hospital.service.ApplyForFmeditemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyForFmeditemServiceImpl implements ApplyForFmeditemService {
    @Resource
    FmeditemMapper fmeditemMapper;
    @Resource
    VisitItemMapper visitItemMapper;
    @Resource
    VisitItemDetailMapper visitItemDetailMapper;
    @Resource
    CommonOptionMapper commonOptionMapper;
    @Resource
    VisitItemResultMapper visitItemResultMapper;
    @Resource
    UserMapper userMapper;

    //1检查 2检验 3处置
    private char type;

    public void setType(char type) {
        this.type = type;
    }

    /*
     * @Description 获得项目列表
     * @Param [medicalRecordInfoId]
     * @return java.util.List<edu.neu.medical.hospital.bean.VisitItem>
     **/
    public List<VisitItem> getVisitItemListById(int medicalRecordInfoId){
        VisitItemExample visitItemExample = new VisitItemExample();
        VisitItemExample.Criteria criteria = visitItemExample.createCriteria();
        criteria.andTypeEqualTo(type+"");
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        return visitItemMapper.selectByExample(visitItemExample);
    }

    /*
     * @Description 根据项目列表获得申请人名字//TODO
     * @Param [visitItems]
     * @return java.lang.String[]
     **/
    public String[] getApplyForPeopleName(List<VisitItem> visitItems) {
        String[] strings = new String[visitItems.size()];
        for (int i=0;i<visitItems.size();i++) {
            strings[i]=userMapper.selectByPrimaryKey(visitItems.get(i).getApplicationDoctorId()).getTrustName();
        }
        return strings;
    }

    /*
     * @Description 获得指定项目明细列表
     * @Param [visitItemId]
     * @return java.util.List<edu.neu.medical.hospital.bean.VisitItemDetail>
     **/
    public List<VisitItemDetail> getVisitItemDetailListById(int visitItemId){
        VisitItemDetailExample visitItemDetailExample=new VisitItemDetailExample();
        VisitItemDetailExample.Criteria criteria = visitItemDetailExample.createCriteria();
        criteria.andVisitItemIdEqualTo(visitItemId);
        return visitItemDetailMapper.selectByExample(visitItemDetailExample);
    }

    /*
     * @Description 获得项目明细中的非药品信息
     * @Param [visitItemDetailList]
     * @return java.util.List<edu.neu.medical.hospital.bean.Fmeditem>
     **/
    public List<Fmeditem> getFmeditemListByList(List<VisitItemDetail> visitItemDetailList){
        List<Fmeditem> list=new ArrayList<>();
        for(VisitItemDetail visitItemDetail:visitItemDetailList){
            list.add(fmeditemMapper.selectByPrimaryKey(visitItemDetail.getFmeditemId().shortValue()));
        }
        return list;
    }

    /*
     * @Description 设置看诊项目和明细，暂存开立删除作废
     * @Param [visitItem id为-1则需要创建, visitItemDetailList 前者为-1需要获取对应id]
     * @return java.lang.Boolean
     **/
    public Boolean setVisitItemAndDetailList(VisitItem visitItem,List<VisitItemDetail> visitItemDetailList){
        if(visitItem.getId()==-1){//不存在,插入
            visitItemMapper.insertSelective(visitItem);
            //获得申请单号
            int lastId=visitItemMapper.getLastId(visitItem.getMedicalRecordInfoId());
            for (VisitItemDetail visitItemDetail:visitItemDetailList){
                visitItemDetail.setVisitItemId(lastId);
            }
            visitItemDetailMapper.insertForeach(visitItemDetailList);
        }else {
            visitItemMapper.updateByPrimaryKeySelective(visitItem);
            int visitItemId=visitItem.getId();
            for (VisitItemDetail visitItemDetail:visitItemDetailList){
                visitItemDetail.setVisitItemId(visitItemId);
            }
            //删除原有明细
            VisitItemDetailExample visitItemDetailExample = new VisitItemDetailExample();
            VisitItemDetailExample.Criteria criteria=visitItemDetailExample.createCriteria();
            criteria.andVisitItemIdEqualTo(visitItemId);
            visitItemDetailMapper.deleteByExample(visitItemDetailExample);
            visitItemDetailMapper.insertForeach(visitItemDetailList);
        }
        return true;
    }

    /*
     * @Description 根据常用选项存储的id在非药品表获得常用项目列表(常用选项列表在门诊医生工作站服务类)
     * @Param [commonOptionList]
     * @return java.util.List<edu.neu.medical.hospital.bean.Fmeditem>
     **/
    public List<Fmeditem> getCommonFmeditemList(List<CommonOption> commonOptionList){
        List<Fmeditem> list=new ArrayList<>();
        for(CommonOption commonOption:commonOptionList){
            list.add(fmeditemMapper.selectByPrimaryKey(commonOption.getBelongId().shortValue()));
        }
        return list;
    }

    /*
     * @Description 删除常用项目,真删
     * @Param [doctorId,fmeditemId]
     * @return int
     **/
    public int deleteCommonFmeditem(int doctorId,int fmeditemId){
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andDoctorIdEqualTo(doctorId);
        criteria.andBelongIdEqualTo(fmeditemId);
        criteria.andTypeEqualTo(String.valueOf(type-46));
        return commonOptionMapper.deleteByExample(commonOptionExample);
    }

    /*
     * @Description 增加常用项目，在常用选项表上
     * @Param [doctorId, visitItemDetail]
     * @return java.lang.Boolean
     **/
    public Boolean addCommonFmeditem(int doctorId,VisitItemDetail visitItemDetail){
        //判断是否存在
        CommonOptionExample commonOptionExample=new CommonOptionExample();
        CommonOptionExample.Criteria criteria=commonOptionExample.createCriteria();
        criteria.andTypeEqualTo(String.valueOf(type-46));
        criteria.andBelongIdEqualTo(visitItemDetail.getFmeditemId());
        criteria.andDoctorIdEqualTo(doctorId);
        int count=commonOptionMapper.countByExample(commonOptionExample);
        if(count==0){
            CommonOption commonOption=new CommonOption();
            commonOption.setDoctorId(doctorId);
            commonOption.setBelongId(visitItemDetail.getFmeditemId());
            commonOption.setType(String.valueOf(type-46));
            commonOptionMapper.insertSelective(commonOption);
            return true;
        }else{
            return false;
        }
    }

    /*
     * @Description 根据明细id获得结果
     * @Param [visitItemDetail]
     * @return edu.neu.medical.hospital.bean.VisitItemResult
     **/
    public VisitItemResult getVisitItemResult(VisitItemDetail visitItemDetail){
        VisitItemResultExample visitItemResultExample = new VisitItemResultExample();
        VisitItemResultExample.Criteria criteria = visitItemResultExample.createCriteria();
        criteria.andVisitRecordDetailIdEqualTo(visitItemDetail.getId());
        return visitItemResultMapper.selectByExample(visitItemResultExample).get(0);
    }

}
