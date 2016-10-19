package com.dreamdream.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamdream.dao.DreamGreaterDao;
import com.dreamdream.dao.DreamGreaterViewDao;
import com.dreamdream.dao.DreamMessageViewDao;
import com.dreamdream.dao.DreamReplyViewDao;
import com.dreamdream.model.DreamGreater;
import com.dreamdream.model.DreamMessage;
import com.dreamdream.page.view.DreamPageView;
import com.dreamdream.util.ConstString;
import com.dreamdream.view.DreamGreaterView;
import com.dreamdream.view.DreamMessageView;
import com.dreamdream.view.DreamReplyView;
import com.google.common.collect.Lists;

@Service
public class DreamPageViewServ {
    @Autowired
    private DreamMessageViewDao dmvDao;
    @Autowired
    private DreamReplyViewDao drvDao;
    @Autowired
    private DreamGreaterViewDao dgvDao;
    @Autowired
    private DreamGreaterDao dgDao;
    
    private List<DreamPageView> fixedDreamPageView(List<DreamMessageView> dmvList, int dreamerId) throws Exception {
        List<DreamPageView> res = Lists.newLinkedList();
        for (DreamMessageView dreamMessageView : dmvList) {
            DreamPageView dpv = new DreamPageView();
            dpv.setDreamMessageView(dreamMessageView);
            if(dreamMessageView.getImage_url() != null)
                dpv.setImageList(Lists.newArrayList(dreamMessageView.getImage_url().split(",")));
            else
                dpv.setImageList(Lists.newArrayList());
            //query dream reply view for this message
            DreamReplyView drv = new DreamReplyView();
            drv.setMessageId(dreamMessageView.getMessageId());
            drv.setReplyStats(true);
            drv.setDreamerStats(true);
            drv.setLimit(ConstString.REPLY_HEADER_COUNT);
            drv.setIndex(0);
            drv.orderBy("replyCreateTime");
            drv.setAsc(false);
            List<DreamReplyView> replyList = drvDao.selectMany(drv);
            dpv.setReplyList(replyList);
            //query greater dreamer for this message
            DreamGreaterView dgv = new DreamGreaterView();
            dgv.setMessageId(dreamMessageView.getMessageId());
            dgv.setGreaterStats(true);
            dgv.setDreamerStats(true);
            dgv.orderBy("greaterCreateTime");
            dgv.setAsc(false);
            List<DreamGreaterView> greaterList = dgvDao.selectMany(dgv);
            dpv.setGreaterList(greaterList);
            for (DreamGreaterView dreamGreaterView : greaterList) {
                if(dreamGreaterView.getDreamerId().intValue() == (dreamerId)) {
                    dpv.setGreated(true);
                }
            }
            res.add(dpv);
        }
        return res;        
    }
    
    public List<DreamPageView> getDreamPageView(int sortType, int limit, int index, int dreamerId) throws Exception {
        //query dream message view(s)
        DreamMessageView dmv = new DreamMessageView();
        dmv.setMessageStats(true);
        dmv.setDreamerStats(true);
        dmv.setType(DreamMessage.DreamMessageType.PUBLIC.name());
        List<DreamMessageView> dmvList = dmvDao.selectMany(dmv, false, limit, index, "messageCreateTime");
        
        return fixedDreamPageView(dmvList, dreamerId);
    }
    
    public List<DreamPageView> getSomeonesDreamPageView(int dreamerId, int limit, int index, int loginDreamerId) throws Exception {
        //query dream message view(s)
        DreamMessageView dmv = new DreamMessageView();
        dmv.setMessageStats(true);
        dmv.setDreamerStats(true);
        dmv.setType(DreamMessage.DreamMessageType.PUBLIC.name());
        dmv.setDreamerId(dreamerId);
        List<DreamMessageView> dmvList = dmvDao.selectMany(dmv, false, limit, index, "messageCreateTime");
        
        return fixedDreamPageView(dmvList, loginDreamerId);
    }
    
    public List<DreamPageView> getSomeonesSomeTypeDreamPageView(String type, int dreamerId, int limit, int index, int loginDreamerId) throws Exception {
        //query dream message view(s)
        DreamMessageView dmv = new DreamMessageView();
        dmv.setMessageStats(true);
        dmv.setDreamerStats(true);
        dmv.setDreamerId(dreamerId);
        dmv.setType(type);
        List<DreamMessageView> dmvList = dmvDao.selectMany(dmv, false, limit, index, "messageCreateTime");
        
        return fixedDreamPageView(dmvList, loginDreamerId);
    }
    
    public List<DreamPageView> getSomeonesLikeDreamPageView(int dreamerId, int limit, int index, int loginDreamerId) throws Exception {
        DreamGreater dg = new DreamGreater();
        dg.setDreamerId(dreamerId);
        dg.setStats(true);
        dg.setLimit(limit);
        dg.setIndex(index);
        dg.setAsc(false);
        dg.orderBy("createTime");
        List<DreamGreater> dgList = dgDao.selectMany(dg);
        List<Integer> messageIdList = Lists.newLinkedList();
        for (DreamGreater dreamGreater : dgList) {
            messageIdList.add(dreamGreater.getMessageId());
        }
        //query dream message view(s)
        DreamMessageView dmv = new DreamMessageView();
        dmv.in("messageId", messageIdList.toArray());
        dmv.setMessageStats(true);
        dmv.setDreamerStats(true);
        dmv.setType(DreamMessage.DreamMessageType.PUBLIC.name());
        List<DreamMessageView> dmvList = dmvDao.selectMany(dmv, false, limit, index, "messageCreateTime");
        
        return fixedDreamPageView(dmvList, loginDreamerId);
    }
}
