package ind.lgh.system.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 功能描述：kafka消费者.
 *
 * @author liuguanghui
 * @since 2018-10-09
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"mi_db_upload_audit"})
    public void listenUploadAudit(ConsumerRecord<?, ?> record) {
        log.info("Kafka 监听到上传审核消息：【{}】，即将开始消费...", record.key());
        log.info("Kafka 监听到上传审核消息：【{}】，即将开始消费...", record.value());
        log.info("Kafka消息消费完成！");
    }
}
