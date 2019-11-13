import DateTimeFormat = Intl.DateTimeFormat;

export interface Notification {
  id: number;
  message: string;
  idUser: number;
  read: boolean;
  type: string;
  timeCreated: DateTimeFormat;
}
