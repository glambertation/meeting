package org.meeting.demo.service.impl;

import org.meeting.demo.core.AbstractService;
import org.meeting.demo.dao.EventMapper;
import org.meeting.demo.model.Event;
import org.meeting.demo.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/04/30.
*/
@Service
@Transactional
public class EventServiceImpl extends AbstractService<Event> implements EventService {
@Resource
private EventMapper eventMapper;

}