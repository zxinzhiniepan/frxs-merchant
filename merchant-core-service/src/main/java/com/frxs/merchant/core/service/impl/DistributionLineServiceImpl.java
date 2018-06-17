package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.DistributionLine;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.entity.StoreLine;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.DistributionLineMapper;
import com.frxs.merchant.core.mapper.StoreLineMapper;
import com.frxs.merchant.core.mapper.StoreMapper;
import com.frxs.merchant.core.mapstruct.DistributionLineMapStruct;
import com.frxs.merchant.core.mapstruct.StoreMapStruct;
import com.frxs.merchant.core.service.DistributionLineService;
import com.frxs.merchant.service.api.dto.DistributionLineDto;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author jiangboxuan
 * @version @version $Id: DistributionLineService.java,v 0.1 2018年01月29日 下午 15:31 $Exp
 */
@Service
public class DistributionLineServiceImpl implements DistributionLineService {

    @Autowired
    private TransactionTemplate newTransactionTemplate;

    @Autowired
    private MerchantResultHelper<MerchantResult> merchantResultHelper;

    @Autowired
    DistributionLineMapper distributionLineMapper;

    @Autowired
    private StoreLineMapper storeLineMapper;

    @Autowired
    private StoreMapper storeMapper;
    @Override
    public MerchantResult<Page<DistributionLineDto>> listDistributionLine(DistributionLineDto distributionLineDto,Integer pageNo,Integer pageSize) {
        Page<DistributionLine> pageObject = new Page<DistributionLine>(pageNo,pageSize);
        MerchantResult<Page<DistributionLineDto>> merchantResult = new MerchantResult<>();
        Page<DistributionLineDto> res = new Page<DistributionLineDto>(pageNo,pageSize);
        Integer total = distributionLineMapper.getCount(distributionLineDto);
        List<DistributionLineDto> distributionLineDtoList = new ArrayList<>();
        if(total>0){
            List<DistributionLine> distributionLineList = distributionLineMapper.listDistributionLine(pageObject,distributionLineDto);
            distributionLineDtoList = DistributionLineMapStruct.MAPPER.distributionLinesToDistributionLineDtos(distributionLineList);
            res.setTotal(total);
            res.getPages();
            res.setRecords(distributionLineDtoList);
            merchantResult.setData(res);
            merchantResult.setSuccess(true);
        }else{
            res.setTotal(0);
            res.setRecords(distributionLineDtoList);
            merchantResult.setData(res);
            merchantResult.setSuccess(true);
        }
        return merchantResult;
    }

    @Override
    public MerchantResult<List<DistributionLineDto>> listAll(DistributionLineDto distributionLineDto) {
        List<DistributionLine> distributionLineList = new ArrayList<DistributionLine>();
        List<DistributionLineDto> distributionLineDtoList = new ArrayList<>();
        if(distributionLineDto.getIds()==null){
            DistributionLine distributionLine = DistributionLineMapStruct.MAPPER.distributionLineDtoToDistributionLine(distributionLineDto);
            distributionLineList = distributionLineMapper.listAll(distributionLine);
        }else {
            List<Integer> ids = new ArrayList<>();
            String[] strings = distributionLineDto.getIds().split("&");

                for(int i=0;i<strings.length;i++){
                    if (strings[i].split("=").length>1){
                        Integer id = Integer.valueOf(strings[i].split("=")[1]);
                        ids.add(id);
                    }
                }
                if (ids.size()>0){
                    distributionLineList = distributionLineMapper.listDistributionLineByWarehouseIds(ids);
                }


        }
        if (distributionLineList!=null&&distributionLineList.size()>0){
            distributionLineDtoList = DistributionLineMapStruct.MAPPER.distributionLinesToDistributionLineDtos(distributionLineList);
        }
        MerchantResult<List<DistributionLineDto>> merchantResult = new MerchantResult<>();
        merchantResult.setData(distributionLineDtoList);
        return merchantResult;
    }

    @Override
    public MerchantResult<DistributionLineDto> getDistributionLine(Integer id) {
        DistributionLine distributionLine = new DistributionLine();
         DistributionLineDto distributionLineDto = new DistributionLineDto();
        distributionLine = distributionLineMapper.selectByPrimaryKey(id);
        distributionLineDto = DistributionLineMapStruct.MAPPER.distributionLineToDistributionLineDto(distributionLine);
        MerchantResult<DistributionLineDto> merchantResult = new MerchantResult<DistributionLineDto>();
        merchantResult.setData(distributionLineDto);
        return merchantResult;
    }

