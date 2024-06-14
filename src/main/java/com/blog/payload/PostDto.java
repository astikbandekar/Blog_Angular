package com.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min = 2, message = "Title should be more than two character")
    private String title;
    @Size(min =5 , message = " Description  should be minimum five character")
    private String description;
    private String content;
//
}
