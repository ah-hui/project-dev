package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述: 预处理功能组.
 * 注：@ConfigurationProperties注解的location属性被移除
 * 另@PropertySource注解又无法加载yml
 *
 * @author liuguanghui
 * @since 2018-7-26
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "preprocessing.function-list")
public class DpPreprocessingFunctionList {

    private String name;

    private List<DpPreprocessingFunctionGroup> group;
}
