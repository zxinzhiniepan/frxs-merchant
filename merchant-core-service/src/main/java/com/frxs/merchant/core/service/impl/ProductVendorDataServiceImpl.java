/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.common.domain.Money;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.ProductVendorData;
import com.frxs.merchant.common.dal.entity.ProductVendorDataAttr;
import com.frxs.merchant.common.dal.entity.ProductVendorDataImg;
import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.dal.enums.ProductSpecEnum;
import com.frxs.merchant.core.exception.BaseMerchantException;
import com.frxs.merchant.core.exception.ProductBizException;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.ProductVendorDataAttrMapper;
import com.frxs.merchant.core.mapper.ProductVendorDataImgMapper;
import com.frxs.merchant.core.mapper.ProductVendorDataMapper;
import com.frxs.merchant.core.mapper.VendorMapper;
import com.frxs.merchant.core.mapstruct.ProductVendorDataAttrMapStruct;
import com.frxs.merchant.core.mapstruct.ProductVendorDataImgMapStruct;
import com.frxs.merchant.core.mapstruct.ProductVendorDataMapStruct;
import com.frxs.merchant.core.service.ProductVendorDataService;
import com.frxs.merchant.service.api.dto.ProductVendorDataAttrDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataImgDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataQueryDto;
import com.frxs.merchant.service.api.dto.ProductVendorDto;
import com.frxs.merchant.service.api.dto.VendorProductDataOperateDto;
import com.frxs.merchant.service.api.enums.ProductAuditStatusEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.promotion.service.api.enums.AuditStatusEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author fygu
 * @version $Id: ProductVendorDataService.java,v 0.1 2018年01月29日 10:06 $Exp
 */
@Service("productVendorDataService")
public class ProductVendorDataServiceImpl implements ProductVendorDataService {


  @Autowired
  private ProductVendorDataMapper productVendorDataMapper;

  @Autowired
  private ProductVendorDataImgMapStruct productVendorDataImgMapStruct;

  @Autowired
  private ProductVendorDataAttrMapper productVendorDataAttrMapper;

  @Autowired
  private ProductVendorDataImgMapper productVendorDataImgMapper;

  @Autowired
  private VendorMapper vendorMapper;

  @Autowired
  private TransactionTemplate newTransactionTemplate;

  @Autowired
  private MerchantResultHelper<MerchantResult> merchantResultHelper;


