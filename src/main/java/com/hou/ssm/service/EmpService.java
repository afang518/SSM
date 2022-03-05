package com.hou.ssm.service;

import com.github.pagehelper.PageHelper;
import com.hou.ssm.bean.Emp;
import com.hou.ssm.bean.EmpExample;
import com.hou.ssm.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author shadow
 * @Description
 * @create 2022-03-03 15:43
 */
@Service
public class EmpService {
    @Autowired
    private EmpMapper mapper;

    public List<Emp> getAll(){

        return mapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Emp emp) {
        mapper.insertSelective(emp);
    }

    public boolean checkName(String empName) {


        EmpExample example = new EmpExample();
        example.createCriteria().andEmpNameEqualTo(empName);
        int count = mapper.countByExample(example);
        return count == 0;
    }

    public Emp getEmpById(Integer empId) {

        return mapper.selectByPrimaryKeyWithDept(empId);
    }

    public void editEmp(Emp emp) {
        mapper.updateByPrimaryKeySelective(emp);
    }

    public void deleteEmpById(Integer empId) {
        mapper.deleteByPrimaryKey(empId);
    }

    public void deleteEmps(String ids) {
        mapper.deleteEmpsIn(ids);
    }
}
