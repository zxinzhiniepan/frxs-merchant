package com.frxs.merchant.core.generator;

import com.frxs.framework.util.common.StringUtil;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.core.common.SqlHelper;
import com.frxs.merchant.core.exception.TransactionPromotionException;
import com.frxs.merchant.core.mapper.SeqStoreMapper;
import com.frxs.merchant.core.mapper.SeqVendorAccountMapper;
import com.frxs.merchant.core.mapper.SeqVendorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wushuo
 * @version $Id: IdGenerator.java,v 0.1 2018年02月27日 10:13 $Exp
 */

@Service("idGeneratorFactory")
public class IdGenerator {
    private static final int SEQ_LENGTH = 10;

    @Autowired SeqStoreMapper seqStoreMapper;
    @Autowired SeqVendorMapper seqVendorMapper;
    @Autowired SeqVendorAccountMapper seqVendorAccountMapper;


    /**
     * 生成芙蓉兴盛门店ID
     *
     * 门店Id的生成规则:
     * 门店：[6688固定前缀] + [10位流水号的后10位]
     * @return 芙蓉兴盛门店ID
     */
    public Long genStoreId() {
        StringBuilder buffer = new StringBuilder();
        // 生成10位顺序号
        String originalSeq = String.valueOf(seqStoreMapper.getNextSeqForStore());
        String strSeq = StringUtil.alignRight(originalSeq, SEQ_LENGTH, "0");

        // 6688
        buffer.append(StringUtil.alignRight("" + "6688", 4, "0"));
        // 10位流水号
        buffer.append(strSeq);
        if (originalSeq.length() > SEQ_LENGTH) {
            LogUtil.error("[IDGeneratorFactory:主键策略]ID生成异常,originalSeq:{} 长度超过 SEQ_LENGTH:{}",originalSeq,SEQ_LENGTH);
            throw new TransactionPromotionException(ErrorCodeDetailEnum.PARAM_ILLEGAL,"storeId的sequence超长");
        }

        return Long.valueOf(buffer.toString().trim());

    }

    /**
     * 生成芙蓉兴盛供应商ID
     *
     * 供应商Id的生成规则:
     * 供应商：[7688固定前缀] + [10位流水号的后10位]
     * @return 芙蓉兴盛供应商ID
     */
    public Long genVendorId() {
        StringBuilder buffer = new StringBuilder();
        // 生成10位顺序号
        String originalSeq = String.valueOf(seqVendorMapper.getNextSeqForVendor());
        String strSeq = StringUtil.alignRight(originalSeq, SEQ_LENGTH, "0");

        // 7688
        buffer.append(StringUtil.alignRight("" + "7688", 4, "0"));
        // 10位流水号
        buffer.append(strSeq);
        if (originalSeq.length() > SEQ_LENGTH) {
            LogUtil.error("[IDGeneratorFactory:主键策略]ID生成异常,originalSeq:{} 长度超过 SEQ_LENGTH:{}",originalSeq,SEQ_LENGTH);
            throw new TransactionPromotionException(ErrorCodeDetailEnum.PARAM_ILLEGAL,"vendorId的sequence超长");
        }

        return Long.valueOf(buffer.toString().trim());

    }

    /**
     * 生成芙蓉兴盛供应商账户ID
     *
     * 供应商账户Id的生成规则:
     * 供应商账户 [8688固定前缀] + [10位流水号的后10位]
     * @return 芙蓉兴盛供应商账户ID
     */
    public Long genVendorAccountId() {
        StringBuilder buffer = new StringBuilder();
        // 生成10位顺序号
        String originalSeq = String.valueOf(seqVendorAccountMapper.getNextSeqForVendorAccount());
        String strSeq = StringUtil.alignRight(originalSeq, SEQ_LENGTH, "0");

        // 8688
        buffer.append(StringUtil.alignRight("" + "8688", 4, "0"));
        // 10位流水号
        buffer.append(strSeq);
        if (originalSeq.length() > SEQ_LENGTH) {
            LogUtil.error("[IDGeneratorFactory:主键策略]ID生成异常,originalSeq:{} 长度超过 SEQ_LENGTH:{}",originalSeq,SEQ_LENGTH);
            throw new TransactionPromotionException(ErrorCodeDetailEnum.PARAM_ILLEGAL,"vendorAccountId的sequence超长");
        }

        return Long.valueOf(buffer.toString().trim());

    }
}
