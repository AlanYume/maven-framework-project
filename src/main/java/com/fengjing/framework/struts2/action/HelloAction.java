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

            employee.setEmpname("����");
            employee.setSex("Ů");
            employee.setLocation("��������");

            final Department department = new Department();
            department.setDeptname("���Բ�");

            employee.setDepartment(department);

            employeeService.add(employee);

            writer.write("<script type='text/javascript'>alert('����ɹ�!');</script>");
        } catch (final Exception e) {
            writer.write("<script type='text/javascript'>alert('����ʧ��!\t" + e.getMessage() +
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

            writer.write("<script type='text/javascript'>alert('ɾ���ɹ�!');</script>");
        } catch (final Exception e) {
            writer.write("<script type='text/javascript'>alert('ɾ��ʧ��!\t" + e.getMessage() +
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

            employee.setEmpname("������");
            employee.setSex("��");
            employee.setLocation("�й�����");

            employee.getDepartment().setDeptname("����Ժ");

            employeeService.update(employee);
            writer.write("<script type='text/javascript'>alert('�޸ĳɹ�!');</script>");
        } catch (final Exception e) {
            writer.write("<script type='text/javascript'>alert('�޸�ʧ��!\t" + e.getMessage() +
                    "');</script>");
        }

        writer.flush();
        writer.close();
        return null;

    }


}
