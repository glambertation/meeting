package org.meeting.demo.service.impl;

import org.meeting.demo.dao.AppMenuMapper;
import org.meeting.demo.model.AppMenu;
import org.meeting.demo.service.AppMenuService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/28.
*/
@Service
@Transactional
public class AppMenuServiceImpl extends AbstractService<AppMenu> implements AppMenuService {
@Resource
private AppMenuMapper appMenuMapper;

}