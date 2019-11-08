import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {CommentModel} from "../../../models/comment.model";
import {CommentService} from "../comment.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit, OnDestroy {
  private _requestId: number;

  get requestId(): number {
    return this._requestId;
  }

  @Input()
  set requestId(requestId: number) {
    this._requestId = requestId;

    this.commentService.getCommentsFromAPI(requestId);
  }

  comments: CommentModel[] = [];
  private commentsListenerSubs: Subscription;
  constructor(private commentService:CommentService) { }

  ngOnInit() {
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
