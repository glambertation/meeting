package org.meeting.demo.service.impl;

import org.meeting.demo.dao.AskerinfoMapper;
import org.meeting.demo.model.Askerinfo;
import org.meeting.demo.service.AskerinfoService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/30.
*/
@Service
@Transactional
public class AskerinfoServiceImpl extends AbstractService<Askerinfo> implements AskerinfoService {
@Resource
private AskerinfoMapper askerinfoMapper;

}