  /**
   * 供应商端 供应商商品管理列表展示 仅展示isVendorDel='FALSE' and isAreaDel ='FALSE'即供应商端或区域后台都没有删除。
   *
   * @param vendorId 供应商Id
   * @param auditStatus auditStatus false  展示待提交列 true 展示已提交列
   * @param page 分页
   * @return MerchantResult<PageDto               <               ProductVendorDataDto>>
   */
  @Override
  public MerchantResult<Page<ProductVendorDto>> productVendorDtoList(Long vendorId, boolean auditStatus, Page<ProductVendorDto> page) {

    MerchantResult<Page<ProductVendorDto>> merchantResult = new MerchantResult<Page<ProductVendorDto>>();
    if (vendorId == null) {
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PARAM_ILLEGAL, "供应商id不能为空"));
      return merchantResult;
    }
    List<ProductVendorDto> productVendorDtoList = new LinkedList<>();
    try {
      Map<String, Object> queryMap = new HashMap<>();
      queryMap.put("vendorId", vendorId);
      queryMap.put("auditStatus", auditStatus);
      //待提交：驳回、草稿，已提交：待审核、审核通过
      int total = productVendorDataMapper.countProductVendorList(queryMap);
      if (total > 0) {
        Page<ProductVendorDto> queryPage = new Page<>(page.getCurrent(), page.getSize());
        List<ProductVendorData> productVendorDataList = productVendorDataMapper.productVendorList(queryPage.getOffset(), queryPage.getSize(), queryMap);
        List<Long> vendorProductDataIds = productVendorDataList.stream().map(ProductVendorData::getVendorProductDataId).collect(Collectors.toList());
        //查询供应商图片
        EntityWrapper<ProductVendorDataImg> imgEntityWrapper = new EntityWrapper<>();
        imgEntityWrapper.in("vendorProductDataId", vendorProductDataIds);
        List<ProductVendorDataImg> productVendorDataImgList = productVendorDataImgMapper.selectList(imgEntityWrapper);
        Map<Long, List<ProductVendorDataImg>> imgGroup = productVendorDataImgList.stream().collect(Collectors.groupingBy(ProductVendorDataImg::getVendorProductDataId));

        //供应商规格
        EntityWrapper<ProductVendorDataAttr> attrEntityWrapper = new EntityWrapper<>();
        attrEntityWrapper.in("vendorProductDataId", vendorProductDataIds);
        List<ProductVendorDataAttr> productVendorDataAttrList = productVendorDataAttrMapper.selectList(attrEntityWrapper);
        Map<Long, List<ProductVendorDataAttr>> attrGroup = productVendorDataAttrList.stream().collect(Collectors.groupingBy(ProductVendorDataAttr::getVendorProductDataId));

        for (ProductVendorData productVendorData : productVendorDataList) {
          ProductVendorDto productVendorDto = new ProductVendorDto();
          List<ProductVendorDataAttr> attrs = attrGroup.get(productVendorData.getVendorProductDataId());
          List<ProductVendorDataImg> imgs = imgGroup.get(productVendorData.getVendorProductDataId());

          ProductVendorDataDto productVendorDataDto = ProductVendorDataMapStruct.MAPPER.fromProductVendorData(productVendorData);
          //处理图片
          List<ProductVendorDataImgDto> imgList = productVendorDataImgMapStruct.fromProductVendorDataImgs(imgs);

          //处理规格
          List<ProductVendorDataAttrDto> attrList = ProductVendorDataAttrMapStruct.MAPPER.fromProductVendorDataAttrs(attrs);

          productVendorDto.setAttrs(attrList);
          productVendorDto.setImgs(imgList);
          productVendorDto.setProdVendorData(productVendorDataDto);
          productVendorDtoList.add(productVendorDto);
        }
      }
      page.setTotal(total);
      page.setRecords(productVendorDtoList);
      merchantResult.setData(page);
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductVendorDataServiceImpl:供应商商品]商品列表查询异常");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_QUERY_ERROR, "供应商商品查询失败"));
    }
    return merchantResult;
  }

  @Override
  public MerchantResult areaDel(VendorProductDataOperateDto vendorProductDataOperateDto) {
    MerchantResult merchantResult = new MerchantResult();
    try {
      List<ProductVendorData> productVendorDataList = productVendorDataMapper.selectVendorDataList(vendorProductDataOperateDto.getVendorProductDataIdList());

      List<Long> idList = new ArrayList<>();
      for (ProductVendorData productVendorData : productVendorDataList) {
        if (Boolean.TRUE.toString().toUpperCase().equals(productVendorData.getIsAreaDel())) {
          continue;
        }
        if (!ProductAuditStatusEnum.PASS.getValueDefined().equals(productVendorData.getAuditStatus())) {
          merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT,
              new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_DELETE_ERROR, String.format("商品【%s】不是审核通过状态，不能删除", productVendorData.getVendorProductName())));
          return merchantResult;
        }
        idList.add(productVendorData.getVendorProductDataId());
      }
      if (!idList.isEmpty()) {
        //TODO SQL
        productVendorDataMapper.updateAreaDel(idList, vendorProductDataOperateDto.getOperateId(), vendorProductDataOperateDto.getOperateName());
      }
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductVendorDataServiceImpl:供应商商品]商品删除区域操作异常");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_DELETE_ERROR, "供应商商品删除失败"));
    }
    return merchantResult;

  }

  @Override
  public MerchantResult isVendorDel(VendorProductDataOperateDto vendorProductDataOperateDto) {
    MerchantResult merchantResult = new MerchantResult();
    try {
      List<Long> vendorProductDataIdList = vendorProductDataOperateDto.getVendorProductDataIdList();
      Long vendorProductDataId = vendorProductDataIdList.get(0);
      ProductVendorData productVendorData = productVendorDataMapper.selectById(vendorProductDataId);
      if (Boolean.FALSE.toString().toUpperCase().equals(productVendorData.getIsVendorDel())) {
        if (!ProductAuditStatusEnum.DRAFT.getValueDefined().equals(productVendorData.getAuditStatus())) {
          merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_DELETE_ERROR, "已提交的商品不能删除"));
          return merchantResult;
        }
        //TODO SQL
        productVendorDataMapper.updateVendorDel(vendorProductDataId, vendorProductDataOperateDto.getOperateId(), vendorProductDataOperateDto.getOperateName());
      }
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductVendorDataServiceImpl:供应商商品]商品供应商删除操作异常");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_DELETE_ERROR, "供应商商品删除失败"));
    }
    return merchantResult;
  }

  @Override
  public MerchantResult updateAuditStatus(VendorProductDataOperateDto vendorProductDataOperateDto) {
    MerchantResult merchantResult = new MerchantResult();
    try {
      if (vendorProductDataOperateDto.getVendorProductDataIdList() == null || vendorProductDataOperateDto.getVendorProductDataIdList().isEmpty()) {
        merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, "供应商商品id不能为空"));
        return merchantResult;
      }
      if (vendorProductDataOperateDto.getAuditStatus() == null) {
        merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, "修改的状态值不能为空"));
        return merchantResult;
      }
      //如果输入参数auditStatus 不在AuditStatusEnum枚举值中 则返回失败
      ProductAuditStatusEnum auditStatusEnum = ProductAuditStatusEnum.getByValue(vendorProductDataOperateDto.getAuditStatus());
      if (auditStatusEnum == null) {
        merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, "修改的状态值有误"));
        return merchantResult;
      }
      //查询供应商品数据
      EntityWrapper<ProductVendorData> vendorDataEntityWrapper = new EntityWrapper<>();
      vendorDataEntityWrapper.in("vendorProductDataId", vendorProductDataOperateDto.getVendorProductDataIdList());
      List<ProductVendorData> vendorDatas = productVendorDataMapper.selectList(vendorDataEntityWrapper);

      List<Long> idList = new ArrayList<>();
      if (!vendorDatas.isEmpty()) {
        for (ProductVendorData data : vendorDatas) {
          switch (auditStatusEnum) {
            case PENDING:
              //提交审核
              if (!ProductAuditStatusEnum.DRAFT.getValueDefined().equals(data.getAuditStatus())) {
                throw new ProductBizException(ErrorCodeDetailEnum.VENDORDATA_SUBMIT_ERROR, String.format("商品【%s】当前不是草稿状态不能提交审核", data.getVendorProductName()));
              }
              idList.add(data.getVendorProductDataId());
              break;
            case PASS:
              //审核通过
            case REJECT:
              //审核驳回
              if (!AuditStatusEnum.PENDING.getValueDefined().equals(data.getAuditStatus())) {
                throw new ProductBizException(ErrorCodeDetailEnum.VENDORDATA_SUBMIT_ERROR, String.format("商品【%s】当前不是待审核状态不能审核", data.getVendorProductName()));
              }
              idList.add(data.getVendorProductDataId());
              break;
            default:
              LogUtil.info("没有这个操作值");
          }
        }
        if (!idList.isEmpty()) {
          //TODO SQL
          productVendorDataMapper.updateAuditStatus(idList,
              vendorProductDataOperateDto.getAuditRession(),
              vendorProductDataOperateDto.getAuditStatus(),
              vendorProductDataOperateDto.getOperateId(),
              vendorProductDataOperateDto.getOperateName());
        }
      }
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (ProductBizException pbe) {
      LogUtil.error(pbe, "[ProductVendorDataService:供应商商品]供应商商品提交审核失败");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, pbe);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductVendorDataService:供应商商品]供应商商品提交审核失败");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_SUBMIT_ERROR, "供应商商品提交审核失败"));
    }
    return merchantResult;
  }

  @Override
  public MerchantResult createProductVendor(ProductVendorDto productVendorDto) {

    return newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
      MerchantResult result = new MerchantResult();

      @Override
      public MerchantResult doInTransaction(TransactionStatus transactionStatus) {

        try {
          ProductVendorDataDto productVendorDataDto = productVendorDto.getProdVendorData();

          List<ProductVendorDataAttrDto> ProductVendorDataAttrDtoList = productVendorDto
              .getAttrs();

          List<ProductVendorDataImgDto> productVendorDataImgDtoList = productVendorDto
              .getImgs();

          if (productVendorDataDto == null || ProductVendorDataAttrDtoList == null || productVendorDataImgDtoList == null) {
            merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_CREATE_ERROR, "供应商商品创建失败"));
            return result;
          }
          Vendor vendor = vendorMapper.selectById(productVendorDataDto.getVendorId());
          ProductVendorData productVendorData = new ProductVendorData();
          Date date = new Date();
          productVendorData.setVendorProductName(productVendorDataDto.getVendorProductName());
          productVendorData.setVendorId(productVendorDataDto.getVendorId());
          productVendorData.setVendorCode(vendor.getVendorCode());
          productVendorData.setVendorName(vendor.getVendorName());
          productVendorData.setVendorProductDesc(productVendorDataDto.getVendorProductDesc());
          productVendorData.setAuditStatus(ProductAuditStatusEnum.DRAFT.getValueDefined());
          if (ProductVendorDataAttrDtoList.size() > 1) {
            productVendorData.setSpecType(ProductSpecEnum.MULTI.getValueDefined());
          } else {
            productVendorData.setSpecType(ProductSpecEnum.SINGLE.getValueDefined());
          }
          productVendorData.setIsVendorDel(Boolean.FALSE.toString().toUpperCase());
          productVendorData.setIsAreaDel(Boolean.FALSE.toString().toUpperCase());
          productVendorData.setCreateUserId(productVendorDataDto.getCreateUserId());
          productVendorData.setCreateUserName(productVendorDataDto.getCreateUserName());
          productVendorData.setTmCreate(date);
          productVendorData.setTmPublish(date);
          productVendorDataMapper.insertProductVendorData(productVendorData);
          Long vendorProductDataId = productVendorData.getVendorProductDataId();

          insertVendorProductAttr(vendorProductDataId, productVendorDataDto.getCreateUserId(), productVendorDataDto.getCreateUserName(), ProductVendorDataAttrDtoList);

          insertVendorProductImg(vendorProductDataId, productVendorDataDto.getCreateUserId(), productVendorDataDto.getCreateUserName(), productVendorDataImgDtoList);
          merchantResultHelper.fillWithSuccess(result);
        } catch (Exception e) {
          LogUtil.error(e, "[ProductVendorDataService:供应商商品]供应商商品创建失败");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_CREATE_ERROR, "供应商商品创建失败"));
          // 事务回滚
          transactionStatus.setRollbackOnly();
        }
        return result;
      }
    });
  }

  /**
   * 新增供应商商品规格
   *
   * @param vendorProductDataId 供应商商品id
   * @param userId 用户id
   * @param userName 用户名称
   * @param attrList 规格列表
   */
  private void insertVendorProductAttr(Long vendorProductDataId, Long userId, String userName, List<ProductVendorDataAttrDto> attrList) {

    //清理原规格
    EntityWrapper<ProductVendorDataAttr> imgEntityWrapper = new EntityWrapper<>();
    imgEntityWrapper.where("vendorProductDataId = {0}", vendorProductDataId);
    productVendorDataAttrMapper.delete(imgEntityWrapper);

    Date date = new Date();
    if (attrList != null && !attrList.isEmpty()) {
      for (ProductVendorDataAttrDto productVendorDataAttrDto : attrList) {
        ProductVendorDataAttr productVendorDataAttr = new ProductVendorDataAttr();
        productVendorDataAttr.setVendorProductDataId(vendorProductDataId);
        productVendorDataAttr.setVendorProductAttr(productVendorDataAttrDto.getVendorProductAttr());
        productVendorDataAttr.setVendorProductAmt(new Money(productVendorDataAttrDto.getVendorProductAmt()));
        productVendorDataAttr.setVendorProductQty(productVendorDataAttrDto.getVendorProductQty());
        productVendorDataAttr.setCreateUserId(userId);
        productVendorDataAttr.setCreateUserName(userName);
        productVendorDataAttr.setTmCreate(date);
        productVendorDataAttrMapper.insert(productVendorDataAttr);
      }
    }
  }

  /**
   * 处理供应商商品图片
   *
   * @param vendorProductDataId 供应商商品id
   * @param userId 操作人id
   * @param userName 操作人名称
   * @param productVendorDataImgDtoList 图片列表
   */
  private void insertVendorProductImg(Long vendorProductDataId, Long userId, String userName, List<ProductVendorDataImgDto> productVendorDataImgDtoList) {

    //清理原图片
    EntityWrapper<ProductVendorDataImg> imgEntityWrapper = new EntityWrapper<>();
    imgEntityWrapper.where("vendorProductDataId = {0}", vendorProductDataId);
    productVendorDataImgMapper.delete(imgEntityWrapper);

    if (productVendorDataImgDtoList != null && productVendorDataImgDtoList.size() > 0) {
      Date date = new Date();
      for (ProductVendorDataImgDto productVendorDataImgDto : productVendorDataImgDtoList) {
        ProductVendorDataImg productVendorDataImg = new ProductVendorDataImg();
        productVendorDataImg.setVendorProductDataId(vendorProductDataId);
        productVendorDataImg.setOriginalImgUrl(productVendorDataImgDto.getOriginalImgUrl());
        productVendorDataImg.setImgUrl60(productVendorDataImgDto.getImgUrl60());
        productVendorDataImg.setImgUrl120(productVendorDataImgDto.getImgUrl120());
        productVendorDataImg.setImgUrl200(productVendorDataImgDto.getImgUrl200());
        productVendorDataImg.setImgUrl400(productVendorDataImgDto.getImgUrl400());
        productVendorDataImg.setSortSeq(productVendorDataImgDto.getSortSeq());
        productVendorDataImg.setCreateUserId(userId);
        productVendorDataImg.setCreateUserName(userName);
        productVendorDataImg.setTmCreate(date);
        productVendorDataImgMapper.insert(productVendorDataImg);
      }
    }
  }

  @Override
  public MerchantResult<ProductVendorDto> previewProductVendor(Long vendorProductDataId) {
    MerchantResult<ProductVendorDto> merchantResult = new MerchantResult<>();
    ProductVendorDto productVendorDto = new ProductVendorDto();
    List<ProductVendorDataAttrDto> productVendorDataAttrDtoList = new ArrayList<>();
    List<ProductVendorDataImgDto> productVendorDataImgDtoList = new ArrayList<>();
    //ProductVendorDataDto productVendorDataDto = new ProductVendorDataDto();
    try {
      ProductVendorData productVendorData = productVendorDataMapper.selectById(vendorProductDataId);
      if (productVendorData == null) {
        merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT,
            new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_DELETE_ERROR, "供应商商品查询失败"));
        return merchantResult;
      }
      //TODO SQL
      List<ProductVendorDataImg> productVendorDataImgList = productVendorDataImgMapper
          .selectVendorDataImgByCondition(null, false, vendorProductDataId);
      List<ProductVendorDataAttr> productVendorDataAttrList = productVendorDataAttrMapper
          .selectVendorDataAttrByCondition(null, false, vendorProductDataId);
      if (productVendorDataImgList == null || productVendorDataAttrList == null) {
        merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT,
            new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_DELETE_ERROR, "供应商商品查询失败"));
        return merchantResult;
      }

      ProductVendorDataDto productVendorDataDto = ProductVendorDataMapStruct.MAPPER.fromProductVendorData(productVendorData);
      //处理图片
      for (ProductVendorDataImg productVendorDataImg : productVendorDataImgList) {
        ProductVendorDataImgDto productVendorDataImgDto = productVendorDataImgMapStruct
            .fromProductVendorDataImg(productVendorDataImg);
        productVendorDataImgDtoList.add(productVendorDataImgDto);
      }
      //处理规格
      for (ProductVendorDataAttr productVendorDataAttr : productVendorDataAttrList) {
        ProductVendorDataAttrDto productVendorDataAttrDto = ProductVendorDataAttrMapStruct.MAPPER
            .fromProductVendorDataAttr(productVendorDataAttr);
        productVendorDataAttrDtoList.add(productVendorDataAttrDto);
      }
      productVendorDto.setProdVendorData(productVendorDataDto);
      productVendorDto.setImgs(productVendorDataImgDtoList);
      productVendorDto.setAttrs(productVendorDataAttrDtoList);
      merchantResult.setData(productVendorDto);
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductVendorDataServiceImpl:供应商商品]商品详情查询异常");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_DELETE_ERROR, "供应商商品查询失败"));
    }
    return merchantResult;
  }

  @Override
  public MerchantResult<Page<ProductVendorDataDto>> productVendorDataList(ProductVendorDataQueryDto productVendorDataQueryDto, Page<ProductVendorDataDto> page) {
    MerchantResult<Page<ProductVendorDataDto>> merchantResult = new MerchantResult<Page<ProductVendorDataDto>>();
    try {
      int total = 0;
      List<ProductVendorDataDto> productVendorDataDtos = new ArrayList<>();
      if (productVendorDataQueryDto != null) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(productVendorDataQueryDto);
        total = productVendorDataMapper.countProductVendorDataList(jsonObject);
        if (total > 0) {
          Page<ProductVendorDataDto> queryPage = new Page<>(page.getCurrent(), page.getSize());
          List<ProductVendorData> list = productVendorDataMapper
              .productVendorDataList(queryPage.getOffset(), queryPage.getSize(), jsonObject);
          for (ProductVendorData productVendorData : list) {
            ProductVendorDataDto productVendorDataDto = ProductVendorDataMapStruct.MAPPER.fromProductVendorData(productVendorData);
            productVendorDataDtos.add(productVendorDataDto);
          }
        }
      }
      page.setTotal(total);
      page.setRecords(productVendorDataDtos);
      merchantResult.setData(page);
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductVendorDataServiceImpl:供应商商品]供应商商品列表查询失败");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_QUERY_ERROR, "供应商商品列表查询失败"));
    }
    return merchantResult;

  }

  /**
   * 供应商商品编辑保存
   *
   * @return MerchantResult
   */
  @Override
  public MerchantResult updateProductVendor(ProductVendorDto productVendorDto) {
    MerchantResult merchantResult = new MerchantResult();
    ProductVendorDataDto productVendorDataDto = productVendorDto.getProdVendorData();
    if (productVendorDataDto == null || productVendorDto.getAttrs() == null || productVendorDto.getImgs() == null) {
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_MODIFY_ERROR, "供应商商品参数异常"));
      return merchantResult;
    }
    //仅能修改草稿状态和驳回状态的供应商商品
    ProductVendorData productVendorData = productVendorDataMapper.selectById(productVendorDataDto.getVendorProductDataId());
    if (productVendorData.getAuditStatus().equals(ProductAuditStatusEnum.DRAFT.getValueDefined()) || productVendorData.getAuditStatus().equals(ProductAuditStatusEnum.REJECT.getValueDefined())) {
      return newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
        @Override
        public MerchantResult doInTransaction(TransactionStatus transactionStatus) {

          try {

            ProductVendorData productVendorData = ProductVendorDataMapStruct.MAPPER.toProductVendorData(productVendorDto.getProdVendorData());
            productVendorData.setAuditStatus(ProductAuditStatusEnum.DRAFT.getValueDefined());
            if (productVendorDto.getAttrs().size() > 1) {
              productVendorData.setSpecType(ProductSpecEnum.MULTI.getValueDefined());
            } else {
              productVendorData.setSpecType(ProductSpecEnum.SINGLE.getValueDefined());
            }
            productVendorDataMapper.updateById(productVendorData);

            //处理规格
            List<ProductVendorDataAttrDto> productVendorDataAttrDtoList = productVendorDto.getAttrs();

            insertVendorProductAttr(productVendorData.getVendorProductDataId(), productVendorDataDto.getModifyUserId(), productVendorDataDto.getModifyUserName(), productVendorDataAttrDtoList);

            //处理图片
            insertVendorProductImg(productVendorData.getVendorProductDataId(), productVendorDataDto.getModifyUserId(), productVendorDataDto.getModifyUserName(), productVendorDto.getImgs());

            merchantResultHelper.fillWithSuccess(merchantResult);
          } catch (Exception e) {
            LogUtil.error(e, "[ProductVendorDataService:供应商商品]供应商商品修改失败");
            merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_MODIFY_ERROR, "供应商商品修改失败"));
            // 事务回滚
            transactionStatus.setRollbackOnly();
          }
          return merchantResult;
        }
      });
    }
    merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.VENDOR_PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.VENDORDATA_MODIFY_ERROR, "供应商商品当前状态不能修改"));
    return merchantResult;
  }
}
