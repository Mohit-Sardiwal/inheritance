package com.example.inheritance.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class Parent {
  private String id;
  private String name;
}
