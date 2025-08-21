package com.hcl.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trading")
public class TradingEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long Id;
     
     private String name;
     
     @Email
     private String email;
     
     @Min(value = 0,message = "Balance must be greater than or equal to 0")
     private Double balance;
     
     @CreationTimestamp  // ✅ Hibernate annotation - auto-sets on creation
     @Column(nullable = false, updatable = false)
     private LocalDateTime createdAt;
     
     @UpdateTimestamp
     @Column(nullable = false, updatable = false)
     private LocalDateTime updatedAt;
     
     
}
