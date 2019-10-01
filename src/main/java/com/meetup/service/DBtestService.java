package com.meetup.service;

import com.meetup.entities.Listener;
import com.meetup.entities.Speaker;
import com.meetup.repository.IListenerDAO;
import com.meetup.repository.ISpeakerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DBtestService {
    @Autowired
    private IListenerDAO listenersRepository;
    @Autowired
    private ISpeakerDAO speakersRepository;

    public  DBtestService(){

    }
    public String testInsert(){

        Speaker a0 = new Speaker ();
        a0.setPassword("123");
        a0.setEmail("ernu@gmail.com");
        a0.setNativeLanguage("english");
        a0.setName("Eugenie");
        a0.setSurname("Pslsl");
        a0.setLogin("eduEugen");

        Listener a1 = new Listener ();
        a1.setPassword("kddkdk");
        a1.setEmail("slff@gmail.com");
        a1.setLogin("mrBob");

        Listener a2 = new Listener();
        a2.setPassword("dk2k");
        a2.setEmail("bbbb@gmail.com");
        a2.setLogin("OnDaTrA");

        Listener a3 = new Listener ();
        a3.setPassword("38282");
        a3.setEmail("ldldl@gmail.com");
        a3.setLogin("pnmd");
//speakersRepository.insertSpeaker(a0);
        //       listenersRepository.insertListener(a1);
        //      listenersRepository.insertListener(a2);
        // listenersRepository.insertListener(a3);
        int aa= listenersRepository.getAllListeners().size()+speakersRepository.getAllSpeakers().size();
        return "\n All number of ppl:"+aa;

    }
}
