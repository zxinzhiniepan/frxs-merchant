/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.config;

import com.frxs.merchant.core.rocketmq.TestRocketmqStringListener;
import com.frxs.framework.integration.rocketmq.adapter.RocketMqConsumerAdapter;
import com.frxs.framework.integration.rocketmq.adapter.RocketMqProducerAdapter;

import java.util.HashMap;
import java.util.Map;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.config.FieldRetrievingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketmqConfig
 *
 * @author mingbo.tang
 * @version $Id: RocketmqConfig.java,v 0.1 2018年01月07日 下午 13:46 $Exp
 */
@Configuration
public class
RocketmqConfig {

//    @Bean("CONSUME_FROM_FIRST_OFFSET")
    public FieldRetrievingFactoryBean consumeFromFirstOffset() {
        FieldRetrievingFactoryBean fieldRetrievingFactoryBean = new FieldRetrievingFactoryBean();
        fieldRetrievingFactoryBean.setStaticField(
            "org.apache.rocketmq.common.consumer.ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET");
        return fieldRetrievingFactoryBean;
    }

//    @Bean("CONSUME_FROM_LAST_OFFSET")
    public FieldRetrievingFactoryBean consumeFromLastOffset() {
        FieldRetrievingFactoryBean fieldRetrievingFactoryBean = new FieldRetrievingFactoryBean();
        fieldRetrievingFactoryBean.setStaticField(
            "org.apache.rocketmq.common.consumer.ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET");
        return fieldRetrievingFactoryBean;
    }

//    @Bean("CONSUME_FROM_TIMESTAMP")
    public FieldRetrievingFactoryBean consumeFromTimestamp() {
        FieldRetrievingFactoryBean fieldRetrievingFactoryBean = new FieldRetrievingFactoryBean();
        fieldRetrievingFactoryBean.setStaticField(
            "org.apache.rocketmq.common.consumer.ConsumeFromWhere.CONSUME_FROM_TIMESTAMP");
        return fieldRetrievingFactoryBean;
    }

    /**
     * 配置Test消息生产者
     *
     * @return DefaultMQProducer
     */
//    @Bean("testProducer")
    public DefaultMQProducer testDefaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(RocketmqConstant.TEST_PRODUCER_GROUP);
        producer.setInstanceName(RocketmqConstant.TEST_PRODUCER_INSTANCE);
        return producer;
    }

    /**
     * 配置消息生产者适配器
     *
     * @param testProducer 测试消息生产者
     * @return RocketMqProducerAdapter
     */
//    @Bean("rocketMqProducerAdapter")
    public RocketMqProducerAdapter rocketMqProducerAdapter(DefaultMQProducer testProducer) {
        RocketMqProducerAdapter rocketMqProducerAdapter = new RocketMqProducerAdapter();
        rocketMqProducerAdapter.getDefaultMQProducerMap()
            .put(RocketmqConstant.TEST_PRODUCER_INSTANCE, testProducer);
        return rocketMqProducerAdapter;
    }

    /**
     * 配置测试String消息消费者
     *
     * @param testRocketmqStringListener 测试消息String消费者监听器
     * @return 测试消息消费者
     */
//    @Bean("testStringConsumer")
    public DefaultMQPushConsumer testStringDefaultMQPushConsumer(
        TestRocketmqStringListener testRocketmqStringListener) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(RocketmqConstant.TEST_STRING_CONSUMER_GROUP);
        consumer.setInstanceName(RocketmqConstant.TEST_STRING_CONSUMER_INSTANCE);
        Map<String, String> subscription = new HashMap<>(16);
        // tag:只消费string消息
        subscription.put(RocketmqConstant.TEST_TOPIC, "string");
        consumer.setSubscription(subscription);
        consumer.setMessageListener(testRocketmqStringListener);
        return consumer;
    }

    /**
     * 配置测试Object消息消费者
     *
     * @param testRocketmqObjectListener 测试消息Object消费者监听器
     * @return 测试消息消费者
     */
//    @Bean("testObjectConsumer")
    public DefaultMQPushConsumer testObjectDefaultMQPushConsumer(
        TestRocketmqStringListener testRocketmqObjectListener) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(RocketmqConstant.TEST_OBJECT_CONSUMER_GROUP);
        consumer.setInstanceName(RocketmqConstant.TEST_OBJECT_CONSUMER_INSTANCE);
        Map<String, String> subscription = new HashMap<>(16);
        // * 消费该TOPIC所有消息
        // tag:只消费object消息
        subscription.put(RocketmqConstant.TEST_TOPIC, "object");
        consumer.setSubscription(subscription);
        consumer.setMessageListener(testRocketmqObjectListener);
        return consumer;
    }

    /**
     * 配置消息消费者适配器
     *
     * @param testStringConsumer 测试消息消费者
     * @return RocketMqConsumerAdapter
     */
//    @Bean("rocketMqConsumerAdapter")
    public RocketMqConsumerAdapter rocketMqConsumerAdapter(DefaultMQPushConsumer testStringConsumer,
        DefaultMQPushConsumer testObjectConsumer) {
        RocketMqConsumerAdapter rocketMqConsumerAdapter = new RocketMqConsumerAdapter();
        rocketMqConsumerAdapter.getDefaultMQPushConsumerMap()
            .put(RocketmqConstant.TEST_STRING_CONSUMER_INSTANCE, testStringConsumer);
        rocketMqConsumerAdapter.getDefaultMQPushConsumerMap()
            .put(RocketmqConstant.TEST_OBJECT_CONSUMER_INSTANCE, testObjectConsumer);
        return rocketMqConsumerAdapter;
    }

}
