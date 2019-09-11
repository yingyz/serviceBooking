import {UserModel} from "../auth/user.model";

export class RequestModel {
  public requestId: number;
  public requestTitle: string;
  public info: string;
  public active: boolean;
  public create_At: Date;
  public User: UserModel;

  constructor(requestId: number, requestTitle: string, info: string, active: boolean, create_At: Date, User: UserModel) {
    this.requestId = requestId;
    this.requestTitle = requestTitle;
    this.info = info;
    this.active = active;
    this.create_At = create_At;
    this.User = User;
  }
}
