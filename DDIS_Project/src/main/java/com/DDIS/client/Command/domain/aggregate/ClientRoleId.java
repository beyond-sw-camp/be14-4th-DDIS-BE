package com.DDIS.client.Command.domain.aggregate;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ClientRoleId implements Serializable {
    private Long clientNum;
    private Integer roleNum;
}
