import {UserModel} from "./user.model";

export class ServiceProvideModel {
  serviceId: number;
  detail: string;
  price: string;
  serviceType: string;
  userModel: UserModel;

  constructor(serviceId: number, detail: string, price: string, serviceType: string, userModel: UserModel) {
    this.serviceId = serviceId;
    this.detail = detail;
    this.price = price;
    this.serviceType = serviceType;
    this.userModel = userModel;
  }
}
