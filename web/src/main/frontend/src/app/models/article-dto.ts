import {Topic} from "./topic";
import {User} from "./user";

export interface ArticleDTO {
  title: string;
  content: string;
  topics: Topic[];
  dateTimePosted: string;
  author: User;

}
