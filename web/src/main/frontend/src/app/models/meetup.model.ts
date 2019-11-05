import DateTimeFormat = Intl.DateTimeFormat;
import {User} from "./user";
import {Language} from "./language";
import {TopicClass} from "./topic_class";
import {Observable} from "rxjs";
import {LanguagesList} from "./languagesList";
import {Topic} from "./topic";

export interface Meetup{
  id : number;
  speaker: User;
  language: LanguagesList;
  state: string;
  title : string;
  startDate: DateTimeFormat;
  durationMinutes: number;
  minAttendees : number;
  maxAttendees : number;
  description : string;
  topics : Topic[];
}
