package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_num")
    private int roomNum;

    @Column(name = "member_count")
    private int memberCount;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "color_rgb")
    private String colorRgb;

    @Column(name = "approve_required_count")
    private int approveRequiredCount;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

}
