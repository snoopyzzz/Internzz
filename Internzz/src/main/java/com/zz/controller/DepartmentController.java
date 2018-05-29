package com.zz.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zz.entity.Department;
import com.zz.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Resource
	DepartmentService departmentService;
	
    @RequestMapping(value = "/alldep", method = RequestMethod.GET)
    public String getAllField( Model model){   	
    	model.addAttribute("listDepartment", departmentService.findAll());
        return "allDepartment";       	
    	}
    
    @RequestMapping(value = "/checkDep")
    @ResponseBody
    public String checkDepById(@RequestBody String depID, Model model){   	

    	Department department = departmentService.findDepartmentById(depID);
    	if(department != null) {
    		return "Y";  	//存在返回“N
    	}else {
    		return "N";		//不在这返回”Y
    	}


     
    }
}
