package com.frxs.merchant.service.api.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * table name:  t_warehouse
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
public class WarehouseDto implements Serializable{

	private static final long serialVersionUID = 3113795021399142710L;

	/**
	 * 仓库ID
	 */
	private Integer warehouseId;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 区域ID
	 */
	private Integer areaId;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 创建时间
	 */
	private Date tmCreate;
	/**
	 * 最后更新时间
	 */
	private Date tmSmp;
}
