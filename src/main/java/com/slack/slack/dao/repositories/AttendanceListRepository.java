package com.slack.slack.dao.repositories;

import com.slack.slack.dao.models.AttendanceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceListRepository extends JpaRepository<AttendanceList, Long> {

    @Query(value = "select * from attendance a join attendance_list al on a.attendance_list_id = al.id where a.date_time = to_date(:date, 'yyyy-MM-dd')",
        nativeQuery = true)
    List<AttendanceList> findAllByDate(@Param("date") LocalDate date);

    @Query(value = "select * from attendance a join attendance_list al on a.attendance_list_id = al.id where a.date_time = :date and course_id = :courseId",
            nativeQuery = true)
    List<AttendanceList> findAllByDateAndCourseId(@Param("date") LocalDate date, Long courseId);

    @Query(value = "select attendance_list_id, date_time from attendance a join attendance_list al on a.attendance_list_id = al.id where course_id = :courseId group by attendance_list_id, date_time",
            nativeQuery = true)
    List<Tuple> findAllDatesByCourseId(Long courseId);
}
