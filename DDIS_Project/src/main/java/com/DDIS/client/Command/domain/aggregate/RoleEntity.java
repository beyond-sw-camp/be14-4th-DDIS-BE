package com.DDIS.client.Command.domain.aggregate;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleNum;

    @Column(nullable = false)
    private String roleName; // 예: ROLE_USER, ROLE_ADMIN

    @OneToMany(mappedBy = "role")
    private List<ClientRoleEntity> clientRoles;
}
