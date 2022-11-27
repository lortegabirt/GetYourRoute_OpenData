package birt.eus.gyropendatadownload.domain.mapper;

import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import birt.eus.gyropendatadownload.domain.document.PointOfInterest;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper implements OpenDataMapper {

  @Getter
  private final FeatureType type = FeatureType.RESTAURANT;
  @Override
  public PointOfInterest toDocument(OpenDataRaw origin) {
    PointOfInterest restaurant = new PointOfInterest();
    restaurant.setType(FeatureType.RESTAURANT);
    restaurant.setId(type + origin.id());
    restaurant.setName(origin);
    restaurant.getProperties().put("web", origin.properties().get("web"));
    restaurant.setLocation(origin);
    return restaurant;
  }

}
