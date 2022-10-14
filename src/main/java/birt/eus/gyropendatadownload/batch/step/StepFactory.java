package birt.eus.gyropendatadownload.batch.step;

import birt.eus.gyropendatadownload.batch.processor.OpenDataProcessor;
import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.batch.reader.OpenDataItemReaderFactory;
import birt.eus.gyropendatadownload.batch.writer.MongoItemWriterFactory;
import birt.eus.gyropendatadownload.domain.MapFeature;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class StepFactory {

  private final StepBuilderFactory stepBuilderFactory;
  private final OpenDataItemReaderFactory itemReaderFactory;
  private final MongoItemWriterFactory itemWriterFactory;
  private final List<OpenDataMapper<?>> mappers;
  private Map<String, OpenDataMapper<?>> mapperMap;

  @PostConstruct
  private void setUpMapperMap() {
    mapperMap = mappers.stream().collect(Collectors.toMap(OpenDataMapper::getDocumentName, Function.identity()));
  }

  public <T extends MapFeature> Step createStep(Class<T> clazz) {
    return stepBuilderFactory.get(MapFeature.getDocumentName(clazz))
      .<OpenDataRaw, T>chunk(10)
      .reader(itemReaderFactory.getReader(clazz))
      .processor(new OpenDataProcessor<>(gettOpenDataMapper(clazz)))
      .writer(itemWriterFactory.getWriter(clazz)).build();
  }

  @SuppressWarnings("unchecked")
  private <T extends MapFeature> OpenDataMapper<T> gettOpenDataMapper(Class<T> clazz) {
    return (OpenDataMapper<T>) mapperMap.get(MapFeature.getDocumentName(clazz));
  }
}
