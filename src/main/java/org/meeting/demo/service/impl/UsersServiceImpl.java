package org.meeting.demo.service.impl;

import org.meeting.demo.dao.UsersMapper;
import org.meeting.demo.model.Users;
import org.meeting.demo.service.UsersService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/03/14.
*/
@Service
@Transactional
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
@Resource
private UsersMapper usersMapper;

}