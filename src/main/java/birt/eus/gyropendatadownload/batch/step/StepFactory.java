package birt.eus.gyropendatadownload.batch.step;

import birt.eus.gyropendatadownload.batch.processor.OpenDataProcessor;
import birt.eus.gyropendatadownload.batch.reader.OpenDataItemReaderFactory;
import birt.eus.gyropendatadownload.batch.writer.MongoItemWriterFactory;
import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import birt.eus.gyropendatadownload.domain.document.PointOfInterest;
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
  private final List<OpenDataMapper> mappers;
  private Map<FeatureType, OpenDataMapper> mapperMap;

  @PostConstruct
  private void setUpMapperMap() {
    mapperMap = mappers.stream().collect(Collectors.toMap(OpenDataMapper::getType, Function.identity()));
  }

  public Step createStep(FeatureType type) {
    return stepBuilderFactory.get(type.name())
      .<OpenDataRaw, PointOfInterest>chunk(10)
      .reader(itemReaderFactory.getReader(type))
      .processor(new OpenDataProcessor(getOpenDataMapper(type)))
      .writer(itemWriterFactory.getWriter()).build();
  }

  private OpenDataMapper getOpenDataMapper(FeatureType type) {
    return mapperMap.get(type);
  }
}
