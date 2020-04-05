package org.meeting.demo.dao;

import org.meeting.demo.core.Mapper;
import org.meeting.demo.model.AppUser;

public interface AppUserMapper extends Mapper<AppUser> {
    AppUser loadUserByUsername(String username);
}