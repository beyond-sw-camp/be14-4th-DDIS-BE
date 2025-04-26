# 특정 회원의 핀고정된 todo 조회 (회원 기준, 일자 포함)

SELECT pt.*, ptd.todo_date, ptd.pin_order
FROM personal_todos pt
         JOIN personal_todo_date ptd ON pt.todo_num = ptd.todo_num
WHERE pt.client_num = ?
  AND ptd.pin_order > 0
ORDER BY ptd.pin_order ASC;

