package com.frxs.merchant.service.api.facade;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.service.api.domain.request.VendorPageRequest;
import com.frxs.merchant.service.api.domain.request.VendorRequest;
import com.frxs.merchant.service.api.dto.AreaInfoDto;
import com.frxs.merchant.service.api.dto.ProductDto;
import com.frxs.merchant.service.api.dto.VendorDto;
import com.frxs.merchant.service.api.dto.VendorInfoDto;
import com.frxs.merchant.service.api.dto.VendorProFileInfoDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.merchant.service.api.result.VendorResult;

import java.util.List;

/**
 * 供应商信息DUBBO接口
 * @author Cukor.fu
 * @version $Id: VendorFacade.java,v 0.1 2018年01月25日 下午 16:37 $Exp
 */
public interface VendorFacade {
    /**
     * 保存供应商信息
     * @param vendorDto
     * @return MerchantResult
     */
    MerchantResult saveOneVendor(VendorDto vendorDto);

    /**
     * 修改供应商信息
     * @param vendorDto
     * @return
     */
    MerchantResult updateVendor(VendorDto vendorDto);

    /**
     * 批量修改供应商状态（删除，冻结，解冻）
     * @param vendorDto
     * @return MerchantResult
     */
    MerchantResult batchUpdateStatus(VendorDto vendorDto);

    /**
     * 查询供应商信息
     * @param vendorPageRequest
     * @return MerchantResult<Page<VendorDto>>
     */
    MerchantResult<Page<VendorDto>> vendorPageList(VendorPageRequest vendorPageRequest);


    /**
     * 查询供应商信息/无分页
     * @param vendorPageRequest
     * @return MerchantResult<Page<VendorDto>>
     */
    MerchantResult<List<VendorDto>> vendorList(VendorPageRequest vendorPageRequest);

    /**
     * 根据主键ID查询供应商信息
     * @param vendorId
     * @return  MerchantResult<VendorDto>
     */
    MerchantResult<VendorDto> getVendorById(Long vendorId);

    /**
     * 保存供应商信息
     * @param vendorDto
     * @param vendorTypeIds
     * @param areaIds
     * @return MerchantResult
     */
    MerchantResult saveVendor(VendorDto vendorDto,List<Integer> vendorTypeIds,List<Integer> areaIds);

    /**
     * 供应商web端供应商详细信息
     * @param vendorId
     * @return MerchantResult<VendorInfoDto>
     */
    MerchantResult<VendorInfoDto> getInfoByKey(Long vendorId);

    /**
     * 供应商web端银行卡详细信息
     * @param vendorId
     * @return MerchantResult<VendorProFileInfoDto>
     */
    MerchantResult<VendorProFileInfoDto> getProFileByKey(Long vendorId);

    /**
     * 根据手机号查询供应商信息
     * @param contactsTel
     * @return MerchantResult<List<VendorDto>>
     */
    MerchantResult<List<VendorDto>> getVendorListByContactsTel(String contactsTel);

    /**
     * 获取供应商区域信息
     * @param vendorId
     * @return MerchantResult<List<AreaInfoDto>>
     */
    MerchantResult<List<AreaInfoDto>> getVendorAreaInfo(Long vendorId);

    /**
     * 根据vendorIds查询供应商信息
     * @param vendorIds
     * @return
     */
    MerchantResult<List<VendorDto>> getVendorListByVendorIds(List<Long> vendorIds);

    /**
     * 通过商户号集合查询供应商集合
     * @param unionPayMIDs
     * @return
     */
    MerchantResult<List<VendorDto>> getVendorListByUnionPayMID(List<String> unionPayMIDs);

    MerchantResult<List<VendorDto>> getVendorListByUnionPayCID(List<String> unionPayCIDs);
    /**
     * 取得兴盛供应商对象;
     *
     * @param areaId 区域id;
     * @return MerchantResult
     */
    MerchantResult<VendorDto> getXsVendorInfo(Integer areaId);

    /**
     * 根据 VendorRequest里的提供的条件进行查询
     * @param vendorRequest
     * @return
     */
    MerchantResult<List<VendorDto>> getVendorListByVendorRequest(VendorRequest vendorRequest);

    /**
     * 查询供应商所在区域商品数量
     * @param vendorId
     * @param areaId
     * @return
     */
    MerchantResult<Integer> getVendorAreaProductCount(Long vendorId,Integer areaId);

}
