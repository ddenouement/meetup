

export class CommentDto {
  constructor(

 public  id:number,
 public   authorID: number,
 public  authorLogin: string,
 public articleID:number,
 public contents: string,
 public timePosted: Date  )
{
  };
}
