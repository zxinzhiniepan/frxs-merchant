/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.enums;

import lombok.Getter;

/**
 * frxs-framework错误场景分类枚举。
 *
 * <p>对应于标准错误码6~9位，frxs-framework可以使用的取值范围是0000~0019 <p>在标准错误码的位置如下： <table border="1"> <tr>
 * <td>位置</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td bgcolor="red">6</td><td
 * bgcolor="red">7</td><td bgcolor="red">8</td><td bgcolor="red">9</td><td>10</td><td>11</td><td>12</td>
 * </tr> <tr> <td>示例</td><td>F</td><td>E</td><td>0</td><td>1</td><td>0</td><td>1</td><td>0</td><td>1</td><td>1</td><td>0</td><td>2</td><td>7</td>
 * </tr> <tr> <td>说明</td><td colspan=2>固定<br>标识</td><td>规<br>范<br>版<br>本</td><td>错<br>误<br>级<br>别</td><td>错<br>误<br>类<br>型</td><td
 * colspan=4>错误场景</td><td colspan=3>错误编<br>码</td> </tr> </table>
 *
 * @author mingbo.tang
 * @version $Id: PromotionErrorCodeScenarioEnum.java,v 0.1 2017年12月27日 上午 11:28 $Exp
 */
@Getter
public enum ErrorCodeScenarioEnum {

  // ~~~  场景
  ACTIVITY("0000", "活动场景"),

  PRODUCT("0001", "商品场景"),
  VENDOR_PRODUCT("0002", "供应商商品场景"),
  // ~~~ 配置场景
  CONFIG("9998", "配置场景"),
  // ~~~ 未知场景
  UNKNOWN("9999", "未知业务场景"),
  VENDOR("0070", "供应商业务场景"),
  STORE("0071", "门店业务场景"),
  DISTRIBUTIONLINE("0072", "配送路线业务场景"),
  SETACTIVITYDYNAMICPROMPT("0073", "活动提示设置业务场景"),
  PARAMETERSETTING("0074", "系统参数设置业务场景"),
  AREA("0075", "区域配置业务场景"),
  VENDOR_ACCOUNT("0076", "供应商账户业务场景"),
  OP_LOG("0077", "后台操作日志"),;

  /**
   * 枚举编码
   */
  private final String code;

  /**
   * 描述说明
   */
  private final String desc;

  /**
   * 私有构造函数。
   *
   * @param code 枚举编码
   * @param desc 描述说明
   */
  ErrorCodeScenarioEnum(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  /**
   * 通过枚举<code>code</code>获得枚举
   *
   * @param code 枚举编码
   * @return 支付错误场景枚举
   */
  public static ErrorCodeScenarioEnum getByCode(String code) {
    for (ErrorCodeScenarioEnum scenario : values()) {
      if (scenario.getCode().equals(code)) {
        return scenario;
      }
    }
    return null;
  }
}
