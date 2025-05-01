package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService{
    private final MemberShareTodoDateRepository memberShareTodoDateRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<DailyShareTodoDTO> getTodosByDate(Long roomNum, Long clientNum, String date) {
        return memberShareTodoDateRepository.findDailyTodos(roomNum, clientNum, date);
    }

    @Override
    public Map<String, String> getDateColorMap(Long roomNum) {
        // 1. 방의 색상과 인원수 조회
        Rooms room = roomRepository.findById(roomNum)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다"));

        String baseColor = room.getColorRgb(); // 예: #50D4C6
        int memberCount = room.getMemberCount();

        // 2. 날짜별 완료 수 조회
        List<Object[]> countResult = memberShareTodoDateRepository.countDoneByDate(roomNum);
        Map<String, Long> dateToCountMap = countResult.stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0], // todo_date
                        r -> (Long) r[1]    // count
                ));

        // 3. 날짜별 진하기 색 계산
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Long> entry : dateToCountMap.entrySet()) {
            String date = entry.getKey();
            long count = entry.getValue();
            float ratio = (float) count / memberCount;
            result.put(date, adjustColorBrightness(baseColor, ratio));
        }

        return result;
    }

    // 밝기를 비율만큼 어둡게 (0~1)
    private String adjustColorBrightness(String hexColor, float ratio) {
        Color color = Color.decode(hexColor);
        int r = (int) (color.getRed() * (1 - ratio));
        int g = (int) (color.getGreen() * (1 - ratio));
        int b = (int) (color.getBlue() * (1 - ratio));
        return String.format("#%02X%02X%02X", r, g, b);
    }

}
