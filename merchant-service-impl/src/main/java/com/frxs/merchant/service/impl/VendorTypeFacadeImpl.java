package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.VendorTypeService;
import com.frxs.merchant.service.api.dto.VendorTypeDto;
import com.frxs.merchant.service.api.facade.VendorTypeFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 供应商分类DUBBO接口
 * @author wushuo
 * @version $Id: VendorTypeFacadeImpl.java,v 0.1 2018年01月31日 11:13 $Exp
 */
@Service(version = "1.0.0")
public class VendorTypeFacadeImpl implements VendorTypeFacade {

    @FrxsAutowired
    private VendorTypeService vendorTypeService;
    /**
     * 主键ID查询
     * @param vendorTypeId
     * @return MerchantResult<VendorTypeDto>
     */
    @Override
    public MerchantResult<VendorTypeDto> getById(Integer vendorTypeId) {
        return vendorTypeService.getById(vendorTypeId);
    }

    /**
     * 保存供应商信息
     * @param vendorTypeDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult<VendorTypeDto> save(VendorTypeDto vendorTypeDto) {
        return vendorTypeService.save(vendorTypeDto);
    }

    /**
     * 修改供应商信息
     * @param vendorTypeDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult update(VendorTypeDto vendorTypeDto) {
        return vendorTypeService.update(vendorTypeDto);
    }


    /**
     * 删除供应商信息
     * @param vendorTypeId
     * @return MerchantResult
     */
    @Override
    public MerchantResult delete(Integer vendorTypeId) {
        return vendorTypeService.delete(vendorTypeId);
    }

    /**
     * 查询所有供应商分类
     * @param vendorTypeDto
     * @return MerchantResult<List<VendorTypeDto>>
     */
    @Override
    public MerchantResult<List<VendorTypeDto>> getList(VendorTypeDto vendorTypeDto) {
        return vendorTypeService.getList(vendorTypeDto);
    }

    /**
     * 根据供应商id查询供应商分类信息
     * @param vendorId
     * @return MerchantResult<List<Integer>>
     */
    @Override public MerchantResult<List<Integer>> getListByVendorId(Long vendorId,String isDeleted) {
        return vendorTypeService.getListByVendorId(vendorId,isDeleted);
    }

    @Override public MerchantResult getVendorTypeRefListById(Integer vendorTypeId) {
        return vendorTypeService.getVendorTypeRefListById(vendorTypeId);
    }

    @Override
    public MerchantResult<VendorTypeDto> getVendorTypeByVendorTypeName(String vendorTypeName) {
        return vendorTypeService.getVendorTypeByVendorTypeName(vendorTypeName);
    }
}
