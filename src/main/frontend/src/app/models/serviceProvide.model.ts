import {UserModel} from "./user.model";

export class ServiceProvideModel {
  serviceId: number;
  detail: string;
  price: string;
  servicetype: string;
  userDto: UserModel;

  constructor(serviceId: number, detail: string, price: string, serviceType: string, userModel: UserModel) {
    this.serviceId = serviceId;
    this.detail = detail;
    this.price = price;
    this.servicetype = serviceType;
    this.userDto = userModel;
  }
}
