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
