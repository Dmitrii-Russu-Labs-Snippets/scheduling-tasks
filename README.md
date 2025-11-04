# Spring Scheduling Tasks Example

A demonstration of Spring's task scheduling capabilities using `@Scheduled` annotation for automated task execution.

## Overview

This project shows how to configure and use Spring's built-in scheduler to execute tasks automatically at defined intervals.

## Key Features

- **`@EnableScheduling`** - Enables Spring's scheduled task execution capability
- **`@Scheduled`** - Configures task execution timing with various options:
  - `fixedRate` - Fixed interval between method invocations
  - `fixedDelay` - Delay between completion and next invocation
  - `cron` - Cron expressions for complex schedules

## Code Example

### Main Application Class
```java
@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedulingTasksApplication.class, args);
    }
}
```

### Scheduled Task
```java
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
```
### Expected Output:

```
20yy-mm-ddT07:23:01.665-04:00  INFO 19633 --- [scheduling-1] c.e.s.ScheduledTasks : The time is now 07:23:01
20yy-mm-ddT07:23:06.663-04:00  INFO 19633 --- [scheduling-1] c.e.s.ScheduledTasks : The time is now 07:23:06
20yy-mm-ddT07:23:11.663-04:00  INFO 19633 --- [scheduling-1] c.e.s.ScheduledTasks : The time is now 07:23:11
```
### Testing
The project includes tests using Awaitility to verify scheduled task execution:
```
@SpringBootTest
public class ScheduledTasksTest {
    @SpyBean
    ScheduledTasks tasks;

    @Test
    public void reportCurrentTime() {
        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            verify(tasks, atLeast(2)).reportCurrentTime();
        });
    }
}
```

### Use Cases
Perfect for:

Periodic data cleanup

Scheduled reports generation

Health checks and monitoring

Cache refresh operations

Automated synchronization tasks
