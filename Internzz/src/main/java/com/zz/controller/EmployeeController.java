package com.zz.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zz.entity.Employee;
import com.zz.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;
	
    //升职
    @RequestMapping(value = "/promotedAll")
    @ResponseBody
    public Map<String,Object> UpdateLevel(Model model){   	
    	Map<String, Object> map = new HashMap<String, Object>(); 	
    	List<Employee> promotedList = employeeService.findPromotedByDepartment();  	
    	if(!promotedList.isEmpty()) {		
        	System.out.println("xxxxxxx"+promotedList);   	
        	employeeService.updateLevel(promotedList);
        	System.out.println("ooooooooooooooooo"+promotedList.get(0).getName());
        	map.put("promotedList", promotedList);
    	}else {	//如果没有人升职，则返回其他的参数
    		System.out.println("没有人要晋升！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
    		map.put("promotedList", "null");
    	}
    	return map;  
    }

    //根据部门编号，显示成员（前端通过JSON数据显示）
    @RequestMapping(value = "/all")
    @ResponseBody
    public Map<String,Object> getAllEmployee(@RequestBody String depID, Model model){   	
    	Map<String, Object> map = new HashMap<String, Object>();   	
    	List<Employee> employeeList = employeeService.findByDepartment(depID);  	 	
    	map.put("部门名称", employeeList.get(0).getDepartment().getName());
    	map.put("部门编号", employeeList.get(0).getDepartment().getId());
    	map.put("员工", employeeList);
    	return map;  
    }
	
    //通过ajax获取输入的部门编号，并跳转指定路径
    @RequestMapping(value = "/allEmpOnEl")
    public @ResponseBody
    String getAllEmpOnEl(@RequestBody String a, Model model){   	
    	System.out.println("您要查找的部门："+a);
        return "id/"+a;       	
    	}
    
    //查询指定部门编号的所有雇员（前台用EL表达式显示数据）
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public String getAllEmployeeByDepId(@PathVariable String id, Model model){   	
    	model.addAttribute("listEmployee", employeeService.findByDepartment(id));
    	System.out.println("1id"+id);
        return "allEmployee";       	
    	}
    
    //通过ajax获取输入的部门编号，并跳转指定路径
    @RequestMapping(value = "/byLikeName",produces = {"text/html;charset=utf-8"})
    public @ResponseBody
    String getEmpByLikeName(@RequestBody String name, Model model) throws UnsupportedEncodingException{   	
    	//name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
    	System.out.println("关键字："+name);
        return "name/"+name;       	
    	}
    
    //查询指定部门编号的所有雇员（前台用EL表达式显示数据）
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String getEmployeeByLikeName(@PathVariable String name, Model model){   	
    	model.addAttribute("listEmployee", employeeService.findByLikeName(name));
    	System.out.println("要查找的名字："+name);
        return "allEmployee";       	
    	}
    
    

    
    

}
