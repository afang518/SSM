package com.hou.test;

import com.hou.ssm.bean.Dept;
import com.hou.ssm.bean.DeptExample;
import com.hou.ssm.mapper.DeptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author shadow
 * @Description
 * @create 2022-03-03 13:10
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DeptMapperTest {

    @Autowired
    DeptMapper deptMapper;
    @Test
    public void countByExample() {
//        System.out.println(deptMapper);
        DeptExample example = new DeptExample();
        example.createCriteria().andDeptIdBetween(1, 2);
        System.out.println(deptMapper.countByExample(example));
    }

    @Test
    public void deleteByExample() {

    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }


    @Test
    public void selectByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}