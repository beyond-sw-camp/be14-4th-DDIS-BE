SELECT
    p.post_num,
    p.post_title,
    p.post_content,
    p.recruitment_start_date,
    p.recruitment_end_date,
    p.activity_time,
    p.recruitment_limit,
    p.applicant_count,
    c.category_name,
    cl.client_name,
FROM posts p
         JOIN categories ca ON p.category_num = ca.category_num
         JOIN clients cl ON p.client_num = cl.client_num;
