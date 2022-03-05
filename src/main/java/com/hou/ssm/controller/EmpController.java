package com.hou.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hou.ssm.bean.Emp;
import com.hou.ssm.bean.Msg;
import com.hou.ssm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author shadow
 * @Description
 * @create 2022-03-03 15:39
 */
@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    /*
     * 查询员工列表(分页查询)
     * @param
     * @return {@link java.lang.String}
     **/
//    @GetMapping("/emps/{pageNo}")
    public String getEmps(//@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                          Model model, @PathVariable Integer pageNo) {

        //开启分页查询
        PageHelper.startPage(pageNo, 5);
        List<Emp> emps = empService.getAll();
        PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);
        model.addAttribute("pageInfo", pageInfo).addAttribute("emps", emps);
        return "list";
    }

    /*
     * 通过ajax+json查询员工列表
     * @param pageNo
     * @return {@link com.github.pagehelper.PageInfo<com.hou.ssm.bean.Emp>}
     **/
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

    @ResponseBody
    @DeleteMapping("/emps/{ids}")
    public Msg deleteEmps(@PathVariable String ids) {
        empService.deleteEmps(ids);
        return Msg.success();
    }

    @DeleteMapping("/emp/{empId}")
    @ResponseBody
    public Msg deleteEmpByUd(@PathVariable Integer empId) {
        empService.deleteEmpById(empId);
        return Msg.success();
    }

    @PutMapping("/emp")
    @ResponseBody
    public Msg editEmp(@Valid Emp emp,BindingResult result){
        if (result.hasErrors()) {
            String error = result.getFieldErrors().get(0).getDefaultMessage();
            return Msg.fail().add("error",error);
        }
        empService.editEmp(emp);
        return Msg.success();
    }

    @PostMapping("/emp")
    @ResponseBody
    //加@Valid开启校验,并用BingdingResult接收校验结果
    public Msg addEmp(@Valid Emp emp, BindingResult result) {
        //如果校验失败,返回相应的信息
        if (result.hasErrors()) {
            //获取所有错误的字段,然后取第一个,将其错误信息添加到Msg中
            String error = result.getFieldErrors().get(0).getDefaultMessage();
            return Msg.fail().add("error",error);
        }
        empService.saveEmp(emp);
        return Msg.success();
    }

    @GetMapping("/emp/check/{empName}")
    @ResponseBody
    public Msg checkName(@PathVariable String empName) {
        String name = null;
        try {
            //解决中文乱码问题
            name = URLDecoder.decode(empName, "utf-8");
            //在后端也校验name是否满足要求
            String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
            if(!name.matches(regex)){
                Msg msg = new Msg();
                msg.setCode(300);
                msg.setMessage("用户名格式不正确");
                return msg;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        boolean b = empService.checkName(name);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @GetMapping("/emp/{empId}")
    @ResponseBody
    public Msg getEmpById(@PathVariable Integer empId) {
        Emp emp = empService.getEmpById(empId);
        return Msg.success().add("emp",emp);
    }


    @RequestMapping("/")
    public String index() {
        return "index";
    }



}
