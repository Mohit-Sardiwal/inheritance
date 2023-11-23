package com.example.inheritance.entity;

import lombok.Data;

@Data
public class Child extends Parent{
  private String childId;
  private String value;
}
