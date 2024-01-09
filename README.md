# School-fleak market
一个由springboot+vue架构实现的校园二手市场系统

后端技术栈：springboot+mybatisplus+knife4j(swagger增强)+MySQL

前端技术栈：Vue+ElementUI+axios+vue-router+Vuex+vue-cli

前端后台界面使用vue-admin-template框架

前端代码改自https://github.com/Rean-S/MallShop.git

本项目为软件工程课程大作业，第一次做项目代码不规范，仅供参考


# 使用部署说明

1、clone项目到本地 git clone https://github.com/ASTK007/School-fleak-market.git

2、在本地MySQL（默认版本8.0以上，否则自行修改驱动路径及依赖）中建立一个空数据库，编码格式为utf-8，使用根目录下的schoolmarket.sql文件建表

3、使用IDEA、Vscode等具备springboot项目（默认springboot项目版本3.2.0，需要JDK17及以上，可自行尝试降低版本运行）运行环境的软件启动hzcuFleakMarket\backEnd中的入口类文件HzcuFleakmarketApplication.java

4、后端启动后，可在浏览器中输入：

http://localhost:8081/doc.html

进入knife4j后端调试工具界面，成功进入则启动成功

5、进入到frontEnd目录中，依次在命令行输入：

#安装依赖
npm install

#启动项目
npm run dev

前端启动成功之后会自动在浏览器打开网页
