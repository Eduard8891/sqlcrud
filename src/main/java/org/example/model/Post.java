package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private Timestamp created;
    private Timestamp updated;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<Label> labels;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private PostStatus status;
}
