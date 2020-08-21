package api.models;

import java.util.Map;

public class Comment {

    private Integer id;
    private String body;
    private Integer postId;

    public static Comment createComment(Map<String, String> entry) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(entry.get("id")));
        comment.setBody(entry.get("body"));
        comment.setPostId(Integer.parseInt(entry.get("postId")));
        return comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
