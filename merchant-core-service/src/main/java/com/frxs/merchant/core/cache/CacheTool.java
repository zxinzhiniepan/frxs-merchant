package com.frxs.merchant.core.cache;

import com.alibaba.fastjson.JSON;
import com.frxs.framework.integration.redis.JedisService;
import com.frxs.merchant.common.dal.entity.*;
import com.frxs.merchant.common.dal.enums.CacheKeyEnum;
import com.frxs.merchant.service.api.dto.StoreCache;
import com.google.gson.Gson;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Cukor.fu
 * @version $Id: CacheTool.java,v 0.1 2018年02月02日 下午 18:50 $Exp
 */
@Service
public class CacheTool {

    @Autowired
    private JedisService jedisService;


    /**
     * 缓存门店信息
     *
     * @param key
     * @param store
     */
    public void saveStore(String key, Store store) {
        key = CacheUtil.getCacheKey(CacheKeyEnum.STORE.getPrefix()) + ":" + key;
        jedisService.setex(key, 31 * 24 * 60 * 60 , JSON.toJSONString(store));
    }
    /**
     * 通过key获取缓存中门店
     *
     * @param key
     * @return
     */
    public Store findStoreByKey(String key) {
        String userJson = jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.STORE.getPrefix()) + ":" + key);
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            return JSON.parseObject(userJson, Store.class);
        }
        return null;
    }

    /**
     * 缓存门店线路信息
     *
     * @param key
     * @param storeLine
     */
    public void saveStoreLine(String key, StoreLine storeLine) {
        key = CacheUtil.getCacheKey(CacheKeyEnum.STORELINE.getPrefix()) + ":" + key;
        jedisService.setex(key, 31 * 24 * 60 * 60 , JSON.toJSONString(storeLine));
    }
    /**
     * 通过key获取缓存中门店线路
     *
     * @param key
     * @return
     */
    public StoreLine findStoreLineByKey(String key) {
        String userJson = jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.STORELINE.getPrefix()) + ":" + key);
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            return JSON.parseObject(userJson, StoreLine.class);
        }
        return null;
    }

    /**
     * 缓存供应商账户信息
     *
     * @param key
     * @param vendorAccount
     */
    public void saveVendorAccount(String key, VendorAccount vendorAccount) {
        key = CacheUtil.getCacheKey(CacheKeyEnum.VENDORACCOUNT.getPrefix()) + ":" + key;
        jedisService.setex(key, 31 * 24 * 60 * 60 , JSON.toJSONString(vendorAccount));
    }

    /**
     * 获取缓存中供应商账户信息
     *
     * @param key
     */
    public VendorAccount findVendorAccountByKey(String key) {
        String userJson = jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.VENDORACCOUNT.getPrefix()) + ":" + key);
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            return JSON.parseObject(userJson, VendorAccount.class);
        }
        return null;
    }

    /**
     * 清除供应商账户缓存
     * @param key
     */
    public Long deleteVendorAccount(String key) {
        Long del = jedisService.del(CacheUtil.getCacheKey(CacheKeyEnum.VENDORACCOUNT.getPrefix()) + ":" + key);
        return del;
    }

    /**
     * 缓存供应商信息
     *
     * @param key
     * @param vendor
     */
    public void saveVendor(String key, Vendor vendor) {
        key = CacheUtil.getCacheKey(CacheKeyEnum.VENDOR.getPrefix()) + ":" + key;
        jedisService.setex(key, 31 * 24 * 60 * 60 , JSON.toJSONString(vendor));
    }

    public Long deleteVendor(String key) {
        Long del = jedisService.del(CacheUtil.getCacheKey(CacheKeyEnum.VENDOR.getPrefix()) + ":" + key);
        return del;
    }

    /**
     * 通过key获取缓存门店信息
     *
     * @param key
     * @param storeCache
     */
    public void saveStoreCache(String key, StoreCache storeCache) {
        key = CacheUtil.getCacheKey(CacheKeyEnum.STORE.getPrefix()) + ":" + key;
        jedisService.setex(key, 31 * 24 * 60 * 60 , JSON.toJSONString(storeCache));
    }
    /**
     * 通过key获取缓存中门店
     *
     * @param key
     * @return
     */
    public Vendor findVendorByKey(String key) {
        String userJson =
            jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.VENDOR.getPrefix()) + ":" + key);
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            return JSON.parseObject(userJson, Vendor.class);
        }
        return null;
    }

    public StoreCache findStoreCacheByKey(String key) {
        String userJson = jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.STORE.getPrefix()) + ":" + key);
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            return JSON.parseObject(userJson, StoreCache.class);
        }
        return null;
    }

    /**
     * 缓存用户门店信息
     *
     * @param key
     * @param storeId
     */
    public void saveStoreUser(String key, String storeId) {
        key = CacheUtil.getCacheKey(CacheKeyEnum.STOREUSER.getPrefix()) + ":" + key;
        jedisService.setex(key, 31 * 24 * 60 * 60 , JSON.toJSONString(storeId));
    }
    /**
     * 通过key获取缓存中门店
     *
     * @param key
     * @return
     */
    public Long findStoreUserByKey(String key) {
        String userJson = jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.STOREUSER.getPrefix()) + ":" + key);
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            Gson gson = new Gson();
            return gson.fromJson(userJson, Long.class);
        }
        return null;
    }

    /**
     * 清除门店信息缓存
     * @param key
     */
    public Long deleteStoreCache(String key) {
        Long del = jedisService.del(CacheUtil.getCacheKey(CacheKeyEnum.STORE.getPrefix()) + ":" + key);
        return del;
    }

    /**
     * 缓存系统参数信息
     *
     * @param key
     * @param map
     */
    public void saveParameterSetting(String key, Map<String,String> map) {
        key = CacheUtil.getCacheKey(CacheKeyEnum.PARAMETERSETTING.getPrefix()) + ":" + key;
        jedisService.setex(key, 31 * 24 * 60 * 60 , JSON.toJSONString(map));
    }
    /**
     * 通过key获取缓存中系统参数
     *
     * @param key
     * @return
     */
    public Map<String,String> findParameterSettingByKey(String key) {
        String userJson = jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.PARAMETERSETTING.getPrefix()) + ":" + key);
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            return JSON.parseObject(userJson, Map.class);
        }
        return null;
    }

    /**
     * 清除系统参数信息缓存
     * @param key
     */
    public Long deleteParameterSetting(String key) {
        Long del = jedisService.del(CacheUtil.getCacheKey(CacheKeyEnum.PARAMETERSETTING.getPrefix()) + ":" + key);
        return del;
    }

    public List<Area> findAreas() {
        String userJson = jedisService.get(CacheUtil.getCacheKey(CacheKeyEnum.AREA.getPrefix()) + ":AREA");
        if (userJson != null && StringUtil.isNotBlank(userJson)) {
            return JSON.parseArray(userJson, Area.class);
        }
        return null;
    }

    public void saveAreas(List<Area> areas) {
        jedisService.setex(CacheUtil.getCacheKey(CacheKeyEnum.AREA.getPrefix()) + ":AREA",  5 * 60 , JSON.toJSONString(areas));
    }
}
