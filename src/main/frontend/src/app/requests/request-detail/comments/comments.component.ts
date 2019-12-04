import {
  AfterContentChecked,
  Component,
  DoCheck,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  SimpleChanges
} from '@angular/core';
import {CommentModel} from "../../../models/comment.model";
import {CommentService} from "../comment.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit, OnDestroy, OnChanges {
  //private _requestId: number;
  /*
  get requestId(): number {
    return this._requestId;
  }

  @Input()
  set requestId(requestId: number) {
    this._requestId = requestId;
    //this.commentService.getCommentsFromAPI(requestId);
  }
*/
  @Input() private requestId: number;
  comments: CommentModel[] = [];
  private commentsListenerSubs: Subscription;
  constructor(private commentService:CommentService) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.commentService.getCommentsFromAPI(this.requestId);
  }

  ngOnInit() {
    this.comments = this.commentService.getComments();
    this.commentsListenerSubs = this.commentService.getCommentUpdateListener()
      .subscribe(
        comments => {
          this.comments = comments;
        }
      );
  }

  ngOnDestroy(): void {
    this.commentsListenerSubs.unsubscribe();
  }

}
