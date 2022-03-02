package com.lyh.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyh.domain.Book;
import com.lyh.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//这个仅做示例，不作表现层，所以把@RestController给取消
//@RestController
@RequestMapping("/books")
//下面是restful风格和前后端分离的开发
//restful在这里的体现是路径的设置、请求的方式。如这里在类上设置一个路径，类中的方法不设置路径，仅通过客户端请求的方式来决定进入哪个方法
//前后端分离体现在参数的设置上，在需要接收Book实例的方法中，用于接收客户端传来的Json串，在结束整形类型的方法中，用于接收URL后面跟的数字，如/books/1
public class BookController2 {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<Book> GetAll(){
        return bookService.list();
    }

    @PutMapping
    public Boolean update(@RequestBody Book book){
        return bookService.updateById(book);
    }

    @PostMapping
//    @RequestBody主要用来接收前端传递给后端的json字符串中的数据
    public Boolean save(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("{id}")
    public Boolean delect(@PathVariable Integer id){
        return bookService.removeById(id);
    }

    @GetMapping("{id}")
//    @PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值，即路径变量
    public Book getById(@PathVariable Integer id){
        return bookService.getById(id);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public IPage<Book> getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return bookService.getPage(currentPage,pageSize);
    }
}
