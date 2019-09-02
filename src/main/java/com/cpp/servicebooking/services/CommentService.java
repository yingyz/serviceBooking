package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.CommentRequest.CommentRequest;
import com.cpp.servicebooking.exceptions.Exception.CommentNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.DuplicateCommentException;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
import com.cpp.servicebooking.models.Comment;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.repository.CommentRepo;
import com.cpp.servicebooking.repository.RequestOrderRepo;
import com.cpp.servicebooking.repository.UserRepo;
import com.cpp.servicebooking.util.CommentComparator;
import com.cpp.servicebooking.util.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestOrderService requestOrderService;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    private CommentComparator commentComparator;

    public Comment saveComment(String RequestOrderId, CommentRequest commentRequest, String name) {
        RequestOrder requestOrder = requestOrderService.findById(RequestOrderId);
        User user = userRepo.findByUsername(name);
        Comment tmpComment = commentRepo.findCommentByRequestOrderAndUser(requestOrder, user);
        if(tmpComment != null) {
            throw new DuplicateCommentException("You have already comment this Request!");
        }
        Comment comment = new Comment();
        comment.setDetail(commentRequest.getDetail());

        comment.setRequestOrder(requestOrder);
        comment.setUser(user);

        commentRepo.save(comment);
        return comment;
    }


    public List<Comment> getCommentsByRequestId(String RequestOrderId, String name) {
        RequestOrder requestOrder = requestOrderService.findById(RequestOrderId);
        User user = userRepo.findByUsername(name);
        int zip = user.getUserInfo().getZipcode();

        if (!requestOrder.getUser().equals(user)) {
            throw new RequestOrderNotFoundException("RequestOrder is not yours!");
        }

        List<Comment> ans = sortedList(zip, requestOrder.getComments());
        return ans;
    }

    private List<Comment> sortedList(int zip, List<Comment> ans) {
        for (Comment comment : ans) {
            double dis = distanceCalculator.getDistance(zip, comment.getUser().getUserInfo().getZipcode());
            comment.getUser().getUserInfo().setDistance(dis);
        }
        Collections.sort(ans, commentComparator);
        return  ans;
    }

    public Comment findById(String CommentId, String name) {
        long id = Long.parseLong(CommentId);
        Comment comment = commentRepo.findById(id);
        if (comment == null) {
            throw new CommentNotFoundException("Comment not found!");
        }

        RequestOrder requestOrder = comment.getRequestOrder();

        if (!comment.getUser().getUsername().equals(name) || !!comment.getUser().getUsername().equals(requestOrder.getUser().getUsername())) {
            throw new CommentNotFoundException("You cannot view this comment");
        }
        return comment;
    }

    public void deleteComment(String CommentId, String name) {
        Comment comment = findById(CommentId, name);
        if (!comment.getUser().getUsername().equals(name)) {
            throw new CommentNotFoundException("Comment is not yours!");
        }
        commentRepo.delete(comment);
    }

}
