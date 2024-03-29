package com.unicom.redistemplate.pojo;

import java.io.Serializable;

/** @author by ctf */
public class User implements Serializable {

  private static final long serialVersionUID = -1L;

  private String username;
  private Integer age;

  public User(String username, Integer age) {
    this.username = username;
    this.age = age;
  }

  public User() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
