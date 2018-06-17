package com.frxs.merchant.service.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * view name:  v_org_area
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
public class OrgAreaViewDto implements Serializable{

	private static final long serialVersionUID = 4457745258831709724L;
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

}
