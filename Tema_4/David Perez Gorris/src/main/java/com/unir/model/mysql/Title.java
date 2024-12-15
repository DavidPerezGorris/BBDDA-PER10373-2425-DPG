package com.unir.model.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "titles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(TitleId.class)

public class Title {
	@Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", nullable = false)
    private Employee empNo;

	@Id
    @Column(name = "title", nullable = false)
    private String title;

	@Id
    @Column(name = "from_date", columnDefinition = "DATE", nullable = false)
    private Date fromDate;

	@Column(name = "to_date", columnDefinition = "DATE")
    private Date toDate;
}
