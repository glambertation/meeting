package org.meeting.demo.service.impl;

import org.meeting.demo.dao.AskqueueMapper;
import org.meeting.demo.model.Askqueue;
import org.meeting.demo.service.AskqueueService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/29.
*/
@Service
@Transactional
public class AskqueueServiceImpl extends AbstractService<Askqueue> implements AskqueueService {
@Resource
private AskqueueMapper askqueueMapper;

}