package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> postMap;
    private final AtomicLong idCounter;

    public PostRepository() {
        postMap = new ConcurrentHashMap<>();
        idCounter = new AtomicLong(0);
    }

    public List<Post> all() {
        return Collections.unmodifiableList(List.copyOf(postMap.values()));
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postMap.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = idCounter.incrementAndGet();
            post.setId(newId);
            postMap.put(newId, post);
        } else {
            postMap.put(post.getId(), post);
        }
        return post;
    }

    public boolean removeById(long id) {
        if (postMap.remove(id) == null) {
            return true;
        }
        return false;
    }
}