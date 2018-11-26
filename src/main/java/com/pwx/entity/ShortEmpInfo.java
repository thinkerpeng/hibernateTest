package com.pwx.entity;

/**
 * Created by Thinker Peng on 2018/11/26.
 */
public class ShortEmpInfo
{
    private Long empId;
    private String empNo;
    private String empName;

    public ShortEmpInfo()
    {
    }

    public ShortEmpInfo(Long empId, String empNo, String empName)
    {
        this.empId = empId;
        this.empNo = empNo;
        this.empName = empName;
    }

    public Long getEmpId()
    {
        return empId;
    }

    public void setEmpId(Long empId)
    {
        this.empId = empId;
    }

    public String getEmpNo()
    {
        return empNo;
    }

    public void setEmpNo(String empNo)
    {
        this.empNo = empNo;
    }

    public String getEmpName()
    {
        return empName;
    }

    public void setEmpName(String empName)
    {
        this.empName = empName;
    }
}
