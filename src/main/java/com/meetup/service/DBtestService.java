package com.meetup.service;

import com.meetup.entities.Listener;
import com.meetup.entities.Speaker;
import com.meetup.repository.IAdminDAO;
import com.meetup.repository.IListenerDAO;
import com.meetup.repository.ISpeakerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DBtestService {
    @Autowired
    private IAdminDAO admRepository;
    @Autowired
    private IListenerDAO listenersRepository;
    @Autowired
    private ISpeakerDAO speakersRepository;

    public  DBtestService(){

    }
    public String testInsert(){

        Speaker a0 = new Speaker ();
        a0.setPassword("123");
        a0.setEmail("ppp@gmail.com");
        a0.setNativeLanguage("english");
        a0.setName("Eugenie");
        a0.setSurname("Pslsl");
        a0.setLogin("ppPp");

        Listener a1 = new Listener ();
        a1.setPassword("kddkdk");
        a1.setEmail("qw@gmail.com");
        a1.setLogin("qw");

        Listener a2 = new Listener();
        a2.setPassword("gh");
        a2.setEmail("gh@gmail.com");
        a2.setLogin("gh");

        Listener a3 = new Listener ();
        a3.setPassword("38282");
        a3.setEmail("ldldl@gmail.com");
        a3.setLogin("pnmd");
 speakersRepository.insertSpeaker(a0);
           listenersRepository.insertListener(a1);
           listenersRepository.insertListener(a2);
        // listenersRepository.insertListener(a3);
        int aa= listenersRepository.getAllListeners().size()+speakersRepository.getAllSpeakers().size();
        //int a9= admRepository.getAllAdmins().size();
        return "\n All number of ppl:"+aa;

    }
}
