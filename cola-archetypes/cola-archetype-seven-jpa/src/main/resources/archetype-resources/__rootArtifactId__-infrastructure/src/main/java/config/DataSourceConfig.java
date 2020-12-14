#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import com.wayne.jpa.base.config.BootConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author carter
 * create_date  2020/6/2 18:18
 * description     JPA基本配增强配置
 */
@Configuration
@EntityScan({"${package}.entity"})
public class DataSourceConfig extends BootConfig {


}