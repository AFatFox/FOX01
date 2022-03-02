package com.lyh.Dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyh.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
	@Autowired
	BookDao bookDao;

	@Test
//	按照ID查询
	void contextLoads() {
		System.out.println(bookDao.selectById(1));
	}

	@Test
//	增加
	void add(){
		Book book=new Book();
		book.setDescription("Test描述");
		book.setName("Test名字");
		book.setType("Test类型");
		bookDao.insert(book);
	}

	@Test
//	查找所有
	void GetAll(){
		System.out.println(bookDao.selectList(null));
	}

	@Test
//	修改
	void update(){
		Book book=new Book();
		book.setDescription("Test描述01");
		book.setName("Test名字01");
		book.setType("Test类型01");
		book.setId(13);
		bookDao.updateById(book);
	}

	@Test
//	分页
	void page(){
		IPage page=new Page(2,5);
		bookDao.selectPage(page,null);
		System.out.println(page.getCurrent());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
		System.out.println(page.getRecords());
	}

	@Test
//	条件查询01
	void GetBy(){
//		queryWrapper是mybatis plus中实现查询的对象封装操作类
		QueryWrapper<Book> qw=new QueryWrapper<Book>();
		qw.like("name","程");
		bookDao.selectList(qw);
	}

	@Test
//	条件查询02
	void GetBy02(){
		String name=null;
//		用LambdaQueryWrapper代替QueryWrapper可以使用表达式，即Book::getName，这个可以获得
		LambdaQueryWrapper<Book> Lqw=new LambdaQueryWrapper<Book>();
		Lqw.like(name!=null,Book::getName,name);
//		condition用于加一个条件，只有条件通过才会执行这个语句
		bookDao.selectList(Lqw);
	}

}
