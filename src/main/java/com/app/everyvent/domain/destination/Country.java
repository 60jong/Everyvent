package com.app.everyvent.domain.destination;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "country_id")
    private Long id;
    
    private String englishName;

    private String koreanName;

    @OneToMany(mappedBy = "country")
    private List<City> cities = new ArrayList<>();

    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
