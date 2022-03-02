package com.lyh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyh.domain.Book;

//用MP开发业务层，使用MyBatisPlus提供有业务层通用接口(ISerivce<T>)与业务层通用实现类(ServiceImpl<M,T> )
public interface IBookService extends IService<Book> {

//    IPage是MP中专门用于分页的一个类
    IPage<Book> getPage(int currentPage,int pageSize);

//    用于条件查询，Book中的参数值作为查询条件
    IPage<Book> getPage(int currentPage, int pageSize, Book book);
}
