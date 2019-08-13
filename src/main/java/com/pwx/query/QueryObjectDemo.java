package com.pwx.query;

import com.pwx.HibernateUtils;
import com.pwx.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by Thinker Peng on 2018/11/25.
 */
public class QueryObjectDemo
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryObjectDemo.class);

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
            String sql = "from Employee e order by e.empName, e.empNo ";

            // Create Query object.
            Query<Employee> query = session.createQuery(sql);

            // Execute query.
            List<Employee> employees = query.getResultList();
            employees.forEach(employee -> LOGGER.info("Emp: {}, name: {}", employee.getEmpNo(), employee.getEmpName()));

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
