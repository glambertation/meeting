package org.meeting.demo.service.impl;

import org.meeting.demo.core.AbstractService;
import org.meeting.demo.dao.MachinenumberMapper;
import org.meeting.demo.model.Machinenumber;
import org.meeting.demo.service.MachinenumberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/26.
*/
@Service
@Transactional
public class MachinenumberServiceImpl extends AbstractService<Machinenumber> implements MachinenumberService {
@Resource
private MachinenumberMapper machinenumberMapper;

}