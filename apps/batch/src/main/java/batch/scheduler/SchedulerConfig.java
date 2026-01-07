package batch.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "batch.scheduler", name = "enabled", havingValue = "true")
@EnableScheduling
@Configuration
public class SchedulerConfig {

}
