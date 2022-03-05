package com.hou.test;

import com.github.pagehelper.PageInfo;
import com.hou.ssm.bean.Emp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/** 通过虚拟mvc请求来测试控制器方法
 * @author shadow
 * @Description
 * @create 2022-03-03 16:00
 */
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
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getEmps() throws Exception {
//        //模拟发送 "/"get请求 ,参数为 pageNo=1,并拿到返回值
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps/1"))
//                                    .andReturn();
//
//        //获取请求域中的数据
//        Object emps = result.getRequest().getAttribute("emps");
//        Object page = result.getRequest().getAttribute("page");
//        PageInfo<Emp> pageInfo= (PageInfo<Emp>) page;
//        int[] nums = pageInfo.getNavigatepageNums();
//        System.out.println();
//
//        System.out.println(emps);
//        System.out.println(page);
    }
}