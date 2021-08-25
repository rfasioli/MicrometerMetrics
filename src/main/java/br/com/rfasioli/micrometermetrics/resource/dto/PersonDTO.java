package br.com.rfasioli.micrometermetrics.resource.dto;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PersonDTO {
  private String id;
  private String firstName;
  private String sureName;
  private String lastName;
}
