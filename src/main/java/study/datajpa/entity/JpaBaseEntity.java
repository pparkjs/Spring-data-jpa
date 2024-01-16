package study.datajpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 순수 JPA를 이용한 Audiding
 */
@Getter
@Setter
@MappedSuperclass
public class JpaBaseEntity {

    @Column(updatable = false) // 변경 되지 못하게 함
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist // persist 하기 전에 이벤트 발생
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    public void preUpdate(){
        updatedDate = LocalDateTime.now();
    }

}
