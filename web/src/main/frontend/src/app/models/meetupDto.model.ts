import {User} from "./user";import {Observable} from "rxjs";
import {LanguagesList} from "./languagesList";
import {Topic} from "./topic";

export interface MeetupDto{
  id : number;
  speaker: User;
  language: LanguagesList;
  state: string;
  title : string;
  startDate: Date;
  durationMinutes: number;
  minAttendees : number;
  maxAttendees : number;
  description : string;
  topics : Topic[];
}
