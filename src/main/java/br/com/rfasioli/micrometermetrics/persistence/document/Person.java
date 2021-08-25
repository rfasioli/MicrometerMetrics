package br.com.rfasioli.micrometermetrics.persistence.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
  @Id
  @EqualsAndHashCode.Include
  private ObjectId id;

  private String firstName;
  private String sureName;
  private String lastName;
}
