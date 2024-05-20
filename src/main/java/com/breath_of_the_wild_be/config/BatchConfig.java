package com.breath_of_the_wild_be.config;

import com.breath_of_the_wild_be.service.festivalService.FestivalService;
import com.breath_of_the_wild_be.service.weatherService.WeatherService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private PlatformTransactionManager transactionManager;

  @Autowired
  private WeatherService weatherService;

  @Autowired
  private FestivalService festivalService;

  @Autowired
  private JobLauncher jobLauncher;

  @Bean
  public Job fetchWeatherJob() {
    return new JobBuilder("fetchWeatherJob", jobRepository)
        .start(fetchWeatherStep())
        .build();
  }


//날씨데이터 스케쥴링

  @Bean
  public Step fetchWeatherStep() {
    return new StepBuilder("fetchWeatherStep", jobRepository)
        .tasklet((contribution, chunkContext) -> {
          weatherService.printWeatherDataFromApi();
          return RepeatStatus.FINISHED;
        }, transactionManager)
        .build();
  }

  @Scheduled(cron = "0 30 9 * * ?")
  public void performJob() {
    try {
      JobParameters jobParameters = new JobParametersBuilder()
          .addLong("time", System.currentTimeMillis())
          .toJobParameters();
      jobLauncher.run(fetchWeatherJob(), jobParameters);
    } catch (Exception e) {
      System.out.println("스케쥴링실패");
      e.printStackTrace();
    }
  }

//축제정보 스케쥴링
  @Bean
  public Job fetchAndSaveDataJob() {
    return new JobBuilder("fetchAndSaveDataJob", jobRepository)
        .start(fetchAndSaveDataStep())
        .build();
  }

  @Bean
  public Step fetchAndSaveDataStep() {
    return new StepBuilder("fetchAndSaveDataStep", jobRepository)
        .tasklet((contribution, chunkContext) -> {
          festivalService.fetchAndSaveData();
          return RepeatStatus.FINISHED;
        }, transactionManager)
        .build();
  }

  @Scheduled(cron = "0 30 9 * * ?") // 매일 새벽 1시에 실행
  public void performFetchAndSaveDataJob() {
    try {
      JobParameters jobParameters = new JobParametersBuilder()
          .addLong("time", System.currentTimeMillis())
          .toJobParameters();
      jobLauncher.run(fetchAndSaveDataJob(), jobParameters);
    } catch (Exception e) {
      System.out.println("스케줄링 실패: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
