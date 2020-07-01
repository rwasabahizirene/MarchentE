package com.restAssured.solution.pojos.comments;

import java.util.Objects;

public class Comments {
    private String name;
    private int postId;
    private int id;
    private String body;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return getPostId() == comments.getPostId() &&
                getId() == comments.getId() &&
                Objects.equals(getName(), comments.getName()) &&
                Objects.equals(getBody(), comments.getBody()) &&
                Objects.equals(getEmail(), comments.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPostId(), getId(), getBody(), getEmail());
    }

    @Override
    public String toString() {
        return "Posts{" +
                "name='" + name + '\'' +
                ", postId=" + postId +
                ", id=" + id +
                ", body='" + body + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
