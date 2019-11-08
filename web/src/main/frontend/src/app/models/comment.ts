import DateTimeFormat = Intl.DateTimeFormat;

export class Comment {
  constructor(


  public id:number,
  public authorID:number,
  public articleID:number,

  public   contents: string,
  public timePosted : Date,
  public timePostedNumeric: number,
  ){}
}
