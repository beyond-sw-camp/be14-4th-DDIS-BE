# 특정 회원이 등록한 모든 카테고리 목록 조회

SELECT personal_category_num, personal_category_name, personal_category_color_rgb
FROM personal_categories
WHERE client_num = ?;
