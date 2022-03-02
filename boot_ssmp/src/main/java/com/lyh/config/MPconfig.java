package com.lyh.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
// 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
// 并用于构建bean定义，初始化Spring容器。
public class MPconfig {

    @Bean
//    创建一个MP的拦截器
    public MybatisPlusInterceptor interceptor(){
        MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
//        上面一句表示为这个拦截器加一个功能，功能就是后面new的那个，这里的用于就是用于分页
        return interceptor;
    }
}
