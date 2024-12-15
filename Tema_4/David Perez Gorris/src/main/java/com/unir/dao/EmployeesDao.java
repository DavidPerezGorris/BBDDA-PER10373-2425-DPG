package com.unir.dao;
import com.unir.model.mysql.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
@Slf4j
public class EmployeesDao {
private final Session session;
     /**
     * Consulta de todos los empleados de la base de datos
     * Se ofrecen dos versiones
     * 1. Con SQL nativo
     * 2. Con HQL: https://docs.jboss.org/hibernate/orm/3.5/reference/es-ES/html/queryhql.html
     * @throws SQLException Excepción en caso de error
     */
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = session.createNativeQuery("SELECT * FROM employees", Employee.class).list();
        log.debug("Número de empleados: {}", employees.size());
        session.createQuery("FROM Employee", Employee.class).list();
        return employees;
    }
     /**
     * Consulta de todos los empleados de un departamento
     * @param departmentId Identificador del departamento
     * @return Lista de empleados
     * @throws SQLException Excepción en caso de error
     */
    public List<Employee> findByDepartment(String departmentId) throws SQLException {
        Query<Employee> query = session.createNativeQuery("SELECT e.*\n" +
                "FROM employees.employees e\n" +
                "JOIN employees.dept_emp de ON e.emp_no = de.emp_no\n" +
                "JOIN employees.departments d ON de.dept_no = d.dept_no\n" +
                "WHERE d.dept_no = :deptNo", Employee.class);
        query.setParameter("deptNo", departmentId);
        return query.list();
    }
     /**
     * Obtención de un empleado por su identificador.
     * @param id - Identificador del empleado.
     * @return Empleado.
     * @throws SQLException - Excepción en caso de error.
     */
    public Employee getById(Integer id) throws SQLException {
        return session.get(Employee.class, id);
    }
     /**
     * Elimina un empleado de la base de datos.
     * @param employee - Empleado a eliminar.
     * @return true si se ha eliminado correctamente.
     * @throws SQLException - Excepción en caso de error.
     */
    public Boolean remove(Employee employee) throws SQLException {
        session.remove(employee);
        return true;
    }
     /**
     * Inserta un nuevo empleado en la base de datos.
     * @param employee - Empleado a insertar.
     * @return Empleado insertado.
     * @throws SQLException - Excepción en caso de error.
     */
    public Employee save(Employee employee) throws SQLException {
        session.persist(employee);
        return employee;
    }

    /**
     * Obtener el número de hombres y mujeres de la base de datos, ordenado de forma descendente.
     * @return Lista de objetos con género y conteo.
     * @throws SQLException ExcepciÃ³n en caso de error.
     */
    public List<Object[]> getGenderCount() throws SQLException {
        List<Object[]> results = session.createNativeQuery(
            "SELECT gender AS 'Gender', COUNT(*) AS 'GenderCount' " +
            "FROM employees.employees " +
            "GROUP BY gender " +
            "ORDER BY gender DESC"
        ).list();
        log.debug("Número de géneros en la base de datos: {}", results.size());
        return results;
    }
     /**
     * Mostrar el número de empleados contratados en un mes concreto.
     * @param month Mes de contrataciÃ³n (1-12).
     * @return Lista con el mes y el número de empleados contratados.
     * @throws SQLException Excepción en caso de error.
     */
    public List<Object[]> getEmployeesHiredInMonth(int month) throws SQLException {
        List<Object[]> results = session.createNativeQuery(
            "SELECT MONTH(e.hire_date) AS 'MesContratacion', COUNT(*) AS 'NumeroEmpleadosContratados' " +
            "FROM employees.employees e " +
            "WHERE MONTH(e.hire_date) = :month " +
            "GROUP BY MesContratacion"
        ).setParameter("month", month).list();
        log.debug("Número de empleados contratados en el mes {}: {}", month, results.size());
        return results;
    }
}
