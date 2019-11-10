import {UserModel} from "./user.model";

export class RequestModel {
  requestId: bigint;
  servicetype: string;
  info: string;
  active: boolean;
  userDto: UserModel;
  create_At: Date;
  update_At: Date;

  constructor(requestId: bigint, title: string, info: string, active: boolean, userModel: UserModel, create_At: Date, update_At: Date) {
    this.requestId = requestId;
    this.servicetype = title;
    this.info = info;
    this.active = active;
    this.userDto = userModel;
    this.create_At = create_At;
    this.update_At = update_At;
  }
}
