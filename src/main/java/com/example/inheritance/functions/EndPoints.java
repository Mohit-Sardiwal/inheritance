package com.example.inheritance.functions;

import java.util.List;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.inheritance.entity.Child;

@Service
public class EndPoints {

  @Bean
  public Function<List<Child>, List<Child>> getFamilyData() {
    return new FxGetFamilyData();
  }
}
