# SpringMVC与Spring整合

###  分容目的

1. SpringMVC和Spring整合的目的：分工明确

   1. SpringMVC的配置文件就来配置和网站转发逻辑以及网站功能有关的

      （视图解析器，文件上传解析器，支持ajax，xxx）

   2. Spring的配置文件来配置和业务有关的（事务控制，数据源，xxx）



### **SpringMVC和Spring分容器**

#### Spring管理业务逻辑组件

存放 @Service , @Component , @Repository  等组件

```xml
<context:component-scan base-package="com.atguigu">
    <!--不添加 Controller和Controller注解的组件-->
    <context:exclude-filter type="annotation"expression="....Controller"/>
    <context:exclude-filter type="annotation" expression="...ControllerAdvice"/>
</context:component-scan>
```

#### SpringMVC管理控制器组件

存放 @Controller 和 @ControllerAdvice

```xml
<context:component-scan base-package="com.atguigu" use-default-filters="false">
    <!--只添加 Controller和Controller注解的组件-->
    <context:include-filter type="annotation" expression="...Controller"/>
    <context:include-filter type="annotation" expression="...ControllerAdvice"/>
</context:component-scan>

```

Spring是一个父容器

SpringMVC是一个子容器

- **子容器还可以引用(装配)父容器的组件**
- **父容器不能引用子容器的组件**

![image-20220228211906231](C:\Users\14552\AppData\Roaming\Typora\typora-user-images\image-20220228211906231.png)

# Spring与Mybatis整合

整合适配包下载

`MyBatis-Spring `会帮助你将 MyBatis 代码无缝地整合到 Spring 中。它将允许 MyBatis 参与到 Spring 的事务管理之中，创建映射器 mapper 和 SqlSession 并注入到 bean 中，以及将 Mybatis 的异常转换为 Spring 的 DataAccessException。最终，可以做到应用代码不依赖于 MyBatis，Spring 或 MyBatis-Spring。

```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.6</version>
</dependency>
```

