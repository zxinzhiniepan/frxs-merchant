package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.VendorType;
import com.frxs.merchant.common.dal.entity.VendorTypeRef;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.VendorTypeMapper;
import com.frxs.merchant.core.mapper.VendorTypeRefMapper;
import com.frxs.merchant.core.mapstruct.VendorTypeMapStruct;
import com.frxs.merchant.core.service.VendorTypeService;
import com.frxs.merchant.service.api.dto.VendorTypeDto;
import com.frxs.merchant.service.api.enums.IsDeleteEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 供应商分类核心实现
 *
 * @author wushuo
 * @version $Id: VendorTypeServiceImpl.java,v 0.1 2018年01月31日 10:01 $Exp
 */
@Service public class VendorTypeServiceImpl implements VendorTypeService {

    @Autowired private VendorTypeMapper mapper;

    @Autowired private VendorTypeRefMapper vendorTypeRefMapper;

    @Autowired private TransactionTemplate newTransactionTemplate;

    @Autowired private MerchantResultHelper<MerchantResult> merchantResultHelper;


    /**
     * 主键ID查询
     *
     * @return MerchantResult<VendorTypeDto>
     */
    @Override public MerchantResult<VendorTypeDto> getById(Integer vendorTypeId) {
        MerchantResult<VendorTypeDto> result = new MerchantResult<VendorTypeDto>();
        VendorType vendorType = mapper.selectByPrimaryKey(vendorTypeId);
        if (vendorType == null) {
            return result;
        }
        VendorTypeDto vendorTypeDto = VendorTypeMapStruct.MAPPER.typeTOTypeDto(vendorType);
        result.setData(vendorTypeDto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 保存供应商分类信息
     *
     * @return MerchantResult<VendorTypeDto>
     */
    @Override public MerchantResult<VendorTypeDto> save(VendorTypeDto vendorTypeDto) {
        MerchantResult<VendorTypeDto> result = newTransactionTemplate
            .execute(new TransactionCallback<MerchantResult<VendorTypeDto>>() {
                @Override
                public MerchantResult<VendorTypeDto> doInTransaction(TransactionStatus status) {
                    MerchantResult<VendorTypeDto> result = new MerchantResult<VendorTypeDto>();
                    try {
                        VendorType vendorType =
                            VendorTypeMapStruct.MAPPER.typeDtoTOType(vendorTypeDto);
                        if (vendorType.getVendorTypeId() != null) {
                            //修改
                            mapper.updateById(vendorType);
                        } else {
                            //添加
                            vendorType.setTmCreate(new Date());
                            vendorType.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
                            mapper.insert(vendorType);
                        }
                        result.setSuccess(true);
                    } catch (Exception e) {
                        merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR,
                            ErrorCodeDetailEnum.VENDOR_TYPE_SAVE_ERROR);
                        LogUtil.error(e, "[vendorType-service:保存供应商分类信息]处理异常  errorCode={}",
                            result.getErrorContext().fetchCurrentErrorCode());
                        status.setRollbackOnly();
                    }
                    return result;
                }
            });
        return result;
    }

    /**
     * 修改供应商分类信息
     *
     * @return MerchantResult<VendorTypeDto>
     */
    @Override public MerchantResult<VendorTypeDto> update(VendorTypeDto vendorTypeDto) {
        MerchantResult<VendorTypeDto> result = newTransactionTemplate
            .execute(new TransactionCallback<MerchantResult<VendorTypeDto>>() {
                @Override
                public MerchantResult<VendorTypeDto> doInTransaction(TransactionStatus status) {
                    MerchantResult<VendorTypeDto> result = new MerchantResult<VendorTypeDto>();
                    try {
                        VendorType vendorType =
                            VendorTypeMapStruct.MAPPER.typeDtoTOType(vendorTypeDto);
                        mapper.updateById(vendorType);
                        result.setSuccess(true);
                    } catch (Exception e) {
                        merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR,
                            ErrorCodeDetailEnum.VENDOR_TYPE_UPDATE_ERROR);
                        LogUtil.error(e, "[vendorType-service:修改供应商分类信息]处理异常  errorCode={}",
                            result.getErrorContext().fetchCurrentErrorCode());
                        status.setRollbackOnly();
                    }
                    return result;
                }
            });
        return result;
    }

