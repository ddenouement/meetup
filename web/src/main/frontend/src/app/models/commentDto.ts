import DateTimeFormat = Intl.DateTimeFormat;
import moment from "moment";

export class CommentDto {
  constructor(

 public  id:number,
 public   authorID: number,
 public  authorLogin: string,
 public articleID:number,
 public contents: string,
 public timePosted: Date,
 public  timePostedNumeric: number )
{
  };
   get timePostedAsDateTime(): Date {
     var re = this.timePosted.toString().substr(0, this.timePosted.toString().indexOf('.'));
     alert(re);

   //  return moment(this.timePosted, 'YYYY-MM-DD').toDate();
     return new Date(re);
    }
}
