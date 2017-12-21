package ind.lgh.business.repository;

import ind.lgh.business.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 暂用作测试多数据源 - 消息
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
