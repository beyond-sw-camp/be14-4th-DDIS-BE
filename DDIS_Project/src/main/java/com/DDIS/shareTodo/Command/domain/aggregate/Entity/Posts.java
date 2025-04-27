//package com.DDIS.shareTodo.Command.domain.aggregate.Entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//@Table(name = "posts")
//public class Posts {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "post_num")
//    private Long postNum;
//
//    @Column(name = "post_title")
//    private String postTitle;
//
//    @Column(name = "post_content")
//    private String postContent;
//
//    @Column(name = "recruitment_start_date")
//    private String recruitmentStartDate;
//
//    @Column(name = "recruitment_end_date")
//    private String recruitmentEndDate;
//
//    @Column(name = "activity_time")
//    private int activityTime;
//
//    @Column(name = "recruitment_limit")
//    private int recruitmentLimit;
//
//    @Column(name = "applicant_count")
//    private int applicantCount;
//
//    @Column(name = "is_public")
//    private boolean isPublic;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_num")
//    private PostCategory categoryNum;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_num")
//    private Clients clientNum;
//}
