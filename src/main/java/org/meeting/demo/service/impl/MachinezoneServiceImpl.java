package org.meeting.demo.service.impl;

import org.meeting.demo.dao.MachinezoneMapper;
import org.meeting.demo.model.Machinezone;
import org.meeting.demo.service.MachinezoneService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/26.
*/
@Service
@Transactional
public class MachinezoneServiceImpl extends AbstractService<Machinezone> implements MachinezoneService {
@Resource
private MachinezoneMapper machinezoneMapper;

}