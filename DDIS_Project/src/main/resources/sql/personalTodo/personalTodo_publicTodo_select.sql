# 공개 여부가 공개(true)인 특정 회원의 todo 조회

SELECT pt.*, ptd.todo_date
FROM personal_todos pt
         JOIN personal_todo_date ptd ON pt.todo_num = ptd.todo_num
WHERE pt.client_num = ?
  AND pt.is_public = TRUE;
