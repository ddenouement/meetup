# nc-autumn-2019

Rest api:

MEETUP CONTROLLER
____________________________________
Get all meetups
Type: GET
api/v1/meetups

Get meetup
Type: GET
/api/v1/meetups/{id}

Get all topics
Type: GET
api/v1/meetups/topics

Get meetups of speaker
Type: GET
/api/v1/meetups/speaker/{id}
____________________________________


USER CONTROLLER
____________________________________
Get user profile
Type: GET
/api/v1/user/profile

Get other user profile
Type: GET
RequestParam: String login
/api/v1/user/people/profile

Join meetup
Type: POST
/api/v1/user/meetups/{id}

Leave meetup
Type: DELETE
/api/v1/user/meetups/{id}
____________________________________


SPEAKER CONTROLLER
____________________________________
Create meetup (speaker only)
Type: POST
RequestBody: Meetup meetup
/api/v1/user/speaker/meetups

Update meetup (speaker only)
Type: PUT
RequestBody: Meetup editedMeetup
/api/v1/user/speaker/meetups/{id}

Cancel meetup (speaker only)
Type: DELETE
/api/v1/user/speaker/meetups/{id}

Create article (speaker only)
Type: POST
RequestBody: Article article
api/v1/user/speaker/articles

Remove article (speaker only)
Type: DELETE
api/v1/user/speaker/articles/{id}
____________________________________

