# nc-autumn-2019

## Rest api:

### ADMIN CONTROLLER
____________________________________
##### Deactivate user (admin only)
- Type: POST
- /api/v1/user/users/{id}/deactivate

##### Activate user (admin only)
- Type: POST
- /api/v1/users/{id}/activate

##### Get all complaints (admin only)
- Type: GET
- /api/v1/user/complaints

##### Mark complaint as read (admin only)
- Type: POST
- /api/v1/user/complaints/{id}/read

### ARTICLE CONTROLLER
____________________________________
##### Create article (speaker only)
- Type: POST
- RequestBody: Article article
- /api/v1/user/speaker/articles

##### Remove article (speaker only)
- Type: DELETE
- /api/v1/user/speaker/articles/{id}

##### Remove article (admin only)
- Type: DELETE
- /api/v1/user/articles/{id}

##### Get all articles
- Type: GET
- /api/v1/user/articles

##### Get article
- Type: GET
- /api/v1/user/articles/{id}

##### Get all comments of article
- Type: GET
- /api/v1/user/articles/{id}/comments

##### Post comment on article
- Type: POST
- RequestBody: Commentary commentary
- /api/v1/user/articles/{id}/comments

##### Remove comment on article
- Type: DELETE
- /api/v1/user/articles/comments/{id}

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

### TOPIC CONTROLLER
____________________________________
##### Get all topics
- Type: GET
- /api/v1/meetups/topics

##### Get topic
- Type: GET
- /api/v1/meetups/topics/{id}

##### Create topic
- Type: POST
- RequestBody: Topic
- /api/v1/meetups/topics

##### Update topic
- Type: PUT
- RequestBody: Topic
- /api/v1/meetups/topics/{id}

##### Delete topic
- Type: DELETE
- /api/v1/meetups/topics/{id}


### MEETUP CONTROLLER
____________________________________
##### Get all meetups
- Type: GET
- /api/v1/meetups

##### Get meetup
- Type: GET
- /api/v1/meetups/{id}

##### Get meetups of speaker
- Type: GET
- /api/v1/meetups/speakers/{id}

##### Join meetup
- Type: POST
- /api/v1/user/meetups/{id}

##### Leave meetup
- Type: DELETE
- /api/v1/user/meetups/{id}

##### Rate meetup
- Type: POST
- RequestBody: Feedback
- /api/v1/rate/meetups/{id}

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

### PROFILE CONTROLLER
____________________________________
##### Get user profile
- Type: GET
- /api/v1/user/profile

##### Update user profile
- Type: PUT
- /api/v1/user/profile

##### Get other user profile
- Type: GET
- RequestParam: String login
- /api/v1/user/people/profile/{id}

### NOTIFICATION CONTROLLER
____________________________________
#### Get all unread notifications
- Type: GET
- /api/v1/user/notifications

#### Get number of unread notifications
- Type: GET
- /api/v1/user/notifications/count

#### Mark notification as read
- Type: PUT
- /api/v1/user/notifications/{id}/read

### USER CONTROLLER
____________________________________
##### Get all speakers
- Type: GET
- RequestParam: String login
- /api/v1/user/speakers

##### Get all active users
- Type: GET
- RequestParam: String login
- /api/v1/user/users

##### Get all users
- Type: GET
- /api/v1/user/users/all

##### Complain on user
- Type: POST
- RequestBody: ComplaintDTO
- /api/v1/user/complaint

##### Subscribe to user
- Type: POST
- /api/v1/user/speakers/{id}/subscribe

##### Unsubscribe from user
- Type: DELETE
- /api/v1/user/speakers/{id}/subscribe

##### Get subscribers of user
- Type: GET
- /api/v1/user/speakers/{id}/subscribers

#### Change user's password
- Type: PUT
- RequestBody: String password
- /api/v1/user/password

#### Get user's ID
- Type: GET
- /api/v1/user/id

