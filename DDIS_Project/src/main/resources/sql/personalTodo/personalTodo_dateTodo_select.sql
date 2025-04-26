# 특정 일자별 특정 회원의 todo 조회

SELECT pt.*, ptd.todo_date, ptd.is_done, ptd.pin_order
FROM personal_todos pt
         JOIN personal_todo_date ptd ON pt.todo_num = ptd.todo_num
WHERE pt.client_num = ?
  AND ptd.todo_date = ?;
