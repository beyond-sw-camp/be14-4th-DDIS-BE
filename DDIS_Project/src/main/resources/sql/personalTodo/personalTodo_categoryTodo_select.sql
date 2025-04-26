# 특정 회원의 카테고리 별 투두 조회

SELECT pt.*, pc.personal_category_name, pc.personal_category_color_rgb, ptd.todo_date
FROM personal_todos pt
         LEFT JOIN personal_categories pc ON pt.personal_category_num = pc.personal_category_num
         LEFT JOIN personal_todo_date ptd ON pt.todo_num = ptd.todo_num
WHERE pt.client_num = ?
  AND pt.personal_category_num = ?;
