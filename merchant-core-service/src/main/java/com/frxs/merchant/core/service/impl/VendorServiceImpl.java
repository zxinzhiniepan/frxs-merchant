package com.frxs.merchant.core.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.Area;
import com.frxs.merchant.common.dal.entity.Product;
import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorQy;
import com.frxs.merchant.common.dal.entity.VendorTypeRef;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.dal.enums.IsDeleteEnum;
import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.core.cache.CacheTool;
import com.frxs.merchant.core.generator.IdGenerator;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.AreaMapper;
import com.frxs.merchant.core.mapper.ProductMapper;
import com.frxs.merchant.core.mapper.VendorMapper;
import com.frxs.merchant.core.mapper.VendorTypeRefMapper;
import com.frxs.merchant.core.mapstruct.AreaMapStruct;
import com.frxs.merchant.core.mapstruct.VendorMapStruct;
import com.frxs.merchant.core.service.VendorAccountService;
import com.frxs.merchant.core.service.VendorAreaService;
import com.frxs.merchant.core.service.VendorService;
import com.frxs.merchant.service.api.domain.request.VendorPageRequest;
import com.frxs.merchant.service.api.domain.request.VendorRequest;
import com.frxs.merchant.service.api.dto.AreaInfoDto;
import com.frxs.merchant.service.api.dto.VendorDto;
import com.frxs.merchant.service.api.dto.VendorInfoDto;
import com.frxs.merchant.service.api.dto.VendorProFileInfoDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 供应商信息service实现
 *
 * @author Cukor.fu
 * @version $Id: VendorService.java,v 0.1 2018年01月29日 上午 10:53 $Exp
 */
