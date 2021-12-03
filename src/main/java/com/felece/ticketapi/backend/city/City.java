package com.felece.ticketapi.backend.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city")
@Entity
@Builder
public class City {

    @Id
    private String code;

    @Column(name = "name")
    private String name;



}