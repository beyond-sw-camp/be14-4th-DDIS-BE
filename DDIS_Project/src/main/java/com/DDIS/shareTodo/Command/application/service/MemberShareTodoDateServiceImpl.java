package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import com.DDIS.approve.Command.domain.repository.ApproveRepository;
import com.DDIS.shareTodo.Command.application.dto.DoneLogResponse;
import com.DDIS.shareTodo.Command.application.dto.GenerateTodoDatesRequest;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MemberShareTodoDateServiceImpl implements MemberShareTodoDateService {
    private final MemberShareTodoDateRepository repository;
    private final MemberShareTodoRepository memberShareTodoRepository;
    private final MemberShareTodoDateRepository memberShareTodoDateRepository;
    private final ApproveRepository approveRepository;

    public void generateTodoDates(List<GenerateTodoDatesRequest> requestList) {

        List<MemberShareTodoDate> allDates = new ArrayList<>();

        // 1. shareTodoNum 기준으로 모든 MemberShareTodo 가져오기
        for (GenerateTodoDatesRequest request : requestList) {
            List<MemberShareTodo> memberShareTodos = memberShareTodoRepository.findByShareTodo_ShareTodoNum(request.getShareTodoNum());

            int year = request.getYear();
            int month = request.getMonth();
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

            for (MemberShareTodo memberShareTodo : memberShareTodos) {
                Long memberShareTodoNum = memberShareTodo.getMemberShareTodoNum();

                for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                    int dayOfWeek = date.getDayOfWeek().getValue();

                    if (request.getSelectedWeekdays().contains(dayOfWeek)) {
                        allDates.add(MemberShareTodoDate.builder()
                                .todoDate(date.toString())
                                .memberShareTodoNum(memberShareTodoNum)
                                .isDone(false)
                                .isPublic(false)
                                .pinOrder(0)
                                .build());
                    }
                }
            }
        }

        repository.saveAll(allDates);
    }

    @Override
    public List<DoneLogResponse> getDoneLogs(Long roomNum, String date) {
        List<MemberShareTodoDate> doneList = memberShareTodoDateRepository.findDoneTodos(date, roomNum);
        System.out.println("✅ 완료된 투두 개수: " + doneList.size());


        return doneList.stream()
                .flatMap(todoDate -> {
                    MemberShareTodo mst = memberShareTodoRepository.findById(todoDate.getMemberShareTodoNum())
                            .orElseThrow(() -> new IllegalArgumentException("MemberShareTodo 없음"));

                    String nickname = mst.getMemberNum().getClient().getClientNickname();
                    String todoName = mst.getShareTodo().getShareTodoName();

                    List<Approve> approves = approveRepository.findByMemberShareTodoNum(mst.getMemberShareTodoNum());

                    if (approves.isEmpty()) {
                        return Stream.of(new DoneLogResponse(nickname, todoName, null));
                    }

                    return approves.stream()
                            .map(a -> new DoneLogResponse(nickname, todoName, a.getApproveTime()));
                })
                .collect(Collectors.toList());
    }


}
