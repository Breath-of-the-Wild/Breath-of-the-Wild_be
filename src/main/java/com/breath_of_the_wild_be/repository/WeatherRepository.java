package com.breath_of_the_wild_be.repository;

import com.breath_of_the_wild_be.domain.Weather;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//public interface ProductRepository extends JpaRepository<Product, Long> {
//}

//수정1.
public interface WeatherRepository extends JpaRepository<Weather, Long> {
  Weather findByCityIdAndDate(Integer cityId, String date);




  @Query("SELECT w FROM Weather w WHERE w.date >= :startDate AND w.date <= :endDate ")
  List<Weather> findWeatherByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

}


