package com.DDIS.personalTodo.Command.application.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepeatInfo {
    @NotNull(message = "시작 날짜는 필수입니다.")
    private String startDate; // yyyy-MM-dd

    @NotNull(message = "종료 날짜는 필수입니다.")
    private String endDate; // yyyy-MM-dd

    @NotEmpty(message = "반복 요일은 하나 이상 선택해야 합니다.")
    private List<Integer> days;  // 👈 프론트에서 숫자 배열로 보내면 여기에 매핑됨

    public List<DayOfWeek> getRepeatDays() {
        return days.stream()
                .map(i -> DayOfWeek.of((i % 7) + 1)) // 0=월 → MONDAY(1)
                .toList();
    }
}
