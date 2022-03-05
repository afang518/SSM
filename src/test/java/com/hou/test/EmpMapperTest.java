package com.hou.test;

import com.hou.ssm.bean.Emp;
import com.hou.ssm.bean.EmpExample;
import com.hou.ssm.mapper.EmpMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author shadow
 * @Description
 * @create 2022-03-03 13:10
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmpMapperTest {

    @Autowired
    EmpMapper empMapper;

    @Autowired
    SqlSession sqlSession;

//    @Test
//    public void countByExample() {
//        System.out.println(empMapper);
//    }
//
//    @Test
//    public void deleteByExample() {
//        empMapper.deleteEmpsIn("39,37");
//    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
//        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
//        for (int i = 0; i < 100; i++) {
//            String uuid = UUID.randomUUID().toString().substring(0, 5)+i;
//            mapper.insertSelective(new Emp(null, uuid,  1, uuid+"@qq.com", 1));
//        }
//        System.out.println("批量完成");
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectByExampleWithDept() {
//        EmpExample example = new EmpExample();
//        example.createCriteria().andEmpIdBetween(1, 5);
//        example.or().andEmpIdEqualTo(10);
//        System.out.println(empMapper.selectByExampleWithDept(example));
    }

//    @Test
//    public void selectByPrimaryKeyWithDept() {
//        System.out.println(empMapper.selectByPrimaryKeyWithDept(1));
//    }

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