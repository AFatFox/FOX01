package com.lyh.controller.utils;

import lombok.Data;

//为了后端返回给前端的数据统一，可以在控制层(表现层)写一个能把所有的数据都封装起来的类，然后所有的返回操作都返回这个类的实例
//对于数据层的操作，一般有两种，一种是有返回数据的，即查询，一种是不用返回数据的，如增删改
//对于查询 ，这个类至少需要两个属性，一个用于表示对于请求的处理有没有异常(比如在查询操作，无法连接数据库，这就是服务端的异常)，
// 假设设置一个Boolean类型的属性用于记录是否有异常，如果服务端无异常则返回true，如果有异常，则返回flase
//另一个属性设置为Object类型，用于存储所有返回的数据。比如查询单个用户返回的实体类型，可直接放在Object中，或者查询全部返回的集合，也可直接放在Object中
//或者是查询操作未查到，返回的null也可放在object中。
//对于增删改，因为我们的结果只有一个true或者flase，表示成功或未成功，所以这个类我们只用用到一个属性
@Data
public class R {
    private Boolean flag;
    private Object data;
//    这个属性用于记录操作的状态，执行成功则返回成功，失败则返回失败或失败的原因
    private String msg;

    public R() {}


//    这个用于查找
//    查找中的flag仅用于判断系统是否出现异常
    public R(Boolean flag, Object object) {
        this.flag = flag;
        this.data = object;
    }

//    这个构造方法用于增删改,未出现异常则进入这里
    public R(Boolean flag, String msg){
        this.flag=flag;
        this.msg=msg;
    }

//    用于出现异常，则用到这个构造函数
    public R(String msg){
        this.flag=false;
        this.msg=msg;
    }
}
