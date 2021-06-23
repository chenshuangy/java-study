package com.csy.demo;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import lombok.extern.slf4j.Slf4j;

/**
 * kafka生产者测试类
 *
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-06-22
 */
@Slf4j
public class KafkaProducerTest {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // kafka broker 列表，可以指定一台机器的地址，也可以指定多台，kafka可以从其中某一个broker获取其他broker的机器信息
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.172.131:9092");
        // 指定key的序列化器
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        // 指定value的序列化器
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer(properties);
        ProducerRecord<String, String> msg = new ProducerRecord<>("topic_test", "1", "test01");
       log.info("开始发送");
        producer.send(msg);
        log.info("发送结束");
        producer.close();
    }
}
