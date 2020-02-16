package com.innso.customertracking.entity;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"reference"})})
@Data
@Builder
public class CustomerFile {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private String client;

  @Column
  @CreatedDate
  private LocalDateTime createdDateTime;

  @Column
  private String reference;

  @OneToMany(mappedBy = "customerFile", cascade = CascadeType.ALL)
  private Set<CustomerMessage> customerMessages;

}
