package com.frxs.merchant.core.service.impl;

import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.core.mapstruct.SetActivityDynamicPromptMapStruct;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.SetActivityDynamicPrompt;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.SetActivityDynamicPromptMapper;

import com.frxs.merchant.core.service.SetActivityDynamicPromptService;
import com.frxs.merchant.service.api.dto.SetActivityDynamicPromptDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptService.java,v 0.1 2018年01月29日 下午 18:27 $Exp
 */
@Service
public class SetActivityDynamicPromptServiceImpl implements SetActivityDynamicPromptService {

    @Autowired
    private TransactionTemplate newTransactionTemplate;

    @Autowired
    private MerchantResultHelper<MerchantResult> merchantResultHelper;

    @Autowired
    private SetActivityDynamicPromptMapper setActivityDynamicPromptMapper;

    @Override
    public MerchantResult<Page<SetActivityDynamicPromptDto>> listSetActivityDynamicPrompt(SetActivityDynamicPromptDto setActivityDynamicPromptDto, Integer pageNo, Integer pageSize) {
        Page<SetActivityDynamicPrompt> pageObject = new Page<SetActivityDynamicPrompt>(pageNo, pageSize);
        SetActivityDynamicPrompt setActivityDynamicPrompt = SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptDtoToSetActivityDynamicPrompt(setActivityDynamicPromptDto);

        List<SetActivityDynamicPromptDto> setActivityDynamicPromptDtoList = new ArrayList<>();
        Page<SetActivityDynamicPromptDto> res = new Page<SetActivityDynamicPromptDto>(pageNo, pageSize);
        MerchantResult<Page<SetActivityDynamicPromptDto>> merchantResult = new MerchantResult<>();
        Integer total = setActivityDynamicPromptMapper.getCount(setActivityDynamicPrompt);
        if (total > 0) {
            List<SetActivityDynamicPrompt> setActivityDynamicPromptList = setActivityDynamicPromptMapper.listSetActivityDynamicPrompt(pageObject, setActivityDynamicPrompt);
            setActivityDynamicPromptDtoList = SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptsToSetActivityDynamicPromptDtos(setActivityDynamicPromptList);
            res.setTotal(total);
            res.setRecords(setActivityDynamicPromptDtoList);
            res.getPages();
            merchantResult.setSuccess(true);
            merchantResult.setData(res);
        } else {
            res.setTotal(0);
            res.setRecords(setActivityDynamicPromptDtoList);
            merchantResult.setSuccess(true);
            merchantResult.setData(res);
        }
        return merchantResult;
    }

    @Override
    public MerchantResult<SetActivityDynamicPromptDto> getSetActivityDynamicPromptDto(Integer dynamicPromptId) {
        SetActivityDynamicPrompt setActivityDynamicPrompt = new SetActivityDynamicPrompt();
        SetActivityDynamicPromptDto setActivityDynamicPromptDto = new SetActivityDynamicPromptDto();
        setActivityDynamicPrompt = setActivityDynamicPromptMapper.selectByPrimaryKey(dynamicPromptId);
        setActivityDynamicPromptDto = SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptToSetActivityDynamicPromptDto(setActivityDynamicPrompt);
        MerchantResult<SetActivityDynamicPromptDto> merchantResult = new MerchantResult<SetActivityDynamicPromptDto>();
        merchantResult.setData(setActivityDynamicPromptDto);
        return merchantResult;
    }

