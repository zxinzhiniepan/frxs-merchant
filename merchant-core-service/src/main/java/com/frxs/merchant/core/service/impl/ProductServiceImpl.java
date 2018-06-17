/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.common.domain.Money;
import com.frxs.framework.util.common.StringUtil;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.AreaServiceamtCode;
import com.frxs.merchant.common.dal.entity.Product;
import com.frxs.merchant.common.dal.entity.ProductAttrvalRelation;
import com.frxs.merchant.common.dal.entity.ProductDesc;
import com.frxs.merchant.common.dal.entity.ProductImg;
import com.frxs.merchant.common.dal.entity.ProductInfo;
import com.frxs.merchant.common.dal.entity.ProductServiceDetail;
import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorAreaRef;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.dal.enums.ProductSaleTypeEnum;
import com.frxs.merchant.common.dal.enums.ProductSpecEnum;
import com.frxs.merchant.core.dubbo.PromotionDubboProcess;
import com.frxs.merchant.core.exception.BaseMerchantException;
import com.frxs.merchant.core.exception.ProductBizException;
import com.frxs.merchant.core.generator.SkuGenerator;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.ProductAttrvalRelationMapper;
import com.frxs.merchant.core.mapper.ProductDescMapper;
import com.frxs.merchant.core.mapper.ProductImgMapper;
import com.frxs.merchant.core.mapper.ProductInfoMapper;
import com.frxs.merchant.core.mapper.ProductMapper;
import com.frxs.merchant.core.mapper.ProductServiceDetailMapper;
import com.frxs.merchant.core.mapper.VendorAreaRefMapper;
import com.frxs.merchant.core.mapper.VendorMapper;
import com.frxs.merchant.core.mapstruct.ProductAttrvalRelationMapStruct;
import com.frxs.merchant.core.mapstruct.ProductImgMapStruct;
import com.frxs.merchant.core.mapstruct.ProductMapStruct;
import com.frxs.merchant.core.service.AreaServiceamtCodeService;
import com.frxs.merchant.core.service.ProductService;
import com.frxs.merchant.service.api.dto.ProductAttrvalRelationDto;
import com.frxs.merchant.service.api.dto.ProductDescDto;
import com.frxs.merchant.service.api.dto.ProductDto;
import com.frxs.merchant.service.api.dto.ProductImgDto;
import com.frxs.merchant.service.api.dto.ProductQueryDto;
import com.frxs.merchant.service.api.dto.ProductSortDto;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import com.frxs.merchant.service.api.enums.AreaServiceamtCodeEnum;
import com.frxs.merchant.service.api.enums.ProductImgTypeEnum;
import com.frxs.merchant.service.api.enums.ProductSaleLimitTimeUnitEnum;
import com.frxs.merchant.service.api.enums.ProductSkuStatusEnum;
import com.frxs.merchant.service.api.enums.StatusEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.promotion.service.api.dto.PreproductDto;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author fygu
 * @version $Id: ProductService.java,v 0.1 2018年01月26日 12:23 $Exp
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private ProductImgMapper productImgMapper;

  @Autowired
  private ProductServiceDetailMapper productServiceDetailMapper;

  @Autowired
  private ProductAttrvalRelationMapper productAttrvalRelationMapper;

  @Autowired
  private ProductInfoMapper productInfoMapper;

  @Autowired
  private MerchantResultHelper<MerchantResult> merchantResultHelper;

  @Autowired
  private TransactionTemplate newTransactionTemplate;

  @Autowired
  private SkuGenerator skuGenerator;

  @Autowired
  private AreaServiceamtCodeService areaServiceamtCodeService;

  @Autowired
  private ProductDescMapper productDescMapper;

  @Autowired
  private VendorMapper vendorMapper;

  @Autowired
  private PromotionDubboProcess promotionDubboProcess;

  @Autowired
  private VendorAreaRefMapper vendorAreaRefMapper;


  /**
   * 创建单规格商品
   *
   * @param product 商品信息
   * @param productAttrvalRelation 商品关联的属性
   * @param productImgList 商品图片
   */
  @Override
  public MerchantResult<Long> createSingleProduct(ProductDto product, ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList) {

    try {
      Preconditions.checkArgument(product.getAreaId() != null, "区域id不能为空");
      Preconditions.checkArgument(product.getVendorId() != null, "供应商id不能为空");

      checkProductArgs(product, productAttrvalRelation, productImgList);
    } catch (IllegalArgumentException e) {
      LogUtil.error(e, "[ProductService:商品]商品创建参数异常");
      MerchantResult<Long> result = new MerchantResult<>();
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, e.getMessage()));
      return result;
    }
    return newTransactionTemplate.execute(new TransactionCallback<MerchantResult<Long>>() {

      MerchantResult<Long> result = new MerchantResult<>();

      @Override
      public MerchantResult<Long> doInTransaction(TransactionStatus transactionStatus) {

        try {
          //新增商品信息
          product.setStock(product.getStock() == null ? BigDecimal.ZERO : product.getStock());
          ProductInfo productInfo = buildProductInfo(null, product);
          productInfo.setAreaId(product.getAreaId());
          productInfo.setCreateUserId(product.getCreateUserId());
          productInfo.setCreateUserName(product.getCreateUserName());
          productInfo.setTmCreate(new Date());
          productInfoMapper.insert(productInfo);

          //商品SKU
          Product insertProduct = buildProduct(productInfo.getProductInfoId(), product);
          insertProduct.setSku(skuGenerator.generatorSkuCode());
          insertProduct.setSkuStatus(ProductSkuStatusEnum.UP.getValueDefined());
          insertProduct.setSpecType(ProductSpecEnum.SINGLE.getValueDefined());
          insertProduct.setTmCreate(new Date());
          productMapper.insert(insertProduct);

          //商品详情
          ProductDesc productDesc = new ProductDesc();
          productDesc.setProductInfoId(productInfo.getProductInfoId());
          productDesc.setBriefDesc(product.getBriefDesc());
          productDesc.setShareTitle(product.getShareTitle());
          productDesc.setCreateUserId(insertProduct.getCreateUserId());
          productDesc.setCreateUserName(insertProduct.getCreateUserName());
          productDesc.setTmCreate(new Date());
          productDescMapper.insertDesc(productDesc);

          //商品属性
          productAttrvalRelation.setAttrName("规格");
          productAttrvalRelation.setCreateUserId(insertProduct.getCreateUserId());
          productAttrvalRelation.setCreateUserName(insertProduct.getCreateUserName());
          createSingleProductAttr(insertProduct, productAttrvalRelation);

          //商品图片
          createProductImg(insertProduct, insertProduct.getCreateUserId(), insertProduct.getCreateUserName(), productImgList);

          //商品服务明细
          createPreproductServiceAmt(insertProduct);

          result.setData(insertProduct.getProductId());
          merchantResultHelper.fillWithSuccess(result);
        } catch (IllegalArgumentException iae) {
          LogUtil.error(iae, "[ProductService:商品]商品创建参数异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, iae.getMessage()));
        } catch (ProductBizException pbe) {
          LogUtil.error(pbe, "[ProductService:商品]商品创建业务异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, pbe);
        } catch (Exception e) {
          LogUtil.error(e, "[ProductService:商品]商品创建异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_CREATE_ERROR, "商品创建失败"));
        }
        if (!result.isSuccess()) {
          // 事务回滚
          transactionStatus.setRollbackOnly();
        }
        return result;
      }
    });

  }

  /**
   * 活动商品服务明细
   *
   * @param product 商品信息
   */
  private void createPreproductServiceAmt(Product product) {

    //区域服务费用配置
    List<AreaServiceamtCode> areaServiceamtList = areaServiceamtCodeService.queryAreaServiceamt(product.getAreaId());
    Map<String, BigDecimal> rateMap = new LinkedHashMap<>();
    if (areaServiceamtList == null || areaServiceamtList.isEmpty()) {
      //没有区域服务费用配置则读取系统统一配置
      List<SysDictDetailDto> details = areaServiceamtCodeService.queryServiceamtSet();
      if (details.isEmpty()) {
        throw new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_SERVICE_DETAIL_ERROR, "商品平台服务费比例未配置");
      }
      details.sort((a, b) -> new BigDecimal(a.getDictValue()).compareTo(new BigDecimal(b.getDictValue())));
      for (SysDictDetailDto detail : details) {
        rateMap.put(detail.getDetailCode(), new BigDecimal(detail.getDictValue()));
      }
    } else {
      //将服务费用按比例从例按从大到小排序
      Collections.sort(areaServiceamtList, Comparator.comparing(AreaServiceamtCode::getRate));
      for (AreaServiceamtCode rateCode : areaServiceamtList) {
        rateMap.put(rateCode.getCode(), rateCode.getRate());
      }
    }
    //删除原服务明细
    if (product.getProductId() != null) {
      Map<String, Object> deleteMap = new HashMap<>();
      deleteMap.put("productId", product.getProductId());
      productServiceDetailMapper.deleteByMap(deleteMap);
    }

    for (AreaServiceamtCodeEnum code : AreaServiceamtCodeEnum.values()) {
      if (!rateMap.containsKey(code.getValueDefined())) {
        rateMap.put(code.getValueDefined(), BigDecimal.ZERO);
      }
    }

    List<ProductServiceDetail> serviceDetailList = new ArrayList<>();
    ProductServiceDetail serviceDetail;
    Money serviceDetailAmt;
    Money serviceAmt = product.getPerServiceAmt();
    Money caledAmt = new Money();
    int i = 0;
    for (String codeKey : rateMap.keySet()) {
      AreaServiceamtCodeEnum codeEnum = AreaServiceamtCodeEnum.getAreaServiceamtCodeEnum(codeKey);
      serviceDetail = new ProductServiceDetail();
      if (i == (rateMap.size() - 1)) {
        serviceDetailAmt = serviceAmt.subtract(caledAmt);
      } else {
        serviceDetailAmt = serviceAmt.multiply(rateMap.get(codeKey)).divide(100);
        caledAmt.add(serviceAmt);
      }
      serviceDetail.setProductId(product.getProductId());
      serviceDetail.setServiceAmtCode(codeEnum.getValueDefined());
      serviceDetail.setServiceAmt(serviceDetailAmt);
      serviceDetail.setServiceRate(rateMap.get(codeKey));
      serviceDetail.setServiceDesc(codeEnum.getDesc());
      serviceDetail.setCreateUserId(product.getCreateUserId());
      serviceDetail.setCreateUserName(product.getCreateUserName());
      serviceDetail.setModifyUserId(product.getModifyUserId());
      serviceDetail.setModifyUserName(product.getModifyUserName());
      serviceDetail.setTmCreate(new Date());
      serviceDetailList.add(serviceDetail);
      i++;
    }
    //创建服务明细
    productServiceDetailMapper.insertBatch(serviceDetailList);
  }

  /**
   * 商品校验
   *
   * @param product 商品信息
   */
  private void checkProductArgs(ProductDto product, ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList) {

    Preconditions.checkArgument(StringUtil.isNotBlank(product.getProductName()), "商品名称不能为空");
    Preconditions.checkArgument(StringUtil.isNotBlank(product.getYieldly()), "商品产地不能为空");
    Preconditions.checkArgument(StringUtil.isNotBlank(product.getBrandName()), "商品品牌不能为空");
    Preconditions.checkArgument(StringUtil.isNotBlank(productAttrvalRelation.getAttrVal()), "商品规格不能为空");
    Preconditions.checkArgument(product.getPackageQty() != null, "商品包装数量不能为空");
    Preconditions.checkArgument(product.getLimitQty() != null, "商品限量不能为空");
    Preconditions.checkArgument(product.getSaleAmt() != null, "商品价格不能为空");
    Preconditions.checkArgument(product.getMarketAmt() != null, "商品市场价格不能为空");
    Preconditions.checkArgument(product.getMarketAmt().compareTo(product.getSaleAmt()) >= 0, "商品价格不能大于市场价");
    Preconditions.checkArgument(product.getPerServiceAmt() != null, "商品平台服务费不能为空");
    Preconditions.checkArgument(product.getPerServiceAmt().compareTo(product.getSaleAmt()) < 0, "商品平台服务费不能大于商品价格");
    Preconditions.checkArgument((product.getPerCommission() != null) && (BigDecimal.ZERO.compareTo(product.getPerCommission()) < 0), "商品门店提成不能为空或小于等于0");
    Money perCommission = new Money(product.getPerCommission());
    Money perServiceAmt = new Money(product.getPerServiceAmt());
    Money saleAmt = new Money(product.getSaleAmt());
    Preconditions.checkArgument(perCommission.compareTo(saleAmt) < 0, "门店提成不能大于商品价格");
    Preconditions.checkArgument(perCommission.add(perServiceAmt).compareTo(saleAmt) < 0, "商品每份提成加商品平台服务费不能大于商品价格");
    Preconditions.checkArgument(product.getSaleLimitTime() != null, "商品售后时限不能为空");
    Preconditions.checkArgument(StringUtil.isNotBlank(product.getSaleLimitTimeUnit()) && (ProductSaleLimitTimeUnitEnum.getProductSaleLimitTimeUnitEnum(product.getSaleLimitTimeUnit()) != null), "商品售后时限单位不能为空");
    Preconditions.checkArgument(productImgList != null && !productImgList.isEmpty(), "商品图片不能为空");
    Preconditions.checkArgument(productImgList.stream().anyMatch(t -> ProductImgTypeEnum.AD.getValueDefined().equals(t.getImgType())), "商品广告图不能为空");
    Preconditions.checkArgument(productImgList.stream().anyMatch(t -> ProductImgTypeEnum.PRIMARY.getValueDefined().equals(t.getImgType())), "商品主图不能为空");
  }

  /**
   * 构建商品信息
   *
   * @param productInfoId 商品信息id
   * @param product 商品
   * @return 商品信息
   */
  private ProductInfo buildProductInfo(Long productInfoId, ProductDto product) {

    //判断当前供应商是否在当前区域下
    EntityWrapper<VendorAreaRef> areaRefEntityWrapper = new EntityWrapper<>();
    areaRefEntityWrapper.where("vendorId={0} and areaId = {1} and IsDeleted = 'N'", product.getVendorId(), product.getAreaId());
    List<VendorAreaRef> vendorAreaRefs = vendorAreaRefMapper.selectList(areaRefEntityWrapper);
    if (vendorAreaRefs.isEmpty()) {
      //当前供应商不属于这个区域下不能保存
      throw new ProductBizException(ErrorCodeDetailEnum.PRODUCT_VENDOR_ERROR, "当前供应商不在该区域下");
    }

    Vendor vendor = vendorMapper.selectById(product.getVendorId());
    if (vendor == null || StatusEnum.DELETE.getValueDefined().equals(vendor.getVendorStatus())) {
      throw new ProductBizException(ErrorCodeDetailEnum.PRODUCT_VENDOR_ERROR, "当前供应商不存在，请选择其他供应商");
    }
    ProductInfo productInfo;
    if (productInfoId != null) {
      productInfo = productInfoMapper.selectById(productInfoId);
    } else {
      productInfo = new ProductInfo();
    }
    productInfo.setIsCreateTemplet(Boolean.FALSE.toString().toUpperCase());
    productInfo.setTotalStock(product.getStock());
    productInfo.setProductName(product.getProductName() != null ? StringUtil.trim(product.getProductName()) : product.getProductName());
    productInfo.setProductTitle(product.getProductTitle() != null ? StringUtil.trim(product.getProductTitle()) : product.getProductTitle());
    productInfo.setBrandName(product.getBrandName() != null ? StringUtil.trim(product.getBrandName()) : product.getBrandName());
    return productInfo;
  }

  /**
   * 构建商品
   *
   * @param productInfoId 商品信息id
   * @param product 商品
   * @return 商品sku
   */
  private Product buildProduct(Long productInfoId, ProductDto product) throws Exception {

    Product insertProduct = ProductMapStruct.MAPPER.productDtoToProduct(product);
    //供货价=价格-门店提成-平台服务费
    Money saleAmt = new Money(product.getSaleAmt());
    Money perServiceAmt = new Money(product.getPerServiceAmt());
    Money perCommission = new Money(product.getPerCommission());
    Money supplyAmt = saleAmt.subtract(perServiceAmt).subtract(perCommission);
    insertProduct.setSupplyAmt(supplyAmt);
    insertProduct.setProductInfoId(productInfoId);
    insertProduct.setStock(product.getStock());
    insertProduct.setSurplusStock(product.getSurplusStock() == null ? BigDecimal.ZERO : product.getSurplusStock());
    insertProduct.setSaleQty(BigDecimal.ZERO);
    insertProduct.setSaleType(ProductSaleTypeEnum.NORMAL.getValueDefined());
    insertProduct.setSpecType(ProductSpecEnum.SINGLE.getValueDefined());
    return insertProduct;
  }

  /**
   * 创建单规格商品属性
   *
   * @param product 商品
   * @param productAttrvalRelation 属性
   */
  private void createSingleProductAttr(Product product, ProductAttrvalRelationDto productAttrvalRelation) {

    //删除原属性
    if (product.getProductId() != null) {
      Map<String, Object> relationMap = new HashMap<>();
      relationMap.put("productId", product.getProductId());
      productAttrvalRelationMapper.deleteByMap(relationMap);
    }

    ProductAttrvalRelation par = ProductAttrvalRelationMapStruct.MAPPER.productAttrvalRelationDtoToProductAttrvalRelation(productAttrvalRelation);
    par.setProductId(product.getProductId());
    par.setProductInfoId(product.getProductInfoId());
    par.setAttrLevel(0);
    par.setTmCreate(new Date());
    productAttrvalRelationMapper.insert(par);
  }

  /**
   * 创建商品图片
   *
   * @param product 商品
   * @param userId 用户id
   * @param userName 用户名
   * @param productImgList 图片列表
   */
  private void createProductImg(Product product, Long userId, String userName, List<ProductImgDto> productImgList) {

    //删除原商品主图和广告图
    if (product.getProductId() != null) {
      EntityWrapper<ProductImg> imgEntityWrapper = new EntityWrapper<>();
      imgEntityWrapper.where("productId={0} and imgType in ('AD','PRIMARY')", product.getProductId());
      productImgMapper.delete(imgEntityWrapper);
    }
    productImgList.forEach(t -> {
      Preconditions.checkArgument(StringUtil.isNotBlank(t.getImgType()), "图片类型不能为空");
      Preconditions.checkArgument(ProductImgTypeEnum.getImgTypeEnum(t.getImgType()) != null, "图片类型有误");
      t.setProductId(product.getProductId());
      t.setCreateUserId(userId);
      t.setCreateUserName(userName);
      t.setTmCreate(new Date());
    });

    List<ProductImg> imgList = ProductImgMapStruct.MAPPER.productImgDtoListToProductImgList(productImgList);
    productImgMapper.insertBatch(imgList);
  }

  /**
   * 修改单规格商品
   *
   * @param product 商品
   * @param productAttrvalRelation 属性
   * @param productImgList 图片
   * @return 结果
   */
  @Override
  public MerchantResult updateSingleProduct(ProductDto product, ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList) {

    try {
      Preconditions.checkArgument(product.getProductId() != null, "商品id不能为空");
      Preconditions.checkArgument(product.getModifyUserId() != null, "商品修改人id不能为空");
      checkProductArgs(product, productAttrvalRelation, productImgList);
    } catch (IllegalArgumentException e) {
      LogUtil.error(e, "[ProductService:商品]商品创建参数异常");
      MerchantResult<Long> result = new MerchantResult<>();
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, e.getMessage()));
      return result;
    }
    return newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {

      MerchantResult result = new MerchantResult<>();

      @Override
      public MerchantResult doInTransaction(TransactionStatus transactionStatus) {

        try {
          Product p = productMapper.selectById(product.getProductId());
          if (ProductSkuStatusEnum.DELETED.getValueDefined().equals(p.getSkuStatus())) {
            throw new ProductBizException(ErrorCodeDetailEnum.PRODUCT_MODIFY_ERROR, "商品已被删除");
          }
          //商品信息
          product.setStock(product.getStock() == null ? BigDecimal.ZERO : product.getStock());
          ProductInfo productInfo = buildProductInfo(p.getProductInfoId(), product);
          productInfo.setAreaId(p.getAreaId());
          productInfo.setModifyUserId(product.getModifyUserId());
          productInfo.setModifyUserName(product.getModifyUserName());
          productInfo.setTmSmp(new Date());
          productInfoMapper.updateAllColumnById(productInfo);

          //商品SKU
          Product updateProduct = buildProduct(p.getProductInfoId(), product);
          updateProduct.setProductId(p.getProductId());
          updateProduct.setSku(p.getSku());
          updateProduct.setSkuStatus(p.getSkuStatus());
          updateProduct.setSpecType(p.getSpecType());
          updateProduct.setModifyUserId(product.getModifyUserId());
          updateProduct.setModifyUserName(product.getModifyUserName());
          updateProduct.setCreateUserName(p.getCreateUserName());
          updateProduct.setTmCreate(p.getTmCreate());
          updateProduct.setCreateUserId(p.getCreateUserId());
          updateProduct.setTmSmp(new Date());
          productMapper.updateAllColumnById(updateProduct);

          //商品详情
          ProductDesc productDesc = productDescMapper.selectById(productInfo.getProductInfoId());
          productDesc.setBriefDesc(product.getBriefDesc());
          productDesc.setShareTitle(product.getShareTitle());
          productDesc.setModifyUserId(updateProduct.getModifyUserId());
          productDesc.setModifyUserName(updateProduct.getModifyUserName());
          productDesc.setTmSmp(new Date());
          productDescMapper.updateAllColumnById(productDesc);

          //商品属性
          productAttrvalRelation.setAttrName("规格");
          productAttrvalRelation.setCreateUserId(updateProduct.getModifyUserId());
          productAttrvalRelation.setCreateUserName(updateProduct.getCreateUserName());
          createSingleProductAttr(updateProduct, productAttrvalRelation);
          //商品图片
          createProductImg(updateProduct, updateProduct.getModifyUserId(), updateProduct.getModifyUserName(), productImgList);
          //商品服务明细
          updateProduct.setCreateUserId(updateProduct.getModifyUserId());
          updateProduct.setCreateUserName(updateProduct.getModifyUserName());
          createPreproductServiceAmt(updateProduct);

          updateProductDetailCache(p);
          merchantResultHelper.fillWithSuccess(result);
        } catch (IllegalArgumentException iae) {
          LogUtil.error(iae, "[ProductService:商品]商品创建参数异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, iae.getMessage()));
        } catch (ProductBizException pbe) {
          LogUtil.error(pbe, "[ProductService:商品]商品创建业务异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, pbe);
        } catch (Exception e) {
          LogUtil.error(e, "[ProductService:商品]商品创建异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_MODIFY_ERROR, "商品修改失败"));
        }
        if (!result.isSuccess()) {
          // 事务回滚
          transactionStatus.setRollbackOnly();
        }
        return result;
      }
    });
  }

  /**
   * 商品展示列表
   *
   * @param productQueryDto 商品id
   * @param page 分页页数
   */
  @Override
  public MerchantResult<Page<ProductDto>> productQueryList(ProductQueryDto productQueryDto, Page<ProductDto> page) {
    MerchantResult<Page<ProductDto>> result = new MerchantResult<>();
    List<Product> productList = null;
    try {
      int total = 0;
      List<ProductDto> dtoList = new ArrayList<>();
      if (productQueryDto != null) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(productQueryDto);
        total = productMapper.countProductList(jsonObject);
        if (total > 0) {
          Page<ProductDto> queryPage = new Page<>(page.getCurrent(), page.getSize());
          productList = productMapper.findProductByConditions(jsonObject, queryPage.getSize(), queryPage.getOffset());
          List<Long> productIds = productList.stream().map(Product::getProductId).collect(Collectors.toList());
          //查询商品广告图
          EntityWrapper<ProductImg> imgEntityWrapper = new EntityWrapper<>();
          imgEntityWrapper.in("productId", productIds).and("imgType = 'PRIMARY'");
          List<ProductImg> imgs = productImgMapper.selectList(imgEntityWrapper);
          Map<Long, List<ProductImg>> imgGroup = imgs.stream().collect(Collectors.groupingBy(ProductImg::getProductId));

          //查询供应商名称
          List<Long> vendorIds = productList.stream().map(Product::getVendorId).collect(Collectors.toList());
          Map<Long, Vendor> vendorMap = new HashMap<>();
          if (!vendorIds.isEmpty()) {
            EntityWrapper<Vendor> vendorWrapper = new EntityWrapper<>();
            imgEntityWrapper.in("vendorId", vendorIds);
            List<Vendor> vendorList = vendorMapper.selectList(vendorWrapper);
            vendorMap = vendorList.stream().collect(Collectors.toMap(Vendor::getVendorId, Function.identity()));
          }

          //商品规格
          EntityWrapper<ProductAttrvalRelation> productAttrvalRelationWrapper = new EntityWrapper<>();
          imgEntityWrapper.in("productId", productIds);
          List<ProductAttrvalRelation> productAttrvalRelationList = productAttrvalRelationMapper.selectList(productAttrvalRelationWrapper);
          Map<Long, ProductAttrvalRelation> productAttrvalRelationListMap = productAttrvalRelationList.stream().collect(Collectors.toMap(ProductAttrvalRelation::getProductId, Function.identity()));

          for (Product product : productList) {
            ProductDto productDto = ProductMapStruct.MAPPER.productToProductDto(product);
            if (imgGroup.get(product.getProductId()) != null) {
              List<ProductImg> prImgs = imgGroup.get(product.getProductId());
              if (prImgs != null && !prImgs.isEmpty()) {
                ProductImgDto imgDto = ProductImgMapStruct.MAPPER.productImgToProductImgDto(prImgs.get(0));
                //这里为主图不是广告图
                productDto.setAdImgUrl(imgDto);
              }
            }

            if (vendorMap.get(product.getVendorId()) != null) {
              Vendor vendor = vendorMap.get(product.getVendorId());
              productDto.setVendorName(vendor.getVendorName());
              productDto.setVendorCode(vendor.getVendorCode());
            }
            List<ProductAttrvalRelationDto> attrs = new ArrayList<>();
            if (productAttrvalRelationListMap != null && !productAttrvalRelationListMap.isEmpty()) {
              for (Map.Entry entry : productAttrvalRelationListMap.entrySet()) {
                if (entry.getKey().equals(product.getProductId())) {
                  ProductAttrvalRelation productAttrvalRelation = (ProductAttrvalRelation) entry.getValue();
                  ProductAttrvalRelationDto productAttrvalRelationDto = ProductAttrvalRelationMapStruct.MAPPER.productAttrvalRelationToProductAttrvalRelationDto(productAttrvalRelation);
                  attrs.add(productAttrvalRelationDto);
                }
              }
              productDto.setAttrs(attrs);
            }
            dtoList.add(productDto);
          }
        }
      }
      page.setTotal(total);
      page.setRecords(dtoList);
      result.setData(page);
      merchantResultHelper.fillWithSuccess(result);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductService:商品]商品列表查询失败");
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_QUERY_ERROR, "商品列表查询失败"));
    }
    return result;
  }

  /**
   * 更新商品图文详情
   *
   * @param productDesc 商品详情
   * @param descImgList 详情图片
   * @return 结果
   */
  @Override
  public MerchantResult updateProductDesc(ProductDescDto productDesc, List<ProductImgDto> descImgList) {

    try {
      Preconditions.checkArgument(productDesc.getProductId() != null, "商品id不能为空");
      Preconditions.checkArgument(productDesc.getModifyUserId() != null, "商品详情修改人id不能为空");
    } catch (IllegalArgumentException e) {
      LogUtil.error(e, "[ProductService:商品]商品图文详情修改参数异常");
      MerchantResult<Long> result = new MerchantResult<>();
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, e.getMessage()));
      return result;
    }

    return newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {

      MerchantResult result = new MerchantResult();

      @Override
      public MerchantResult doInTransaction(TransactionStatus transactionStatus) {

        try {
          Product p = productMapper.selectById(productDesc.getProductId());
          ProductDesc desc = productDescMapper.selectById(p.getProductInfoId());
          desc.setDetailDesc(productDesc.getDetailDesc());
          desc.setModifyUserName(productDesc.getModifyUserName());
          desc.setModifyUserId(productDesc.getModifyUserId());
          desc.setTmSmp(new Date());
          productDescMapper.updateAllColumnById(desc);

          //清除原来的图文详情图片
          EntityWrapper<ProductImg> imgEntityWrapper = new EntityWrapper<>();
          imgEntityWrapper.where("productId={0} and imgType in ('DETAIL')", productDesc.getProductId());
          productImgMapper.delete(imgEntityWrapper);

          List<ProductImg> imgs = null;
          if (descImgList != null && !descImgList.isEmpty()) {
            imgs = ProductImgMapStruct.MAPPER.productImgDtoListToProductImgList(descImgList);
            imgs.forEach(t -> {
              t.setProductId(productDesc.getProductId());
              t.setImgType(ProductImgTypeEnum.DETAIL.getValueDefined());
              t.setCreateUserId(desc.getModifyUserId());
              t.setCreateUserName(desc.getModifyUserName());
              t.setTmCreate(new Date());
              t.setTmSmp(new Date());
            });
            productImgMapper.insertBatch(imgs);
          }

          updateProductDetailCache(p);
          merchantResultHelper.fillWithSuccess(result);
        } catch (ProductBizException pbe) {
          LogUtil.error(pbe, "[ProductService:商品]商品图文详情修改业务异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, pbe);
        } catch (Exception e) {
          LogUtil.error(e, "[ProductService:商品]商品图文详情修改异常");
          merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_MODIFY_DESC_ERROR, "商品图文详情修改失败"));
        }
        if (!result.isSuccess()) {
          //事务处理
          transactionStatus.setRollbackOnly();
        }
        return result;
      }
    });
  }

  /**
   * 更新商品详情缓存
   *
   * @param p 商品信息
   */
  private void updateProductDetailCache(Product p) {

    //主图
    EntityWrapper<ProductImg> prImgEntityWrapper = new EntityWrapper<>();
    prImgEntityWrapper.where("productId={0} and imgType in ('PRIMARY')", p.getProductId());
    List<ProductImg> prImgs = productImgMapper.selectList(prImgEntityWrapper);

    ProductDesc desc = productDescMapper.selectById(p.getProductInfoId());

    //详情图
    EntityWrapper<ProductImg> deImgEntityWrapper = new EntityWrapper<>();
    deImgEntityWrapper.where("productId={0} and imgType in ('DETAIL')", p.getProductId());
    List<ProductImg> deImgs = productImgMapper.selectList(deImgEntityWrapper);
    promotionDubboProcess.updateProductDetailCache(p, desc, prImgs, deImgs);
  }

  /**
   * 逻辑删除商品  productIds可以是一个也可以是多个 根据删除商品时需要判断商品是否已经加入活动
   *
   * @param productSortDto 商品id
   */
  @Override
  public MerchantResult delProducts(ProductSortDto productSortDto) {
    MerchantResult merchantResult = new MerchantResult();
    if (productSortDto.getProductIds() == null || productSortDto.getProductIds().isEmpty()) {
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, "商品id不能为空"));
      return merchantResult;
    }
    try {
      //校验商品是否参加活动
      checkProductActivity(productSortDto.getProductIds());
      //TODO SQL
      productMapper.updateSkuStatus(productSortDto.getProductIds(), ProductSkuStatusEnum.DELETED.getValueDefined(), productSortDto.getAreaId(), productSortDto.getOperateId(), productSortDto.getOperateName());
      merchantResult.setSuccess(true);
    } catch (ProductBizException pbe) {
      LogUtil.error(pbe, "[ProductService:商品]商品删除业务异常");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.PRODUCT, pbe);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductService:商品]商品删除失败");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_DELETE_ERROR, "商品删除失败"));
    }
    return merchantResult;
  }

  /**
   * 校验商品是否正在参与活动
   *
   * @param productIds 商品id
   */
  private void checkProductActivity(List<Long> productIds) {

    if (productIds != null && !productIds.isEmpty()) {
      List<PreproductDto> preproducts = promotionDubboProcess.checkProductInActivity(productIds);
      if (preproducts != null) {
        Map<Long, List<PreproductDto>> preproductMap = preproducts.stream().collect(Collectors.groupingBy(PreproductDto::getProductId));
        for (Long productId : productIds) {
          if (preproductMap.containsKey(productId)) {
            throw new ProductBizException(ErrorCodeDetailEnum.PRODUCT_CHECK_ACTIVITY_ERROR, String.format("商品SKU【%s】参与的活动正在进行中，不能操作", preproductMap.get(productId).get(0).getSku()));
          }
        }
      }
    }
  }

  /**
   * 批量上下架 productIds可以是一个也可以是多个
   *
   * @param productSortDto 商品上下架状态 商品上架 UP   商品下架 DOWN
   */
  @Override
  public MerchantResult updateSkuStatus(ProductSortDto productSortDto) {

    MerchantResult merchantResult = new MerchantResult();
    try {
      if (productSortDto.getProductIds() == null || productSortDto.getProductIds().isEmpty()) {
        merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.REQUEST_PARAM_ILLEGAL, "商品id不能为空"));
        return merchantResult;
      }
      //商品活动校验
      if (ProductSkuStatusEnum.DOWN.getValueDefined().equals(productSortDto.getSkuStatus())) {
        checkProductActivity(productSortDto.getProductIds());
      }

      //TODO SQL
      List<Product> productList = productMapper.findProductList(productSortDto.getProductIds());

      List<Long> productIdList = new ArrayList<>();
      for (Product product : productList) {
        if (ProductSkuStatusEnum.DELETED.getValueDefined().equals(product.getSkuStatus())) {
          merchantResultHelper
              .fillWithFailure(merchantResult, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_UPDATE_ERROR, String.format("商品【%s】商品被删除，不能上下架", product.getProductName())));
          return merchantResult;
        }
        if (!product.getSkuStatus().equals(productSortDto.getSkuStatus())) {
          productIdList.add(product.getProductId());
        }
      }
      if (!productIdList.isEmpty()) {
        productMapper.updateSkuStatus(productIdList, productSortDto.getSkuStatus(), productSortDto.getAreaId(), productSortDto.getOperateId(), productSortDto.getOperateName());
      }
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (ProductBizException pbe) {
      LogUtil.error(pbe, "[ProductService:商品]商品上下架业务异常");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.PRODUCT, pbe);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductService:商品]商品上下架失败");
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_UPDATE_ERROR, "商品上下架失败"));
    }
    return merchantResult;
  }

  /**
   * 查询商品详情
   *
   * @param productId 商品id
   * @return 商品详情
   */
  @Override
  public MerchantResult<ProductDto> queryProductDetail(Long productId) {

    MerchantResult<ProductDto> result = new MerchantResult<>();
    try {
      Product product = productMapper.selectById(productId);
      ProductDto productInfo = ProductMapStruct.MAPPER.productToProductDto(product);

      ProductInfo info = productInfoMapper.selectById(product.getProductInfoId());
      productInfo.setBrandName(info.getBrandName());

      Map<String, Object> queryMap = new HashMap<>();
      queryMap.put("productId", productInfo.getProductId());
      List<ProductAttrvalRelation> attrs = productAttrvalRelationMapper.selectByMap(queryMap);
      productInfo.setAttrs(ProductAttrvalRelationMapStruct.MAPPER.productAttrvalRelationsToProductAttrvalRelationDtos(attrs));

      ProductDesc productDesc = productDescMapper.selectById(productInfo.getProductInfoId());
      productInfo.setShareTitle(productDesc.getShareTitle());
      productInfo.setBriefDesc(productDesc.getBriefDesc());
      productInfo.setDetailDesc(productDesc.getDetailDesc());

      List<ProductImg> imgs = productImgMapper.selectByMap(queryMap);
      List<ProductImgDto> imgList = ProductImgMapStruct.MAPPER.productImgListToProductImgDtoList(imgs);
      Map<String, List<ProductImgDto>> imgMap = imgList.stream().collect(Collectors.groupingBy(ProductImgDto::getImgType));
      productInfo.setAdImgUrl(imgMap.get(ProductImgTypeEnum.AD.getValueDefined()).get(0));
      productInfo.setPrimaryUrls(imgMap.get(ProductImgTypeEnum.PRIMARY.getValueDefined()));
      productInfo.setDetailUrls(imgMap.get(ProductImgTypeEnum.DETAIL.getValueDefined()) == null ? new ArrayList<>() : imgMap.get(ProductImgTypeEnum.DETAIL.getValueDefined()));

      //查询供应商信息
      Vendor vendor = vendorMapper.selectById(product.getVendorId());
      productInfo.setVendorLogo(vendor.getVendorLogo());
      productInfo.setVendorName(vendor.getVendorName());
      productInfo.setVendorShortName(vendor.getVendorShortName());

      result.setData(productInfo);
      merchantResultHelper.fillWithSuccess(result);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductServiceImpl:商品]商品详情查询失败");
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_QUERY_ERROR, "商品查询失败"));
    }
    return result;
  }


  @Override
  public MerchantResult<List<ProductDto>> queryProductImgAndDesc(List<Long> productIds) {

    MerchantResult<List<ProductDto>> result = new MerchantResult<>();
    try {
      if (productIds != null && !productIds.isEmpty()) {
        EntityWrapper<Product> productEntityWrapper = new EntityWrapper<>();
        productEntityWrapper.in("productId", productIds);
        List<Product> products = productMapper.selectList(productEntityWrapper);
        List<ProductDto> productDtos = ProductMapStruct.MAPPER.productsToProductDtos(products);

        List<Long> productInfoIds = productDtos.stream().map(ProductDto::getProductInfoId).collect(Collectors.toList());

        EntityWrapper<ProductDesc> descEntityWrapper = new EntityWrapper<>();
        descEntityWrapper.in("productInfoId", productInfoIds);
        List<ProductDesc> descList = productDescMapper.selectList(descEntityWrapper);
        Map<Long, ProductDesc> descMap = descList.stream().collect(Collectors.toMap(ProductDesc::getProductInfoId, Function.identity()));

        EntityWrapper<ProductImg> imgEntityWrapper = new EntityWrapper<>();
        imgEntityWrapper.in("productId", productIds);
        List<ProductImg> imgs = productImgMapper.selectList(imgEntityWrapper);
        List<ProductImgDto> imgList = ProductImgMapStruct.MAPPER.productImgListToProductImgDtoList(imgs);
        Map<Long, List<ProductImgDto>> imgGroup = imgList.stream().collect(Collectors.groupingBy(ProductImgDto::getProductId));

        for (ProductDto product : productDtos) {
          List<ProductImgDto> imgInfos = imgGroup.get(product.getProductId());
          Map<String, List<ProductImgDto>> imgMap = imgInfos.stream().collect(Collectors.groupingBy(ProductImgDto::getImgType));
          product.setAdImgUrl(imgMap.get(ProductImgTypeEnum.AD.getValueDefined()).get(0));
          product.setPrimaryUrls(imgMap.get(ProductImgTypeEnum.PRIMARY.getValueDefined()));
          product.setDetailUrls(imgMap.get(ProductImgTypeEnum.DETAIL.getValueDefined()) == null ? new ArrayList<>() : imgMap.get(ProductImgTypeEnum.DETAIL.getValueDefined()));

          ProductDesc productDesc = descMap.get(product.getProductInfoId());
          if (productDesc != null) {
            product.setShareTitle(productDesc.getShareTitle());
            product.setBriefDesc(productDesc.getBriefDesc());
            product.setDetailDesc(productDesc.getDetailDesc());
          }
        }
        result.setData(productDtos);
      }
      merchantResultHelper.fillWithSuccess(result);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductServiceImpl:商品]商品图片和简介查询失败");
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_QUERY_ERROR, "商品图片和简介查询失败"));
    }
    return result;
  }

  @Override
  public MerchantResult<List<ProductDto>> queryProductSku(List<Long> productIds) {

    MerchantResult<List<ProductDto>> result = new MerchantResult<>();
    try {
      if (productIds == null || productIds.isEmpty()) {
        merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PARAM_ILLEGAL, "商品id不能为空"));
        return result;
      }
      EntityWrapper<Product> productEntityWrapper = new EntityWrapper<>();
      productEntityWrapper.in("productId", productIds);
      List<Product> products = productMapper.selectList(productEntityWrapper);
      List<ProductDto> productDtos = ProductMapStruct.MAPPER.productsToProductDtos(products);
      result.setData(productDtos);
      merchantResultHelper.fillWithSuccess(result);
    } catch (Exception e) {
      LogUtil.error(e, "[ProductServiceImpl:商品]商品SKU基本信息查询失败");
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_QUERY_ERROR, "商品SKU基本信息查询失败"));
    }
    return result;
  }

}
