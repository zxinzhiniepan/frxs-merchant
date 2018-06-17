/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.enums;

import com.frxs.framework.common.errorcode.constant.ErrorLevels;
import lombok.Getter;

/**
 * demo错误明细码枚举
 * <p>
 * <p>code对应于标准错误码10~12位。而errorLevel对应于标准错误码的第4位 <p>在标准错误码的位置如下： <table border="1"> <tr>
 * <td>位置</td><td>1</td><td>2</td><td>3</td><td bgcolor="yellow">4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td
 * bgcolor="red">10</td><td bgcolor="red">11</td><td bgcolor="red">12</td> </tr> <tr>
 * <td>示例</td><td>F</td><td>E</td><td>0</td><td>1</td><td>0</td><td>1</td><td>0</td><td>1</td><td>1</td><td>0</td><td>2</td><td>7</td>
 * </tr> <tr> <td>说明</td><td colspan=2>固定<br>标识</td><td>规<br>范<br>版<br>本</td><td>错<br>误<br>级<br>别</td><td>错<br>误<br>类<br>型</td><td
 * colspan=4>错误场景</td><td colspan=3>错误编<br>码</td> </tr> </table>
 * <p>
 * <p>错误明细码的CODE取值空间如下： <ul> <li>公共类错误码[000-099,999] </ul>
 *
 * @author mingbo.tang
 * @version $Id: DemoErrorCodeDetailEnum.java,v 0.1 2017年12月27日 上午 11:14 $Exp
 */
@Getter
public enum ErrorCodeDetailEnum {
  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
  //                      公共错误码[000-099,999],商品错误码[100,199]            //
  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//

  UNKNOWN_EXCEPTION("999", ErrorLevels.ERROR, "其它未知异常"),
  CONFIGURATION_ERROR("000", ErrorLevels.FATAL, "配置错误"),
  OP_LOG_ERROR("001", ErrorLevels.FATAL, "记录日志错误"),

  //========================================================================//
  //                              请求校验                                   //
  //========================================================================//
  REQUEST_PARAM_ILLEGAL("001", ErrorLevels.WARN, "请求参数非法"),
  //========================================================================//
  //                              Business                                  //
  //========================================================================//
  PARAM_ILLEGAL("002", ErrorLevels.WARN, "参数非法"),
  //商品相关从400开始
  PRODUCT_CREATE_ERROR("400", ErrorLevels.ERROR, "商品创建失败"),
  PRODUCT_MODIFY_ERROR("401", ErrorLevels.ERROR, "商品修改失败"),
  PRODUCT_MODIFY_DESC_ERROR("402", ErrorLevels.ERROR, "商品图文详情修改失败"),


  PRODUCT_DELETE_ERROR("403", ErrorLevels.ERROR, "商品删除失败"),

  PRODUCT_UPDATE_ERROR("404", ErrorLevels.ERROR, "商品上下架失败"),

  PRODUCT_QUERY_ERROR("405", ErrorLevels.ERROR, "商品查询失败"),
  PRODUCT_SERVICE_DETAIL_ERROR("406", ErrorLevels.ERROR, "商品服务明细配置查询失败"),

  PRODUCT_CHECK_ACTIVITY_ERROR("407", ErrorLevels.ERROR, "商品活动校验失败"),
  PRODUCT_UPDATE_DETAIL_CACHE_ERROR("408", ErrorLevels.ERROR, "商品详情缓存更新失败"),
  PRODUCT_VENDOR_ERROR("408", ErrorLevels.ERROR, "供应商信息错误"),

  //供应商商品相关从500开始
  VENDORDATA_DELETE_ERROR("500", ErrorLevels.ERROR, "供应商商品删除失败"),

  VENDORDATA_SUBMIT_ERROR("501", ErrorLevels.ERROR, "供应商商品提交失败"),

  VENDORDATA_CREATE_ERROR("502", ErrorLevels.ERROR, "供应商商品创建失败"),

  VENDORDATA_MODIFY_ERROR("503", ErrorLevels.ERROR, "供应商商品修改失败"),

