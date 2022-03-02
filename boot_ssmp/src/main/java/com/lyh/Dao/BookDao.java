package com.lyh.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyh.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
//类的上面需要写上@Mapper注释用来表示该接口类的实现类对象交给mybatis底层创建，然后交由Spring框架管理。
@Repository
//MP的方法
public interface BookDao extends BaseMapper<Book> {

//    @Select("select * from tbl_book where id = #{id}")
//    Book getById(Integer id);
}
