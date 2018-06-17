package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frxs.fund.service.api.domain.dto.vendor.VendorBalanceDto;
import com.frxs.merchant.common.dal.entity.Area;
import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorAreaRef;
import com.frxs.merchant.common.dal.enums.IsDeleteEnum;
import com.frxs.merchant.core.dubbo.FundDubboProcess;
import com.frxs.merchant.core.mapper.AreaMapper;
import com.frxs.merchant.core.mapper.VendorAreaRefMapper;
import com.frxs.merchant.core.service.VendorAreaService;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  供应商区域关联实现
 * @author wushuo
 * @version $Id: VendorAreaServiceImpl.java,v 0.1 2018年02月28日 15:09 $Exp
 */
@Service
public class VendorAreaServiceImpl implements VendorAreaService{

    @Autowired
    private VendorAreaRefMapper vendorAreaRefMapper;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private FundDubboProcess fundDubboProcess;

    /**
     * 保存供应商区域
     * @param vendor
     * @param areaIds
     * @return
     */
    @Override
    public MerchantResult saveVendorArea(Vendor vendor, List<Integer> areaIds) {
        MerchantResult result = new MerchantResult();
            List<VendorAreaRef> vendorAreaRefs = new ArrayList<>();
            if(areaIds!=null && areaIds.size()>0){
                for (Integer areaId : areaIds) {
                    VendorAreaRef vendorAreaRef = new VendorAreaRef();
                    vendorAreaRef.setVendorId(vendor.getVendorId());
                    vendorAreaRef.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
                    vendorAreaRef.setCreateUserId(vendor.getCreateUserId());
                    vendorAreaRef.setCreateUserName(vendor.getCreateUserName());
                    vendorAreaRef.setAreaId(areaId);
                    vendorAreaRef.setTmCreate(vendor.getTmCreate());
                    vendorAreaRefs.add(vendorAreaRef);
                    //保存供应商资金账户
                    VendorBalanceDto vendorBalanceDto = new VendorBalanceDto();
                    vendorBalanceDto.setVendorId(vendor.getVendorId());
                    vendorBalanceDto.setVendorName(vendor.getVendorName());
                    vendorBalanceDto.setVendorCode(vendor.getVendorCode());
                    vendorBalanceDto.setAreaId(areaId);
                    Area area = areaMapper.selectByPrimaryKey(areaId);
                    vendorBalanceDto.setAreaName(area.getAreaName());
                    fundDubboProcess.addVendorBalance(vendorBalanceDto);
                }
            }
            vendorAreaRefMapper.saveVendorArea(vendorAreaRefs);
            result.setSuccess(true);
            return result;
    }

    /**
     * 删除供应商区域
     * @param vendorId
     * @return
     */
    @Override
    public MerchantResult deleteVendroArea(Long vendorId) {
        MerchantResult result = new MerchantResult();
            VendorAreaRef vendorAreaRef = new VendorAreaRef();
            vendorAreaRef.setVendorId(vendorId);

            VendorAreaRef vendorArea = new VendorAreaRef();
            vendorArea.setIsDeleted(IsDeleteEnum.IS_DELETE_Y.getValueDefined());
            vendorAreaRefMapper.update(vendorArea,new EntityWrapper<VendorAreaRef>(vendorAreaRef));
            result.setSuccess(true);
            return result;
    }

    /**
     * 更新供应商区域信息
     * @param vendor
     * @param areaIds
     * @return
     */
    @Override
    public MerchantResult updateVendorArea(Vendor vendor, List<Integer> areaIds) {
        MerchantResult result = new MerchantResult();
        List<VendorAreaRef> vendorAreaRefs = vendorAreaRefMapper.selectList(new EntityWrapper<VendorAreaRef>().
            eq("vendorId", vendor.getVendorId()));
        if (vendorAreaRefs == null || vendorAreaRefs.size() == 0) {
            saveVendorArea(vendor, areaIds);
            result.setSuccess(true);
            return result;
        }

        Long vendorId = vendor.getVendorId();
        List<VendorAreaRef> deleteVendorAreaRefs = new ArrayList<>();
        List<VendorAreaRef> updateVendorAreaRefs = new ArrayList<>();
        List<Integer> existAreaIds = new ArrayList<>();
        for (VendorAreaRef vendorAreaRef : vendorAreaRefs) {
            Integer areaId = vendorAreaRef.getAreaId();
            boolean flag = areaIds.contains(areaId);
            existAreaIds.add(areaId);
            if (flag) {
                if (vendorAreaRef.getIsDeleted()
                    .equals(IsDeleteEnum.IS_DELETE_Y.getValueDefined())) {
                    VendorAreaRef vendorArea = new VendorAreaRef();
                    vendorArea.setId(vendorAreaRef.getId());
                    vendorArea.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
                    vendorArea.setModifyUserId(vendor.getModifyUserId());
                    vendorArea.setModifyUserName(vendor.getModifyUserName());
                    updateVendorAreaRefs.add(vendorArea);
                }
            } else {
                VendorAreaRef vendorArea = new VendorAreaRef();
                vendorArea.setId(vendorAreaRef.getId());
                vendorArea.setIsDeleted(IsDeleteEnum.IS_DELETE_Y.getValueDefined());
                vendorArea.setModifyUserId(vendor.getModifyUserId());
                vendorArea.setModifyUserName(vendor.getModifyUserName());
                deleteVendorAreaRefs.add(vendorArea);
            }
        }

        List<VendorAreaRef> saveVendorAreaRefs = new ArrayList<>();
        for (Integer areaId : areaIds) {
            boolean flag = existAreaIds.contains(areaId);
            if (!flag) {
                VendorAreaRef vendorArea = new VendorAreaRef();
                vendorArea.setVendorId(vendorId);
                vendorArea.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
                vendorArea.setCreateUserId(vendor.getModifyUserId());
                vendorArea.setCreateUserName(vendor.getModifyUserName());
                vendorArea.setAreaId(areaId);
                vendorArea.setTmCreate(new Date());
                saveVendorAreaRefs.add(vendorArea);
                //保存供应商资金账户
                VendorBalanceDto vendorBalanceDto = new VendorBalanceDto();
                vendorBalanceDto.setVendorId(vendor.getVendorId());
                vendorBalanceDto.setVendorName(vendor.getVendorName());
                vendorBalanceDto.setVendorCode(vendor.getVendorCode());
                vendorBalanceDto.setAreaId(areaId);
                Area area = areaMapper.selectByPrimaryKey(areaId);
                vendorBalanceDto.setAreaName(area.getAreaName());
                fundDubboProcess.addVendorBalance(vendorBalanceDto);
            }
        }
        if (saveVendorAreaRefs != null && saveVendorAreaRefs.size() != 0) {
            vendorAreaRefMapper.saveVendorArea(saveVendorAreaRefs);
        }
        if (updateVendorAreaRefs != null && updateVendorAreaRefs.size() != 0) {
            vendorAreaRefMapper.batchUpdateVendorAreaRef(updateVendorAreaRefs);
        }
        if (deleteVendorAreaRefs != null && deleteVendorAreaRefs.size() != 0) {
            vendorAreaRefMapper.batchUpdateVendorAreaRef(deleteVendorAreaRefs);
        }
        result.setSuccess(true);
        return result;
    }
}
