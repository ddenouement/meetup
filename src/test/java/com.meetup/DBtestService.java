package com.meetup;

import com.meetup.entities.Listener;
import com.meetup.entities.Speaker;
import com.meetup.entities.User;
import com.meetup.repository.IAdminDAO;
import com.meetup.repository.IListenerDAO;
import com.meetup.repository.ISpeakerDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DBtestService {

    @Autowired
    private IUserDAO usRepository;
    @Autowired
    private AuthenticationService su;

    public  DBtestService(){

    }
    public String testInsert(){

        User a0 = new User();

        a0.setPassword("123");
        a0.setEmail("ssss@gmail.com");
        a0.setLogin("g");
        List<String> a = new ArrayList<>();
        a.add("LISTENER");
        a.add("SPEAKER");
        a0.setRoles(a);
 usRepository.insertNewUser(a0);
        int aa= su.loadUserByUsername(a0.getLogin()).getAuthorities().size();
        //int a9= admRepository.getAllAdmins().size();
        return "\n All number of suthorities:"+aa;

    }
}
