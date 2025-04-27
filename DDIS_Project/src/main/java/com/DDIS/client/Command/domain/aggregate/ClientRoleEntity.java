package com.DDIS.client.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_roles")
@Getter
@Setter
@NoArgsConstructor
public class ClientRoleEntity {

    @EmbeddedId
    private ClientRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clientNum")
    @JoinColumn(name = "client_num")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleNum")
    @JoinColumn(name = "role_num")
    private RoleEntity role;
}
