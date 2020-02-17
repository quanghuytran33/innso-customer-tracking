package com.innso.customertracking.entity;

import com.innso.customertracking.enumeration.EChannel;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_file_id")
  private CustomerFile customerFile;

}
