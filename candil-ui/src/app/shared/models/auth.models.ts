export interface AuthRegisterDto {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
}

export interface AuthLoginDto {
  username: string;
  password: string;
}

export interface AuthResponseDto {
  jwt: string;
}