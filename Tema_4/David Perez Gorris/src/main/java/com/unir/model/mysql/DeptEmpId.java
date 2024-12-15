package com.unir.model.mysql;
	
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
	
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode // En lugar de implementar manualmente los métodos equals y hashCode, utilizamos la anotación @EqualsAndHashCode de Lombok

public class DeptEmpId implements Serializable {
    private Integer empNo;
    private String deptNo;

    /* Se mantiene el código base proporcionado solo como referencia
		@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEmpId that = (DeptEmpId) o;
        return Objects.equals(empNo, that.empNo) && Objects.equals(deptNo, that.deptNo)
    }
		
    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo);
    }*/
}