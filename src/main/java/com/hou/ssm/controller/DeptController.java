package com.hou.ssm.controller;

import com.hou.ssm.bean.Dept;
import com.hou.ssm.bean.Msg;
import com.hou.ssm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shadow
 * @Description
 * @create 2022-03-04 9:56
 */
@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    @ResponseBody
    public Msg getDepts() {
        List<Dept> depts = deptService.getDepts();
        return Msg.success().add("depts", depts);
    }
}
