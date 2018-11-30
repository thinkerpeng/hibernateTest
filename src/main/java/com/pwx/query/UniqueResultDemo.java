package com.pwx.query;

import com.pwx.HibernateUtils;
import com.pwx.entity.Department;
import com.pwx.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Set;

/**
 * Created by Thinker Peng on 2018/11/28.
 */
public class UniqueResultDemo
{
    public static Department getDepartment(Session session, String deptNo)
    {
        String sql = "select d from " + Department.class.getName() + " d where d.deptNo= :deptNo";
        Query<Department> query = session.createQuery(sql);
        query.setParameter("deptNo", deptNo);
        return (Department) query.getSingleResult();
    }

    public static Employee getEmployee(Session session, Long empId)
    {
        String sql = "select e from " + Employee.class.getName() + " e where e.empId = :empId";
        Query<Employee> query = session.createQuery(sql);
        query.setParameter("empId", empId);
        return (Employee)query.getSingleResult();
    }

    public static void main(String[] args)
    {
        Session session = HibernateUtils.getSession();

        try
        {
            session.getTransaction().begin();

            Department dept = getDepartment(session, "D10");
            Set<Employee> employees = dept.getEmployees();

            System.out.println("Dept Name: " + dept.getDeptName());
            employees.forEach(employee -> System.out.println(employee.getEmpName()));

            Employee emp = getEmployee(session, 7839L);
            System.out.println("Emp Name: " + emp.getEmpName());

            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
