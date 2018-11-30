package com.pwx;

import com.pwx.entity.Department;
import com.pwx.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 * Created by Thinker Peng on 2018/11/28.
 */
public class DataUtils
{
    public static Department findDepartment(Session session, String deptNo)
    {
        String sql = "select d from " + Department.class.getName() + " d where d.deptNo = :deptNo";
        Query<Department> query = session.createQuery(sql);
        query.setParameter("deptNo", deptNo);
        return query.getSingleResult();
    }

    public static Long getMaxEmpId(Session session)
    {
        String sql = "select max(e.empId) from " + Employee.class.getName() + " e ";
        Query<Number> query = session.createQuery(sql);
        Number value = query.getSingleResult();
        if (value == null)
        {
            return 0L;
        }
        return value.longValue();
    }

    public static Employee findEmployee(Session session, String empNo)
    {
        String sql = "select e from " + Employee.class.getName() + " e where e.empNo = :empNo";
        Query<Employee> query = session.createQuery(sql);
        query.setParameter("empNo", empNo);
        return query.getSingleResult();
    }

}
