package com.meetup;


//@Service
//public class DBtestService {
//    @Autowired
//    private IAdminDAO admRepository;
//    @Autowired
//    private IListenerDAO listenersRepository;
//    @Autowired
//    private ISpeakerDAO speakersRepository;
//
//    public  DBtestService(){
//
//    }
//    public String testInsert(){
//
//        Speaker a0 = new Speaker ();
//
//        a0.setPassword("123");
//        a0.setEmail("ssss@gmail.com");
//        a0.setNativeLanguage("english");
//        a0.setName("Eugenie");
//        a0.setSurname("Pslsl");
//        a0.setLogin("g");
//
//        Listener a1 = new Listener ();
//        a1.setPassword("kddkdk");
//        a1.setEmail("y@gmail.com");
//        a1.setLogin("q");
//
//        Listener a2 = new Listener();
//        a2.setPassword("gh");
//        a2.setEmail("t@gmail.com");
//        a2.setLogin("w");
//
//        Listener a3 = new Listener ();
//        a3.setPassword("38282");
//        a3.setEmail("r@gmail.com");
//        a3.setLogin("e");
//// speakersRepository.insertSpeaker(a0);
////listenersRepository.insertListener(a1);
//  //         listenersRepository.insertListener(a2);
//        // listenersRepository.insertListener(a3);
//        int aa= listenersRepository.getAllListeners().size()+speakersRepository.getAllSpeakers().size();
//        //int a9= admRepository.getAllAdmins().size();
//        return "\n All number of ppl:"+aa;
//
//    }
//}
