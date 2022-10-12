package birt.eus.gyropendatadownload;

import birt.eus.gyropendatadownload.batch.step.StepFactory;
import birt.eus.gyropendatadownload.domain.Restaurant;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class GyrOpenDataDownloadApplication {

  public static void main(String[] args) {
    SpringApplication.run(GyrOpenDataDownloadApplication.class, args);
  }

  @Bean
  public Job mainJob(JobBuilderFactory jobBuilderFactory, StepFactory stepFactory) {
    return jobBuilderFactory.get("getOpenData")
      .start(stepFactory.createStep(Restaurant.class))
      .build();
  }
}
