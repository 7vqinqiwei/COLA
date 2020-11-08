#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * Spring Boot Starter
 *
 * COLA framework initialization is configured in {@link ${package}.config.ColaConfig}
 *
 * @author Frank Zhang
 */
@SpringBootApplication(scanBasePackages = {"${package}","com.alibaba.cola","com.wingto.space.controller.support"})
@EnableSwagger2Doc
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
