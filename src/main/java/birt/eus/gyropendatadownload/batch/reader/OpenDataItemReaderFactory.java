package birt.eus.gyropendatadownload.batch.reader;

import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class OpenDataItemReaderFactory {

  private final Map<String, String> URL_MAP;
  private final Map<String, ItemReader<OpenDataRaw>> readers = new HashMap<>();

  public OpenDataItemReaderFactory(@Value("#{${euskadi.opendata.urls}}") Map<String, String> url_map) {
    URL_MAP = url_map;
  }

  @PostConstruct
  private void populateReaderMap() {
    URL_MAP.forEach((key, value) -> {
      ItemReader<OpenDataRaw> reader = generateReader(value);
      readers.put(key, reader);
    });
  }

  public ItemReader<OpenDataRaw> getReader(FeatureType type) {
    if (readers.containsKey(type.name())) {
      return readers.get(type.name());
    }
    throw new IllegalArgumentException("No reader of type " + type.name() + " found");
  }

  @SneakyThrows
  private ItemReader<OpenDataRaw> generateReader(String url) {
    JsonNode jsonNode = new ObjectMapper().readTree(new URL(url)).get("features");
    JacksonJsonObjectReader<OpenDataRaw> reader = new JacksonJsonObjectReader<>(new ObjectMapper(), OpenDataRaw.class);
    return new JsonItemReader<>(new ByteArrayResource(jsonNode.toString().getBytes()), reader);
  }

}
