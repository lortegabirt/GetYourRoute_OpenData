package birt.eus.gyropendatadownload.batch.processor.mapper;

import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import birt.eus.gyropendatadownload.domain.Restaurant;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper implements OpenDataMapper<Restaurant> {
  @Override
  public Restaurant toDocument(OpenDataRaw origin) {
    Restaurant restaurant = new Restaurant();
    restaurant.setId(origin.id());
    restaurant.setName(origin.properties().get("documentname"));
    restaurant.setDescription(origin.properties().get("documentdescription"));
    restaurant.setLocation(new Point(new Position(origin.geometry().coordinates())));
    return restaurant;
  }

  @Override
  public Class<Restaurant> getMapFeatureClass() {
    return Restaurant.class;
  }

}
