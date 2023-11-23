package com.example.inheritance.functions;

import java.util.List;
import java.util.function.UnaryOperator;

import com.example.inheritance.entity.Child;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FxGetFamilyData implements UnaryOperator<List<Child>> {
  @Override
  public List<Child> apply(List<Child> children) {
    log.debug("Received Event :{}",children);
    return children;
  }
}
