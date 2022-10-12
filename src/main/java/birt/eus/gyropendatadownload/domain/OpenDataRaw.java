package birt.eus.gyropendatadownload.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenDataRaw(String id, Map<String, String> properties, Point geometry) {
  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Point(List<Double> coordinates) {
  }
}
