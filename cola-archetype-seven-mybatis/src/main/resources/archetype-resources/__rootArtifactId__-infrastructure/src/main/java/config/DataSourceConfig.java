#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import com.wingto.space.common.mybatis.config.AbstractMybatisConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author carter
 * create_date  2020/6/2 18:18
 * description     mybtis增强配置
 */
@Configuration
public class DataSourceConfig extends AbstractMybatisConfig {
    @Override
    protected String getBasePackage() {
        String sourceConfigPackage = getClass().getPackage().getName();
        String repositoryPackage = sourceConfigPackage.substring(0,sourceConfigPackage.lastIndexOf("."));
        return repositoryPackage + ".repository";
    }

}
