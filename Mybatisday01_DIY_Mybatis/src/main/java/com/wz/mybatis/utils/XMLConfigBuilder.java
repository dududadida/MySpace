package com.wz.mybatis.utils;

import com.wz.mybatis.annotation.Select;
import com.wz.mybatis.cfg.Configuration;
import com.wz.mybatis.cfg.Mapper;
import com.wz.mybatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.wz.mybatis.utils
 * @ClassName XMLConfigBuilder
 * @Author wangzheng
 * @Date 2020/2/8 11:53
 * @Description 用于解析配置文件
 */
public class XMLConfigBuilder {
    /**
     * 解析主配置文件：把里边的内容填充到DefaultSqlSession所需要的的地方
     * 使用技术：dom4j+xpath
     * @param config
     * @return
     */
    public static Configuration loadConfiguration(InputStream config){
        try {
            //定义封装连接信息的配置对象
            Configuration configuration = new Configuration();

            //1、获取SAXReader对象
            SAXReader reader = new SAXReader();
            //2、根据字节输入流获取Document对象
            Document document = reader.read(config);
            //3、获取根节点
            Element root = document.getRootElement();
            //4、使用xpath中选择指定节点的方式，获取所有property节点
            List<Element> propertyElements = root.selectNodes("//property");
            //5、遍历节点
            for (Element propertyElement : propertyElements) {
                //判断节点是连接数据库的哪部分信息
                //取出name属性的值
                String name = propertyElement.attributeValue("name");
                if ("driver".equals(name)) {
                    //表示驱动
                    //获取property标签value属性的值
                    String driver = propertyElement.attributeValue("value");
                    configuration.setDriver(driver);
                }

                if ("url".equals(name)) {
                    //表示驱动
                    //获取property标签value属性的值
                    String url = propertyElement.attributeValue("value");
                    configuration.setUrl(url);
                }

                if ("username".equals(name)) {
                    //表示驱动
                    //获取property标签value属性的值
                    String username = propertyElement.attributeValue("value");
                    configuration.setUsername(username);
                }

                if ("password".equals(name)) {
                    //表示驱动
                    //获取property标签value属性的值
                    String password = propertyElement.attributeValue("value");
                    configuration.setPassword(password);
                }
            }

            //取出mappers中的所有mapper标签，判断他们使用了resource还是class属性
            List<Element> mapperElements = root.selectNodes("//mappers/mapper");

            //遍历集合
            for (Element mapperElement : mapperElements) {
                //判断mapperElement使用的是哪个属性
                Attribute attribute = mapperElement.attribute("resource");
                if (attribute != null) {
                    System.out.println("使用的是XML");
                    //表示有resource属性，用的是XML
                    //取出属性的值
                    String mapperPath = attribute.getValue();
                    //把映射配置文件的内容获取出来，封装成一个map
                    Map<String, Mapper> mappers = loadMapperConfiguration(mapperPath);
                    //给configuration中的mappers赋值
                    configuration.setMappers(mappers);
                }else {
                    System.out.println("使用的是注解");
                    //表示有没resource属性，用的是注解
                    //取出class属性的值
                    String daoClassPath = mapperElement.attributeValue("class");
                    //根据daoClassPath获取封装的必要信息
                    Map<String, Mapper> mappers = loadMapperAnnotation(daoClassPath);
                    configuration.setMappers(mappers);
                }
            }
            //返回Configuration
            return configuration;
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            try {
                config.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据传入的参数解析XML，并封装到Map中
     * @param mapperPath 映射配置文件的位置
     * @return map中包含了获取的唯一标识(key是由dao的全限定类名和方法组成)
     * 以及执行所需的必要信息
     */
    private static Map<String, Mapper> loadMapperConfiguration(String mapperPath) throws IOException {
        InputStream in = null;
        try {
            //定义返回值对象
            Map<String, Mapper> mappers = new HashMap<String, Mapper>();
            //1、根据路径获取字节流
            in = Resources.getResourceAsStream(mapperPath);
            //2、根据字节流输入获取Document对象
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            //3、获取根节点
            Element root = document.getRootElement();
            //4、根据根节点的namespace属性取值
            String namespace = root.attributeValue("namespace"); //map中key的成分
            //5、获取所有的select节点
            List<Element> selectElements = root.selectNodes("//select");
            //6、遍历select节点集合
            for (Element selectElement : selectElements) {
                //取出id属性的值      组成map中的key的成分
                String id = selectElement.attributeValue("id");
                //取出resultType属性的值      组成map中的value的成分
                String resultType = selectElement.attributeValue("resultType");
                //取出文本内容      组成map中的value的成分
                String queryString = selectElement.getText();
                //创建key
                String key = namespace + "." + id;
                //创建Value
                Mapper mapper = new Mapper();
                mapper.setQueryString(queryString);
                mapper.setResultType(resultType);
                //把key和value存入mappers中
                mappers.put(key, mapper);
            }
            return mappers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            in.close();
        }
    }

    private static Map<String, Mapper> loadMapperAnnotation(String daoClassPath)throws Exception{
        Map<String, Mapper> mappers = new HashMap<String, Mapper>();

        //1、得到dao接口的字节码文件
        Class daoClass = Class.forName(daoClassPath);
        //2得到dao接口中的方法数组
        Method[] methods = daoClass.getMethods();
        //3遍历method数组
        for (Method method : methods) {
            //取出每一个方法，判断是否有select注解
            boolean isAnnotated  = method.isAnnotationPresent(Select.class);
            if (isAnnotated) {
                //创建Mapper对象
                Mapper mapper = new Mapper();
                //取出注解的value值
                Select selectAnno = method.getAnnotation(Select.class);
                String queryString = selectAnno.value();
                mapper.setQueryString(queryString);
                //获取当前方法的返回值    还要求必须带有泛型信息
                Type type = method.getGenericReturnType();  //List<User>
                //判断type是不是参数化类型
                if (type instanceof ParameterizedType) {
                    //强转
                    ParameterizedType ptype = (ParameterizedType) type;
                    //得到参数化类型的实际参数
                    Type[] types = ptype.getActualTypeArguments();
                    //取出第一个
                    Class domainClass = (Class)types[0];
                    //获取domainClass的类名
                    String resultType = domainClass.getName();
                    //给mapper赋值
                    mapper.setResultType(resultType);
                }
                //组装key的信息
                //获取方法的名称
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String key = className + "." + methodName;
                //给map赋值
                mappers.put(key, mapper);
            }
        }
        return mappers;
    }
}
