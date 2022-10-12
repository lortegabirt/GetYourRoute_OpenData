package birt.eus.gyropendatadownload.domain.document;

import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.MapFeature;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("accommodations")
public final class Accommodation extends MapFeature {
  @Id
  private String id;
  @Builder.Default
  private FeatureType type = FeatureType.ACCOMMODATION;
  private String web;
  private String subtype;
}
