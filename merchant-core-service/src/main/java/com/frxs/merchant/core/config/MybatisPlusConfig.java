/*
package com.frxs.merchant.core.config;

import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;


*/
/**
 * @author wushuo
 * @version $Id: MybatisPlusConfig.java,v 0.1 2018年04月09日 9:17 $Exp
 *//*

@Configuration
@MapperScan("com.frxs.merchant.core.mapper*")
public class MybatisPlusConfig {

    */
/**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     *//*

    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

    */
/**
     *   mybatis-plus分页插件
     *//*

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
*/
