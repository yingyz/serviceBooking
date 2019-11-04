package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.CommentRequest.CommentRequest;
import com.cpp.servicebooking.exceptions.Exception.CommentNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.DuplicateCommentException;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
import com.cpp.servicebooking.models.Comment;
import com.cpp.servicebooking.models.RequestOrder;
import com.cpp.servicebooking.models.User;
import com.cpp.servicebooking.models.UserInfo;
import com.cpp.servicebooking.models.dto.CommentDto;
import com.cpp.servicebooking.models.dto.UserDto;
import com.cpp.servicebooking.repository.CommentRepo;
import com.cpp.servicebooking.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private ModelMapper modelMapper;

    public CommentDto saveComment(String RequestOrderId, CommentRequest commentRequest, String name) {
        RequestOrder requestOrder = requestOrderService.findRequestById(RequestOrderId);
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

        return transferCommentDto(comment);
    }

    public List<CommentDto> getCommentsByRequestId(String RequestOrderId, String name) {
        RequestOrder requestOrder = requestOrderService.findRequestById(RequestOrderId);
        User user = userRepo.findByUsername(name);

        if (!requestOrder.getUser().equals(user)) {
            throw new RequestOrderNotFoundException("RequestOrder is not yours!");
        }

        List<Comment> ans = requestOrder.getComments();

        return transferCommentDtos(ans);
    }

    private Comment findCommentById(String CommentId, String name) {
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

    public CommentDto findById(String CommentId, String name) {
        Comment comment = findCommentById(CommentId, name);
        return transferCommentDto(comment);
    }

    public void deleteComment(String CommentId, String name) {
        Comment comment = findCommentById(CommentId, name);
        if (!comment.getUser().getUsername().equals(name)) {
            throw new CommentNotFoundException("Comment is not yours!");
        }
        commentRepo.delete(comment);
    }

    private List<CommentDto> transferCommentDtos(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(transferCommentDto(comment));
        }
        return  commentDtos;
    }

    private CommentDto transferCommentDto(Comment comment) {
        User user = comment.getUser();
        User requestUser = comment.getRequestOrder().getUser();

        CommentDto commentDto = CommentDto.builder()
                .active(comment.getRequestOrder().getActive())
                .commentDetail(comment.getDetail())
                .title(comment.getRequestOrder().getTitle())
                .info(comment.getRequestOrder().getInfo())
                .requestUser(transferUserDto(requestUser))
                .userdto(transferUserDto(user))
                .build();
        return commentDto;
    }

    private UserDto transferUserDto(User user) {
        UserInfo userInfo = user.getUserInfo();

        UserDto userDto = modelMapper.map(userInfo, UserDto.class);
        userDto.setUsername(user.getUsername());

        return userDto;
    }
}