  VENDORDATA_QUERY_ERROR("410", ErrorLevels.ERROR, "供应商商品查询失败"),

  //供应商基础业务相关从200-209
  VENDOR_SAVE_ERROR("200", ErrorLevels.ERROR, "保存供应商失败"),
  VENDOR_DELETE_ERROR("201", ErrorLevels.ERROR, "删除供应商失败"),
  VENDOR_UPDATE_ERROR("202", ErrorLevels.ERROR, "修改供应商失败"),
  //供应商类型业务相关从250-259
  VENDOR_TYPE_SAVE_ERROR("250", ErrorLevels.ERROR, "保存供应商类型失败"),
  VENDOR_TYPE_DELETE_ERROR("251", ErrorLevels.ERROR, "删除供应商类型失败"),
  VENDOR_TYPE_UPDATE_ERROR("252", ErrorLevels.ERROR, "修改供应商类型失败"),
  //门店基础业务相关从260-269
  STORE_SAVE_ERROR("260", ErrorLevels.ERROR, "保存门店失败"),
  STORE_DELETE_ERROR("261", ErrorLevels.ERROR, "删除门店失败"),
  STORE_UPDATE_ERROR("262", ErrorLevels.ERROR, "修改门店失败"),
  STORE_CONTACTS_SAVE_ERROR("263", ErrorLevels.ERROR, "保存门店联系人失败"),
  //区域相关业务从270-279
  AREA_SAVE_ERROR("270", ErrorLevels.ERROR, "保存区域失败"),
  AREA_DELETE_ERROR("271", ErrorLevels.ERROR, "删除区域失败"),
  AREA_UPDATE_ERROR("272", ErrorLevels.ERROR, "修改区域失败"),
  //供应商账户业务相关从280-259
  VENDOR_ACCOUNT_SAVE_ERROR("280", ErrorLevels.ERROR, "保存供应商账户失败"),
  VENDOR_ACCOUNT_DELETE_ERROR("281", ErrorLevels.ERROR, "删除供应商账户失败"),
  VENDOR_ACCOUNT_UPDATE_ERROR("282", ErrorLevels.ERROR, "修改供应商账户失败"),
  VENDOR_ACCOUNT_PWD_ERROR("282", ErrorLevels.ERROR, "修改供应商账户密码失败"),

  DISTRIBUTIONLINE_SAVE_ERROR("220", ErrorLevels.ERROR, "保存配送路线失败"),
  DISTRIBUTIONLINE_DELETE_ERROR("221", ErrorLevels.ERROR, "删除配送路线失败"),
  DISTRIBUTIONLINE_DETAIL_ERROR("222", ErrorLevels.ERROR, "查询配送路线详情失败"),
  SETACTIVITYDYNAMICPROMPT_SAVE_ERROR("230", ErrorLevels.ERROR, "保存活动提示设置失败"),
  SETACTIVITYDYNAMICPROMPT_MODIFY_STATE_ERROR("231", ErrorLevels.ERROR, "修改活动提示设置状态失败"),
  PARAMETERSETTING_MODIFY_STATE_ERROR("240", ErrorLevels.ERROR, "修改系统参数设置失败"),;
  /**
   * 枚举编码
   */
  private final String code;

  /**
   * 错误级别
   */
  private final String errorLevel;

  /**
   * 描述说明
   */
  private final String desc;

  /**
   * 私有构造函数。
   *
   * @param code 枚举编码
   * @param errorLevel 错误级别
   * @param desc 描述说明
   */
  ErrorCodeDetailEnum(String code, String errorLevel, String desc) {
    this.code = code;
    this.errorLevel = errorLevel;
    this.desc = desc;
  }

  /**
   * 通过枚举<code>code</code>获得枚举
   *
   * @param code 枚举编码
   * @return 支付错误明细枚举
   */
  public static ErrorCodeDetailEnum getByCode(String code) {
    for (ErrorCodeDetailEnum detailCode : values()) {
      if (detailCode.getCode().equals(code)) {
        return detailCode;
      }
    }
    return null;
  }
}
