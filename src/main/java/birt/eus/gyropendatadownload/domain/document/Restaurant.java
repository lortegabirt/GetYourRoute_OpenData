package birt.eus.gyropendatadownload.domain.document;

import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.MapFeature;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("restaurants")
public final class Restaurant extends MapFeature {
  @Id
  private String id;
  private final FeatureType type = FeatureType.RESTAURANT;
  private String web;
}
