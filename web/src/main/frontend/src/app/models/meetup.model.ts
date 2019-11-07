import DateTimeFormat = Intl.DateTimeFormat;
import {User} from "./user";
import {Language} from "./language";
import {TopicClass} from "./topic_class";
import {Observable} from "rxjs";
import {LanguagesList} from "./languagesList";
import {Topic} from "./topic";
import {SimpleUserDto} from "./simpleUserDto.model";

export interface Meetup{
  id : number;
  speakerId: number;
  languageId: number;
  topicId: number;
  state: string;
  title : string;
  startDate: Date;
  durationMinutes: number;
  minAttendees : number;
  maxAttendees : number;
  description : string;
  // topics : Topic[];
}
