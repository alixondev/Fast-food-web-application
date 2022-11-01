package com.example.appdbservice.repository.order;

import com.example.appdbservice.entity.order.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(value = "select *\n" +
            "from sale_bonus\n" +
            "where ((:startDate <= start_date and :endDate >= start_date) or\n" +
            "       (:startDate <= end_date and :endDate >= end_date) or\n" +
            "       (:startDate >= start_date and :endDate <= end_date) or\n" +
            "       (:startDate <= start_date and :endDate >= end_date)) and deleted=false and active\n" +
            "  and case\n" +
            "          when start_time > end_time then\n" +
            "              (:startTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSanaPlusKun + end_time)\n" +
            "                  or :endTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSanaPlusKun + end_time)\n" +
            "                  or (select date :kelishilganSana + start_time) between :startTime and :endTime\n" +
            "                  or (select date :kelishilganSanaPlusKun + end_time) between :startTime and :endTime)\n" +
            "\n" +
            "          when start_time < end_time then\n" +
            "              (:startTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSana + end_time)\n" +
            "                  or :endTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSana + end_time)\n" +
            "                  or (select date :kelishilganSana + start_time) between :startTime and :endTime\n" +
            "                  or (select date :kelishilganSana + end_time) between :startTime and :endTime)\n" +
            "    end;",nativeQuery = true)
    List<Sale> getExistsByGivenTime(@Param("startDate") Date startDate,
                                    @Param("endDate")Date endDate,
                                    @Param("startTime") Timestamp startTime,
                                    @Param("endTime") Timestamp endTime,
                                    @Param("kelishilganSana") Date kelishilganSana,
                                    @Param("kelishilganSanaPlusKun") Date kelishilganSanaPlusKun);

    @Query(value = "select *\n" +
            "from sale_bonus\n" +
            "where ((:startDate <= start_date and :endDate >= start_date) or\n" +
            "       (:startDate <= end_date and :endDate >= end_date) or\n" +
            "       (:startDate >= start_date and :endDate <= end_date) or\n" +
            "       (:startDate <= start_date and :endDate >= end_date)) and deleted=false and active and id!=:id\n" +
            "  and case\n" +
            "          when start_time > end_time then\n" +
            "              (:startTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSanaPlusKun + end_time)\n" +
            "                  or :endTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSanaPlusKun + end_time)\n" +
            "                  or (select date :kelishilganSana + start_time) between :startTime and :endTime\n" +
            "                  or (select date :kelishilganSanaPlusKun + end_time) between :startTime and :endTime)\n" +
            "\n" +
            "          when start_time < end_time then\n" +
            "              (:startTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSana + end_time)\n" +
            "                  or :endTime between (select date :kelishilganSana + start_time) and (select date :kelishilganSana + end_time)\n" +
            "                  or (select date :kelishilganSana + start_time) between :startTime and :endTime\n" +
            "                  or (select date :kelishilganSana + end_time) between :startTime and :endTime)\n" +
            "    end;",nativeQuery = true)
    List<Sale> getExistsByGivenTimeIdNot(@Param("startDate") Date startDate,
                                         @Param("endDate")Date endDate,
                                         @Param("startTime") Timestamp startTime,
                                         @Param("endTime") Timestamp endTime,
                                         @Param("kelishilganSana") Date kelishilganSana,
                                         @Param("kelishilganSanaPlusKun") Date kelishilganSanaPlusKun,
                                         @Param("id") Long id);

    @Query(value = "select *\n" +
            "from sale\n" +
            "where (:date >= start_date and :date <= end_date) and deleted=false\n" +
            "  and case\n" +
            "          when start_time > end_time then\n" +
            "              (:time between (select date :kelishilganSana + start_time) and (select date :kelishilganSanaPlusKun + end_time))\n" +
            "          when start_time < end_time then\n" +
            "              (:time between (select date :kelishilganSana + start_time) and (select date :kelishilganSana + end_time))", nativeQuery = true)
    Optional<Sale> getSaleByCurrentTime(@Param("date") Date date,
                                        @Param("time") Timestamp time,
                                        @Param("kelishilganSana") Date kelishilganSana,
                                        @Param("kelishilganSanaPlusKun") Date kelishilganSanaPlusKun);


}
