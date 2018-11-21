package com.hc.sys.core.common.idService;

/**
 * 系统缓存目录配置常量
 *
 * @author liweidong
 * @date 2017年03月10日 13:43
 */
public interface CacheID {

    /**
     *光源表缓存目录前缀
     */
    String ILLUMINANT = "illuminant";
    /**
     *浓光源表缓存目录前缀
     */
    String CONCENTRATION = "concentration";
    /**
     *配方缓存目录前缀
     */
    String FORMULA = "formula";
    /**
     *光源缓存目录前缀
     */
    String ILLUMINANTRELATIVEENERGYDISTRIBUTION = "illuminantrelativeenergydistribution";
    /**
     *光源缓存目录前缀
     */
    String ILLUMINANTOBSERVERXYZ = "illuminantobserverxyz";
    /**
     *光源缓存目录前缀
     */
    String ILLUMINANTWHITEXYZ = "illuminantwhitexyz";
}
