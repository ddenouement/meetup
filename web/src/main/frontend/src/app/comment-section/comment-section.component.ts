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
import {PickerModule} from '@ctrl/ngx-emoji-mart';


@Component({
  selector: 'app-comment-section',
  templateUrl: './comment-section.component.html',
  styleUrls: ['./comment-section.component.scss'],
  providers: [Commentsectionservice]
})
export class CommentSectionComponent implements OnChanges, OnInit{
  articleId:number;
  authorId: number;
  authorLogin: string;
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
    this.loadComments();
    this.authorLogin="ksddddd";
     this.refreshData();
      this.route.params.subscribe(params => {

      this.articleId = +params['id'];//from this route

    });

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
    alert(this.articleId);
    this.comments.unshift(new CommentDto(0,this.authorId, this.articleId, this.authorLogin, this.text,  +new Date()));

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
