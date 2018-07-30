package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 功能描述: 预处理功能组.
 *
 * @author liuguanghui
 * @since 2018-7-26
 */
@Getter
@Setter
public class DpPreprocessingFunctionGroup {

    private String name;

    private Short type;

    private List<DpPreprocessingFunction> functions;
}
