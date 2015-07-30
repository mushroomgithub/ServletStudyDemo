# ServletStudyDemo
Servlet/request/response/cookie/session Demo

---

###JavaBean的使用
> 采用javaBean时需要导入相应的jar包到WebRoot/WEB-INF/lib中，主要需要导入commons-beanutils-1.8.0.jar和commons_logging.jar两个包

###MVC设计模式在Web开发的使用方式

**MVC (model(可以看做javaBean) view(可以看做jsp) controller（可以看做Servlet))**

    >>> 客户端浏览器向服务器发送请求，servlet(Controller)这个控制器收到请求产生数据
    >>> 然后将产生的数据使用javaBean这个Model来封装然而servlet控制器并不适合做输出，因此将封装数据写给response域
    >>> 把用javaBean(Model)封装后的对象数据存到request域中然后使用forward技术带给jsp这个view，由jsp这个view取出数据做输出显示


