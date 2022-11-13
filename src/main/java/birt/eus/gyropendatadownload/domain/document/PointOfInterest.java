package birt.eus.gyropendatadownload.domain.document;

import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Document("points_of_interest")
public class PointOfInterest {
  @Id
  private String id;
  private FeatureType type;
  private String name;
  private GeoJsonPoint location;
  private final Map<String, String> properties = new HashMap<>();

  public void setLocation(OpenDataRaw origin) {
    List<Double> coordinates = origin.geometry().coordinates();
    location = new GeoJsonPoint(coordinates.get(0), coordinates.get(1));
  }

  public void setName(OpenDataRaw origin) {
    name = origin.properties().get("documentname");
  }

  public static String getDocumentName() {
    return PointOfInterest.class.getAnnotation(Document.class).value();
  }
}
