import {UserModel} from "./user.model";

export class RequestModel {
  requestId: bigint;
  title: string;
  info: string;
  active: boolean;
  userModel: UserModel;
  create_At: Date;
  update_At: Date;

  constructor(requestId: bigint, title: string, info: string, active: boolean, userModel: UserModel, create_At: Date, update_At: Date) {
    this.requestId = requestId;
    this.title = title;
    this.info = info;
    this.active = active;
    this.userModel = userModel;
    this.create_At = create_At;
    this.update_At = update_At;
  }
}
