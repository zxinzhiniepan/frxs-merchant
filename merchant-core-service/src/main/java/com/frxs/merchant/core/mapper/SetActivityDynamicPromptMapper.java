package com.frxs.merchant.core.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.SetActivityDynamicPrompt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptMapper.java,v 0.1 2018年01月29日 下午 17:17 $Exp
 */
@Repository
public interface SetActivityDynamicPromptMapper extends SuperMapper<SetActivityDynamicPrompt>{

    /**
     * 活动设置详情
     *
     * @param id
     * @return com.frxs.merchant.common.dal.entity.SetActivityDynamicPrompt
     */
    SetActivityDynamicPrompt selectByPrimaryKey(Integer id);

    /**
     * 活动设置列表展示
     *
     * @param setActivityDynamicPrompt 活动动态提示
     * @return List<SetActivityDynamicPrompt>
     */
    List<SetActivityDynamicPrompt> listSetActivityDynamicPrompt(Page<SetActivityDynamicPrompt> pageObject,SetActivityDynamicPrompt setActivityDynamicPrompt);

    /**
     * 全部活动提示设置数量
     *
     * @param setActivityDynamicPrompt 活动提示设置
     * @return count
     */
    Integer getCount(SetActivityDynamicPrompt setActivityDynamicPrompt);

    /**
     * 批量删除
     *
     * @param ids id的list
     * @return
     */
    void updateByIds(@Param("ids") List<Integer> ids,@Param("status") String status);

    /**
     * 通过时间段查询活动动态提示
     *
     * @param setActivityDynamicPrompt 活动动态提示
     * @return com.frxs.merchant.common.dal.entity.SetActivityDynamicPrompt
     */
    SetActivityDynamicPrompt selectByTime(SetActivityDynamicPrompt setActivityDynamicPrompt);

    /**
     * 活动提示设置通过时间段查询时间段
     *
     * @param setActivityDynamicPrompt 活动动态提示
     * @return List<SetActivityDynamicPrompt>
     */
    List<SetActivityDynamicPrompt> listSetActivityDynamicPromptByTimePeriod(SetActivityDynamicPrompt setActivityDynamicPrompt);
}
