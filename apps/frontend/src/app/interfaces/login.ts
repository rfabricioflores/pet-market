import { AuthenticatedUser } from './auth';

interface LoginForm {
  username: string;
  password: string;
}

interface LoginResponse {
  token: string;
  expDate: string;
  user: AuthenticatedUser;
}

export { LoginForm, LoginResponse };
