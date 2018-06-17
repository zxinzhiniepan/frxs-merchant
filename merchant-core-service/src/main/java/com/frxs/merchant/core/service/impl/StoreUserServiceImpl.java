package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.entity.StoreUserRef;
import com.frxs.merchant.common.dal.enums.IsDeleteEnum;
import com.frxs.merchant.core.dubbo.UserDubboProcess;
import com.frxs.merchant.core.mapper.StoreUserRefMapper;
import com.frxs.merchant.core.service.StoreUserService;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.user.service.api.dto.UserDto;
import com.frxs.user.service.api.enums.UserTypeEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wushuo
 * @version $Id: StoreUserServiceImpl.java,v 0.1 2018年02月28日 17:27 $Exp
 */
@Service
public class StoreUserServiceImpl implements StoreUserService {

    @Autowired
    private StoreUserRefMapper storeUserRefMapper;

    @Autowired
    private UserDubboProcess userDubboProcess;

    /**
     * 添加门店用户信息
     * @param store
     * @return
     */
    @Override
    public MerchantResult saveStoreUser(Store store,String username) {
            MerchantResult result = new MerchantResult();
            //保存用户
            UserDto userDto = new UserDto();
            userDto.setMobileNo(username);
            userDto.setAlias(username);
            userDto.setUserName(username);
            userDto.setStatus(store.getStoreStatus());
            userDto.setUserType(UserTypeEnum.STORE.getValue());
            UserDto user = userDubboProcess.saveUser(userDto);
            //保存关联信息
            StoreUserRef storeUserRef = new StoreUserRef();
            storeUserRef.setStoreId(store.getStoreId());
            storeUserRef.setUserId(user.getUserId());
            storeUserRef.setUserName(username);
            storeUserRef.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
            storeUserRef.setTmCreate(store.getTmCreate());
            storeUserRefMapper.insert(storeUserRef);

            result.setSuccess(true);
            return result;
    }

    /**
     * 修改门店用户状态
     * @param storeId
     * @param status
     * @return
     */
    @Override
    public MerchantResult updateStoreUserStatus(Long storeId, String status) {
        MerchantResult result = new MerchantResult();
            //查询关联信息
            List<StoreUserRef> storeUserRefs =
                storeUserRefMapper.selectList(new EntityWrapper<StoreUserRef>().eq("storeId", storeId));
            if(storeUserRefs==null || storeUserRefs.size()==0){
                return result;
            }
            Long userId = storeUserRefs.get(0).getUserId();
            StoreUserRef storeUserRef = new StoreUserRef();
            storeUserRef.setId(storeUserRefs.get(0).getId());
            storeUserRef.setIsDeleted(IsDeleteEnum.IS_DELETE_Y.getValueDefined());
            storeUserRefMapper.updateById(storeUserRef);
            UserDto userDto = new UserDto();
            userDto.setUserId(userId);
            userDto.setStatus(status);
            userDubboProcess.updateUser(userDto);
            result.setSuccess(true);
            return result;

    }
    /**
     * 批量修改状态
     * @param storeIds
     * @param status
     * @return
     */
    @Override public MerchantResult batchUpdateStatus(List<Long> storeIds, String status) {
        MerchantResult result = new MerchantResult();
            //查询关联信息
            List<StoreUserRef> storeUserRefs;
            storeUserRefs = storeUserRefMapper.getByStoreIds(storeIds);
            if(storeUserRefs!=null && storeUserRefs.size()>0){
                List<Long> userIds = new ArrayList<>();
                for (StoreUserRef storeUserRef : storeUserRefs) {
                    userIds.add(storeUserRef.getUserId());
                }
                //批量修改门店状态
                userDubboProcess.batchUpdateStatus(userIds, status);
            }
            result.setSuccess(true);
            return result;
    }
}
