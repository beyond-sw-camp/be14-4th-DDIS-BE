# 특정 회원의 핀고정된 todo 조회 (회원 기준, 일자 포함)

SELECT
    pt.todo_num, pt.todo_content, pt.personal_category_num, pt.client_num,
    pc.personal_category_name, pc.personal_category_color_rgb,
    ptd.todo_date, ptd.is_done, ptd.pin_order, ptd.is_public
FROM personal_todos pt
         LEFT JOIN personal_categories pc ON pt.personal_category_num = pc.personal_category_num
         JOIN personal_todo_date ptd ON pt.todo_num = ptd.todo_num
WHERE pt.client_num = ?
  AND ptd.pin_order > 0
ORDER BY ptd.pin_order ASC;