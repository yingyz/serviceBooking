import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from "../comment.service";
import {CommentModel} from "../comment.model";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  private _requestId: number;

  get requestId(): number {
    return this._requestId;
  }

  @Input()
  set requestId(requestId: number) {
    console.log('got name: ', requestId);
    this._requestId = requestId;

    this.commentService.getComments(requestId)
      .subscribe(
      transformedComment => {
        this.comments = transformedComment;
      });
  }

  comments: CommentModel[] = [];

  constructor(private commentService:CommentService) {

  }

  ngOnInit() {
  }

}
