package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.ParameterSettingService;
import com.frxs.merchant.core.service.VendorService;
import com.frxs.merchant.service.api.domain.request.VendorPageRequest;
import com.frxs.merchant.service.api.domain.request.VendorRequest;
import com.frxs.merchant.service.api.dto.AreaInfoDto;
import com.frxs.merchant.service.api.dto.VendorDto;
import com.frxs.merchant.service.api.dto.VendorInfoDto;
import com.frxs.merchant.service.api.dto.VendorProFileInfoDto;
import com.frxs.merchant.service.api.enums.ParameterSettingEnum;
import com.frxs.merchant.service.api.facade.VendorFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;

/**
 * 供应商信息DUBBO实现
 * @author Cukor.fu
 * @version $Id: VendorFacadeImpl.java,v 0.1 2018年01月30日 下午 19:42 $Exp
 */
@Service(version="1.0.0")
public class VendorFacadeImpl implements VendorFacade {

    @FrxsAutowired
    VendorService vendorService;

    @FrxsAutowired
    ParameterSettingService parameterSettingService;

    @Override
    public MerchantResult saveOneVendor(VendorDto vendorDTO) {
        return vendorService.saveOneVendor(vendorDTO);
    }

    @Override
    public MerchantResult updateVendor(VendorDto vendorDTO) {

        return vendorService.updateVendor(vendorDTO);
    }

    @Override
    public MerchantResult batchUpdateStatus(VendorDto vendorDto) {
        return vendorService.batchUpdateStatus(vendorDto);
    }

    @Override public MerchantResult<Page<VendorDto>> vendorPageList(VendorPageRequest vendorPageRequest) {
        return vendorService.vendorPageList(vendorPageRequest);
    }

    @Override
    public MerchantResult<List<VendorDto>> vendorList(VendorPageRequest vendorPageRequest) {
        return vendorService.vendorList(vendorPageRequest);
    }

    @Override
    public MerchantResult<VendorDto> getVendorById(Long vendorId) {
        return vendorService.getVendorById(vendorId);
    }

    @Override public MerchantResult saveVendor(VendorDto vendorDto, List<Integer> vendorTypeIds,List<Integer> areaIds) {
        return vendorService.saveVendor(vendorDto,vendorTypeIds,areaIds);
    }

    @Override public MerchantResult<VendorInfoDto> getInfoByKey(Long vendorId) {
        return  vendorService.getInfoByKey(vendorId);
    }

    @Override public MerchantResult<VendorProFileInfoDto> getProFileByKey(Long vendorId) {
        return vendorService.getProFileByKey(vendorId);
    }

    @Override
    public MerchantResult<List<VendorDto>> getVendorListByContactsTel(String contactsTel) {
        return vendorService.getVendorListByContactsTel(contactsTel);
    }

    @Override public MerchantResult<List<AreaInfoDto>> getVendorAreaInfo(Long vendorId) {
        return vendorService.getVendorAreaInfo(vendorId);
    }

    @Override
    public MerchantResult<List<VendorDto>> getVendorListByVendorIds(List<Long> vendorIds) {
        return vendorService.getVendorListByVendorIds(vendorIds);
    }

    @Override
    public MerchantResult<List<VendorDto>> getVendorListByUnionPayMID(List<String> unionPayMIDs) {
        return vendorService.getVendorListByUnionPayMID(unionPayMIDs);
    }

    @Override
    public MerchantResult<List<VendorDto>> getVendorListByUnionPayCID(List<String> unionPayCIDs) {
        return vendorService.getVendorListByUnionPayCID(unionPayCIDs);
    }

    /**
     * 取得兴盛供应商对象;
     *
     * @param areaId 区域id;
     * @return MerchantResult
     */
    @Override
    public MerchantResult<VendorDto> getXsVendorInfo(Integer areaId) {

        MerchantResult<VendorDto> result = vendorService.getVendorByVendorCode(ParameterSettingEnum.XS_VENDOR_CODE.getValueDefined());
        return result;
    }

    @Override public MerchantResult<List<VendorDto>> getVendorListByVendorRequest(VendorRequest vendorRequest) {
        return vendorService.getVendorListByVendorRequest(vendorRequest);
    }

    @Override
    public MerchantResult<Integer> getVendorAreaProductCount(Long vendorId, Integer areaId) {
        return vendorService.getVendorAreaProductCount(vendorId,areaId);
    }

}
