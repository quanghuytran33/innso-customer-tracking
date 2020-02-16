package com.innso.customertracking.entity;

import com.innso.customertracking.enumeration.EChannel;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
public class CustomerMessage {

  @Id
  @GeneratedValue
  private long id;

  @Column
  @CreatedDate
  private LocalDateTime createdDateTime;

  @Column
  private String sender;

  @Column
  private String content;

  @Column
  @Enumerated(value = EnumType.STRING)
  private EChannel channel;

  @ManyToOne
  @JoinColumn(name = "customer_file_id")
  private CustomerFile customerFile;

}
