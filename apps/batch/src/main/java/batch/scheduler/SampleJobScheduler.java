package batch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "batch.scheduler", name = "enabled", havingValue = "true")
public class SampleJobScheduler {

  private final JobLauncher jobLauncher;
  private final Job sampleJob;

  public SampleJobScheduler(
      JobLauncher jobLauncher,
      @Qualifier("sampleJob") Job sampleJob) {
    this.jobLauncher = jobLauncher;
    this.sampleJob = sampleJob;
  }

  @Scheduled(cron = "0 */5 * * * *")
  public void run() throws Exception {
    JobParameters params = new JobParametersBuilder()
        .addLong("runId", System.currentTimeMillis())
        .toJobParameters();
    jobLauncher.run(sampleJob, params);
  }
}
