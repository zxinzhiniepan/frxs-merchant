package com.frxs.merchant.core.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.entity.StoreQy;
import com.frxs.merchant.service.api.domain.request.StorePageRequest;
import com.frxs.merchant.service.api.domain.request.StoreRequest;
import com.frxs.merchant.service.api.dto.StoreDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 门店MAPPER
 *
 * @author wushuo
 * @version $Id: StoreService.java,v 0.1 2018年01月29日 18:38 $Exp
 */
@Repository
public interface StoreMapper extends SuperMapper<Store>{
    /**
     * 主键ID查询
     * @param storeId
     * @return Store
     */
    Store selectByPrimaryKey(Long storeId);

    /**
     * 条件分页
     * @param page
     * @param storePageRequest
     * @return
     */
    List<StoreQy> getPageList(Page<Store> page, @Param("storePageRequest") StorePageRequest storePageRequest);

    /**
     * 主表条件分页
     * @param m
     * @param n
     * @param storePageRequest
     * @return
     */
    List<StoreQy> getPageListNotOther(@Param("m") Integer m ,@Param("n") Integer n, @Param("storePageRequest") StorePageRequest storePageRequest);

    List<StoreQy> getList(@Param("storePageRequest") StorePageRequest storePageRequest);
    /**
     * 批量更新门店状态
     * @param storeDto
     */
    void batchUpdateStatus(@Param("storeDto")StoreDto storeDto);

    /**
     * 总记录条数
     * @param storePageRequest
     * @return Integer
     */
    Integer getCount(@Param("storePageRequest") StorePageRequest storePageRequest);

    Integer getCountNotOther(@Param("storePageRequest") StorePageRequest storePageRequest);


    /**
     * 条件单表查询
     * @param storeRequest
     * @return
     */
    List<Store> getStoreList(@Param("storeRequest") StoreRequest storeRequest);

    /**
     * 多个编号查询
     * @param codes
     * @return
     */
    List<Store> getStoreListByCodes(@Param("codes") List<String> codes);

    /**
     * 根据storeIds批量查询门店信息
     * @param storeIds
     * @return List<Store>
     */
    List<Store> getBatchVendorIds(@Param("storeIds") List<Long> storeIds);

    /**
     * 通过userId查询Store信息
     * @param userId
     * @return
     */
    Store getStoreByUserId(@Param("userId") Long userId);

    /**
     * 根据线路id批量查询门店信息
     * @param id
     * @return List<Store>
     */
    List<Store> getStoreListByLineIds(Integer id);


    List<Store> getStoreListByStoreRequest(@Param("storeRequest") StoreRequest storeRequest);

    /**
     * 修改门店头像和门店签名
     * @param store
     */
    void updateStoreSignAndLog(@Param("store") Store store);
}
