package com.DDIS.post.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "clients")
public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "client_num")
        private int clientNum;

        @Column(name = "client_type")
        private String clientType;

        @Column(name = "client_name")
        private String clientName;

        @Column(name = "client_id")
        private String clientId;

        @Column(name = "client_pwd")
        private String clientPwd;

        @Column(name = "client_email")
        private String clientEmail;

        @Column(name = "client_birth")
        private String clientBirth;

        @Column(name = "client_nickname")
        private String clientNickname;

        @Column(name = "client_photo_url")
        private String clientPhotoUrl;

        @Column(name = "client_report_num")
        private int clientReportNum;

        @Column(name = "client_account_status")
        private String clientAccountStatus;

        @Column(name = "client_color_rgb")
        private String clientColorRgb;
    }

