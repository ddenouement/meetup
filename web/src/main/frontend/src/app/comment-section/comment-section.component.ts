import {Component, TemplateRef, ViewChild} from '@angular/core';
import {Commentsectionservice} from "./commentsectionservice";
import {CommentDto} from "../models/commentDto";
import {PickerModule} from '@ctrl/ngx-emoji-mart';


@Component({
  selector: 'app-comment-section',
  templateUrl: './comment-section.component.html',
  styleUrls: ['./comment-section.component.scss'],
  providers: [Commentsectionservice]
})
export class CommentSectionComponent {
  articleId:number;
  authorId: number;
  article_author_id:number;
  authorLogin: string;
  //@Input ( ) articleId:number;
  @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any>;

   comments: Array<CommentDto>;
  statusMessage: string;
  dataRefresher: any;
  text: string;
showEmojiPicker:boolean;


  constructor(private serv: Commentsectionservice) {
    this.comments = new Array<CommentDto>();
  }

  ngOnInit() {
    this.loadComments();
    this.authorLogin="ksddddd";
    //this.refreshData();
    this.articleId=2;
    this.article_author_id=4;
  }
  public openPopup: Function;

  setPopupAction(fn: any) {
    this.openPopup = fn;
  }

  toggleEmojiPicker(){
    this.showEmojiPicker=!this.showEmojiPicker;
  }
  addEmoji(event){
    if(this.text) {
      const   txt = `${this.text}${event.emoji.native}`;
      this.text = txt;
    }
    else {
      const   txt = `${event.emoji.native}`;
      this.text = txt;
    }
    this.showEmojiPicker=false;
  }
  private refreshData(){
    this.dataRefresher =
      setInterval(() => {
        this.loadComments();
      }, 30000);
  }
  private loadComments() {
    this.comments = [];
    this.comments.push(new CommentDto(1,1, this.articleId, "katerine", "good article!",  +new Date()));
    this.comments.push(new CommentDto(2,2, this.articleId, "ti-reks", "bad badbad bad d badbad bad bad badbadbadv bad badbadbadbad d badbad bad bad badbadbadv bad badbadbadbad bad badbad bad bad badbadbadv bad badbadbadbad article!",  +new Date()));
    this.comments.push(new CommentDto(3,3, this.articleId, "helow", "ii really like the article!!",  +new Date()));
    this.comments.push(new CommentDto(4,4, this.articleId, "gs", "its my article;) thanks",  +new Date()));
    /*
    this.serv.getComments()
      .subscribe(
        coms => {
          this.comments = coms;
        },
        err => {
          console.log(err);
        });*/
  }
  //charset : 'utf8mb4'
  addComment() {
    this.comments.unshift(new CommentDto(0,this.authorId, this.articleId, this.authorLogin, this.text,  +new Date()));
    alert(this.text.toString());
    alert(+new Date());
  /*   const editedComment = new Comment(0,this.authorId, this.articleId, this.text,  +new Date());
    this.serv.createComment(editedComment).subscribe(data => {

        this.statusMessage  = 'Added',
          this.comments.unshift(data['comment']);

      },
      err => {
        this.statusMessage = err.error;
      });
*/
  }
  loadTemplate (c: CommentDto) {

      return this.readOnlyTemplate;
    }

}
