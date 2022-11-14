package com.example.weatherCoder.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Style {
    @Id
    private String styleName;
}