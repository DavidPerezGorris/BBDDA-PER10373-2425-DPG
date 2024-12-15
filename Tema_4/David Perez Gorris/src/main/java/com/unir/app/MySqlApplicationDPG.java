package com.unir.app;

import com.unir.config.HibernateMySqlConfig;
import com.unir.config.LogbackConfig;
import com.unir.dao.DepartmentsDao;
import com.unir.dao.EmployeesDao;
import com.unir.model.mysql.Department;
import com.unir.model.mysql.DeptEmp;
import com.unir.model.mysql.Employee;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

@Slf4j
public class MySqlApplicationDPG {

    public static void main(String[] args) {

        // Configuramos Logback para que muestre las sentencias SQL que se ejecutan unicamente.
        LogbackConfig.configureLogbackForHibernateSQL();

        // Try-with-resources. Se cierra la conexión automáticamente al salir del bloque try.
        try (Session session = HibernateMySqlConfig.getSessionFactory().openSession()) {

            log.info("Conexión establecida con la base de datos MySQL");

            // Creamos los DAOs que nos permitirán interactuar con la base de datos.
            EmployeesDao employeesDao = new EmployeesDao(session);
            DepartmentsDao departmentsDao = new DepartmentsDao(session);

            // Consulta 1: Obtener el número de hombres y mujeres.
            log.info("Ejecutando consulta 1: Obtener el número de hombres y mujeres ordenado de forma descendente");
            List<Object[]> genderCounts = employeesDao.getGenderCount();
            for (Object[] row : genderCounts) {
                log.info("Género: {}, Conteo: {}", row[0], row[1]);
            }

            // Consulta 2: Mostrar el nombre, apellido y salario de la persona mejor pagada de un departamento específico.
            String departmentName = "Development"; // Puedes cambiar el nombre según tus necesidades.
            log.info("Ejecutando consulta 2: Persona mejor pagada del departamento '{}'", departmentName);
            Object[] highestPaid = departmentsDao.getHighestPaidEmployeeInDepartment(departmentName);
            if (highestPaid != null) {
                log.info("Empleado mejor pagado en {} - Nombre: {}, Apellido: {}, Salario: {}",
                        departmentName, highestPaid[0], highestPaid[1], highestPaid[2]);
            } else {
                log.info("No se encontró un empleado para el departamento '{}'", departmentName);
            }

            // Consulta 3: Mostrar el nombre, apellido y salario de la segunda persona mejor pagada de un departamento específico.
            log.info("Ejecutando consulta 3: Segunda persona mejor pagada del departamento '{}'", departmentName);
            Object[] secondHighestPaid = departmentsDao.getSecondHighestPaidEmployeeInDepartment(departmentName);
            if (secondHighestPaid != null) {
                log.info("Segunda persona mejor pagada en {} - Nombre: {}, Apellido: {}, Salario: {}",
                        departmentName, secondHighestPaid[0], secondHighestPaid[1], secondHighestPaid[2]);
            } else {
                log.info("No se encontró una segunda persona para el departamento '{}'", departmentName);
            }

            // Consulta 4: Mostrar el número de empleados contratados en un mes concreto.
            int month = 8; // Cambia el mes si es necesario (1-12).
            log.info("Ejecutando consulta 4: Número de empleados contratados en el mes '{}'", month);
            List<Object[]> hiredEmployees = employeesDao.getEmployeesHiredInMonth(month);
            for (Object[] row : hiredEmployees) {
                log.info("Mes de contratación: {}, Número de empleados: {}", row[0], row[1]);
            }

        } catch (Exception e) {
            log.error("Error al tratar con la base de datos", e);
        }
    }
}
