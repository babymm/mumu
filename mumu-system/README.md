# 1、权限管理系统
## 1.1、简介
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建web项目经常需要的就是管理权限，权限管理是安全最重要的步骤，
   所以我单独创建一个项目专门管理权限的（其他项目想要使用权限，直接通过maven overlay功能来实现即可），省去
   代码的赋值粘贴浪费时间。<br/>
   本权限管理系统主要包括如下几个方面：<br/><br/>
   <font color="aqua" size="6">组织机构</font><br/>
   <font color="aqua" size="6">用户群组</font><br/>
   <font color="aqua" size="6">用户成员</font><br/>
   <font color="aqua" size="6">角色</font><br/>
   <font color="aqua" size="6">按钮</font><br/>
   <font color="aqua" size="6">权限</font><br/>
   <font color="aqua" size="6">日志</font><br/>
   <font color="aqua" size="6">导出设置</font><br/>
   <font color="aqua" size="6">数据字典</font><br/>
   <font color="aqua" size="6">数据监控</font><br/>
   <font color="aqua" size="6">消息</font><br/>

## 1.2、构成
### 1.2.1、接口层
    权限管理系统接口层，定义接口实体和服务接口层，方便rpc、webservice调用
### 1.2.2、服务实现层
    权限管理系统服务层，主要是实现数据中心业务逻辑。
### 1.2.3、控制器层
    权限管理系统控制层，展示页面，操作数据。





# 2、工作日志

2017-08-18 <br/>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权限管理系统基本已经完成，现在准备页面添加导出功能。计划
   下一步实现站内消息的功能，正在准备技术的选型。<br/>
   <a target="_blank" href="http://netty.io/">netty</a><br/>
   <a target="_blank" href="http://t-io.org/#/">t-io</a><br/>
   <a target="_blank" href="http://activemq.apache.org/">activeMQ</a><br/>
   <a target="_blank" href="https://git.oschina.net/rushmore/zbus">zbus</a><br/>
   修改权限管理系统界面展示bug.