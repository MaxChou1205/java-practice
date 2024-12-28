package com.example.first_spring_boot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {
  @Autowired
  private Coach coach;

  // @Autowired
  // public DemoController(Coach coach) {
  // this.coach = coach;
  // }
  @GetMapping("/getCoach")
  public String getCoach() {
    return coach.getDailyWorkout();
  }

}
