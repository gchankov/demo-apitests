package hooks;

import api.models.Comment;
import api.models.Post;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

import java.util.Locale;
import java.util.Map;

public class Configurer implements TypeRegistryConfigurer {

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(Post.class, new TableEntryTransformer<Post>() {
            @Override
            public Post transform(Map<String, String> entry) {
                return Post.createPost(entry);
            }
        }));

        registry.defineDataTableType(new DataTableType(Comment.class, new TableEntryTransformer<Comment>() {
            @Override
            public Comment transform(Map<String, String> entry) {
                return Comment.createComment(entry);
            }
        }));
    }

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }
}
