import {UserModel} from "./user.model";

export class CommentModel {
  commentId: bigint;
  commentDetail: string;
  servicetype: string;
  info: string;
  active: boolean;
  requestUser: UserModel;
  userdto: UserModel;

  constructor(commentId: bigint, commentDetail: string, title: string, info: string, active: boolean, requestUser: UserModel, userModel: UserModel) {
    this.commentId = commentId;
    this.commentDetail = commentDetail;
    this.servicetype = title;
    this.info = info;
    this.active = active;
    this.requestUser = requestUser;
    this.userdto = userModel;
  }
}