    /**
     * 删除供应商分类信息
     *
     * @return MerchantResult<VendorTypeDto>
     */
    @Override public MerchantResult delete(Integer vendorTypeId) {
        MerchantResult<VendorTypeDto> result = newTransactionTemplate
            .execute(new TransactionCallback<MerchantResult<VendorTypeDto>>() {
                @Override
                public MerchantResult<VendorTypeDto> doInTransaction(TransactionStatus status) {
                    MerchantResult<VendorTypeDto> result = new MerchantResult<VendorTypeDto>();
                    try {
                        VendorType vendorType = mapper.selectByPrimaryKey(vendorTypeId);
                        if (vendorType == null) {
                            return result;
                        }
                        vendorType.setIsDeleted(IsDeleteEnum.IS_DELETE_Y.getValueDefined());
                        mapper.updateById(vendorType);
                        VendorTypeRef vendorTypeRef = new VendorTypeRef();
                        vendorTypeRef.setIsDeleted(IsDeleteEnum.IS_DELETE_Y.getValueDefined());
                        vendorTypeRef.setVendorTypeId(vendorTypeId);
                        vendorTypeRefMapper.deleteByVendorTypeId(vendorTypeRef);
                        result.setSuccess(true);
                    } catch (Exception e) {
                        merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR,
                            ErrorCodeDetailEnum.VENDOR_TYPE_DELETE_ERROR);
                        LogUtil.error(e, "[vendorType-service:删除供应商分类信息]处理异常  errorCode={}",
                            result.getErrorContext().fetchCurrentErrorCode());
                        status.setRollbackOnly();
                    }
                    return result;
                }
            });
        return result;
    }

    /**
     * 查询所有供应商分类信息
     *
     * @return MerchantResult<List   <   VendorTypeDto>>
     */
    @Override public MerchantResult<List<VendorTypeDto>> getList(VendorTypeDto vendorTypeDto) {
        MerchantResult<List<VendorTypeDto>> result = new MerchantResult<List<VendorTypeDto>>();
        vendorTypeDto.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
        VendorType vendorType = VendorTypeMapStruct.MAPPER.typeDtoTOType(vendorTypeDto);
        List<VendorType> vendorTypes = mapper.getList(vendorType);
        if (vendorTypes == null || vendorTypes.size() == 0) {
            return result;
        }
        List<VendorTypeDto> vendorTypeDtos =
            VendorTypeMapStruct.MAPPER.typesToTypeDtos(vendorTypes);
        result.setSuccess(true);
        result.setData(vendorTypeDtos);
        return result;
    }

    /**
     * 根据供应商id查询供应商分类ID
     *
     * @return MerchantResult<List   <   VendorTypeDto>>
     */
    @Override public MerchantResult<List<Integer>> getListByVendorId(Long vendorId,
        String isDeleted) {
        MerchantResult<List<Integer>> result = new MerchantResult<>();
        List<Integer> vendorTypeIds = vendorTypeRefMapper.getListByVendorId(vendorId, isDeleted);
        result.setSuccess(true);
        result.setData(vendorTypeIds);
        return result;
    }

    /**
     * 通过供应商分类id查询供应商关联供应商分类信息
     */
    @Override public MerchantResult getVendorTypeRefListById(Integer vendorTypeId) {
        MerchantResult result = new MerchantResult();
        List<VendorTypeRef> vendorTypeRefs = vendorTypeRefMapper.selectList(
            new EntityWrapper<VendorTypeRef>().eq("vendorTypeId", vendorTypeId)
                .eq("isDeleted", IsDeleteEnum.IS_DELETE_N.getValue()));
        if (vendorTypeRefs == null || vendorTypeRefs.size() == 0) {
            return result;
        }
        result.setData(vendorTypeRefs);
        result.setSuccess(true);
        return result;
    }

    /**
     * 通过供应商分类名称查询
     */
    @Override public MerchantResult<VendorTypeDto> getVendorTypeByVendorTypeName(
        String vendorTypeName) {
        MerchantResult<VendorTypeDto> result = new MerchantResult<>();
        VendorType vendorType = new VendorType();
        vendorType.setVendorTypeName(vendorTypeName);
        VendorType vendorTypeResult = mapper.selectOne(vendorType);
        if (vendorTypeResult == null) {
            return result;
        }
        VendorTypeDto vendorTypeDto = VendorTypeMapStruct.MAPPER.typeTOTypeDto(vendorType);
        result.setSuccess(true);
        result.setData(vendorTypeDto);
        return result;
    }
}
