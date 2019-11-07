import {Topic} from "./topic";

export interface Filter {
  id : number;
  id_user: number;
  /**.
   * name given by user
   */
  name : string;
  id_language : string;
  rate_from : number;
  rate_to: number;
  time_from : Date;
  time_to : Date;
  topics_ids : number[];
  //used to transfer data to frontend in user-friendly way (with topic names)
  //this List is filled only on retrieve from DB
  topics : Topic[];
  title_substring : string;
}
