package com.example.first_spring_boot;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
  @Override
  public String getDailyWorkout() {
    return "Practice fast bowling for 15 minutes!!";
  }

}
