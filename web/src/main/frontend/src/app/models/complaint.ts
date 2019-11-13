export class Complaint{
    id:number;
    idUserFrom:number;
  /**.
   * Id of user on whom is complaining
   */
    idUserTo:number;
  content:string;
   postedDate:number;
  /**.
   *  if admin has read this complaint
   */
  postedDateInNumFormat: Date;
    isRead:boolean;
}
