package com.unir.dao;
import com.unir.model.mysql.Department;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
@Slf4j
public class DepartmentsDao {
private final Session session;
     /**
     * Consulta de todos los departamentos en la base de datos.
     * Se ofrece una consulta con SQL nativo.
     * @throws SQLException Excepción en caso de error.
     */
    public List<Department> findAll() throws SQLException {
        List<Department> departments = session.createNativeQuery("SELECT * FROM departments", Department.class).list();
        log.debug("Número de departamentos: {}", departments.size());
        return departments;
    }
     /**
     * Obtención de un departamento por su identificador.
     * @param id - Identificador del departamento.
     * @return Departamento.
     * @throws SQLException - Excepción en caso de error.
     */
    public Department getById(String id) throws SQLException {
        return session.get(Department.class, id);
    }
     /**
     * Elimina un departamento de la base de datos.
     * @param department - Departamento a eliminar.
     * @return true si se ha eliminado correctamente.
     * @throws SQLException - Excepción en caso de error.
     */
    public Boolean remove(Department department) throws SQLException {
        session.remove(department);
        return true;
    }
     /**
     * Inserta un nuevo departamento en la base de datos.
     * @param department - Departamento a insertar.
     * @return Departamento insertado.
     * @throws SQLException - Excepción en caso de error.
     */
    public Department save(Department department) throws SQLException {
        session.persist(department);
        return department;
    }

     /**
     * Mostrar el nombre, apellido y salario de la persona mejor pagada de un departamento.
     * @param departmentName Nombre del departamento.
     * @return Lista con nombre, apellido y salario de la persona mejor pagada.
     * @throws SQLException Excepción en caso de error.
     */
    public Object[] getHighestPaidEmployeeInDepartment(String departmentName) throws SQLException {
        Object[] result = (Object[]) session.createNativeQuery(
            "SELECT e.first_name, e.last_name, s.salary " +
            "FROM employees.employees e " +
            "INNER JOIN employees.dept_emp de ON e.emp_no = de.emp_no " +
            "INNER JOIN employees.departments d ON de.dept_no = d.dept_no " +
            "INNER JOIN employees.salaries s ON e.emp_no = s.emp_no " +
            "WHERE d.dept_name = :deptName " +
            "ORDER BY s.salary DESC " +
            "LIMIT 1"
        ).setParameter("deptName", departmentName).uniqueResult();
        log.debug("Empleado mejor pagado en el departamento {}: {}", departmentName, result);
        return result;
    }
     /**
     * Mostrar el nombre, apellido y salario de la segunda persona mejor pagada de un departamento.
     * @param departmentName Nombre del departamento.
     * @return Lista con nombre, apellido y salario de la segunda persona mejor pagada.
     * @throws SQLException Excepción en caso de error.
     */
    public Object[] getSecondHighestPaidEmployeeInDepartment(String departmentName) throws SQLException {
        Object[] result = (Object[]) session.createNativeQuery(
            "SELECT e.first_name, e.last_name, s.salary " +
            "FROM employees.employees e " +
            "INNER JOIN employees.dept_emp de ON e.emp_no = de.emp_no " +
            "INNER JOIN employees.departments d ON de.dept_no = d.dept_no " +
            "INNER JOIN employees.salaries s ON e.emp_no = s.emp_no " +
            "WHERE d.dept_name = :deptName " +
            "ORDER BY s.salary DESC " +
            "LIMIT 1 OFFSET 1"
        ).setParameter("deptName", departmentName).uniqueResult();
        log.debug("Segunda persona mejor pagada en el departamento {}: {}", departmentName, result);
        return result;
    }
}
