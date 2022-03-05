package com.hou.ssm.service;

/**
 * @author shadow
 * @Description
 * @create 2022-03-04 9:56
 */

import com.hou.ssm.bean.Dept;
import com.hou.ssm.bean.Msg;
import com.hou.ssm.mapper.DeptMapper;
import com.hou.ssm.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> getDepts() {
       return deptMapper.selectByExample(null);
    }
}
