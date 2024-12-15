package com.unir.dao;
import com.unir.model.mysql.DeptEmp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
@Slf4j
public class DeptEmployeesDao {
private final Session session;
     /**
     * Consulta de todos los registros de dept_emp.
     * @throws SQLException Excepción en caso de error.
     */
    public List<DeptEmp> findAll() throws SQLException {
        List<DeptEmp> deptEmployees = session.createNativeQuery("SELECT * FROM dept_emp", DeptEmp.class).list();
        log.debug("Número de registros de dept_emp: {}", deptEmployees.size());
        return deptEmployees;
    }
     /**
     * Consulta de todos los registros de dept_emp para un empleado específico.
     * @param empNo Identificador del empleado.
     * @return Lista de registros de dept_emp.
     * @throws SQLException Excepción en caso de error.
     */
    public List<DeptEmp> findByEmployeeId(Integer empNo) throws SQLException {
        Query<DeptEmp> query = session.createQuery("FROM DeptEmp WHERE employee.empNo = :empNo", DeptEmp.class);
        query.setParameter("empNo", empNo);
        return query.list();
    }
     /**
     * Inserta un nuevo registro en dept_emp.
     * @param deptEmployee - Registro a insertar.
     * @return Registro insertado.
     * @throws SQLException - Excepción en caso de error.
     */
    public DeptEmp save(DeptEmp deptEmployee) throws SQLException {
        session.persist(deptEmployee);
        return deptEmployee;
    }
     /**
     * Elimina un registro de dept_emp de la base de datos.
     * @param deptEmployee - Registro a eliminar.
     * @return true si se ha eliminado correctamente.
     * @throws SQLException - Excepción en caso de error.
     */
    public Boolean remove(DeptEmp deptEmployee) throws SQLException {
        session.remove(deptEmployee);
        return true;
    }
}
