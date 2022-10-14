package birt.eus.gyropendatadownload.domain.mapper;

import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import birt.eus.gyropendatadownload.domain.document.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper implements OpenDataMapper<Restaurant> {
  @Override
  public Restaurant toDocument(OpenDataRaw origin) {
    Restaurant restaurant = new Restaurant();
    restaurant.setId(origin.id());
    restaurant.setName(origin.properties());
    restaurant.setWeb(origin.properties().get("web"));
    restaurant.setLocation(origin);
    return restaurant;
  }

  @Override
  public Class<Restaurant> getMapFeatureClass() {
    return Restaurant.class;
  }

}
