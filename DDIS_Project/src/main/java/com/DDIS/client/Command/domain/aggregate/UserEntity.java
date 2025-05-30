package com.DDIS.client.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_num")
    private Long clientNum;

    @Column(name = "client_type", nullable = false)
    private String clientType;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "client_pwd", nullable = false)
    private String clientPwd;

    @Column(name = "client_email", nullable = false)
    private String clientEmail;

    @Column(name = "client_birth", nullable = false)
    private String clientBirth;

    @Column(name = "client_nickname", nullable = false)
    private String clientNickname;

    @Column(name = "client_photo_url")
    private String clientPhotoUrl;

    @Column(name = "client_report_num")
    private Integer clientReportNum;

    @Column(name = "client_account_status", nullable = false)
    private String clientAccountStatus = "ACTIVE";

    @Column(name = "client_color_rgb", nullable = false)
    private String clientColorRgb = "rgba (80, 212, 198, 100)";
}