    @Override
    public MerchantResult saveSetActivityDynamicPromptDto(SetActivityDynamicPromptDto setActivityDynamicPromptDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<SetActivityDynamicPromptDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    SetActivityDynamicPrompt setActivityDynamicPrompt = new SetActivityDynamicPrompt();
                    setActivityDynamicPrompt = SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptDtoToSetActivityDynamicPrompt(setActivityDynamicPromptDto);
                    if (setActivityDynamicPrompt.getDynamicPromptId() == null) {
                        setActivityDynamicPrompt.setTmCreate(new Date());
                        setActivityDynamicPrompt.setStatus(StatusEnum.FROZEN.getValueDefined());
                        setActivityDynamicPromptMapper.insert(setActivityDynamicPrompt);
                    } else {
                        setActivityDynamicPromptMapper.updateById(setActivityDynamicPrompt);
                    }
                    result.setSuccess(true);
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.SETACTIVITYDYNAMICPROMPT, ErrorCodeDetailEnum.SETACTIVITYDYNAMICPROMPT_SAVE_ERROR);
                    LogUtil.error(e, "[setActivityDynamicPrompt-service:保存活动提示设置]处理异常 errorCode={}", result.getErrorContext().fetchCurrentError());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    @Override
    public MerchantResult modifyState(SetActivityDynamicPromptDto setActivityDynamicPromptDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<SetActivityDynamicPromptDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    if (setActivityDynamicPromptDto.getIds() == null) {
                        SetActivityDynamicPrompt setActivityDynamicPrompt = new SetActivityDynamicPrompt();
                        setActivityDynamicPrompt = SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptDtoToSetActivityDynamicPrompt(setActivityDynamicPromptDto);
                        setActivityDynamicPromptMapper.updateById(setActivityDynamicPrompt);
                    } else {
                        List<Integer> ids = new ArrayList<>();
                        String[] strings = setActivityDynamicPromptDto.getIds().split("&");
                        for (int i = 0; i < strings.length; i++) {
                            Integer id = Integer.valueOf(strings[i].split("=")[1]);
                            ids.add(id);
                        }
                        setActivityDynamicPromptMapper.updateByIds(ids, setActivityDynamicPromptDto.getStatus());
                    }
                    result.setSuccess(true);
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.SETACTIVITYDYNAMICPROMPT, ErrorCodeDetailEnum.SETACTIVITYDYNAMICPROMPT_MODIFY_STATE_ERROR);
                    LogUtil.error(e, "[setActivityDynamicPrompt-service:修改活动提示设置状态]处理异常 errorCode={}", result.getErrorContext().fetchCurrentError());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    @Override
    public MerchantResult<SetActivityDynamicPromptDto> selectByTime(Integer areaId) {
        MerchantResult<SetActivityDynamicPromptDto> merchantResult = new MerchantResult<SetActivityDynamicPromptDto>();
        SetActivityDynamicPromptDto setActivityDynamicPromptDto = new SetActivityDynamicPromptDto();
        SetActivityDynamicPrompt setActivityDynamicPrompt = new SetActivityDynamicPrompt();
        setActivityDynamicPrompt.setAreaId(areaId);
        setActivityDynamicPrompt.setTmDisplayStart(new Date());
        setActivityDynamicPrompt.setStatus(StatusEnum.NORMAL.getValueDefined());
        setActivityDynamicPrompt = setActivityDynamicPromptMapper.selectByTime(setActivityDynamicPrompt);
        setActivityDynamicPromptDto = SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptToSetActivityDynamicPromptDto(setActivityDynamicPrompt);
        merchantResult.setData(setActivityDynamicPromptDto);
        merchantResult.setSuccess(true);
        return merchantResult;
    }

    @Override
    public MerchantResult<List<SetActivityDynamicPromptDto>> listSetActivityDynamicPromptByTimePeriod(SetActivityDynamicPromptDto setActivityDynamicPromptDto) {
        SetActivityDynamicPrompt setActivityDynamicPrompt =
                SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptDtoToSetActivityDynamicPrompt(setActivityDynamicPromptDto);
        List<SetActivityDynamicPrompt> setActivityDynamicPromptList =
                setActivityDynamicPromptMapper.listSetActivityDynamicPromptByTimePeriod(setActivityDynamicPrompt);
        List<SetActivityDynamicPromptDto> setActivityDynamicPromptDtoList =
                SetActivityDynamicPromptMapStruct.MAPPER.setActivityDynamicPromptsToSetActivityDynamicPromptDtos(setActivityDynamicPromptList);
        MerchantResult<List<SetActivityDynamicPromptDto>> merchantResult = new MerchantResult<List<SetActivityDynamicPromptDto>>();
        merchantResult.setData(setActivityDynamicPromptDtoList);
        merchantResult.setSuccess(true);
        return merchantResult;
    }
}
