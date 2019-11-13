import {LanguagesList} from "./languagesList";
import {Topic} from "./topic";
import {SimpleUserDto} from "./simpleUserDto.model";

export interface MeetupDto{
  id : number;
  // speaker: User;
  speaker: SimpleUserDto;
  language: LanguagesList;
  state: string;
  title : string;
  startDate: Date;
  durationMinutes: number;
  minAttendees : number;
  maxAttendees : number;
  description : string;
  // topics : Topic[];
  topic : Topic;
}
