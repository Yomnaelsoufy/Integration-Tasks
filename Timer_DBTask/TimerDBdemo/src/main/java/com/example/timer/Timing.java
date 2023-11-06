package com.example.timer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "timer_tbl")
public class Timing {
   @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "send_time")
   private String send_time;

   public Timing() {
   }

   public Timing(String send_time) {
       this.send_time = send_time;
   }

   public String getSend_time() {
       return send_time;
   }

   public void setSend_time(String send_time) {
       this.send_time = send_time;
   }

}