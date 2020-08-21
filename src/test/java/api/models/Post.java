package api.models;

import java.util.Map;

public class Post {

    private Integer id;
    private String title;

    public static Post createPost(Map<String, String> entry) {
        Post post = new Post();
        post.setId(Integer.parseInt(entry.get("id")));
        post.setTitle(entry.get("title"));
        return post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
