
package org.meeting.demo.service;

import org.meeting.demo.core.Service;
import org.meeting.demo.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
* Created by CodeGenerator on 2020/03/28.
*/
public interface AppUserService extends Service<AppUser>, UserDetailsService {


    public boolean updatePasswd(String oldpass, String pass, Integer id);

}