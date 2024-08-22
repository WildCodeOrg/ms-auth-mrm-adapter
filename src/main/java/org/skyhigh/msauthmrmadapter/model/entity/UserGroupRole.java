package org.skyhigh.msauthmrmadapter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_group_roles")
public class UserGroupRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_critical", nullable = false)
    private boolean isCritical;
}
