package com.unir.model.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dept_emp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(DeptEmpId.class)

public class DeptEmp {
	@Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", nullable = false)
    private Employee empNo;

	@Id
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", columnDefinition = "CHAR(4)", nullable = false)
    private Department deptNo;

	@Column(name = "from_date", columnDefinition = "DATE", nullable = false)
    private Date fromDate;

	@Column(name = "to_date", columnDefinition = "DATE", nullable = false)
    private Date toDate;
}