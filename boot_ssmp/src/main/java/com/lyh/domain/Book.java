package com.lyh.domain;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Repository;

//lombok
//导入坐标后，还需要打开setting-editor-plugins搜Lombok插件，install，才能显示方法。安装完成后CTRL+F12显示界面，直接键盘输入就可以搜索关键字
@Repository
@Data  //可以为当前实体类添上大部分常用的方法，不包含构造方法
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;
}
