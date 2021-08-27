package br.com.rfasioli.micrometermetrics.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonDTO {
  private String id;
  private String firstName;
  private String sureName;
  private String lastName;
}
