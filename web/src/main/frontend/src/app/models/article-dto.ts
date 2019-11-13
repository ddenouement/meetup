import {Topic} from "./topic";
import {SimpleUserDto} from "./simpleUserDto.model";

export interface ArticleDTO {
  id: number;
  title: string;
  content: string;
  topics: Topic[];
  dateTimePosted: string;
  author: SimpleUserDto;

}
