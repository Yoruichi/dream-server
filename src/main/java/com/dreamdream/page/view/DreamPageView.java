package com.dreamdream.page.view;

import java.util.List;

import com.dreamdream.view.DreamGreaterView;
import com.dreamdream.view.DreamMessageView;
import com.dreamdream.view.DreamReplyView;

public class DreamPageView {
    private DreamMessageView dreamMessageView;
    private List<DreamGreaterView> greaterList;
    private List<DreamReplyView> replyList;

    public DreamMessageView getDreamMessageView() {
        return dreamMessageView;
    }

    public void setDreamMessageView(DreamMessageView dreamMessageView) {
        this.dreamMessageView = dreamMessageView;
    }

    public List<DreamReplyView> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<DreamReplyView> replyList) {
        this.replyList = replyList;
    }

    public List<DreamGreaterView> getGreaterList() {
        return greaterList;
    }

    public void setGreaterList(List<DreamGreaterView> greaterList) {
        this.greaterList = greaterList;
    }

    @Override
    public String toString() {
        return "DreamPageView [dreamMessageView=" + dreamMessageView + ", greaterList="
                + greaterList + ", replyList=" + replyList + "]";
    }

}
