package com.csy.demo;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * kafka消费者测试类
 *
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-06-22
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // kafka broker 列表，可以指定一台机器的地址，也可以指定多台，kafka可以从其中某一个broker获取其他broker的机器信息
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.172.131:9092");
        // 指定key的序列化器
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        // 指定value的序列化器
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        // 指定群组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "GID_topic_test");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 指定消费者订阅的主题
        consumer.subscribe(Collections.singletonList("topic_test"));
        while (true) {
            ConsumerRecords<String, String> msg = consumer.poll(Duration.ofMillis(1000));
            if (msg.isEmpty()) {
                continue;
            }
            msg.forEach(record -> System.out.println(String.format("分区：%s, 偏移量：%s, value: %s",
                    record.partition(), record.offset(), record.value())));

        }
    }
}
