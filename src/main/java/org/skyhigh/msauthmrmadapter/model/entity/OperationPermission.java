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
@Table(name = "operation_permissions")
public class OperationPermission {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "permission_name", nullable = false)
    private String permissionName;

    @Column(name = "operation_endpoint", nullable = false)
    private String operationEndpoint;

    @Column(name = "is_critical", nullable = false)
    private boolean isCritical;
}
