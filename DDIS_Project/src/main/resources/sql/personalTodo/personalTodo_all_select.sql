#  회원별 전체 todo 조회 (카테고리, 일자 포함)

SELECT pt.*, pc.personal_category_name, pc.personal_category_color_rgb, ptd.todo_date, ptd.is_done, ptd.pin_order
FROM personal_todos pt
         LEFT JOIN personal_categories pc ON pt.personal_category_num = pc.personal_category_num
         LEFT JOIN personal_todo_date ptd ON pt.todo_num = ptd.todo_num
WHERE pt.client_num = ?;