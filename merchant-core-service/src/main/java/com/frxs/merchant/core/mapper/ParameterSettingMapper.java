package com.frxs.merchant.core.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ParameterSetting;

import java.util.List;
import org.springframework.stereotype.Repository;


/**
 * @author jiangboxuan
 * @version @version $Id: ParameterSettingMapper.java,v 0.1 2018年01月31日 下午 15:10 $Exp
 */
@Repository
public interface ParameterSettingMapper  extends SuperMapper<ParameterSetting> {
    /**
     * 系统参数详情
     *
     * @param id
     * @return com.frxs.merchant.common.dal.entity.ParameterSetting
     */
    ParameterSetting selectByPrimaryKey(Integer id);

    /**
     * 系统参数列表展示
     *
     * @param parameterSetting, pageObject
     * @return List<ParameterSetting>
     */
    List<ParameterSetting> listParameterSetting(Page<ParameterSetting> pageObject, ParameterSetting parameterSetting);

    /**
     * 全部系统参数数量
     *
     * @param parameterSetting 系统参数
     * @return count
     */
    Integer getCount(ParameterSetting parameterSetting);

    /**
     * 通过条件查询系统参数详情
     *
     * @param parameterSetting 系统参数
     * @return count
     */
    ParameterSetting getParameterSetting(ParameterSetting parameterSetting);

    /**
     * 通过区域id,参数code 查询系统参数详情
     *
     * @param parameterSetting 系统参数
     * @return count
     */
    List<ParameterSetting> getParameterSettingByParaCode(ParameterSetting parameterSetting);

    /**
     * 区域系统参数列表展示
     *
     * @param parameterSetting, pageObject
     * @return List<ParameterSetting>
     */
    List<ParameterSetting> listParameterSettingArea(Page<ParameterSetting> pageObject, ParameterSetting parameterSetting);

    /**
     * 区域全部系统参数数量
     *
     * @param parameterSetting 系统参数
     * @return count
     */
    Integer getCountArea(ParameterSetting parameterSetting);

}
