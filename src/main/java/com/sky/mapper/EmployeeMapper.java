package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.management.ValueExp;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    @Update("UPDATE employee SET status = #{status} WHERE id = #{id}")
    void updateStatus(Integer status, Long id);

    @Insert("INSERT INTO employee (name, username, phone, password, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "VALUES (#{name}, #{username}, #{phone}, #{password}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void addEmp(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee getById(Integer id);

    @AutoFill(value = OperationType.UPDATE)
    @Update("update employee set name = #{name}, username = #{username}, phone = #{phone}, sex = #{sex}, id_number = #{idNumber}, update_time = #{updateTime}, update_user = #{updateUser} where id = #{id}")
    void updateEmp(Employee employee);
}
