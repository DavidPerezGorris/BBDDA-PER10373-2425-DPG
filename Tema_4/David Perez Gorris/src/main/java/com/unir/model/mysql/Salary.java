package com.unir.model.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salaries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SalaryId.class)

public class Salary {
	@Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", nullable = false)
    private Employee empNo;

	@Id
    @Column(name = "from_date", columnDefinition = "DATE", nullable = false)
    private Date fromDate;

	@Column(name = "salary", nullable = false)
    private Integer salary;

	@Column(name = "to_date", columnDefinition = "DATE", nullable = false)
    private Date toDate;
}