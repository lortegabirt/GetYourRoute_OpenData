package birt.eus.gyropendatadownload.domain;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
public abstract class MapFeature implements Feature {

  private String name;
  private Point location;

  public void setLocation(OpenDataRaw origin) {
    location = new Point(new Position(origin.geometry().coordinates()));
  }

  public void setName(Map<String, String> properties) {
    name = properties.get("documentname");
  }

  public static <T extends MapFeature> String getDocumentName(Class<T> clazz) {
    return clazz.getAnnotation(Document.class).value();
  }

}
