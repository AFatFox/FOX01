package com.lyh.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyh.controller.utils.R;
import com.lyh.domain.Book;
import com.lyh.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
//下面是restful风格和前后端分离的开发
//restful在这里的体现是路径的设置、请求的方式。如这里在类上设置一个路径，类中的方法不设置路径，仅通过客户端请求的方式来决定进入哪个方法
//前后端分离体现在参数的设置上，在需要接收Book实例的方法中，用于接收客户端传来的Json串，在结束整形类型的方法中，用于接收URL后面跟的数字，如/books/1
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public R GetAll(){
        return new R(true,bookService.list());
    }

    @PutMapping
    public R update(@RequestBody Book book){
        Boolean flag=bookService.updateById(book);
        return new R(flag,flag?"修改成功":"修改失败");
    }

    @PostMapping
//    @RequestBody主要用来接收前端传递给后端的json字符串中的数据
    public R save(@RequestBody Book book){
        Boolean flag=bookService.save(book);
        return new R(flag,flag?"添加成功":"添加失败");
    }

    @DeleteMapping("{id}")
    public R delect(@PathVariable Integer id){
        Boolean flag=bookService.removeById(id);
        return new R(flag,flag?"删除成功":"删除失败");
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Book book){
//        IPage<Book> page=bookService.getPage(currentPage,pageSize);
        IPage<Book> page=bookService.getPage(currentPage,pageSize,book);
//        在这里进行修护前面的分页BUG
//        先判断是否当前页大于最大页
        if (currentPage>page.getPages()){
//            若大于，将当前页设置为最大页再按照分页操作进行一次查询
            page=bookService.getPage((int) page.getPages(),pageSize,book);
        }
        return new R(true,page);
    }

    @GetMapping("{id}")
//    @PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值，即路径变量
    public R getById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }
//    @GetMapping("{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
//        IPage<Book> page=bookService.getPage(currentPage,pageSize);
////        在这里进行修护前面的分页BUG
////        先判断是否当前页大于最大页
//        if (currentPage>page.getPages()){
////            若大于，将当前页设置为最大页再按照分页操作进行一次查询
//            page=bookService.getPage((int) page.getPages(),pageSize);
//        }
//        return new R(true,page);

//    }
}
