package com.pwx.query;

import com.pwx.HibernateUtils;
import com.pwx.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


/**
 * Created by Thinker Peng on 2018/11/25.
 */
public class QueryObjectDemo
{
    public static void main(String[] args)
    {
        Session session = HibernateUtils.getSession();

        try
        {
            // All the action with DB via Hiberante
            // must be located in one transaction.
            // Start Transaction.
            session.getTransaction().begin();

            // create an HQL statement, query the object.
            // Equivalent to the SQL statement: Select e.* EMPLOYEE e order by e.EMP_NAME, E.EMP_NO
            String sql = "Select e from " + Employee.class.getName() + " e order by e.empName, e.empNo ";

            // Create Query object.
            Query<Employee> query = session.createQuery(sql);

            // Execute query.
            List<Employee> employees = query.getResultList();

            for (Employee e : employees)
            {
                System.out.println("Emp: " + e.getEmpNo() + " : " + e.getEmpName());
            }

            // Commit data;
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        }
    }
}
