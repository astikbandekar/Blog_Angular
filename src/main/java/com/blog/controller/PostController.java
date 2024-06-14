package com.blog.controller;

import com.blog.payload.ApiResponse;
import com.blog.payload.PostDto;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders =
        "Access-Control-Allow-Origin", methods = {
        RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.HEAD,
        RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.TRACE })
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //http://localhost:8080/api/posts
    @GetMapping
    public String tes(){
        return "hello";
    }


    //http://localhost:8080/api/posts
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost (@Valid @RequestBody PostDto postDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       PostDto dto = postService.createPost(postDto);
       //return new ResponseEntity(dto, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Post created successfully"));
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=asc
    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    //@CrossOrigin("http://localhost:4200")
    public List<PostDto> listAllPosts(
            @RequestParam(value = "pageNO", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10" , required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id" , required = false) String sortBy,
            @RequestParam(value = "sortBy", defaultValue = "id" , required = false) String sortDir
    ){
        List<PostDto> postDtos = postService.listAllPosts(pageNo,pageSize,sortBy,sortDir);


        int startIndex = pageNo * pageSize;
        int endIndex = Math.min(startIndex + pageSize, postDtos.size());

        return postDtos.subList(startIndex, endIndex);
    }

    //http://localhost:8080/api/posts/id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto post = postService.getPostById(id);
        return  new ResponseEntity<>(post, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable("id") long id,
            @RequestBody PostDto postDto
    ){
        PostDto postDto1 = postService.updatePost(id, postDto);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/
    @DeleteMapping("/{id}")
    public ResponseEntity deletePostById(@PathVariable("id") long id){
        postService.deletePostById(id);
        return ResponseEntity.ok(new ApiResponse("post deleted successfully"));
    }


}
