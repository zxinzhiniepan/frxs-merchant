package com.frxs.merchant.core.service.impl;

import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.Area;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.core.cache.CacheTool;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.AreaMapper;
import com.frxs.merchant.core.mapstruct.AreaMapStruct;
import com.frxs.merchant.core.service.AreaService;
import com.frxs.merchant.service.api.dto.AreaDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 区域服务实现
 *
 * @author wushuo
 * @version $Id: AreaService.java,v 0.1 2018年01月29日 15:49 $Exp
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper mapper;

    @Autowired
    private TransactionTemplate newTransactionTemplate;

    @Autowired
    private MerchantResultHelper<MerchantResult> merchantResultHelper;

    @Autowired
    private CacheTool cacheTool;

    /**
     *  查询所有区域
     * @param isDelete
     * @return
     */
    @Override
    public MerchantResult<List<AreaDto>> getList(String isDelete) {
        MerchantResult<List<AreaDto>> result = new MerchantResult<>();
        List<Area> areas = cacheTool.findAreas();
        if (areas==null){
            areas = mapper.findByIsDelete(isDelete);
        }
        if(areas == null ||areas.size() == 0){
            return result;
        }
        cacheTool.saveAreas(areas);
        List<AreaDto> areaDtos = AreaMapStruct.MAPPER.areasToAreaDtos(areas);
        result.setData(areaDtos);
        result.setSuccess(true);
        return result;
    }

    /**
     * 保存区域
     * @param areaDto
     * @return
     */
    @Override
    public MerchantResult saveArea(AreaDto areaDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>(){

            MerchantResult<AreaDto> result = new MerchantResult<>();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try{
                    Area area = AreaMapStruct.MAPPER.areaDtoToArea(areaDto);
                    if(area.getAreaId() != null){
                        mapper.updateById(area);
                    }else{
                        area.setTmCreate(new Date());
                        mapper.insert(area);
                    }
                }catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.AREA,
                        ErrorCodeDetailEnum.AREA_SAVE_ERROR);
                    LogUtil.error(e,"[area-service:保存区域信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        result.setSuccess(true);
        return result;
    }

    /**
     * 修改区域
     * @param areaDto
     * @return
     */
    @Override
    public MerchantResult updateArea(AreaDto areaDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>(){
            MerchantResult<AreaDto> result = new MerchantResult<>();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try{
                    Area area = AreaMapStruct.MAPPER.areaDtoToArea(areaDto);
                    mapper.updateById(area);
                }catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.AREA,
                        ErrorCodeDetailEnum.AREA_UPDATE_ERROR);
                    LogUtil.error(e,"[area-service:修改区域信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        result.setSuccess(true);
        return result;
    }

    /**
     * 主键查询
     * @param areaId
     * @return
     */
    @Override
    public MerchantResult<AreaDto> getAreaById(Integer areaId) {
        MerchantResult<AreaDto> result = new MerchantResult<AreaDto>();
        Area area = mapper.selectByPrimaryKey(areaId);
        if (area == null) {
            return result;
        }
        AreaDto areaDto = AreaMapStruct.MAPPER.areaToAreaDto(area);
        result.setData(areaDto);
        result.setSuccess(true);
        return result;
    }

    @Override
    public MerchantResult<AreaDto> getAreaByAreaName(String areaName) {
        AreaDto areaDto = new AreaDto();
        Area area = new Area();
        area.setAreaName(areaName);
        area = mapper.selectOne(area);
        areaDto = AreaMapStruct.MAPPER.areaToAreaDto(area);
        MerchantResult<AreaDto> result = new MerchantResult<AreaDto>();
        result.setData(areaDto);
        result.setSuccess(true);
        return result;
    }
}

