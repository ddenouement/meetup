export interface Filter {
  id: number;
  id_user : number;
  /**
   * name given by user.
   */
  name : string;
  id_language : number;
  rate_from : number;
  rate_to: number;
  time_from : Date;
  time_to : Date;
  topic_id : number;
  topic_name: string;

  title_substring: string;

}
