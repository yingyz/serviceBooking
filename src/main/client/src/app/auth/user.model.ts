export class UserModel {
  public userId: number;
  public userName: string;
  public firstname: string;
  public lastname: string;
  public streetname: string;
  public city: string;
  public state: string;
  public zipcode: string;
  public phone: string;
  public fullName: string;
  public userRole: string;

  constructor(userId: number, userName: string, firstname: string,
              lastname: string, streetname: string, city: string,
              state: string, zipcode: string, phone: string,
              fullName: string, userRole: string) {
    this.userId = userId;
    this.userName = userName;
    this.firstname = firstname;
    this.lastname = lastname;
    this.streetname = streetname;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.phone = phone;
    this.fullName = fullName;
    this.userRole = userRole;
  }
}