@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorMapper vendorMapper;

    @Autowired
    private VendorTypeRefMapper vendorTypeRefMapper;

    @Autowired
    private TransactionTemplate newTransactionTemplate;

    @Autowired
    private MerchantResultHelper<MerchantResult> merchantResultHelper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private CacheTool cacheTool;

    @Autowired
    private VendorAccountService vendorAccountService;

    @Autowired
    private VendorAreaService vendorAreaService;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private ProductMapper productMapper;


    /**
     * 保存供应商信息
     *
     * @param vendorDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult saveOneVendor(VendorDto vendorDto) {

        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<VendorDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    Vendor vendor = VendorMapStruct.MAPPER.vendorDtoToVendor(vendorDto);
                    if (vendor.getVendorId() != 0) {
                        vendorMapper.updateById(vendor);
                        //清除缓存
                        cacheTool.deleteVendor(vendor.getVendorId()+"");
                    } else {
                        vendor.setVendorId(idGenerator.genVendorId());
                        vendor.setTmCreate(new Date());
                        vendor.setVendorStatus(StatusEnum.NORMAL.getValueDefined());
                        vendorMapper.insert(vendor);
                    }
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR, ErrorCodeDetailEnum.VENDOR_SAVE_ERROR);
                    LogUtil.error(e,"[vendor-service:保存供应商信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }


    /**
     * 修改供应商信息
     *
     * @param vendorDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult updateVendor(VendorDto vendorDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<VendorDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    Vendor vendor = VendorMapStruct.MAPPER.vendorDtoToVendor(vendorDto);
                    // 判断是否删除操作
                    if (StatusEnum.DELETE.getValueDefined().equals(vendor.getVendorStatus())) {
                        //判断是否关联商品
                        Integer produtCount = productMapper.selectCount(
                            new EntityWrapper<Product>().eq("vendorId", vendor.getVendorId()));
                        if(produtCount > 0){
                            return result;
                        }
                        VendorTypeRef vendorTypeRef = new VendorTypeRef();
                        vendorTypeRef.setVendorId(vendor.getVendorId());
                        vendorTypeRef.setIsDeleted(IsDeleteEnum.IS_DELETE_Y.getValueDefined());
                        //供应商类型关联删除
                        vendorTypeRefMapper.deleteByVendorId(vendorTypeRef);
                        //区域关联删除
                        vendorAreaService.deleteVendroArea(vendor.getVendorId());
                    }
                    vendorMapper.updateById(vendor);
                    //供应商账户状态
                    vendorAccountService.updateStatus(vendor);
                    result.setSuccess(true);
                    //清除缓存
                    cacheTool.deleteVendor(vendor.getVendorId()+"");
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR, ErrorCodeDetailEnum.VENDOR_UPDATE_ERROR);
                    LogUtil.error(e,"[vendor-service:修改供应商信息]处理异常  errorCode={}",result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;

    }

    /**
     * 分页条件查询
     *
     * @param vendorPageRequest
     * @return MerchantResult<Page<VendorDto>>
     */
    @Override
    public MerchantResult<Page<VendorDto>> vendorPageList(VendorPageRequest vendorPageRequest) {
        MerchantResult<Page<VendorDto>> result = new MerchantResult<>();
        Integer page = vendorPageRequest.getPage();
        Integer rows = vendorPageRequest.getRows();
        Page<Vendor> vendorPage = new Page<>(page, rows);
        Page<VendorDto> resultPage = new Page<>();
        List<VendorDto> resultList = new ArrayList<>();
            Integer count = vendorMapper.getCount(vendorPageRequest);
        if(count > 0){
            List<VendorQy> pageList = vendorMapper.getPageList(vendorPage,vendorPageRequest);
           resultList = VendorMapStruct.MAPPER.vendorQysToVendorDtos(pageList);
        }
        resultPage.setRecords(resultList);
        resultPage.setTotal(count);
        result.setData(resultPage);
        result.setSuccess(true);
        return result;
    }
    /**
     * 查询供应商信息/无分页
     * @param vendorPageRequest
     * @return MerchantResult<Page<VendorDto>>
     */
    @Override
    public MerchantResult<List<VendorDto>> vendorList(VendorPageRequest vendorPageRequest) {
        MerchantResult<List<VendorDto>> result = new MerchantResult<>();
        List<VendorQy> pageList = vendorMapper.getList(vendorPageRequest);
        if(pageList == null || pageList.size() == 0){
            return result;
        }
        List<VendorDto> resultList = VendorMapStruct.MAPPER.vendorQysToVendorDtos(pageList);
        result.setData(resultList);
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据主键ID查询供应商信息
     *
     * @param vendorId
     * @return MerchantResult<VendorDto>
     */
    @Override
    public MerchantResult<VendorDto> getVendorById(Long vendorId) {
        MerchantResult<VendorDto> result = new MerchantResult<VendorDto>();
        Vendor vendor = vendorMapper.selectByPrimaryKey(vendorId);
        if (vendor == null) {
            return result;
        }
        VendorDto vendorDto = VendorMapStruct.MAPPER.vendorToVendorDto(vendor);
        result.setData(vendorDto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 保存供应商信息
     *
     * @param vendorDto
     * @param vendorTypeIds
     * @return MerchantResult
     */
    @Override
    public MerchantResult saveVendor(VendorDto vendorDto, List<Integer> vendorTypeIds, List<Integer> areaIds) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<VendorDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    Vendor vendor = VendorMapStruct.MAPPER.vendorDtoToVendor(vendorDto);
                    Long vendorId = vendor.getVendorId();
                    if (vendorId == 0) {
                        vendor.setVendorId(idGenerator.genVendorId());
                        vendor.setTmCreate(new Date());
                        vendor.setVendorStatus(StatusEnum.NORMAL.getValueDefined());
                        Integer insert = vendorMapper.insert(vendor);
                        List<VendorTypeRef> list = new ArrayList<>();
                        for (Integer vendorTypeId : vendorTypeIds) {
                            VendorTypeRef vendorTypeRef = new VendorTypeRef();
                            vendorTypeRef.setVendorId(vendor.getVendorId());
                            vendorTypeRef.setVendorTypeId(vendorTypeId);
                            vendorTypeRef.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
                            vendorTypeRef.setTmCreate(new Date());
                            list.add(vendorTypeRef);
                        }
                        vendorTypeRefMapper.addByVendorId(list);
                        //增加账户
                        vendorAccountService.saveAccount(vendor,vendorDto.getUserName());
                        //增加区域
                        vendorAreaService.saveVendorArea(vendor, areaIds);
                    } else {
                        vendorMapper.updateById(vendor);
                        vendorTypeRefMapper.delByVendorId(vendorId);
                        List<VendorTypeRef> list = new ArrayList<>();
                        for (Integer vendorTypeId : vendorTypeIds) {
                            VendorTypeRef vendorTypeRef = new VendorTypeRef();
                            vendorTypeRef.setVendorId(vendorId);
                            vendorTypeRef.setVendorTypeId(vendorTypeId);
                            vendorTypeRef.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
                            vendorTypeRef.setTmCreate(new Date());
                            list.add(vendorTypeRef);
                        }
                        //供应商类型
                        vendorTypeRefMapper.addByVendorId(list);
                        //供应商账户
                        vendorAccountService.updateAccount(vendor);
                        //供应商区域
                        if(areaIds!=null && areaIds.size()!=0){
                            vendor = vendorMapper.selectByPrimaryKey(vendorId);
                            vendorAreaService.updateVendorArea(vendor,areaIds);
                        }
                    }
                    cacheTool.deleteVendor(vendorId+"");
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR, ErrorCodeDetailEnum.VENDOR_SAVE_ERROR);
                    LogUtil.error(e,"[vendor-service:保存供应商信息]处理异常 errorCode={}",result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 批量修改供应商状态（删除，冻结，解冻）
     *
     * @param vendorDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult batchUpdateStatus(VendorDto vendorDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult result = new MerchantResult();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    List<Long> vendorIds = vendorDto.getVendorIds();
                    if (StatusEnum.DELETE.getValueDefined().equals(vendorDto.getVendorStatus())) {
                        for (Long vendorId : vendorIds) {
                            //判断是否关联商品
                            Integer produtCount = productMapper.selectCount(
                                new EntityWrapper<Product>().eq("vendorId", vendorId));
                            if(produtCount > 0){
                                return result;
                            }
                        }
                        //批量更新供应商类型关联
                        vendorTypeRefMapper.batchVendorTypeByVendorIds(vendorIds, IsDeleteEnum.IS_DELETE_Y.getValueDefined());
                    }
                    vendorMapper.batchUpdateStatus(vendorDto);
                    //供应商账户
                    vendorAccountService.batchUpdateStatus(vendorDto);

                    vendorIds.forEach(vendorId->{
                        cacheTool.deleteVendor(vendorId+"");
                    });

                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR, ErrorCodeDetailEnum.VENDOR_UPDATE_ERROR);
                    LogUtil.error(e,"[vendor-service:修改供应商信息]处理异常 errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 供应商web端供应商详细信息
     *
     * @param vendorId
     * @return MerchantResult<VendorInfoDto>
     */
    @Override
    public MerchantResult<VendorInfoDto> getInfoByKey(Long vendorId) {
        MerchantResult<VendorInfoDto> result = new MerchantResult<VendorInfoDto>();
        Vendor vendor = cacheTool.findVendorByKey(vendorId + "");
        if (vendor == null) {
            vendor = vendorMapper.selectByPrimaryKey(vendorId);
            if (vendor == null) {
                result.setSuccess(false);
                return result;
            }
            cacheTool.saveVendor(vendor.getVendorId() + "", vendor);
        }
        VendorInfoDto vendorInfoDto = VendorMapStruct.MAPPER.vendorToVendorInfoDto(vendor);
        result.setData(vendorInfoDto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 供应商web端银行卡详细信息
     *
     * @param vendorId
     * @return MerchantResult<VendorProFileInfoDto>
     */
    @Override
    public MerchantResult<VendorProFileInfoDto> getProFileByKey(Long vendorId) {
        MerchantResult<VendorProFileInfoDto> result = new MerchantResult<VendorProFileInfoDto>();
        Vendor vendor = cacheTool.findVendorByKey(vendorId + "");
        if (vendor == null) {
            vendor = vendorMapper.selectByPrimaryKey(vendorId);
            if (vendor == null) {
                result.setSuccess(false);
                return result;
            }
            cacheTool.saveVendor(vendor.getVendorId() + "", vendor);
        }
        VendorProFileInfoDto vendorProFileInfoDto = VendorMapStruct.MAPPER.vendorToVendorProFileInfoDto(vendor);
        result.setData(vendorProFileInfoDto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据手机号查询供应商信息
     *
     * @param contactsTel
     * @return MerchantResult<List<VendorDto>>
     */
    @Override
    public MerchantResult<List<VendorDto>> getVendorListByContactsTel(String contactsTel) {
        MerchantResult<List<VendorDto>> merchantResult = new MerchantResult<>();
        List<Vendor> vendors =
                vendorMapper.selectList(new EntityWrapper<Vendor>().eq("contactsTel", contactsTel));
        List<VendorDto> vendorDtos = new ArrayList<>();
        if (vendors == null || vendors.size() == 0) {
            merchantResult.setData(vendorDtos);
            return merchantResult;
        }
        vendorDtos = VendorMapStruct.MAPPER.vendorsToVendorDtos(vendors);
        merchantResult.setSuccess(true);
        merchantResult.setData(vendorDtos);
        return merchantResult;
    }

    /**
     * 获取供应商区域信息
     *
     * @param vendorId
     * @return MerchantResult<List<AreaInfoDto>>
     */
    @Override
    public MerchantResult<List<AreaInfoDto>> getVendorAreaInfo(Long vendorId) {
        MerchantResult<List<AreaInfoDto>> result = new MerchantResult<>();
        List<Area> areas = areaMapper.getByVendorId(vendorId);
        if (areas == null || areas.size() == 0) {
            return result;
        }
        List<AreaInfoDto> areaInfoDtos = AreaMapStruct.MAPPER.areasTOAreaInfoDtos(areas);
        result.setSuccess(true);
        result.setData(areaInfoDtos);
        return result;
    }

    /**
     * 根据vendorIds查询供应商信息
     *
     * @param vendorIds
     * @return
     */
    @Override
    public MerchantResult<List<VendorDto>> getVendorListByVendorIds(List<Long> vendorIds) {
        List<Vendor> vendors = vendorMapper.getBatchVendorIds(vendorIds);
        MerchantResult<List<VendorDto>> result = new MerchantResult<>();
        if (vendors == null || vendors.size() == 0) {
            return result;
        }
        List<VendorDto> vendorDtos = VendorMapStruct.MAPPER.vendorsToVendorDtos(vendors);
        result.setData(vendorDtos);
        result.setSuccess(true);
        return result;
    }


    /**
     * 通过商户号集合查询供应商集合
     * @param unionPayMIDs
     * @return
     */
    @Override
    public MerchantResult<List<VendorDto>> getVendorListByUnionPayMID(List<String> unionPayMIDs) {
        List<Vendor> vendors = vendorMapper.getBatchUnionPayMID(unionPayMIDs);
        MerchantResult<List<VendorDto>> result = new MerchantResult<>();
        if (vendors == null || vendors.size() == 0) {
            return result;
        }
        List<VendorDto> vendorDtos = VendorMapStruct.MAPPER.vendorsToVendorDtos(vendors);
        result.setData(vendorDtos);
        result.setSuccess(true);
        return result;
    }

    @Override
    public MerchantResult<List<VendorDto>> getVendorListByUnionPayCID(List<String> unionPayCIDs) {
        List<Vendor> vendors = vendorMapper.getBatchUnionPayCID(unionPayCIDs);
        MerchantResult<List<VendorDto>> result = new MerchantResult<>();
        if (vendors == null || vendors.size() == 0) {
            return result;
        }
        List<VendorDto> vendorDtos = VendorMapStruct.MAPPER.vendorsToVendorDtos(vendors);
        result.setData(vendorDtos);
        result.setSuccess(true);
        return result;
    }

    /**
     * 依据供应商编号取得供应商信息;
     *
     * @param vendorCode
     * @return MerchantResult<VendorDto>
     */

    @Override
    public MerchantResult<VendorDto> getVendorByVendorCode(String vendorCode) {

        MerchantResult<VendorDto> result = new MerchantResult();
        Vendor vendor = vendorMapper.getBatchVendorByVendorCode(vendorCode);
        if (vendor != null) {
            result.setData(VendorMapStruct.MAPPER.vendorToVendorDto(vendor));
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 根据 VendorRequest里的提供的条件进行查询
     * @param vendorRequest
     * @return
     */
    @Override
    public MerchantResult<List<VendorDto>> getVendorListByVendorRequest(VendorRequest vendorRequest) {
        MerchantResult<List<VendorDto>> result = new MerchantResult();
        List<Vendor> vendors =
            vendorMapper.getVendorListByVendorRequest(vendorRequest);
        if(vendors == null || vendors.size() == 0){
            return result;
        }
        List<VendorDto> vendorDtos = VendorMapStruct.MAPPER.vendorsToVendorDtos(vendors);
        result.setData(vendorDtos);
        result.setSuccess(true);
        return result;
    }

    /**
     * 查询供应商所在区域商品数量
     * @param vendorId
     * @param areaId
     * @return
     */
    @Override
    public MerchantResult<Integer> getVendorAreaProductCount(Long vendorId, Integer areaId) {
        MerchantResult<Integer> result = new MerchantResult<>();
        Integer count = productMapper.selectCount(new EntityWrapper<Product>().
            eq("vendorId", vendorId).
            eq("areaId", areaId));
        result.setSuccess(true);
        result.setData(count);
        return result;
    }
}
