package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * view name:  v_org_area
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("v_org_area")
public class OrgAreaView extends AbstractSuperEntity<OrgAreaView> {

	/**
 	* 省Id
 	*/
	private Integer provinceId;
	/**
 	* 省名称
 	*/ 
	private String provinceName;
	/**
	 * 市Id
	 */
	private Integer cityId;
	/**
	 * 市名称
	 */
	private String cityName;
	/**
	 * 县Id
	 */
	private Integer countyId;
	/**
	 * 县名称
	 */
	private String countyName;

	@Override
	protected Serializable pkVal() {
		return this.countyId;
	}
}
