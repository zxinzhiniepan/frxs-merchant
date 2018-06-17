package com.frxs.merchant.core.mapper;

import com.frxs.merchant.common.dal.entity.SysDict;

public interface SysDictMapper {
    SysDict selectByPrimaryKey(Integer dictId);
}