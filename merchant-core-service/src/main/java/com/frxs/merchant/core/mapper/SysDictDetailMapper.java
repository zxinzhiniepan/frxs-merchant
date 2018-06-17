package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.SysDictDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictDetailMapper extends SuperMapper<SysDictDetail>{
    SysDictDetail selectByPrimaryKey(Integer dictDetailId);

    /**
     * 条件查询SysDictDetail List
     *
     * @param sysDictDetail
     * @return com.frxs.merchant.common.dal.entity.SysDictDetail
     */
    List<SysDictDetail> listSysDictDetail(SysDictDetail sysDictDetail);
}