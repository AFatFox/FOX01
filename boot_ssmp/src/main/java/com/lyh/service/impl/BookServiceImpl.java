package com.lyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyh.Dao.BookDao;
import com.lyh.domain.Book;
import com.lyh.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
//MP开发业务层，使用MyBatisPlus提供有业务层通用接口(ISerivce<T>)与业务层通用实现类(ServiceImpl<M,T> )
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {

    @Autowired
    private BookDao bookDao;

    @Override
//    分页，IPage是MP中专门用于分页的一个类
//    selectPage(参数1，参数2)是MP中按照参将查询出来的结果进行分页的API，参数2传入查询条件所封装成的对象，参数1传入分页的要求所封装成的对象
//    参数2为null时表示查找所有结果
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage<Book> page=new Page<>(currentPage,pageSize);
        bookDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize, Book book) {
//        在MP中，LambdaQueryWrapper是用来封装查询条件的类，我们可以用它来构建查询条件
        LambdaQueryWrapper<Book> lqw=new LambdaQueryWrapper<Book>();
//        这里我们用like，模糊查询。
//        有三个参数，like(Boolean,类名:字段的getMethod,需要在这里字段中模糊查询的值)
//        第一个入参boolean 表示该条件是否加入最后生成的sql中
        lqw.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());

        IPage<Book> page=new Page<>(currentPage,pageSize);
        bookDao.selectPage(page,lqw);

        return page;
    }
}
