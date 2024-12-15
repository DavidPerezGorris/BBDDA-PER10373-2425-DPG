package com.unir.dao;
import com.unir.model.mysql.DeptManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
@Slf4j
public class DeptManagersDao {
private final Session session;
     /**
     * Consulta de todos los registros de dept_manager.
     * @throws SQLException Excepción en caso de error.
     */
    public List<DeptManager> findAll() throws SQLException {
        List<DeptManager> deptManagers = session.createNativeQuery("SELECT * FROM dept_manager", DeptManager.class).list();
        log.debug("Número de registros de dept_manager: {}", deptManagers.size());
        return deptManagers;
    }
     /**
     * Consulta de todos los registros de dept_manager para un empleado específico.
     * @param empNo Identificador del empleado.
     * @return Lista de registros de dept_manager.
     * @throws SQLException Excepción en caso de error.
     */
    public List<DeptManager> findByEmployeeId(Integer empNo) throws SQLException {
        Query<DeptManager> query = session.createQuery("FROM DeptManager WHERE employee.empNo = :empNo", DeptManager.class);
        query.setParameter("empNo", empNo);
        return query.list();
    }
     /**
     * Inserta un nuevo registro en dept_manager.
     * @param deptManager - Registro a insertar.
     * @return Registro insertado.
     * @throws SQLException - Excepción en caso de error.
     */
    public DeptManager save(DeptManager deptManager) throws SQLException {
        session.persist(deptManager);
        return deptManager;
    }
     /**
     * Elimina un registro de dept_manager de la base de datos.
     * @param deptManager - Registro a eliminar.
     * @return true si se ha eliminado correctamente.
     * @throws SQLException - Excepción en caso de error.
     */
    public Boolean remove(DeptManager deptManager) throws SQLException {
        session.remove(deptManager);
        return true;
    }
}
