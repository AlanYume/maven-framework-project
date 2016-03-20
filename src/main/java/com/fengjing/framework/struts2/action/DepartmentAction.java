package com.fengjing.framework.struts2.action;

import com.fengjing.framework.struts2.model.Department;
import com.fengjing.framework.struts2.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller(value = "departmentAction")
@SuppressWarnings("serial")
public class DepartmentAction extends ActionSupport {

    @Resource(name = "departmentServiceImpl")
    private DepartmentService departmentService;

    public String listAll() throws Exception {
        final List<Department> lists = departmentService.listAll();
        for (final Department department : lists) {
            System.out.println(department.getDeptid() + "," + department.getDeptname());
        }
        return SUCCESS;
    }
}
