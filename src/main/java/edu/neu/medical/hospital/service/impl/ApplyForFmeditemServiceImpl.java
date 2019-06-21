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
     * @Description 获得项目列表,不显示删除
     * @Param [medicalRecordInfoId]
     * @return java.util.List<edu.neu.medical.hospital.bean.VisitItem>
     **/
    public List<VisitItem> getVisitItemListById(int medicalRecordInfoId){
        VisitItemExample visitItemExample = new VisitItemExample();
        VisitItemExample.Criteria criteria = visitItemExample.createCriteria();
        criteria.andTypeEqualTo(type+"");
        criteria.andMedicalRecordInfoIdEqualTo(medicalRecordInfoId);
        criteria.andStatusNotEqualTo("3");
        return visitItemMapper.selectByExample(visitItemExample);
    }

    /*
     * @Description 根据项目列表获得申请人名字
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
     * @Description 初始化项目明细列表
     * @Param [fmeditemIds, doctorEntrustments]
     * @return java.util.List<edu.neu.medical.hospital.bean.VisitItemDetail>
     **/
    public List<VisitItemDetail> initeVisitItemDetailList(int[] fmeditemIds,String[] doctorEntrustments){
        List<VisitItemDetail> visitItemDetailList = new ArrayList<>();
        for (int i = 0; i < fmeditemIds.length; i++) {
            VisitItemDetail visitItemDetail = new VisitItemDetail();
            visitItemDetail.setFmeditemId(fmeditemIds[i]);
            visitItemDetail.setDoctorEntrustment(doctorEntrustments[i]);
            visitItemDetail.setExecutionStatus("0");
            visitItemDetailList.add(visitItemDetail);
        }
        return visitItemDetailList;
    }

    /*
     * @Description 设置看诊项目和明细，暂存开立
     * @Param [visitItem id为null则需要创建, visitItemDetailList 前者为null需要获取对应id]
     * @return java.lang.Boolean
     **/
    public int setVisitItemAndDetailList(VisitItem visitItem,List<VisitItemDetail> visitItemDetailList){
        int result=0;
        if(visitItem.getId()==null){//不存在,插入
            result=visitItemMapper.insertSelective(visitItem);
            if(result==0)
                return result;
            //获得申请单号
            int lastId=visitItemMapper.getLastId(visitItem.getMedicalRecordInfoId());
            for (VisitItemDetail visitItemDetail:visitItemDetailList){
                visitItemDetail.setVisitItemId(lastId);
            }
            visitItemDetailMapper.insertForeach(visitItemDetailList);
        }else {
            result=visitItemMapper.updateByPrimaryKeySelective(visitItem);
            if(result==0)
                return result;
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
        return result;
    }

    /*
     * @Description 删除或作废项目申请，作废需要判断是否收费
     * @Param [method, visitItemId]
     * @return int 1成功 0更新失败 2已登记
     **/
    public int cancleVisitItem(char method,int visitItemId){
        if(method=='4'){//作废，获得付费状态
            if(visitItemMapper.selectByPrimaryKey(visitItemId).getFeeStatus().equals("2"))
                return 2;
        }
        VisitItem visitItem = new VisitItem();
        visitItem.setId(visitItemId);
        visitItem.setStatus(method+"");
        return visitItemMapper.updateByPrimaryKeySelective(visitItem);
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
     * @Description 根据明细id获得结果,目前只返回一个，为null可能没找到
     * @Param [visitItemDetail]
     * @return edu.neu.medical.hospital.bean.VisitItemResult
     **/
    public VisitItemResult getVisitItemResult(int visitItemId,int fmeitemId){
        VisitItemDetailExample visitItemDetailExample=new VisitItemDetailExample();
        VisitItemDetailExample.Criteria criteria = visitItemDetailExample.createCriteria();
        criteria.andVisitItemIdEqualTo(visitItemId);
        criteria.andFmeditemIdEqualTo(fmeitemId);
        List<VisitItemDetail> vList=visitItemDetailMapper.selectByExample(visitItemDetailExample);
        if (vList.size() != 1 ) {
            return new VisitItemResult();
        }
        int visitItemDetailId = vList.get(0).getId();
        VisitItemResultExample visitItemResultExample = new VisitItemResultExample();
        VisitItemResultExample.Criteria criteria1 = visitItemResultExample.createCriteria();
        criteria1.andVisitRecordDetailIdEqualTo(visitItemDetailId);
        List<VisitItemResult> rList=visitItemResultMapper.selectByExample(visitItemResultExample);
        if (rList.size() != 1 ) {
            return new VisitItemResult();
        }
        return rList.get(0);
    }

}
