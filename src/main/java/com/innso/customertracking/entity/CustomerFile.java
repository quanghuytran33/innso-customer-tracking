package com.innso.customertracking.entity;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"reference"})})
@Builder
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String client;

  @Column
  @CreatedDate
  private LocalDateTime createdDateTime;

  @Column
  private String reference;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable
      (
          name = "customer_file_message",
          joinColumns = {@JoinColumn(name = "customer_file_id")},
          inverseJoinColumns = {@JoinColumn(name = "customer_message_id", unique = true)}
      )
  private Set<CustomerMessage> customerMessages;

}
