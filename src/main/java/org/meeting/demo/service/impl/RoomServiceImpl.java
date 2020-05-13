package org.meeting.demo.service.impl;

import org.meeting.demo.dao.RoomMapper;
import org.meeting.demo.model.Room;
import org.meeting.demo.service.RoomService;
import org.meeting.demo.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/05/13.
*/
@Service
@Transactional
public class RoomServiceImpl extends AbstractService<Room> implements RoomService {
@Resource
private RoomMapper roomMapper;

}