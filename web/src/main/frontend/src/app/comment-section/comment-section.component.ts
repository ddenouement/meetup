import {
  Component,
  Input,
  OnChanges, OnDestroy,
  OnInit, SimpleChange, SimpleChanges,
  TemplateRef,
  ViewChild
} from '@angular/core';
import {Commentsectionservice} from "./commentsectionservice";
import {CommentDto} from "../models/commentDto";
import {ActivatedRoute, Router} from '@angular/router';
import {Comment} from '../models/comment'
import {MatSnackBar} from '@angular/material/snack-bar';
import {UserService} from "../services/user.service";
import {ISubscription} from "rxjs-compat/Subscription";

@Component({
  selector: 'app-comment-section',
  templateUrl: './comment-section.component.html',
  styleUrls: ['./comment-section.component.scss'],
  providers: [Commentsectionservice]
})
export class CommentSectionComponent implements OnChanges, OnInit, OnDestroy {
  articleId: number;
  authorId: number;
  isAdmin = false;
  adminLogin = "admin";
  currentUserLogin: string;
  private subscription: ISubscription;
  @Input() article_author_id1: number;
  @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any>;

  comments: Array<CommentDto>;
  statusMessage: string;
  dataRefresher: any;
  isAddDisabledButton: boolean;
  text: string;
  showEmojiPicker: boolean;


  constructor(private userService: UserService, private snackBar: MatSnackBar, private serv: Commentsectionservice, private route: ActivatedRoute, private router: Router) {
    this.comments = new Array<CommentDto>();
  }

  ngOnInit() {
    this.isAddDisabledButton = false;
    this.getThisUserLogin();
    this.getThisUserId();
    this.subscription = this.route.params.subscribe(params => {

      this.articleId = +params['articleId'];//from this route
      this.userService.getUserLogin().subscribe(res => {
        if (this.adminLogin === res) {
          this.isAdmin = true;
        }
      });
      this.loadComments();
      this.refreshData();
    });
  }

  public openPopup: Function;

  setPopupAction(fn: any) {
    this.openPopup = fn;
  }

  getThisUserId() {
    this.serv.getUserId()
      .subscribe(
        data => {
          this.authorId = Number(data);
        },
        err => {
          this.snackBar.open(err.error);
        });
  }

  getThisUserLogin() {
    this.serv.getUserLogin()
      .subscribe(
        data => {
          this.currentUserLogin = data;
        },
        err => {
          this.snackBar.open(err.error);
        });
  }

  toggleEmojiPicker() {
    this.showEmojiPicker = !this.showEmojiPicker;
  }

  addEmoji(event) {
    if (this.text) {
      const txt = `${this.text}${event.emoji.native}`;
      this.text = txt;
    } else {
      const txt = `${event.emoji.native}`;
      this.text = txt;
    }
    this.showEmojiPicker = false;
  }

  private refreshData() {
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
          this.snackBar.open(err.error);
        });
  }

  //charset : 'utf8mb4'
  addComment() {
    this.isAddDisabledButton = true;
    const date = new Date();

    const editedComment = new Comment(0, this.authorId, this.articleId, this.text, date, 0);
    this.serv.createComment(this.articleId, editedComment).subscribe(data => {
        //   const savedComment = new CommentDto(0, this.authorId, this.currentUserLogin, this.articleId, this.text,date);
        this.statusMessage = '';
        this.comments.unshift(data);
        this.text = "";
        this.snackBar.open('Posted a comment');
        this.isAddDisabledButton = false;
      },
      err => {
        this.snackBar.open(err.error);
        this.isAddDisabledButton = false;
      });

  }

  loadTemplate(c: CommentDto) {

    return this.readOnlyTemplate;
  }


  ngOnChanges(changes: SimpleChanges): void {

    const a: SimpleChange = changes.article_author_id1;
    this.article_author_id1 = a.currentValue;
  }

  redirectToProfile(id: number) {
    if (this.authorId != id) {
      this.serv.getUserRole(id).subscribe(data => {
        if (data == 'LISTENER') this.router.navigate(['/listener-profile', id]);
        else if (data == 'SPEAKER') this.router.navigate(['/speaker-profile', id]);
      });
    }
  }

  deleteMyComment(comment: CommentDto) {
    this.serv.deleteComment(comment.id).subscribe(data => {
        this.comments.splice(this.comments.indexOf(comment), 1);
        this.snackBar.open('Deleted a comment');
      },
      error => {
        this.snackBar.open('Error!');
      }
    )
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
