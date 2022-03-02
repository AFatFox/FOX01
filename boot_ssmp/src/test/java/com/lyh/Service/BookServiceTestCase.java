package com.lyh.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyh.domain.Book;
import com.lyh.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTestCase {

    @Autowired
    private BookService bookService;

    @Test
    void testGetById(){
        System.out.println(bookService.getById(1));
    }

    @Test
    void testUpdate(){
        Book book=new Book();
        book.setDescription("Test描述02");
        book.setName("Test名字02");
        book.setType("Test类型02");
        System.out.println(bookService.update(book));
    }

    @Test
    void testDelete(){
        System.out.println(bookService.delete(14));
    }

    @Test
    void testGetAll(){
        System.out.println(bookService.getAll());
    }

    @Test
    void testSave(){
        Book book=new Book();
        book.setDescription("Test描述");
        book.setName("Test名字");
        book.setType("Test类型");
        book.setId(13);
        System.out.println(bookService.save(book));
    }

    @Test
    void testGetPage(){
        IPage<Book> page = bookService.getPage(1, 5);
        System.out.println(page.getCurrent());  //current表示展示哪一页
        System.out.println(page.getSize());  //size表示一页展示几条
        System.out.println(page.getTotal());  //total表示总共有多少条数据
        System.out.println(page.getPages());  //pages表示总共有几页
        System.out.println(page.getRecords());  //records表示此页所包含的数据
    }
}
