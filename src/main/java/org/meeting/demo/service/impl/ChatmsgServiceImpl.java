package org.meeting.demo.service.impl;

import org.meeting.demo.dao.ChatmsgMapper;
import org.meeting.demo.model.Chatmsg;
import org.meeting.demo.service.ChatmsgService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/05/02.
*/
@Service
@Transactional
public class ChatmsgServiceImpl extends AbstractService<Chatmsg> implements ChatmsgService {
@Resource
private ChatmsgMapper chatmsgMapper;

}