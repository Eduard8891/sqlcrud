package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private String content;
    private Timestamp created;
    private Timestamp updated;
    private List<Label> labels;
    private PostStatus status;
}
