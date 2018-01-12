package ind.lgh;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Spring主程序.
 *
 * @author lgh
 * @since 2017-12-21
 */
@SpringBootApplication
@MapperScan(basePackages = "ind.lgh.system.mapper")
public class Application extends WebMvcConfigurerAdapter {

    /**
     * fast-json解析数据
     * 示例请参考：
     *
     * @see ind.lgh.system.domain.BaseEntity
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        // 定义一个converter转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //添加fast-json的配置信息，比如：是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        /*
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        */
        // 在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 将converter添加到converters中
        converters.add(fastConverter);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
