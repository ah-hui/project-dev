package ind.lgh.system.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能描述：kafka生产者.
 * 命令行启动：
 * <p>
 * 消费者：
 * bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic mi_db_upload_audit --from-beginning
 * bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic mi_db_parse_success --from-beginning
 * 生产者：
 * bin/kafka-console-producer.sh --broker-list localhost:9092 --topic mi_db_upload_audit
 * bin/kafka-console-producer.sh --broker-list localhost:9092 --topic mi_db_parse_success
 *
 * @author liuguanghui
 * @since 2018-10-09
 */
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送审核完成消息
     *
     * @param visitNumber 就诊编号
     */
    public void sendUploadAudit(String visitNumber) {
        kafkaTemplate.send("mi_db_upload_audit","key", visitNumber);
    }

    /**
     * 发送解析完成消息
     *
     * @param b 是否解析成功
     */
    public void sendParseSuccess(boolean b) {
        kafkaTemplate.send("mi_db_parse_success","key", b);
    }
}
