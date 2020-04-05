package org.meeting.demo.service.impl;

import org.meeting.demo.dao.EventhandleMapper;
import org.meeting.demo.model.Eventhandle;
import org.meeting.demo.service.EventhandleService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/30.
*/
@Service
@Transactional
public class EventhandleServiceImpl extends AbstractService<Eventhandle> implements EventhandleService {
@Resource
private EventhandleMapper eventhandleMapper;

}