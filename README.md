# nc-autumn-2019

## Rest api:

### MEETUP CONTROLLER
____________________________________
##### Get all meetups
- Type: GET
- api/v1/meetups

##### Get meetup
- Type: GET
- /api/v1/meetups/{id}

##### Get all topics
- Type: GET
- api/v1/meetups/topics

##### Get meetups of speaker
- Type: GET
- /api/v1/meetups/speaker/{id}


### USER CONTROLLER
____________________________________
##### Get user profile
- Type: GET
- /api/v1/user/profile

##### Get other user profile
- Type: GET
- RequestParam: String login
- /api/v1/user/people/profile

##### Join meetup
- Type: POST
- /api/v1/user/meetups/{id}

##### Leave meetup
- Type: DELETE
- /api/v1/user/meetups/{id}

#### Get all active speakers
- Type: GET
- /api/v1/user/speakers

#### Get all active listeners
- Type: GET
- /api/v1/user/users


### SPEAKER CONTROLLER
____________________________________
##### Create meetup (speaker only)
- Type: POST
- RequestBody: Meetup meetup
- /api/v1/user/speaker/meetups

##### Update meetup (speaker only)
- Type: PUT
- RequestBody: Meetup editedMeetup
- /api/v1/user/speaker/meetups/{id}

##### Cancel meetup (speaker only)
- Type: DELETE
- /api/v1/user/speaker/meetups/{id}

##### Start meetup (speaker only)
- Type: POST
- /api/v1/user/speaker/meetups/{id}/start

##### Terminate meetup (speaker only)
- Type: POST
- /api/v1/user/speaker/meetups/{id}/terminate

##### Create article (speaker only)
- Type: POST
- RequestBody: Article article
- /api/v1/user/speaker/articles

##### Remove article (speaker only)
- Type: DELETE
- /api/v1/user/speaker/articles/{id}


### BADGE CONTROLLER
____________________________________
##### Get all badges (admin only)
- Type: GET
- /api/v1/badges

##### Get badge by id (admin only)
- Type: GET
- /api/v1/badge/{id}

##### Create badge (admin only)
- Type: POST
- RequestBody: Badge badge
- /api/v1/badge

##### Update badge (admin only)
- Type: PUT
- RequestBody: Badge badge
- /api/v1/badge/{id}

##### Delete badge (admin only)
- Type: DELETE
- /api/v1/badge/{id}

##### Get badges for user with id
- Type: GET
- /api/v1/user/{id}/badges

##### Check badge script (admin only)
- Type: POST
- RequestBody: String script
- /api/v1/badge/check




