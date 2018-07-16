# TestSSM  maven搭建ssm框架

第一步：
拷贝本项目到本地


![](https://gitee.com/uploads/images/2018/0330/092527_a74bedfe_1117056.png "屏幕截图.png")


第二步：
用intellij idea打开本项目，待加载项目完毕后，检查maven所添加的依赖是否成功。
![](https://gitee.com/uploads/images/2018/0330/092934_fd6fb4d3_1117056.png "屏幕截图.png")
确定maven无报错

第三步：准备数据库
我使用的是phpmyadmin来管理mysql的，其实本库的数据库表非常简单，就是一个admin表，username和password两个字段，确保数据库搭建好。

第四步：修改jdbc
主要是用户名和密码，还有数据库的名称修改成自己的。
![](https://gitee.com/uploads/images/2018/0330/094319_b3e4cbd2_1117056.png "屏幕截图.png")


第五步：设置tomcat 启动项目

![](https://gitee.com/uploads/images/2018/0330/101510_85a95cc8_1117056.png "屏幕截图.png")