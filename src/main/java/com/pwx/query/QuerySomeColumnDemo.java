package com.pwx.query;

import com.pwx.HibernateUtils;
import com.pwx.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Thinker Peng on 2018/11/26.
 */
public class QuerySomeColumnDemo
{
    public static void main(String[] args)
    {
        Session session = HibernateUtils.getSession();

        try
        {
            session.getTransaction().begin();

            String sql = "select e.empId, e.empNo, e.empName from " + Employee.class.getName() + " e ";

            Query<Object[]> query = session.createQuery(sql);
            List<Object[]> emps = query.getResultList();

            emps.forEach(emp ->
            {
                System.out.println("Emp Id: " + emp[0]);
                System.out.println("Emp No: " + emp[1]);
                System.out.println("Emp Name: " + emp[2]);
            });

            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
