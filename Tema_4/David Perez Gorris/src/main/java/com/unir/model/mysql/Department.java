package com.unir.model.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Department {
	@Id
    @Column(name = "dept_no", columnDefinition = "CHAR(4)", nullable = false)
    private String deptNo;

	@Column(name = "dept_name", columnDefinition = "VARCHAR(40)", nullable = false, unique = true)
    private String deptName;

	@OneToMany(mappedBy = "deptNo", fetch = FetchType.EAGER)
    private Set<DeptEmp> deptEmp;

	@OneToMany(mappedBy = "deptNo", fetch = FetchType.EAGER)
    private Set<DeptManager> deptManager;
}