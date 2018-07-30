package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 功能描述: 预处理功能.
 *
 * @author liuguanghui
 * @since 2018-7-26
 */
@Getter
@Setter
public class DpPreprocessingFunction {
    private Long id;
    private String name;
    private List<DpPreprocessingFunction> children;
}
