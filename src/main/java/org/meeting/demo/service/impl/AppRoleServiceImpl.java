package org.meeting.demo.service.impl;

import org.meeting.demo.core.AbstractService;
import org.meeting.demo.dao.AppRoleMapper;
import org.meeting.demo.model.AppRole;
import org.meeting.demo.service.AppRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/28.
*/
@Service
@Transactional
public class AppRoleServiceImpl extends AbstractService<AppRole> implements AppRoleService {
@Resource
private AppRoleMapper appRoleMapper;

}