package org.meeting.demo.service.impl;

import org.meeting.demo.dao.SendtextMapper;
import org.meeting.demo.model.Sendtext;
import org.meeting.demo.service.SendtextService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/05/12.
*/
@Service
@Transactional
public class SendtextServiceImpl extends AbstractService<Sendtext> implements SendtextService {
@Resource
private SendtextMapper sendtextMapper;

}