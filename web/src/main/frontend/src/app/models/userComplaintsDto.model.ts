export interface UserComplaintsDto {
  id: number;
  login : string;
  email : string;
  firstName: string;
  lastName : string;
  active : boolean;
  complaintsCount : number;
}
