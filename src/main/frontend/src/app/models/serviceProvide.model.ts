import {UserModel} from "./user.model";

export class ServiceProvideModel {
  serviceId: bigint;
  detail: string;
  price: string;
  serviceType: string;
  userModel: UserModel;

  constructor(serviceId: bigint, detail: string, price: string, serviceType: string, userModel: UserModel) {
    this.serviceId = serviceId;
    this.detail = detail;
    this.price = price;
    this.serviceType = serviceType;
    this.userModel = userModel;
  }
}
