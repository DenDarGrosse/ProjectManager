package ru.local.projectmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype",
        discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractObject implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AbstractObject parent;

    @OneToMany(mappedBy = "parent",
            fetch = FetchType.LAZY)
    private List<AbstractObject> children;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @Column(nullable = false)
    private String objectName;

    @CreatedDate
    @Column(nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Date lastModifiedDate;
}
