import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../../environments/environment";
import {map} from "rxjs/operators";
import {CommentModel} from "./comment.model";
import {UserModel} from "../../auth/user.model";
import {RequestModel} from "../request.model";
import {Subject} from "rxjs";

const BACKEND_URL = environment.apiUrl + '/comment/';

@Injectable({providedIn: 'root'})
export class CommentService {

  private comments = [];
  private commentsChanged = new Subject<CommentModel[]>();

  constructor(private http: HttpClient, private router: Router){}

  addComment(detail: string, requestId: number) {
    this.http.post(BACKEND_URL + 'post/' + requestId, {detail: detail})
      .subscribe(
        (comment:any) => {
          console.log("success");
        }
      );
  }

  getComments(requestId: number) {
    return this.http.get(BACKEND_URL + 'get/' + requestId)
      .pipe(
        map((comments: any[]) => {
          return comments.map(
            comment => {
              return new CommentModel(
                comment.id,
                comment.detail,
                new UserModel(
                  comment.user.id,
                  comment.user.username,
                  comment.user.userInfo.firstname,
                  comment.user.userInfo.lastname,
                  comment.user.userInfo.streetname,
                  comment.user.userInfo.city,
                  comment.user.userInfo.state,
                  comment.user.userInfo.zipcode,
                  comment.user.userInfo.phone,
                  comment.user.fullName,
                  comment.user.role.name
                ),
                new RequestModel(
                  comment.requestOrder.id,
                  comment.requestOrder.title,
                  comment.requestOrder.info,
                  comment.requestOrder.active,
                  comment.requestOrder.create_At,
                  new UserModel(
                    comment.requestOrder.user.id,
                    comment.requestOrder.user.username,
                    comment.requestOrder.user.userInfo.firstname,
                    comment.requestOrder.user.userInfo.lastname,
                    comment.requestOrder.user.userInfo.streetname,
                    comment.requestOrder.user.userInfo.city,
                    comment.requestOrder.user.userInfo.state,
                    comment.requestOrder.user.userInfo.zipcode,
                    comment.requestOrder.user.userInfo.phone,
                    comment.requestOrder.user.fullName,
                    comment.requestOrder.user.role.name
                  )
                )
              );
            }
          );
        })
      );
  }

  getCommentUpdateListener() {return this.commentsChanged.asObservable();}
}
