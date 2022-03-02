package com.lyh.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyh.domain.Book;
import com.lyh.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private IBookService bookService;

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
        book.setId(14);
        System.out.println(bookService.updateById(book));
    }

    @Test
    void testDelete(){
        System.out.println(bookService.removeById(14));
    }

    @Test
    void testGetAll(){
        System.out.println(bookService.list());
    }

    @Test
    void testSave(){
        Book book=new Book();
        book.setDescription("Test描述");
        book.setName("Test名字");
        book.setType("Test类型");
        System.out.println(bookService.save(book));
    }

    @Test
    void testGetPage(){
        IPage<Book> page = new Page<Book>(2,5);
        bookService.page(page);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }
}
