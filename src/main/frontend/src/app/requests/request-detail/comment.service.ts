import {Injectable} from "@angular/core";
import {environment} from "../../../environments/environment";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CommentModel} from "../../models/comment.model";
import {Subject} from "rxjs";
import {map} from "rxjs/operators";

const BACKEND_URL = environment.apiUrl + '/comment/';

@Injectable({providedIn: 'root'})
export class CommentService {
  private comments = [];
  private commentsChanged = new Subject<CommentModel[]>();

  constructor(private http: HttpClient, private router: Router){}

  getComments() {
    return this.comments;
  }

  getCommentUpdateListener() {
    return this.commentsChanged.asObservable();
  }

  addComment(detail: string, requestId: number) {
    return this.http.post(BACKEND_URL + 'post/' + requestId, {detail: detail});
  }

  getCommentsFromAPI(requestId: number) {
    this.http.get(BACKEND_URL + 'get/' + requestId)
      .pipe(
        map(
          (comments: any[]) => {
            return comments.map(
              comment => {
                return new CommentModel(
                  comment.commentId,
                  comment.commentDetail,
                  comment.title,
                  comment.info,
                  comment.active,
                  comment.requestUser,
                  comment.userdto
                );
              }
            );
          }
        )
      )
      .subscribe(
        transformedComments => {
          this.comments = transformedComments;
          this.commentsChanged.next([...this.comments]);
        }
      );
  }

  checkDuplicateComment(requestId: number) {
    return this.http.get(BACKEND_URL + 'check/' + requestId);
  }
}
