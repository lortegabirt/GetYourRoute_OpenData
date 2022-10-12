package birt.eus.gyropendatadownload.domain;

import com.mongodb.client.model.geojson.Point;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("restaurants")
@Getter
@Setter
public class Restaurant implements MapFeature {
  @Id
  private String id;
  private final FeatureType type = FeatureType.RESTAURANT;
  private String name;
  private String description;
  private Point location;
}
