import {
  Component,
  Input,
  OnChanges,
  OnInit, SimpleChange, SimpleChanges,
  TemplateRef,
  ViewChild
} from '@angular/core';
import {Commentsectionservice} from "./commentsectionservice";
import {CommentDto} from "../models/commentDto";
import {ActivatedRoute, Router} from '@angular/router';
import {Comment} from '../models/comment'

@Component({
  selector: 'app-comment-section',
  templateUrl: './comment-section.component.html',
  styleUrls: ['./comment-section.component.scss'],
  providers: [Commentsectionservice]
})
export class CommentSectionComponent implements OnChanges, OnInit{
  articleId:number;
  authorId: number;
  currentUserLogin: string;
  @Input ( ) article_author_id1:number;
  @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any>;

   comments: Array<CommentDto>;
  statusMessage: string;
  dataRefresher: any;
  text: string;
showEmojiPicker:boolean;


  constructor(private serv: Commentsectionservice, private route: ActivatedRoute, private router: Router) {
    this.comments = new Array<CommentDto>();
  }

  ngOnInit() {

    this.getThisUserLogin();
      this.route.params.subscribe(params => {

      this.articleId = +params['articleId'];//from this route
        this.loadComments();
        this.refreshData();
    });

  }
  public openPopup: Function;

  setPopupAction(fn: any) {
    this.openPopup = fn;
  }
getThisUserLogin(){
  this.serv.getUserLogin()
    .subscribe(
      data => {
        this.currentUserLogin = data;
      },
      err => {
        console.log(err.error);
      });
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
 this.serv.getComments(this.articleId)
      .subscribe(
        comments => {
          this.comments = comments;
        },
        err => {
          console.log(err.error);
        });
  }
  //charset : 'utf8mb4'
  addComment() {
const date =  new Date();

    const editedComment = new Comment( 0,this.authorId, this.articleId, this.text, date,0);
    this.serv.createComment( this.articleId, editedComment).subscribe(data => {
         const savedComment = new CommentDto(0, this.authorId, this.currentUserLogin, this.articleId, this.text,date,0);
        this.statusMessage  = 'Added';
          this.comments.unshift(savedComment);

      },
      err => {
        this.statusMessage = err.error;
      });

  }
  loadTemplate (c: CommentDto) {

      return this.readOnlyTemplate;
    }


  ngOnChanges(changes: SimpleChanges): void {

    const a: SimpleChange = changes.article_author_id1;
    this.article_author_id1 = a.currentValue;
  }

  redirectToProfile(id: number) {
    this.serv.getUserRole(id).subscribe(data => {
      if(data=='LISTENER') this.router.navigate(['/listener-profile',id]);
      else
        if(data=='SPEAKER') this.router.navigate(['/speaker-profile', id]);
    });
  }
}
