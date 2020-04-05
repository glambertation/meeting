
package org.meeting.demo.service;
import org.meeting.demo.model.AppUser;
import org.meeting.demo.core.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
* Created by CodeGenerator on 2020/03/28.
*/
public interface AppUserService extends Service<AppUser>, UserDetailsService {


    public boolean updatePasswd(String oldpass, String pass, Integer id);

}