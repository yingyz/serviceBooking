export class UserInfoModel {
  city: string;
  firstname: string;
  lastname: string;
  streetname: string;
  state: string;
  zipcode: string;
  phone: string;
  language: string;

  constructor(city: string,
              firstname: string,
              lastname: string,
              streetname: string,
              state: string,
              zipcode: string,
              phone: string,
              language: string) {
      this.city = city;
      this.firstname = firstname;
      this.lastname = lastname;
      this.streetname = streetname;
      this.state = state;
      this.zipcode = zipcode;
      this.phone = phone;
      this.language = language;
  }
}
