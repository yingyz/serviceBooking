package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.Comment;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    Comment findCommentByRequestOrderAndUser(RequestOrder requestOrder, User user);
    Comment findById(long id);
}