[官方文档](http://mybatis.org/spring/zh/index.html)

[源码仓库](https://github.com/mybatis/spring)

[官方整合示例](https://github.com/mybatis/jpetstore-6)

1. 所有需要的jar包导入 :[pom.xml]()

2. 引入MyBatis的配置文件 :

   [mybatis-config.xml]():

   ```xml
   <configuration>
   	<settings>
   		<setting name="mapUnderscoreToCamelCase" value="true"/>
   		<!--显示的指定每个我们需要更改的配置的值，即使他是默认的。防止版本更新带来的问题  -->
   		<setting name="lazyLoadingEnabled" value="true"/>
   		<setting name="aggressiveLazyLoading" value="false"/>
   		<setting name="cacheEnabled" value="true"/>
   	</settings>
       
       <typeAliases>
           <package name="com.hou.ssm.bean"/>
       </typeAliases>
   
       <!--分页插件-->
       <plugins>
           <plugin interceptor="com.github.pagehelper.PageInterceptor"/>
       </plugins>
   </configuration>
   ```

3. SpringMVC配置文件编写:

   [web.xml]()

   [diapatcher-servlet.xml]()

4. Spring配置文件编写: [applicationContext.xml]()

### **5.Spring整合MyBatis关键配置**

[applicationContext.xml]()

```xml
	...
	<!-- 
	整合mybatis 
		目的：1、spring管理所有组件。mapper的实现类。
				service==>Dao   @Autowired:自动注入mapper；
			2、spring用来管理事务，spring声明式事务
	-->
	<!--根据配置文件创建出SqlSessionFactory对象  -->
	<bean id="sqlSessionFactoryBean"
          class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"/>
		<!-- configLocation指定全局配置文件的位置 -->
		<property name="configLocation" value="classpath:mybatis-config.xml"/>
		<!--mapperLocations: 指定mapper文件的位置-->
		<property name="mapperLocations" value="classpath:mapper文件夹/*.xml"/>
	</bean>
	
	<!--配置一个可以进行批量执行的sqlSession  -->
	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg name="sqlSessionFactory" ref="sqlSessionFactoryBean"/>
		<constructor-arg name="executorType" value="BATCH"/>
	</bean>
	
	<!-- 扫描所有的mapper接口的实现，让这些mapper能够自动注入；
			basePackage：指定mapper接口的包名
	 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="com.hou.ssm.mapper"></property>
	</bean> 
<!--<mybatis-spring:scan base-package="com.hou.ssm.mapper"/>  -->
	
</beans>
```



# 员工管理系统CRUD

### 功能点

- 1、分页
- 2、数据校验 : jquery前端校验+JSR303后端校验
- 3、 ajax
- 4、 Rest风格的URI；使用HTTP协议请求方式的动词，来表示对资源的操作（GET（查询）， POST（新增）， PUT（修改）， DELETE（删除））  

### 技术点

- 基础框架-ssm（SpringMVC+Spring+MyBatis）
- 数据库-MySQL
- 前端框架-bootstrap快速搭建简洁美观的界面
- 项目的依赖管理-Maven
- 分页-pagehelper
- 逆向工程-MyBatis Generator  

### 基础环境搭建 

1. 创建一个maven工程

2. 引入项目依赖的jar包

   • spring

   • springmvc

   • mybatis

   • 数据库连接池，驱动包

   • 其他

3. 引入vue,axios,bootstrap前端框架

4. 编写ssm整合的关键配置文件

   web.xml，spring , springmvc, mybatis，使用mybatis的逆向工程生成对应的bean以

   及mapper

5. 测试mapper  

**页面效果:**![image-20220303162848298](C:\Users\14552\AppData\Roaming\Typora\typora-user-images\image-20220303162848298.png)

### 查询

1. 发送请求"/"查询员工列表

2. EmpController来接受请求，查出员工数据

3. 来到list.html页面进行展示

4. pageHelper分页插件完成分页查询功能  

   ```java
       @GetMapping("/emps/{pageNo}")
       public String getEmps(Model model, @PathVariable Integer pageNo) {
           //开启分页查询
           PageHelper.startPage(pageNo, 5);
           List<Emp> emps = empService.getAll();
           PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);
           model.addAttribute("pageInfo", pageInfo).addAttribute("emps", emps);
           return "list";
       }
   	@RequestMapping("/")
       public String index() {
           return "forward:/emps/1";
       }
   ```

   

### 用虚拟请求对控制器方法测试

```java
//指定spring和Springmvc的配置文件路径
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
                                    "classpath:dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration  //加此注解来注入springmvc的ioc容器
public class EmpControllerTest {
    //注入SpringMvc的ioc容器,来对MockMvc进行初始化
    @Autowired
    WebApplicationContext context;

    //虚拟mvc请求,获取到处理结果
    MockMvc mockMvc;

    @Before //每次测试前都进行初始化
    public void initMockMvc() {
        mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getEmps() throws Exception {
        //模拟发送 "/"get请求 ,参数为 pageNo=1,并拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/").param("pageNo", "1"))
                                    .andReturn();
        
        //获取请求域中的数据
        Object emps = result.getRequest().getAttribute("emps");
        Object page = result.getRequest().getAttribute("page");

        System.out.println(emps);
        System.out.println(page);
    }
}
```

### 查询-ajax

1. html页面通过vue+axios发送ajax请求进行员工分页数据的查询
2. 服务器将查出的数据，以json字符串的形式返回给浏览器
3. 浏览器收到js字符串。通过vue渲染页面
4. 返回json。实现客户端的无关性。  

后端:

```java
	@GetMapping("/emps/{pageNo}")
    @ResponseBody
    public Msg getEmpsByJson(@PathVariable Integer pageNo) {
        //开启分页查询
        PageHelper.startPage(pageNo, 5);
        List<Emp> emps = empService.getAll();
        PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);
        System.out.println(pageInfo);
        return Msg.success().add("pageInfo", pageInfo);
    }
```

自定Msg类

```java
public class Msg {
    //状态码(用于告诉前端增删改操作是否成功)  100-成功  200-失败
    private int code;
    //提示信息
    private String message;

    //传给前端的数据
    private Map<String, Object> extend = new HashMap<>();

    //将键值对数据添加到 调用者的 extend中,从而实现链式添加
    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    //返回一个操作成功的Msg对象
    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMessage("成功");
        return result;
    }
    //返回一个操作失败的Msg对象
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMessage("失败");
        return result;
    }
    ...
}
```

前端发送ajax请求:

```js
			var vue = new Vue({
                el: "#emp_div",
                data: {
                    code:{},
                    msg:{},
                    pageInfo:{}
                },
                methods: {
                    getEmps: function (pageNo) {
                        axios({
                            method: "GET",
                            url: "/ssm/emps/" +pageNo
                        }).then(function (response) {
                            vue.code=response.data.code;
                            vue.msg = response.data.message;
                            vue.pageInfo=response.data.extend.pageInfo;
                        }).catch(function (err) {

                        })
                    },
                },
                beforeMount: function () {
                    this.getEmps(1);
                }
            });
```

返回给前端的数据:

![image-20220304085432296](C:\Users\14552\AppData\Roaming\Typora\typora-user-images\image-20220304085432296.png)

一些小问题:

- vue中用@click.prevent绑定单击事件可以阻止默认事件(比如阻止超链接)

- 上一页/下一页,绑定单击事件时可以通过三元运算符设置传入的pageNo

  ```html
  <a href="" @click.prevent="getEmps(pageInfo.pageNum==1?1:pageInfo.pageNum-1)"
                             aria-label="Previous">
                              <span aria-hidden="true">上一页</span>
                          </a>
  ```

### 新增

![image-20220303223216819](C:\Users\14552\AppData\Roaming\Typora\typora-user-images\image-20220303223216819.png)

1. 在index.html页面点击”新增”

2. 弹出新增对话框

3. 去数据库查询部门列表，显示在对话框中

4. 用户输入数据，并进行校验

    - jquery前端校验， ajax用户名重复校验

    - 重要数据（后端校验(JSR303)，唯一约束)；

      1. 引入Hibernate-Validator依赖

         ```xml
         <dependency>
             <groupId>org.hibernate.validator</groupId>
             <artifactId>hibernate-validator</artifactId>
             <version>6.2.0.Final</version>
         </dependency>
         ```

      2. 在Emp实体类需要校验的属性上加@Pattern注解,通过regexp属性指定正则表达式

         ```java
         @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})",
                     message = "用户名必须是2-5位中文或者6-16位英文和数字的组合")
             private String empName;
         
             //@Email 也可以直接加Email注解表示该属性要满足邮箱的格式
             @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
                      message = "邮箱格式不正确")
             private String email;
         ```

      3. 在控制器方法的Emp参数上加 @Valid 注解开启校验,并用BindingResult接收校验结果

    

5. 完成保存

   • URI:

   • /emp/{id} GET 查询员工

   • /emp POST 保存员工

   • /emp PUT 修改员工

   • /emp/{id} DELETE 删除员工  

### 修改

![image-20220303223158701](C:\Users\14552\AppData\Roaming\Typora\typora-user-images\image-20220303223158701.png)

1. 点击编辑
2. 弹出用户修改的模态框（显示用户信息）
3. 点击更新，发送PUT请求,完成用户修改  URI:/emp  PUT

注意点:

- 通过axios发送json格式时,可以通过 $("表单id").serialize()将表单数据转换为json字符串
- 要发送PUT请求,记得在表单中加隐藏域 name="_method" value="put",并且ajax发送post请求

```js
axios({
	method: "POST",
	url: "/ssm/emp",
	data: $("#editEmp_form").serialize()
}).then(...)
```

- 清空表单:

  ```js
  //jQuery没有reset方法,所以先转化为dom对象
  $("#editEmp_form")[0].reset();
  ```

- 设置单选框/下拉项是否选择

  ```xml
  <input type="radio" name="gender" id="gender2_update_input" value="1"
         :checked="employee.gender==1"> 男
  ```

  ```html
  <option v-for="dept in dept_msg.extend.depts" :value="dept.deptId"
          :selected="employee.dId==dept.deptId">{{dept.deptName}}
  </option>
  ```

- 

### 删除

![image-20220303223304209](C:\Users\14552\AppData\Roaming\Typora\typora-user-images\image-20220303223304209.png)

1. 单个删除

   ​	 URI:/emp/{id} DELETE

2. 批量删除  

## 总结

![image-20220303223424912](C:\Users\14552\AppData\Roaming\Typora\typora-user-images\image-20220303223424912.png)