    @Override
    public MerchantResult modifyState(DistributionLineDto distributionLineDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>(){
            MerchantResult result =new MerchantResult();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    List<Integer> ids = new ArrayList<>();
                    if(distributionLineDto.getIds()==null){
                        List<StoreLine> storeLines = storeLineMapper.selectList(
                            new EntityWrapper<StoreLine>()
                                .eq("lineId", distributionLineDto.getId()));
                        if(storeLines != null && storeLines.size() >0){
                            result.setData(false);
                            return result;
                        }
                        DistributionLine distributionLine = DistributionLineMapStruct.MAPPER.distributionLineDtoToDistributionLine(distributionLineDto);
                        distributionLineMapper.updateById(distributionLine);
                    }else{
                        String[] strings = distributionLineDto.getIds().split("&");
                        for(int i=0;i<strings.length;i++){
                            Integer id = Integer.valueOf(strings[i].split("=")[1]);
                            List<StoreLine> storeLines = storeLineMapper.selectList(
                                new EntityWrapper<StoreLine>()
                                    .eq("lineId", id));
                            if(storeLines != null && storeLines.size() >0){
                                result.setData(false);
                                return result;
                            }
                            ids.add(id);
                        }
                        distributionLineMapper.updateByIds(ids,distributionLineDto.getStatus());
                    }
                    result.setSuccess(true);
                }catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.DISTRIBUTIONLINE, ErrorCodeDetailEnum.DISTRIBUTIONLINE_DELETE_ERROR);
                    LogUtil.error(e, "[distributionLine-service:删除配送路线]处理异常 errorCode={}", result.getErrorContext().fetchCurrentError());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    @Override
    public MerchantResult saveDistributionLine(DistributionLineDto distributionLineDto) {

        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>(){
            MerchantResult<DistributionLineDto> result =new MerchantResult<>();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    DistributionLine distributionLine = DistributionLineMapStruct.MAPPER.distributionLineDtoToDistributionLine(distributionLineDto);
                    if(distributionLine.getId()==0){
                        distributionLine.setTmCreate(new Date());
                        distributionLine.setStatus(StatusEnum.NORMAL.getValueDefined());
                        distributionLineMapper.insert(distributionLine);
                    }else{
                        distributionLine.setCreateUserId(null);
                        distributionLine.setCreateUserName(null);
                        distributionLineMapper.updateById(distributionLine);
                    }
                    result.setSuccess(true);
                }catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.DISTRIBUTIONLINE, ErrorCodeDetailEnum.DISTRIBUTIONLINE_SAVE_ERROR);
                    LogUtil.error(e, "[distributionLine-service:保存配送路线]处理异常 errorCode={}", result.getErrorContext().fetchCurrentError());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }

                return result;
            }
        });

        return result;
    }

    @Override
    public MerchantResult<Map<DistributionLineDto, List<StoreDto>>> listDistributionLineAndStore(Integer warehouseId) {
        List<String> list = new ArrayList<String>();
        list.add("id");
        List<DistributionLine> distributionLineList = distributionLineMapper.selectList(new EntityWrapper<DistributionLine>()
            .eq("warehouseId", warehouseId)
            .notIn("status",StatusEnum.DELETE.getValueDefined()).orderAsc(list));
        List<DistributionLineDto> distributionLineDtoList = DistributionLineMapStruct.MAPPER.distributionLinesToDistributionLineDtos(distributionLineList);
        Map<DistributionLineDto, List<StoreDto>> resultMap = new HashMap<DistributionLineDto, List<StoreDto>>();
        for (DistributionLineDto distributionLineDto : distributionLineDtoList){
            List<Store> storeList = storeMapper.getStoreListByLineIds(distributionLineDto.getId());
            if(storeList != null && storeList.size() != 0){
                List<StoreDto> storeDtos = StoreMapStruct.MAPPER.storesToStoreDtos(storeList);
                resultMap.put(distributionLineDto,storeDtos);
            }
        }
        MerchantResult<Map<DistributionLineDto, List<StoreDto>>> result = new MerchantResult<Map<DistributionLineDto, List<StoreDto>>>();
        result.setData(resultMap);
        result.setSuccess(true);
        return result;
    }
}
