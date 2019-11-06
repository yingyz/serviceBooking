import {UserModel} from "./user.model";

export class CommentModel {
  commentId: bigint;
  commentDetail: string;
  title: string;
  info: string;
  active: boolean;
  requestUser: UserModel;
  userModel: UserModel;

  constructor(commentId: bigint, commentDetail: string, title: string, info: string, active: boolean, requestUser: UserModel, userModel: UserModel) {
    this.commentId = commentId;
    this.commentDetail = commentDetail;
    this.title = title;
    this.info = info;
    this.active = active;
    this.requestUser = requestUser;
    this.userModel = userModel;
  }
}
