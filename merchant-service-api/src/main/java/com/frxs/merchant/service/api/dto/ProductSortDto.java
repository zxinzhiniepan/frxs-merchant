package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author fygu
 * @version $Id: ProductSortDto.java,v 0.1 2018年02月05日 14:55 $Exp
 */
@Data
public class ProductSortDto  implements Serializable{

  private List<Long> productIds ;

  private Long areaId;

  private Long operateId;

  private String operateName;

  private Date operateDate;

  private String skuStatus;

}
