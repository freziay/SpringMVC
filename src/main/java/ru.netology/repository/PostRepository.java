package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private final AtomicLong atomicLong = new AtomicLong(0);

    private final Map<Long, Post> posts = new HashMap<>();

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long id = atomicLong.addAndGet(1);
            post.setId(id);
            posts.put(id, post);
            return posts.get(id);
        } else if (posts.get(post.getId()) == null) {
            throw new NotFoundException();
        } else {
            posts.replace(post.getId(), post);
            return posts.get(post.getId());
        }
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
