package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @PackageName config
 * @Author wangzheng
 * @Date 2020/1/29 16:01
 * @Description 该类是一个配置类
 *              作用：和bean.xml一样
 *
 * Spring中的新注解：
 *  Configuration：
 *      作用：指定当前类是一个配置类
 *      细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写
 *  ComponentScan：
 *      作用：用于通过注解指定Spring在创建容器时要扫描的包
 *      属性：
 *          value：和basePackage的作用一样，都是用于指定创建容器时要扫描的包。
 *                 使用此注解，等同于在xml文件中配置了
 *                 <context:component-scan base-package="com.dududadida"></context:component-scan>
 *
 *  Bean：
 *      作用：把当前方法的返回值作为bean对象存入spring的IOC容器中
 *      属性：
 *          name：用于指定bean的id，当不写时默认值是当前方法的名称
 *      细节：当我们使用注解配置方法是，如果方法有参数，那么spring框架回去容器中查找有没有可用的
 *           bean对象，查找的方式和Autowired的方式是一样的
 *
 *  Import：
 *      作用：用于导入其他的配置类
 *          value：用于指定其他配置类的字节码，当使用Import的注解之后，有Import注解的类就是父配置类
 *              导入的都是子配置类。
 *
 *  PropertySource：
 *      作用：用于指定properties文件的位置
 *      属性：
 *          value：指定文件的名称和路径
 *              关键字：classpath表示类路径下
 */
//@Configuration
@ComponentScan("com.dududadida")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
