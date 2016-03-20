package com.fengjing.framework.struts2.action;

import com.fengjing.framework.hibernate.model.Department;
import com.fengjing.framework.hibernate.model.Employee;
import com.fengjing.framework.hibernate.service.DepartmentService;
import com.fengjing.framework.hibernate.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@SuppressWarnings("serial")
@Controller("helloAction")
public class HelloAction extends ActionSupport {


    private EmployeeService employeeService;

    @Resource(name = "employeeServiceImplHibernate4")
    public void setEmployeeService(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private DepartmentService departmentService;

    @Resource(name = "departmentServiceImplHibernate4")
    public void setDepartmentService(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    public String hello() throws Exception {

        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");

        final PrintWriter writer = response.getWriter();
        writer.write("<script type='text/javascript'>alert('Hello,Maven!');</script>");
        writer.flush();
        writer.close();
        return null;
    }


    public String save() throws Exception {

        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter writer = response.getWriter();

        try {

            final Employee employee = new Employee();

            employee.setEmpname("李四");
            employee.setSex("女");
            employee.setLocation("陕西汉中");

            final Department department = new Department();
            department.setDeptname("测试部");

            employee.setDepartment(department);

            employeeService.add(employee);

            writer.write("<script type='text/javascript'>alert('保存成功!');</script>");
        } catch (final Exception e) {
            writer.write("<script type='text/javascript'>alert('保存失败!\t" + e.getMessage() +
                    "');</script>");
        }

        writer.flush();
        writer.close();
        return null;

    }


    public String delete() throws Exception {

        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter writer = response.getWriter();

        try {

            departmentService.delete(1);

            writer.write("<script type='text/javascript'>alert('删除成功!');</script>");
        } catch (final Exception e) {
            writer.write("<script type='text/javascript'>alert('删除失败!\t" + e.getMessage() +
                    "');</script>");
        }

        writer.flush();
        writer.close();
        return null;

    }


    public String print() throws Exception {

        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter writer = response.getWriter();
        writer.write("<a href='http://mvnrepository.com'>http://mvnrepository.com</a>");
        writer.flush();
        writer.close();
        return null;

    }


    public String modify() throws Exception {

        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter writer = response.getWriter();


        try {
            final List<Employee> list = employeeService.query(
                    " from com.fengjing.framework.hibernate.model.Employee employee where employee.id = 2 ");
            final Employee employee = list.get(0);

            employee.setEmpname("胡锦涛");
            employee.setSex("男");
            employee.setLocation("中国北京");

            employee.getDepartment().setDeptname("国务院");

            employeeService.update(employee);
            writer.write("<script type='text/javascript'>alert('修改成功!');</script>");
        } catch (final Exception e) {
            writer.write("<script type='text/javascript'>alert('修改失败!\t" + e.getMessage() +
                    "');</script>");
        }

        writer.flush();
        writer.close();
        return null;

    }


}
