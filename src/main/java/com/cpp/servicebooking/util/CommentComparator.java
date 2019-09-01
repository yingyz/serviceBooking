package com.cpp.servicebooking.util;


import com.cpp.servicebooking.models.Comment;

import java.util.Comparator;

public class CommentComparator implements Comparator<Comment> {
    @Override
    public int compare(Comment o1, Comment o2) {
        return (int)Math.ceil(o1.getUser().getUserInfo().getDistance() - o2.getUser().getUserInfo().getDistance());
    }
}
