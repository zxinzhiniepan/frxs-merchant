package com.frxs.merchant.core.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cukor.fu
 * @version $Id: CacheUtil.java,v 0.1 2018年02月02日 下午 18:46 $Exp
 */
public class CacheUtil {
    /**
     * guava缓存key列表
     */
    public static List<String> GUAVACACHENAMELIST = new ArrayList<>();

    /**
     * 缓存应用名称
     */
    private static final String APP_NAME = "MERCHANT";

    /**
     * 获取缓存key
     *
     * @param prefixs 前缀（组合）
     * @return 完整的key
     */
    public static String getCacheKey(Object... prefixs) {

        StringBuilder sb = new StringBuilder(APP_NAME);
        for (Object prefix : prefixs) {
            sb.append(":").append(prefix);
        }
        return sb.toString();
    }
}
