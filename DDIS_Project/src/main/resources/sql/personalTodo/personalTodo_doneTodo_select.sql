# 개인 todo 중 완료된 todo 조회

SELECT pt.*, ptd.todo_date, ptd.is_done
FROM personal_todos pt
         JOIN personal_todo_date ptd ON pt.todo_num = ptd.todo_num
WHERE pt.client_num = ?
  AND ptd.is_done = TRUE;