export class Complaint{
    id:number;
    id_user_from:number;
  /**.
   * Id of user on whom is complaining
   */
    id_user_to:number;
  content:string;
   postedDate:number;
  /**.
   *  if admin has read this complaint
   */
  postedDateInNumFormat: Date;
    isRead:boolean;
}
