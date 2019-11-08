export class UserModel {
  userId: number;
  username: string;
  firstname: string;
  lastname: string;
  streetname: string;
  city: string;
  state: string;
  zipcode: number;
  phone: string;
  language: string;
  role: string;

  constructor(userId: number, userName: string, firstName: string, lastName: string, streetName: string, city: string, state: string, zipCode: number, phone: string, language: string, role: string) {
    this.userId = userId;
    this.username = userName;
    this.city = city;
    this.firstname = firstName;
    this.lastname = lastName;
    this.streetname = streetName;
    this.state = state;
    this.zipcode = zipCode;
    this.phone = phone;
    this.language = language;
    this.role = role;
  }
}
