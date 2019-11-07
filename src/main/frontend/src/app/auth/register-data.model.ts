export class RegisterDataModel {
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  streetname: string;
  city: string;
  state: string;
  zipcode: string;
  phone: string;
  language: string;
  role: string;

  constructor(userName: string,
              password: string,
              firstName: string,
              lastName: string,
              streetName: string,
              city: string,
              state: string,
              zipCode: string,
              phone: string,
              language: string,
              role: string) {
    this.username = userName;
    this.password = password;
    this.firstname = firstName;
    this.lastname = lastName;
    this.streetname = streetName;
    this.city = city;
    this.state = state;
    this.zipcode = zipCode;
    this.phone = phone;
    this.language = language;
    this.role = role;
  }
}
