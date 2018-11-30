package com.pwx.save;

import com.pwx.DataUtils;
import com.pwx.HibernateUtils;
import com.pwx.entity.Department;
import com.pwx.entity.Employee;
import org.hibernate.Session;

import java.util.Date;

/**
 * Created by Thinker Peng on 2018/11/28.
 */
public class PersistDemo
{
    public static void main(String[] args)
    {
        Session session = HibernateUtils.getSession();

        Employee emp = null;
        Department department = null;

        try
        {
            session.getTransaction().begin();

            Long maxEmpId = DataUtils.getMaxEmpId(session);
            Long empId = maxEmpId + 1;

            department = DataUtils.findDepartment(session, "D10");

            emp = new Employee(empId, "Test", "coder", null, new Date(),  10000f, department);
//            emp.setEmpId(empId);
//            emp.setEmpNo("E" + empId);
//            emp.setEmpName("Name " + empId);
//            emp.setJob("Coder");
//            emp.setSalary(1000f);
//            emp.setManager(null);
//            emp.setImage(null);
//            emp.setHideDate(new Date());
//            emp.setDepartment(department);

            // Using persist(..)
            // Now 'emp' is managed by Hibernate.
            // it has Persistent status.
            // No action at this time with DB.
            session.persist(emp);

            // At this step the data is pushed to the DB.
            // Execute Insert statement.
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Emp No: " + emp.getEmpNo());
    }
}
