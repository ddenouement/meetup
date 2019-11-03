# nc-autumn-2019

## Rest api:

### MEETUP CONTROLLER
____________________________________
##### Get all meetups
- Type: GET
- /api/v1/meetups

##### Get meetup
- Type: GET
- /api/v1/meetups/{id}

##### Get all topics
- Type: GET
- /api/v1/meetups/topics

##### Get meetups of speaker
- Type: GET
- /api/v1/meetups/speakers/{id}


### USER CONTROLLER
____________________________________
##### Get user profile
- Type: GET
- /api/v1/user/profile

##### Update user profile
- Type: PUT
- /api/v1/user/profile

##### Get all speakers
- Type: GET
- RequestParam: String login
- /api/v1/user/speakers

##### Get all users
- Type: GET
- RequestParam: String login
- /api/v1/user/users

##### Get other user profile
- Type: GET
- RequestParam: String login
- /api/v1/user/people/profile/{id}

##### Join meetup
- Type: POST
- /api/v1/user/meetups/{id}

##### Leave meetup
- Type: DELETE
- /api/v1/user/meetups/{id}

##### Deactivate user (admin only)
- Type: POST
- /api/v1/user/users/{id}/deactivate

##### Complain on user
- Type: POST
- RequestBody: ComplaintDTO
- /api/v1/user/complaint

##### Get all complaints (admin only)
- Type: GET
- /api/v1/user/complaints

##### Mark complaint as read (admin only)
- Type: POST
- /api/v1/user/complaints/{id}/read

##### Subscribe to user
- Type: POST
- /api/v1/user/speakers/{id}/subscribe

##### Unsubscribe from user
- Type: DELETE
- /api/v1/user/speakers/{id}/subscribe

##### Get subscribers of user
- Type: GET
- /api/v1/user/speakers/{id}/subscribers

##### Get article
- Type: GET
- /api/v1/user/articles/{id}

##### Get all articles
- Type: GET
- /api/v1/user/articles

#### Change user's password
- Type: PUT
- RequestBody: String password
- /api/v1/user/password

##### Rate meetup
- Type: POST
- RequestBody: Feedback
- /api/v1/rate/meetups/{id}

#### Get user's ID
- Type: GET
- /api/v1/user/id

#### Get all unread notifications
- Type: GET
- /api/v1/user/notifications

#### Get number of unread notifications
- Type: GET
- /api/v1/user/notifications/count

#### Mark notification as read
- Type: PUT
- /api/v1/user/notifications/{id}/read



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
- /api/v1/badges/{id}

##### Create badge (admin only)
- Type: POST
- RequestBody: Badge badge
- /api/v1/badges

##### Update badge (admin only)
- Type: PUT
- RequestBody: Badge badge
- /api/v1/badges/{id}

##### Delete badge (admin only)
- Type: DELETE
- /api/v1/badges/{id}

##### Get badges for user with id
- Type: GET
- /api/v1/users/{id}/badges

##### Check badge script (admin only)
- Type: POST
- RequestBody: String script
- /api/v1/badge/check

### DICTIONARY CONTROLLER
____________________________________
##### Get all languages
- Type: GET
- /api/v1/languages

##### Create new language
- Type: POST
- RequestBody: Language
- /api/v1/languages

##### Delete language
- Type: DELETE
- /api/v1/languages/{id}

##### Edit language
- Type: PUT
- RequestBody: Language
- /api/v1/languages

