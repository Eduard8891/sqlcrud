package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private String content;
    private Date created;
    private Date updated;
    private List<Label> labels;
    private PostStatus status;
}
