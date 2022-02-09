package com.example.jsondemo;

public class Comment {
    private int postId ;
    private String name ;
    private String body ;

    public int getPostId() {
        return postId;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
