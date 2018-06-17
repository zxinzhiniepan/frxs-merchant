package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.VendorTypeDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 供应商分类外部接口
 * @author wushuo
 * @version $Id: VendorTypeFacade.java,v 0.1 2018年01月31日 9:51 $Exp
 */
public interface VendorTypeFacade {

    /**
     * 主键ID查询
     * @param vendorTypeId
     * @return MerchantResult<VendorTypeDto>
     */
    MerchantResult<VendorTypeDto> getById(Integer vendorTypeId);

    /**
     * 保存供应商分类信息
     * @param vendorTypeDto
     * @return MerchantResult
     */
    MerchantResult save(VendorTypeDto vendorTypeDto);

    /**
     * 修改供应商分类信息
     * @param vendorTypeDto
     * @return MerchantResult<VendorTypeDto>
     */
    MerchantResult update(VendorTypeDto vendorTypeDto);

    /**
     * 删除供应商分类信息
     * @param vendorTypeId
     * @return MerchantResult
     */
    MerchantResult delete(Integer vendorTypeId);

    /**
     * 查询所有供应商分类信息
     * @param vendorTypeDto
     * @return MerchantResult<List<VendorTypeDto>>
     */
    MerchantResult<List<VendorTypeDto>> getList(VendorTypeDto vendorTypeDto);

    /**
     * 根据供应商id查询供应商分类ID
     * @param vendorId
     * @param isDeleted
     * @return MerchantResult<List<Integer>>
     */
    MerchantResult<List<Integer>> getListByVendorId(Long vendorId,String isDeleted);

    /**
     * 通过供应商分类id查询供应商关联供应商分类信息
     * @param vendorTypeId
     * @return
     */
    MerchantResult getVendorTypeRefListById(Integer vendorTypeId);

    /**
     * 通过供应商分类名称查询
     * @param vendorTypeName
     * @return
     */
    MerchantResult<VendorTypeDto> getVendorTypeByVendorTypeName(String vendorTypeName);
}
