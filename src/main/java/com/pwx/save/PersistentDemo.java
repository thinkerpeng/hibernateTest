package com.pwx.save;

import com.pwx.DataUtils;
import com.pwx.HibernateUtils;
import com.pwx.entity.Department;
import org.hibernate.Session;

/**
 * Created by Thinker Peng on 2018/11/30.
 */
public class PersistentDemo
{
    public static void main(String[] args)
    {
        Session session = HibernateUtils.getSession();

        Department department = null;

        try
        {
            session.getTransaction().begin();

            System.out.println("- Finding Department deptNo = D10 ...");

            // Persistent object
            department = DataUtils.findDepartment(session, "D10");

            System.out.println("- Finding change Location");

            // Changing something on Persistent object
            department.setLocation("Chicago " + System.currentTimeMillis());

            System.out.println("- Location = " + department.getLocation());

            System.out.println("- Calling flush...");

            session.flush();

            System.out.println("- Flush OK");

            System.out.println("- Second change Location");

            // change something on persistent object
            department.setLocation("Chicago " + System.currentTimeMillis());

            System.out.println("- Location = " + department.getLocation());

            System.out.println("- Calling commit ...");

            session.getTransaction().commit();

            System.out.println("- Commit OK");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        // create the session after it had been closed earlier
        try
        {
            session.getTransaction().begin();

            System.out.println("- Finding Departemnt deptNo = D10");

            department = DataUtils.findDepartment(session, "D10");

            System.out.println("- D10 Location = " + department.getLocation());

            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
