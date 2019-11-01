import DateTimeFormat = Intl.DateTimeFormat;
import {User} from "./user";
import {Topic} from "./topic";

export interface Meetup{
  id : number;
  speaker: User;
  state: string;
  title : string;
  startDate: DateTimeFormat;
  durationMinutes: number;
  minAttendees : number;
  maxAttendees : number;
  description : string;
  topics : Topic[];
}
