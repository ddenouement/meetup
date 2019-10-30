export class CommentDto {
  constructor(

 public  id:number,
 public id_author:number,
 public id_article:number,
 public login:string,
 public contents: string,
 public  time_posted: number)
{
  };
}
