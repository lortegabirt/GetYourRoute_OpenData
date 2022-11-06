package birt.eus.gyropendatadownload;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class JobScheduler {

  private final JobLauncher jobLauncher;
  private final Job job;

  /**
   * Launch the batch job every day at 00:00
   */
  @Scheduled(cron = "0 0 0 ? * *")
  public void launchJob() throws Exception {
    jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
  }
}
