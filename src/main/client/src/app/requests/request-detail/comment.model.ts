import {UserModel} from "../../auth/user.model";
import {RequestModel} from "../request.model";

export class CommentModel {
  public commentId: string;
  public commentDetail: string;
  public provider: UserModel;
  public requestOrder: RequestModel;

  constructor(commentId: string, commentDetail: string, provider: UserModel, requestOrder: RequestModel) {
    this.commentId = commentId;
    this.commentDetail = commentDetail;
    this.provider = provider;
    this.requestOrder = requestOrder;
  }
}
