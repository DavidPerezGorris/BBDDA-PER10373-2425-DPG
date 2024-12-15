package com.unir.dao;
import com.unir.model.mysql.Title;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
@Slf4j
public class TitlesDao {
private final Session session;
     /**
     * Consulta de todos los títulos.
     * @throws SQLException Excepción en caso de error.
     */
    public List<Title> findAll() throws SQLException {
        List<Title> titles = session.createNativeQuery("SELECT * FROM titles", Title.class).list();
        log.debug("Número de registros de títulos: {}", titles.size());
        return titles;
    }
     /**
     * Consulta de todos los títulos para un empleado específico.
     * @param empNo Identificador del empleado.
     * @return Lista de títulos.
     * @throws SQLException Excepción en caso de error.
     */
    public List<Title> findByEmployeeId(Integer empNo) throws SQLException {
        Query<Title> query = session.createQuery("FROM Title WHERE employee.empNo = :empNo", Title.class);
        query.setParameter("empNo", empNo);
        return query.list();
    }
     /**
     * Inserta un nuevo registro de título.
     * @param title - Registro de título a insertar.
     * @return Registro insertado.
     * @throws SQLException - Excepción en caso de error.
     */
    public Title save(Title title) throws SQLException {
        session.persist(title);
        return title;
    }
     /**
     * Elimina un registro de título de la base de datos.
     * @param title - Registro a eliminar.
     * @return true si se ha eliminado correctamente.
     * @throws SQLException - Excepción en caso de error.
     */
    public Boolean remove(Title title) throws SQLException {
        session.remove(title);
        return true;
    }
}
