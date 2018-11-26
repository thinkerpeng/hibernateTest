package com.pwx;

import com.pwx.entity.Employee;
import com.pwx.entity.ShortEmpInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Thinker Peng on 2018/11/26.
 */
public class QueryShortEmpInfoDemo
{
    public static void main(String[] args)
    {
        Session session = HibernateUtils.getSession();

        try
        {
            session.getTransaction().begin();
            String sql = "select new " + ShortEmpInfo.class.getName() + "(e.empId, e.empNo, e.empName) from " +
                    Employee.class.getName() + " e ";

            Query<ShortEmpInfo> query = session.createQuery(sql);

            List<ShortEmpInfo> empInfos = query.getResultList();

            empInfos.forEach(empInfo ->
            {
                System.out.println("Emp: " + empInfo.getEmpNo() + " name: " + empInfo.getEmpName());
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
