package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.ParameterSetting;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.core.cache.CacheTool;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.ParameterSettingMapper;
import com.frxs.merchant.core.mapstruct.ParamterSettingMapStruct;
import com.frxs.merchant.core.service.ParameterSettingService;
import com.frxs.merchant.service.api.dto.ParameterSettingDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailServiceImpl.java,v 0.1 2018年01月31日 上午 10:07 $Exp
 */
@Service
public class ParameterSettingServiceImpl implements ParameterSettingService {

    @Autowired
    private TransactionTemplate newTransactionTemplate;

    @Autowired
    private MerchantResultHelper<MerchantResult> merchantResultHelper;

    @Autowired
    ParameterSettingMapper parameterSettingMapper;

    @Autowired
    private CacheTool cacheTool;

    @Override
    public MerchantResult<Page<ParameterSettingDto>> listParameterSetting(ParameterSettingDto parameterSettingDto, Integer pageNo, Integer pageSize) {
        Page<ParameterSetting> pageObject = new Page<ParameterSetting>(pageNo, pageSize);
        ParameterSetting parameterSetting = ParamterSettingMapStruct.MAPPER.parameterSettingDtoToParameterSetting(parameterSettingDto);
        MerchantResult<Page<ParameterSettingDto>> merchantResult = new MerchantResult<>();
        Page<ParameterSettingDto> res = new Page<ParameterSettingDto>(pageNo,pageSize);
        List<ParameterSettingDto> parameterSettingDtoList = new ArrayList<>();
        Integer total = parameterSettingMapper.getCount(parameterSetting);
        if(total>0){
            List<ParameterSetting> parameterSettingList = new ArrayList<ParameterSetting>();
            if(parameterSetting.getAreaId()==null){
                parameterSettingList = parameterSettingMapper.listParameterSetting(pageObject,parameterSetting);
            }else {
                parameterSettingList = parameterSettingMapper.listParameterSettingArea(pageObject,parameterSetting);
            }
            parameterSettingDtoList = ParamterSettingMapStruct.MAPPER.paramterSettingsToParameterSettingDtos(parameterSettingList);
            res.setTotal(total);
            res.setRecords(parameterSettingDtoList);
            res.getPages();
            merchantResult.setSuccess(true);
            merchantResult.setData(res);
        }else{
            res.setTotal(0);
            res.setRecords(parameterSettingDtoList);
            merchantResult.setSuccess(true);
            merchantResult.setData(res);
        }

        return merchantResult;
    }

    @Override
    public MerchantResult<Page<ParameterSettingDto>> listParameterSettingArea(ParameterSettingDto parameterSettingDto, Integer pageNo, Integer pageSize) {
        Page<ParameterSetting> pageObject = new Page<ParameterSetting>(pageNo, pageSize);
        ParameterSetting parameterSetting = ParamterSettingMapStruct.MAPPER.parameterSettingDtoToParameterSetting(parameterSettingDto);
        MerchantResult<Page<ParameterSettingDto>> merchantResult = new MerchantResult<>();
        Page<ParameterSettingDto> res = new Page<ParameterSettingDto>(pageNo,pageSize);
        List<ParameterSettingDto> parameterSettingDtoList = new ArrayList<>();
        Integer total = parameterSettingMapper.getCountArea(parameterSetting);
        if(total>0){
            List<ParameterSetting> parameterSettingList = parameterSettingMapper.listParameterSettingArea(pageObject,parameterSetting);
            parameterSettingDtoList = ParamterSettingMapStruct.MAPPER.paramterSettingsToParameterSettingDtos(parameterSettingList);
            res.setTotal(total);
            res.setRecords(parameterSettingDtoList);
            res.getPages();
            merchantResult.setSuccess(true);
            merchantResult.setData(res);
        }else{
            res.setTotal(0);
            res.setRecords(parameterSettingDtoList);
            merchantResult.setSuccess(true);
            merchantResult.setData(res);
        }

        return merchantResult;
    }

    @Override
    public MerchantResult<ParameterSettingDto> getParameterSetting(Integer id) {
        ParameterSetting parameterSetting = new ParameterSetting();
        ParameterSettingDto parameterSettingDto = new ParameterSettingDto();
        parameterSetting = parameterSettingMapper.selectByPrimaryKey(id);
        parameterSettingDto = ParamterSettingMapStruct.MAPPER.paramterSettingToParameterSettingDto(parameterSetting);
        MerchantResult<ParameterSettingDto> result = new MerchantResult<ParameterSettingDto>();
        result.setData(parameterSettingDto);
        return result;
    }

    @Override
    public MerchantResult modifyState(ParameterSettingDto parameterSettingDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>(){
            MerchantResult<ParameterSettingDto> result =new MerchantResult<>();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    ParameterSetting parameterSetting = ParamterSettingMapStruct.MAPPER.parameterSettingDtoToParameterSetting(parameterSettingDto);
                    parameterSettingMapper.updateById(parameterSetting);
                    parameterSetting = parameterSettingMapper.selectOne(parameterSetting);
                    cacheTool.deleteParameterSetting(parameterSetting.getAreaId()+"");
                    result.setSuccess(true);
                }catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PARAMETERSETTING, ErrorCodeDetailEnum.PARAMETERSETTING_MODIFY_STATE_ERROR);
                    LogUtil.error(e, "[parameterSetting-service:修改系统参数设置状态]处理异常 errorCode={}", result.getErrorContext().fetchCurrentError());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 依据系统参数取得
     *
     * @param areaId   （区域id,区域id不传取总部的);
     * @param paraCode (参数Code,必传);
     * @return MerchantResult<ParameterSettingDto>
     */
    @Override
    public MerchantResult<ParameterSettingDto> getParameterByParaCode(Integer areaId, String paraCode) {

        ParameterSetting paraQry=new ParameterSetting();

        //TODO 以后直接从缓存中取得;

        //先取区域参数;
        if (areaId != null) {
            paraQry.setAreaId(areaId);
        } else {
            //只取总部;
            paraQry.setAreaId(0);
        }
        paraQry.setParaCode(paraCode);

        List<ParameterSetting> listData=parameterSettingMapper.getParameterSettingByParaCode(paraQry);
        MerchantResult<ParameterSettingDto> merchantResult=new MerchantResult<>();
        if(listData!=null && listData.size()>=1){
            merchantResult.setData(ParamterSettingMapStruct.MAPPER.paramterSettingToParameterSettingDto(listData.get(0)));
            merchantResult.setSuccess(true);
        }else{
            merchantResult.setSuccess(false);
        }

        return merchantResult;
    }

    @Override
    public MerchantResult<Map<String,String>> getParameterSettingByAreaId(Integer areaId) {
        MerchantResult<Map<String,String>> merchantResult = new MerchantResult<Map<String,String>>();
        Map<String, String> result = cacheTool.findParameterSettingByKey(areaId + "");
        ParameterSetting paraQry=new ParameterSetting();
        paraQry.setAreaId(areaId);
        if (result==null||result.size()==0){
            result = new HashMap<String, String>();
            List<ParameterSetting> parameterSettings = parameterSettingMapper.getParameterSettingByParaCode(paraQry);
            if (parameterSettings==null){
                return merchantResult;
            }
            for (ParameterSetting parameterSetting : parameterSettings){
                result.put(parameterSetting.getParaCode(),parameterSetting.getParaValue());
            }
        }
        cacheTool.saveParameterSetting(areaId+"",result);
        merchantResult.setData(result);
        merchantResult.setSuccess(true);
        return merchantResult;
    }
}
