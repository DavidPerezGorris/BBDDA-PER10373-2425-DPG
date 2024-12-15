package com.unir.dao;
import com.unir.model.mysql.Salary;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
@Slf4j
public class SalariesDao {
private final Session session;
     /**
     * Consulta de todos los registros de salarios.
     * @throws SQLException Excepción en caso de error.
     */
    public List<Salary> findAll() throws SQLException {
        List<Salary> salaries = session.createNativeQuery("SELECT * FROM salaries", Salary.class).list();
        log.debug("Número de registros de salarios: {}", salaries.size());
        return salaries;
    }
     /**
     * Consulta de todos los registros de salarios para un empleado específico.
     * @param empNo Identificador del empleado.
     * @return Lista de registros de salarios.
     * @throws SQLException Excepción en caso de error.
     */
    public List<Salary> findByEmployeeId(Integer empNo) throws SQLException {
        Query<Salary> query = session.createQuery("FROM Salary WHERE employee.empNo = :empNo", Salary.class);
        query.setParameter("empNo", empNo);
        return query.list();
    }
     /**
     * Inserta un nuevo registro de salario.
     * @param salary - Registro de salario a insertar.
     * @return Registro insertado.
     * @throws SQLException - Excepción en caso de error.
     */
    public Salary save(Salary salary) throws SQLException {
        session.persist(salary);
        return salary;
    }
     /**
     * Elimina un registro de salario de la base de datos.
     * @param salary - Registro a eliminar.
     * @return true si se ha eliminado correctamente.
     * @throws SQLException - Excepción en caso de error.
     */
    public Boolean remove(Salary salary) throws SQLException {
        session.remove(salary);
        return true;
    }
}
