package com.DDIS.personalTodo.Command.application.service;

import com.DDIS.personalTodo.Command.application.dto.request.RepeatInfo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepeatDateCalculator {

    public static List<String> calculate(RepeatInfo repeatInfo) {
        List<String> repeatDates = new ArrayList<>();
        LocalDate start = LocalDate.parse(repeatInfo.getStartDate());
        LocalDate end = LocalDate.parse(repeatInfo.getEndDate());

        List<DayOfWeek> repeatDays = repeatInfo.getRepeatDays();

        while (!start.isAfter(end)) {
            if (repeatDays.contains(start.getDayOfWeek())) {
                repeatDates.add(start.toString()); // yyyy-MM-dd 포맷
            }
            start = start.plusDays(1);
        }

        return repeatDates;
    }
}