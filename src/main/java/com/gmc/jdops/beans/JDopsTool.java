package com.gmc.jdops.beans;

import java.io.Serializable;

public class JDopsTool implements Serializable{
  public String id;
  public String name;
  public String purpose;
  public String comments;
  public boolean isApproved;
  public JDopsTool(String id, String name, String purpose, String comments, boolean isApproved){
    this.id = id;
    this.name = name;
    this.purpose = purpose;
    this.comments = comments;
    this.isApproved = isApproved;
  }
} 