package ind.lgh.system.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述：测试用！！！.
 *
 * @author liuguanghui
 * @since 2018-10-09
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource
    private KafkaProducer producer;

    @GetMapping("/test1")
    public void test1() {
        producer.sendUploadAudit("11111");
    }

    @GetMapping("/test2")
    public void test2() {
        producer.sendParseSuccess(true);
    }
}
