package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Writer {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private PostStatus status;
}
